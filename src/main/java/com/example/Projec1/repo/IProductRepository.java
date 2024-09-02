package com.example.Projec1.repo;

import com.example.Projec1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IProductRepository extends JpaRepository<Product, Long> {
}
