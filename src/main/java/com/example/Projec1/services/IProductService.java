package com.example.Projec1.services;

import com.example.Projec1.dao.ProductResponse;
import com.example.Projec1.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> getAllOrders();

    Optional<Product> getOrderById(Long id);

    ProductResponse showOrderById(Long id);

    Product createOrder(Product order);

    Product updateOrder(Long id, Product orderDetails);

    ProductResponse deleteOrder(Long id);

    String getRoleFromToken(String token);

    Product getProductById(Long productId);
}
