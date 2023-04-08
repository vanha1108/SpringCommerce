package com.example.SpringCommerce.service;

import com.example.SpringCommerce.product.Product;

import java.util.List;

public interface IProductService {

    List<Product> searchProduct(String category, String brand, String color, Double priceFrom, Double priceTo);

    Product getProductById(Integer id);
}
