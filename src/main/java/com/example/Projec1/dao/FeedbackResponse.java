package com.example.Projec1.dao;

import com.example.Projec1.entity.Cart;
import com.example.Projec1.entity.Feedback;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
    private HttpStatus code;
    private Feedback data;
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

