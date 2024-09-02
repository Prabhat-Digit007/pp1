package com.example.Projec1.repo;

import com.example.Projec1.entity.Address;
import com.example.Projec1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Long> {
//    Optional<Address> findByUser(User user);
List<Address> findByUser(User user);
}
