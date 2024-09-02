package com.example.Projec1.services;

import com.example.Projec1.dao.FeedbackALLResponse;
import com.example.Projec1.dao.FeedbackResponse;
import com.example.Projec1.entity.Feedback;
import com.example.Projec1.entity.Product;
import com.example.Projec1.entity.User;
import com.example.Projec1.repo.IFeedbackRepository;
import com.example.Projec1.repo.IProductRepository;
import com.example.Projec1.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackSevices{

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Override
    public FeedbackResponse addFeedback(Long id, Feedback body, Long productId) {
        // Fetch the product
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            return new FeedbackResponse(HttpStatus.BAD_REQUEST, null, "Product not found.");
        }
        Product product = productOptional.get();

        // Fetch the user associated with the order
        User user = userRepository.findById(id).get();

        // Create and save feedback
        Feedback feedback = new Feedback();
        feedback.setRating(body.getRating());
        feedback.setComment(body.getComment());
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setUser(user);
        feedback.setProduct(product);

        feedback = feedbackRepository.save(feedback);

        // Add feedback to product and order
        product.getFeedbacks().add(feedback);

        productRepository.save(product);

        return new FeedbackResponse(HttpStatus.OK, feedback, "Feedback added successfully.");
    }

    @Override
    public FeedbackALLResponse showFeedbackById(Long Bid) {
        Optional<Product> byId = productRepository.findById(Bid);
        Product product = byId.get();
        List<Feedback> byProduct = feedbackRepository.findByProduct(product);
        return new FeedbackALLResponse(HttpStatus.ACCEPTED,byProduct,"All feedback of bookID " + Bid);
    }

}
