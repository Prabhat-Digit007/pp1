package com.example.Projec1.dao;

import com.example.Projec1.entity.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
public class AddressResponse {
    private HttpStatus code;
    private List<Address> data;
    private String message;

    public AddressResponse(HttpStatus code, List<Address> data, String message) {
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
