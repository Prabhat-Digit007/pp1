package com.example.Projec1.repo;

import com.example.Projec1.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWishListItemRepository extends JpaRepository<WishlistItem, Long> {
    Optional<WishlistItem> findByWishlistAndBook(Wishlist cart, Product book);
    Optional<Object> findByUser(User user);
}
