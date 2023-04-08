package com.example.SpringCommerce.service;

import com.example.SpringCommerce.product.Cart;
import com.example.SpringCommerce.product.Orders;

public interface ICartService {

    Cart getCartById(Long id);

    Cart addToCart(Long cartId, Integer productId, Integer quantity);

    Cart delete(Cart cart);

    Orders checkout(Long cartId);
}
