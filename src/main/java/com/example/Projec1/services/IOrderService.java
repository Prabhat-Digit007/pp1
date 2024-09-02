package com.example.Projec1.services;

import com.example.Projec1.entity.Order;
import com.example.Projec1.entity.OrderRequest;

public interface IOrderService {
    Order placeOrder(OrderRequest orderRequest, Long id);
}