package com.example.Projec1.controller;

import com.example.Projec1.dao.UserDetailsResponse;
import com.example.Projec1.dao.UserResponse;
import com.example.Projec1.entity.Product;
import com.example.Projec1.entity.User;
import com.example.Projec1.entity.UserDetails;
import com.example.Projec1.services.IProductService;
import com.example.Projec1.services.IUserServices;
import com.example.Projec1.util.AuthMiddleware;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bookstore_user")
@RestController
public class UserController {

    @Autowired
    IUserServices demoService;

    @Autowired
    IProductService productService;

    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/registration")
    public UserResponse register(@Valid @RequestBody User body) {
        System.out.println("In Controller");
        UserResponse res = demoService.register(body);
        return res;
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody User body) {
        return demoService.login(body);
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/edit_user")
    public UserDetailsResponse editDetails(@RequestHeader("Authorization") String token,@Valid @RequestBody UserDetails body) {
        if(AuthMiddleware.getRoleFromToken(token).equals("user")){
            Long id = AuthMiddleware.getIdFromToken(token);
            return demoService.edit(body,id);
        }
        return new UserDetailsResponse(HttpStatus.BAD_REQUEST,null,"user id not found");
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @GetMapping("/show_details")
    public UserDetailsResponse showDetails(@RequestHeader("Authorization") String token) {
        if(AuthMiddleware.getRoleFromToken(token).equals("user")){
            Long id = AuthMiddleware.getIdFromToken(token);
            System.out.println(id);
            return demoService.showDetails(id);
        }
        return new UserDetailsResponse(HttpStatus.BAD_REQUEST,null,"user id not found");
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/login/verification/{token}")
    public UserResponse loginByToken(@PathVariable Long token, @RequestBody User body) {
        return demoService.login(body);
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @GetMapping("/get/book")
    public List<Product> getAllProduct(){
        return productService.getAllOrders();
    }


}

