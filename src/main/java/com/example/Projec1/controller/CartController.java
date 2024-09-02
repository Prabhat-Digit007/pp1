package com.example.Projec1.controller;

import com.example.Projec1.entity.Cart;
import com.example.Projec1.exception.customException.InsufficientStockException;
import com.example.Projec1.exception.customException.ResourceNotFoundException;
import com.example.Projec1.exception.customException.TokenNotValidException;
import com.example.Projec1.services.ICartServices;
import com.example.Projec1.util.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartServices cartService;

    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/add/book/{bookId}")
    public ResponseEntity<Cart> addItemToCart(
            @RequestHeader("Authorization") String token,
            @PathVariable Long bookId) throws TokenNotValidException, ResourceNotFoundException {

        Long userId = AuthMiddleware.getIdFromToken(token);
        Cart cart = cartService.addItemToCart(userId, bookId);
        return ResponseEntity.ok(cart);
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @GetMapping
    public ResponseEntity<?> getCartByUserId(@RequestHeader("Authorization") String token) {
        try {
            Long userId = AuthMiddleware.getIdFromToken(token);
            Cart cart = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(cart);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @DeleteMapping("/delete/book/{bookId}")
    public ResponseEntity<?> removeItemFromCart(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        try {
            Long userId = AuthMiddleware.getIdFromToken(token);
            cartService.removeItemFromCart(userId, bookId);
            return ResponseEntity.ok("Item removed from cart");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Item not present in cart");
        }
    }


    @CrossOrigin(origins = "http://localhost:60517")
    @PutMapping("/increase/quantity/{bookId}")
    public ResponseEntity<?> increaseItemQuantity(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        try {
            Long userId = AuthMiddleware.getIdFromToken(token);
            Cart updatedCart = cartService.increaseItemQuantity(userId, bookId);
            return ResponseEntity.ok(updatedCart);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InsufficientStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @PutMapping("/decrease/quantity/{bookId}")
    public ResponseEntity<?> decreaseItemQuantity(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        try {
            Long userId = AuthMiddleware.getIdFromToken(token);
            Cart updatedCart = cartService.decreaseItemQuantity(userId, bookId);
            return ResponseEntity.ok(updatedCart);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

