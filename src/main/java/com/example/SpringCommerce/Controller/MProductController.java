package com.example.SpringCommerce.Controller;

import com.example.SpringCommerce.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/products")
public class MProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> searchProduct(@RequestParam(required = false) String category,
                                           @RequestParam(required = false) String brand,
                                           @RequestParam(required = false) String color,
                                           @RequestParam(required = false) Double priceFrom,
                                           @RequestParam(required = false) Double priceTo) {
        return new ResponseEntity<>(productService.searchProduct(category, brand, color, priceFrom, priceTo), HttpStatus.OK);
    }
}
