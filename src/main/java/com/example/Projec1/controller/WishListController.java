package com.example.Projec1.controller;

import com.example.Projec1.entity.Wishlist;
import com.example.Projec1.exception.customException.InsufficientStockException;
import com.example.Projec1.exception.customException.ResourceNotFoundException;
import com.example.Projec1.exception.customException.TokenNotValidException;
import com.example.Projec1.services.IWishServices;
import com.example.Projec1.util.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/wish")
public class WishListController {

    @Autowired
    private IWishServices wishService;

    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/add/book/{bookId}")
    public ResponseEntity<Wishlist> addItemToWishlist(
            @RequestHeader("Authorization") String token,
            @PathVariable Long bookId) throws TokenNotValidException, ResourceNotFoundException {

        Long userId = AuthMiddleware.getIdFromToken(token);
        Wishlist wish = wishService.addItemToWishlist(userId, bookId);
        return ResponseEntity.ok(wish);
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @GetMapping
    public ResponseEntity<?> getWishlistByUserId(@RequestHeader("Authorization") String token) {
        try {
            Long userId = AuthMiddleware.getIdFromToken(token);
            Wishlist wish = wishService.getWishlistByUserId(userId);
            return ResponseEntity.ok(wish);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @DeleteMapping("/delete/book/{bookId}")
    public ResponseEntity<?> removeItemFromWishlist(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        try {
            Long userId = AuthMiddleware.getIdFromToken(token);
            wishService.removeItemFromWishlist(userId, bookId);
            return ResponseEntity.ok("Item removed from wish");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Item not present in wish");
        }
    }


    @CrossOrigin(origins = "http://localhost:60517")
    @PutMapping("/increase/quantity/{bookId}")
    public ResponseEntity<?> increaseItemQuantity(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        try {
            Long userId = AuthMiddleware.getIdFromToken(token);
            Wishlist updatedWishlist = wishService.increaseItemQuantity(userId, bookId);
            return ResponseEntity.ok(updatedWishlist);
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
            Wishlist updatedWishlist = wishService.decreaseItemQuantity(userId, bookId);
            return ResponseEntity.ok(updatedWishlist);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

