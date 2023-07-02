package com.example.minorproject1.controllers;

import com.example.minorproject1.dtos.CreateAdminRequest;
import com.example.minorproject1.models.Admin;
import com.example.minorproject1.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    public AdminService adminService;

    @PostMapping("/admin")
    public void create(@RequestBody @Valid CreateAdminRequest adminRequest) {
        adminService.create(adminRequest.to());
    }

    @GetMapping("/admin")
    public Admin getAdmin(@RequestParam("id") Integer adminId) {
        return adminService.getAdmin(adminId);
    }
}
