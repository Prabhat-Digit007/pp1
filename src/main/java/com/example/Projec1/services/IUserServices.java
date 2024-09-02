package com.example.Projec1.services;


import com.example.Projec1.dao.OrderResponse;
import com.example.Projec1.dao.UserDetailsResponse;
import com.example.Projec1.dao.UserResponse;
import com.example.Projec1.entity.User;
import com.example.Projec1.entity.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface IUserServices {
    UserResponse register(User body);
    UserResponse login(User body);
    UserDetailsResponse edit(UserDetails body,Long id);
    UserDetailsResponse showDetails(Long id);
    String getRoleFromToken(String token);
    Long getIdFromToken(String token);
}

