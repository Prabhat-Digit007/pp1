package com.example.Projec1.services;

import com.example.Projec1.entity.Wishlist;
import com.example.Projec1.exception.customException.InsufficientStockException;
import com.example.Projec1.exception.customException.ResourceNotFoundException;

public interface IWishServices {
    Wishlist addItemToWishlist(Long userId, Long bookId) throws ResourceNotFoundException;

    Wishlist getWishlistByUserId(Long userId) throws ResourceNotFoundException;

    void removeItemFromWishlist(Long userId, Long bookId) throws ResourceNotFoundException;

    Wishlist increaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException, InsufficientStockException;
    Wishlist decreaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException;
}
