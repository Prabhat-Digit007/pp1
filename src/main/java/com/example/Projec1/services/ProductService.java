package com.example.Projec1.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Projec1.dao.ProductResponse;
import com.example.Projec1.entity.Product;
import com.example.Projec1.repo.IProductRepository;
import com.example.Projec1.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    IProductRepository orderRepository;

    public List<Product> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Product> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public ProductResponse showOrderById(Long id) {
        Optional<Product> orderOptional = orderRepository.findById(id);
        if((orderOptional).isEmpty()){
            return new ProductResponse(HttpStatus.BAD_REQUEST,null,"Book id is not present");
        }
        Optional<Product> byId = orderRepository.findById(id);
        Product product = byId.get();
        return new ProductResponse(HttpStatus.OK,product,"Found book by id");
    }

//    public Product createOrder(Product order) {
//        try{
//            order.setImage(ImageUtil.convertImageToByteArray("src/main/java/com/example/Projec1/util/Screenshot (1).png"));
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return orderRepository.save(order);
//    }

    public Product createOrder(Product order) {
//        try {
//            byte[] imageBytes = ImageUtil.convertImageToByteArray("src/main/java/com/example/Projec1/util/Screenshot (1).png");
//            order.setImage(imageBytes);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        return orderRepository.save(order);
    }


    public ProductResponse deleteOrder(Long id) {
        Optional<Product> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Product order = orderOptional.get();
            orderRepository.delete(order);
            return new ProductResponse(HttpStatus.OK,order,"Item deleted successfully");
        }
        return new ProductResponse(HttpStatus.BAD_REQUEST,null,"Item not deleted book id is not present");
    }

    @Override
    public String getRoleFromToken(String token) {
        // Use the same secret key that was used to sign the JWT
        String secretKeyString = "My_Secret_Key"; // Replace with your actual secret key
        Algorithm algorithm = Algorithm.HMAC256(secretKeyString);

        // Create JWTVerifier instance using the same algorithm and issuer as in token generation
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Baeldung")
                .build();

        // Decode and verify the JWT token
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));

        // Extract the "role" claim from the decoded token
        String role = decodedJWT.getClaim("role").asString();

        return role; // Return the role from the token
    }

    @Override
    public Product getProductById(Long productId) {
        return null;
    }

    //    public Product updateOrder(Long id, Product orderDetails) {
//        Product order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
////        order.setProduct(orderDetails.Order());
////        order.setQuantity(orderDetails.getQuantity());
//        return orderRepository.save(order);
//    }
//
//    public void deleteOrder(Long id) {
//        Product order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
//        orderRepository.delete(order);
//    }
    public Product updateOrder(Long id, Product orderDetails) {
        if(orderRepository.findById(id).isEmpty()){
            return null;
        }
        Optional<Product> orderOptional = orderRepository.findById(id);
        System.out.println(orderOptional);
        if (orderOptional.isPresent()) {
            Product order = orderOptional.get();
            order.setPrice(orderDetails.getPrice());
            order.setStock(orderDetails.getStock());
            return orderRepository.save(order);
        }
        return null; // or handle the case where the order is not found
    }
}