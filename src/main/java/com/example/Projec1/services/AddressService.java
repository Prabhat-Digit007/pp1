package com.example.Projec1.services;

import com.example.Projec1.entity.Address;
import com.example.Projec1.entity.User;
import com.example.Projec1.repo.IAddressRepository;
import com.example.Projec1.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressServices{

    @Autowired
    IAddressRepository addressRepository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public List<Address> getAddress(Long id) {
        Optional<User> byId1 = userRepository.findById(id);
        if (byId1.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = byId1.get();
        List<Address> addresses = addressRepository.findByUser(user);
        if (addresses.isEmpty()) {
            throw new RuntimeException("No addresses found for user");
        }
        return addresses;
    }

}
