package com.example.Projec1.services;

import com.example.Projec1.dao.AdminResponse;
import com.example.Projec1.entity.Admin;

public interface IAdminServices {
    AdminResponse register(Admin body);
    AdminResponse login(Admin body);
}
