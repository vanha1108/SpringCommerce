package com.example.SpringCommerce.Controller;

import com.example.SpringCommerce.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping("/{cartId}/product/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable Long cartId,
                                       @PathVariable Integer productId,
                                       @RequestParam Integer quantity) {
        try {
            return new ResponseEntity<>(cartService.addToCart(cartId, productId, quantity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{cartId}/checkout")
    public ResponseEntity<?> checkout(@PathVariable Long cartId) {
        try {
            return new ResponseEntity<>(cartService.checkout(cartId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

