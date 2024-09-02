package com.example.Projec1.dao;

import com.example.Projec1.entity.Admin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
public class AdminResponse {
    private HttpStatus code;
    private Admin data;
    private String message;

    public AdminResponse(HttpStatus code, Admin data, String message) {
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

