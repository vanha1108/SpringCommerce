package com.example.SpringCommerce.service;

import com.example.SpringCommerce.product.Orders;
import com.example.SpringCommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }
}
