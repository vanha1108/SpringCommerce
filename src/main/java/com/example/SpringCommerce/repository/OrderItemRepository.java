package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.product.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
