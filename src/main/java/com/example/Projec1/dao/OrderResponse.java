package com.example.Projec1.dao;


import com.example.Projec1.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private HttpStatus code;
    private Order data;
    private String message;

    @Override
    public String toString() {
        return "DemoResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message=" + message +
                '}';
    }
}

