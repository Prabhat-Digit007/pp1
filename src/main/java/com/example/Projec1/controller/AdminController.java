package com.example.Projec1.controller;

import com.example.Projec1.dao.AdminResponse;
import com.example.Projec1.entity.Admin;
import com.example.Projec1.services.IAdminServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/bookstore_user/admin")
@RestController
public class AdminController {

    @Autowired
    IAdminServices demoService;

//    @CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/registration")
    public AdminResponse register(@Valid @RequestBody Admin body) {
        System.out.println("In Controller");
        AdminResponse res = demoService.register(body);
        return res;
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/login")
    public AdminResponse login(@Valid @RequestBody Admin body) {
        return demoService.login(body);
    }

}

