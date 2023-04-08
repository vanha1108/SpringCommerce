package com.example.SpringCommerce.service;

import com.example.SpringCommerce.product.CartItem;

import java.util.List;

public interface ICartItemService {

    CartItem save(CartItem cartItem);

    CartItem getCartItem(Long cartId, Integer productId);

    List<CartItem> getByCartId(Long cartId);

    List<CartItem> deleteAll(List<CartItem> cartItems);
}
