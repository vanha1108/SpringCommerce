package com.example.SpringCommerce.service;

import com.example.SpringCommerce.product.*;
import com.example.SpringCommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderItemService orderItemService;


    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }

    @Override
    public Cart delete(Cart cart) {
        cartRepository.delete(cart);
        return cart;
    }

    @Override
    public Cart addToCart(Long cartId, Integer productId, Integer quantity) {
        Cart cart = new Cart();
        cart.setId(cartId);
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        }
        Product product = productService.getProductById(productId);
        if (cart.getTotalPrice() != null) {
            cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * quantity));
        } else {
            cart.setTotalPrice(product.getPrice() * quantity);
        }
        CartItem cartItem = cartItemService.getCartItem(cartId, productId);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setPro(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
        }
        cart = cartRepository.save(cart);
        cartItemService.save(cartItem);
        return cart;
    }

    @Override
    public Orders checkout(Long cartId) {
        Cart cart = this.getCartById(cartId);
        List<CartItem> cartItems = cartItemService.getByCartId(cart.getId());
        if (CollectionUtils.isEmpty(cartItems)) {
            throw new IllegalArgumentException("Cart must be contained item!");
        }
        Double totalPrice = 0.0;
        Orders orders = new Orders();
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPro(cartItem.getPro());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(orders);
            orderItems.add(orderItem);
            totalPrice += cartItem.getPro().getPrice() * cartItem.getQuantity();
        }
        orders.setTotalPrice(totalPrice);
        orders = orderService.save(orders);
        orderItemService.saveAll(orderItems);
        cartItemService.deleteAll(cartItems);
        this.delete(cart);
        return orders;
    }
}

