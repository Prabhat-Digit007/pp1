package com.example.Projec1.repo;

import com.example.Projec1.entity.Cart;
import com.example.Projec1.entity.CartItem;
import com.example.Projec1.entity.Product;
import com.example.Projec1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndBook(Cart cart, Product book);
    Optional<Object> findByUser(User user);
}