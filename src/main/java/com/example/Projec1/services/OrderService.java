package com.example.Projec1.services;

import com.example.Projec1.entity.*;
import com.example.Projec1.repo.ICartRepository;
import com.example.Projec1.repo.IOrderRepository;
import com.example.Projec1.repo.IProductRepository;
import com.example.Projec1.repo.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

//@Service
//
//public class OrderService implements IOrderService {
//    @Autowired
//    private IOrderRepository orderRepository;
//
//    @Autowired
//    private IUserRepository userRepository;
//    @Override
//    public Order placeOrder(OrderRequest orderRequest) {
//        User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
//
//        Order order = new Order();
//        order.setUser(user);
//        order.setOrderDate(new Date());
//        order.setStatus("Pending");
//        order.setConfirmationNumber(UUID.randomUUID().toString());
//        order.setConfirmationDate(new Date());
//
//        double totalAmount = 0;
//        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setBook(itemRequest.getBook());
//            orderItem.setQuantity(itemRequest.getQuantity());
//            orderItem.setPrice(itemRequest.getPrice());
//            totalAmount += itemRequest.getPrice() * itemRequest.getQuantity();
//            order.getItems().add(orderItem);
//        }
//        order.setTotalAmount(totalAmount);
//
//        return orderRepository.save(order);
//    }
//}

@Service
public class OrderService implements IOrderService {

//    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ICartServices cartServices;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Order placeOrder(OrderRequest orderRequest, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

//        creating a new Order, when ever placeOrder API is hit
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setConfirmationNumber(UUID.randomUUID().toString());
        order.setConfirmationDate(new Date());

        double totalAmount = 0;
        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            OrderItem orderItem = new OrderItem();
            Optional<Product> byId = productRepository.findById(itemRequest.getId());
            Product product = byId.get();
            orderItem.setBook(byId.get());
            System.out.println(product);
            System.out.println(byId);
            orderItem.setOrder(order);
            orderItem.setName(product.getName());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());
            totalAmount += product.getPrice() * itemRequest.getQuantity();
            order.getItems().add(orderItem);
            Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            try {
                cartServices.removeItemFromCart(cart.getId(), product.getId());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);
        System.out.println("meow");
        return savedOrder;
    }
}

