package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByBrandIsNullOrBrandLikeIgnoreCaseAndCategoryIsNullOrCategoryLikeIgnoreCase(String brand, String category);

    List<Product> findByBrandLike(String brand);

    List<Product> findByCategoryLike(String category);

    List<Product> findByCategoryLikeAndBrandLike(String category, String brand);

    List<Product> findByPriceGreaterThanEqual(Double priceFrom);

    List<Product> findByPriceLessThanEqual(Double priceTo);
}
