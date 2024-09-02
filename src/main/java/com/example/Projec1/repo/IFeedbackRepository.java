package com.example.Projec1.repo;

import com.example.Projec1.entity.Feedback;
import com.example.Projec1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByProduct(Product product);
}
