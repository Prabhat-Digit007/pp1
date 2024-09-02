package com.example.Projec1.services;

import com.example.Projec1.dao.AdminResponse;
import com.example.Projec1.entity.Admin;
import com.example.Projec1.repo.IAdminRepository;
import com.example.Projec1.util.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServices implements IAdminServices {
    String token = null;

    @Autowired
    IAdminRepository demoRepo;

    @Override
    public AdminResponse register(Admin body) {

        Optional<Admin> isRegistered = demoRepo.findByEmail(body.getEmail());

        Admin admin = null;
        if(isRegistered.isEmpty()){
            String hashpw = BCrypt.hashpw(body.getPassword(), BCrypt.gensalt());
            body.setPassword(hashpw);
            admin = demoRepo.save(body);
        }else{
            return new AdminResponse(HttpStatus.BAD_REQUEST, null, body.getEmail()+" is already registered");
        }

        return new AdminResponse(HttpStatus.CREATED, body, "User Registered Successfully");
    }

    @Override
    public AdminResponse login(Admin body){

        Optional<Admin> isEmailPresent = demoRepo.findByEmail(body.getEmail());
        System.out.println(isEmailPresent);


        if(isEmailPresent.isEmpty()){
            return new AdminResponse(HttpStatus.BAD_REQUEST, null, body.getEmail()+" is not registered.");
        }

        String passwordFromDb = isEmailPresent.get().getPassword();
        boolean checkpw = BCrypt.checkpw(body.getPassword(), passwordFromDb);
        if(checkpw) {
            String role = "admin";
//            token = AuthMiddleware.getToken(body);
            token = AuthMiddleware.getToken(role, body.getId());
            return new AdminResponse(HttpStatus.OK, body, token);
        }
        return new AdminResponse(HttpStatus.BAD_REQUEST, null, "Please enter valid password");
    }

}

