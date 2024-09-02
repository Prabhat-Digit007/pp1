package com.example.Projec1.services;

import com.example.Projec1.entity.Cart;
import com.example.Projec1.exception.customException.InsufficientStockException;
import com.example.Projec1.exception.customException.ResourceNotFoundException;

public interface ICartServices {
     Cart addItemToCart(Long userId, Long bookId) throws ResourceNotFoundException;

     Cart getCartByUserId(Long userId) throws ResourceNotFoundException;

     void removeItemFromCart(Long userId, Long bookId) throws ResourceNotFoundException;

    Cart increaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException, InsufficientStockException;
    Cart decreaseItemQuantity(Long userId, Long bookId) throws ResourceNotFoundException;
}


