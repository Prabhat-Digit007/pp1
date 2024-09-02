package com.example.Projec1.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Projec1.dao.UserDetailsResponse;
import com.example.Projec1.dao.UserResponse;
import com.example.Projec1.entity.*;
import com.example.Projec1.repo.IUserDetails;
import com.example.Projec1.repo.IUserRepository;
import com.example.Projec1.util.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServices implements IUserServices {
    String token = null;

    @Autowired
    IUserRepository demoRepo;

    @Autowired
    IUserDetails demoDetails;

    @Override
    public UserResponse register(User body) {

        Optional<User> isRegistered = demoRepo.findByEmail(body.getEmail());

        User user = null;
        if(isRegistered.isEmpty()){
            String hashpw = BCrypt.hashpw(body.getPassword(), BCrypt.gensalt());
            body.setPassword(hashpw);
//            token = AuthMiddleware.getToken();
//            body.setAuth(token);
            user = demoRepo.save(body);
        }else{
            return new UserResponse(HttpStatus.BAD_REQUEST, null, body.getEmail()+" is already registered");
        }

        return new UserResponse(HttpStatus.CREATED, body, "User Registered Successfully");
    }

    @Override
    public UserResponse login(User body){

        System.out.println(body.getEmail());
        Optional<User> isEmailPresent = demoRepo.findByEmail(body.getEmail());

        if(isEmailPresent.isEmpty()){
            return new UserResponse(HttpStatus.BAD_REQUEST, null, body.getEmail()+" is not registered.");
        }

        String passwordFromDb = isEmailPresent.get().getPassword();
        boolean checkpw = BCrypt.checkpw(body.getPassword(), passwordFromDb);
        if(checkpw) {
            String role = "user";
//            token = AuthMiddleware.getToken(body);
            Optional<User> optionalUser = demoRepo.findByEmail(body.getEmail());
            Long id = optionalUser.get().getId();
            token = AuthMiddleware.getToken(role,id);
            return new UserResponse(HttpStatus.OK, body, token);
        }
        return new UserResponse(HttpStatus.BAD_REQUEST, null, "Please enter valid password");
    }

//    @Override
//        public UserDetailsResponse edit(UserDetails body,Long id){
//            Optional<User> isIdPresent = demoRepo.findById(id);
//
//
//            if(isIdPresent.isEmpty()){
//                return new UserDetailsResponse(HttpStatus.BAD_REQUEST, null, id +" is not registered.");
//            }
//        User user = isIdPresent.get();
//        body.setUserId(id);
//        UserDetails userDetails = demoDetails.save(body);
//            user.setUserDetails(userDetails);
//            demoRepo.save(user);
//            return new UserDetailsResponse(HttpStatus.OK, userDetails, "User Details fill successful");
//        }

//    @Override
//    public UserDetailsResponse edit(UserDetails body, Long id) {
//        Optional<User> isIdPresent = demoRepo.findById(id);
//
//        if (isIdPresent.isEmpty()) {
//            return new UserDetailsResponse(HttpStatus.BAD_REQUEST, null, id + " is not registered.");
//        }
//
//        User user = isIdPresent.get();
////        body.setUser(user);
////        UserDetails userDetails = demoDetails.save(body);
////
////        user.setUserDetails(userDetails);
////
////        demoRepo.save(user);
//
//        if(user.getUserDetails()!=null){
//            return new UserDetailsResponse(HttpStatus.BAD_REQUEST,null,"Already filled user details");
//        }
//
//        UserDetails userDetails = new UserDetails();
//        userDetails.setUser(user);
//        userDetails.setName(userDetails.getName());
//        userDetails.setName(userDetails.getName());
//        userDetails.setPhoneNumber(userDetails.getPhoneNumber());
//        userDetails.setAddresses(userDetails.getAddresses());
//        userDetails.setEmail(user.getEmail());
//        userDetails = demoDetails.save(userDetails);
//
//        user.setUserDetails(userDetails);
//        demoRepo.save(user);
//
//        return new UserDetailsResponse(HttpStatus.OK, userDetails, "User details updated successfully.");
//    }

//    @Override
//    public UserDetailsResponse edit(UserDetails body, Long id) {
//        Optional<User> isIdPresent = demoRepo.findById(id);
//
//        if (isIdPresent.isEmpty()) {
//            return new UserDetailsResponse(HttpStatus.BAD_REQUEST, null, id + " is not registered.");
//        }
//
//        User user = isIdPresent.get();
//
//        if (user.getUserDetails() != null) {
//            return new UserDetailsResponse(HttpStatus.BAD_REQUEST, null, "Already filled user details");
//        }
//
//        UserDetails userDetails = demoDetails.findByUser(user).orElseGet(() -> {
//            UserDetails newUserDetails = new UserDetails();
//            newUserDetails.setUser(user);
//            return newUserDetails;
//        });
//
//        UserDetails newUserDetails = new UserDetails();
//        newUserDetails.setUser(user);
//        // Ensure all required fields are set
//        userDetails.setName(body.getName());
//        userDetails.setPhoneNumber(body.getPhoneNumber());
////        userDetails.setAddresses(body.getAddresses());
//        userDetails.setEmail(body.getEmail());
//
//        for (Address address : body.getAddresses()) {
//            Address address1 = new Address();
//            address1.setAddressLine1(address.getAddressLine1());
//            address1.setAddressLine2(address.getAddressLine2());
//            address1.setUser(address.getUser());
//            address1.setMobileNumber(address.getMobileNumber());
//            address1.setUser(address.getUser());
//            address1.setFullName(address.getFullName());
//            address1.setState(address.getState());
//            address1.setLandmark(address.getLandmark());
//            address1.setCity(address.getCity());
//            address1.setPincode(address.getPincode());
//            userDetails.getAddresses().add(address1);
//        }
//
//        userDetails = demoDetails.save(userDetails);
//
//        user.setUserDetails(userDetails);
//        demoRepo.save(user);
//
//        return new UserDetailsResponse(HttpStatus.OK, userDetails, "User details updated successfully.");
//    }


    @Override
    public UserDetailsResponse edit(UserDetails body, Long id) {
        Optional<User> isIdPresent = demoRepo.findById(id);
        System.out.println("1");
        if (isIdPresent.isEmpty()) {
            return new UserDetailsResponse(HttpStatus.BAD_REQUEST, null, id + " is not registered.");
        }
        System.out.println("2");

        User user = isIdPresent.get();

        System.out.println("3");
        if (user.getUserDetails() != null) {
            return new UserDetailsResponse(HttpStatus.BAD_REQUEST, null, "Already filled user details");
        }
        System.out.println("4");

        UserDetails userDetails = demoDetails.findByUser(user).orElseGet(() -> {
            UserDetails newUserDetails = new UserDetails();
            newUserDetails.setUser(user);
            return newUserDetails;
        });
        System.out.println("5");

        // Ensure all required fields are set
        userDetails.setName(body.getName());
        userDetails.setPhoneNumber(body.getPhoneNumber());
        userDetails.setEmail(body.getEmail());

        System.out.println("6");
        // Initialize the addresses list
        userDetails.setAddresses(new ArrayList<>());

        System.out.println("7");
        // Check for null values in required fields
        if (userDetails.getName() == null || userDetails.getEmail() == null) {
            return new UserDetailsResponse(HttpStatus.BAD_REQUEST, null, "Name and Email cannot be null.");
        }

        System.out.println("8");
        for (Address address : body.getAddresses()) {
            Address address1 = new Address();
            address1.setAddressLine1(address.getAddressLine1());
            address1.setAddressLine2(address.getAddressLine2());
            address1.setUser(user); // Set the user for the address
            address1.setMobileNumber(address.getMobileNumber());
            address1.setFullName(address.getFullName());
            address1.setState(address.getState());
            address1.setLandmark(address.getLandmark());
            address1.setCity(address.getCity());
            address1.setPincode(address.getPincode());
            address1.setUserDetails(userDetails); // Set the userDetails for the address
            userDetails.getAddresses().add(address1);
        }

        System.out.println("9");
        userDetails = demoDetails.save(userDetails);

        System.out.println("10");
        user.setUserDetails(userDetails);
        System.out.println("11");
//        System.out.println(user);
//        demoRepo.save(user);
        System.out.println("12");

        return new UserDetailsResponse(HttpStatus.OK, userDetails, "User details updated successfully.");
    }

    public UserDetailsResponse showDetails(Long id) {
        User user = demoRepo.findById(id).get();
        Optional<UserDetails> byUser = demoDetails.findByUser(user);
        UserDetails userDetails = byUser.get();
        return new UserDetailsResponse(HttpStatus.OK,userDetails,"Showing Details");
    }


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

    public Long getIdFromToken(String token) {
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
        Long id = decodedJWT.getClaim("id").asLong();

        return id; // Return the role from the token
    }

}


