package com.example.Projec1.dao;

import com.example.Projec1.entity.UserDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
public class UserDetailsResponse {
    private HttpStatus code;
    private UserDetails data;
    private String message;

    public UserDetailsResponse(HttpStatus code, UserDetails data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        return "DemoResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message=" + message +
                '}';
    }
}

