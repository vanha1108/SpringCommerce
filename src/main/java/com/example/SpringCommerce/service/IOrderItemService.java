package com.example.SpringCommerce.service;

import com.example.SpringCommerce.product.OrderItem;

import java.util.List;

public interface IOrderItemService {

    OrderItem save(OrderItem orderItem);

    List<OrderItem> saveAll(List<OrderItem> orderItems);
}
