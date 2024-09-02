package com.example.Projec1.services;

import com.example.Projec1.dao.AddressResponse;
import com.example.Projec1.entity.Address;

import java.util.List;

public interface IAddressServices {
    List<Address> getAddress(Long id);
}
