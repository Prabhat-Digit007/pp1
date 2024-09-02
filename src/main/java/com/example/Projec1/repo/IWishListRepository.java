package com.example.Projec1.repo;

import com.example.Projec1.entity.Cart;
import com.example.Projec1.entity.User;
import com.example.Projec1.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWishListRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUserId(Long userId);

    Optional<Wishlist> findByUser(User user);
}
