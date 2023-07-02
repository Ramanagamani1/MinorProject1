package com.example.minorproject1.services;

import com.example.minorproject1.models.Admin;
import com.example.minorproject1.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public void create(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin getAdmin(Integer adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }
}
