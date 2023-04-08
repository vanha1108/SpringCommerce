package com.example.SpringCommerce.repository;


import com.example.SpringCommerce.product.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCart_IdAndPro_Id(Long cartId, Integer productId);

    List<CartItem> findByCart_Id(Long cartId);
}
