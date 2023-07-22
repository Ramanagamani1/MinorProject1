package com.example.minorproject1.services;

import com.example.minorproject1.models.Admin;
import com.example.minorproject1.models.SecuredUser;
import com.example.minorproject1.repositories.AdminRepository;
import com.example.minorproject1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;


    public void create(Admin admin) {
        SecuredUser securedUser = admin.getSecuredUser();
        securedUser = userService.create(securedUser, Constants.ADMIN_USER);
        admin.setSecuredUser(securedUser);
        adminRepository.save(admin);
    }

    public Admin getAdmin(Integer adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }
}
