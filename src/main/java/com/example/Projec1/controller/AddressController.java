package com.example.Projec1.controller;

import com.example.Projec1.dao.AddressResponse;
import com.example.Projec1.entity.Address;
import com.example.Projec1.services.IAddressServices;
import com.example.Projec1.util.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore_user")
public class AddressController {

    @Autowired
    IAddressServices addressServices;

    @CrossOrigin(origins = "http://localhost:60517")
    @GetMapping("/getAddress")
    public AddressResponse getAddressToken(@RequestHeader("Authorization") String token) {
        Long id = AuthMiddleware.getIdFromToken(token);
        List<Address> addresses = addressServices.getAddress(id);
        return new AddressResponse(HttpStatus.OK, addresses, "Showing Addresses");
    }
}

