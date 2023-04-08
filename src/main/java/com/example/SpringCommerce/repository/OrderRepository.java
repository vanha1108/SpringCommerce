package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.product.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
