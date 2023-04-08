package com.example.SpringCommerce.service;

import com.example.SpringCommerce.product.OrderItem;
import com.example.SpringCommerce.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }
}
