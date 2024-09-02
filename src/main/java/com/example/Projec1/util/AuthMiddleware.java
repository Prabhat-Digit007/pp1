package com.example.Projec1.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

public class AuthMiddleware {

    public static String getToken(String role,Long id){

        Algorithm algorithm = Algorithm.HMAC256("My_Secret_Key");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Baeldung")
                .build();
        String token = JWT.create()
                .withIssuer("Baeldung")
//                .withClaim("role", body.getRole())
                .withClaim("role", role)
                .withClaim("id", id)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 300000000000L))
                .withJWTId(String.valueOf(UUID.randomUUID()))
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .sign(algorithm);

        return token;
    }

    public static Long getIdFromToken(String token) {
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

    public static  String getRoleFromToken(String token) {
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
}

