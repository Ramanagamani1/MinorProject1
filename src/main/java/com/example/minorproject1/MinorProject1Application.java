package com.example.minorproject1;

import com.example.minorproject1.models.Admin;
import com.example.minorproject1.models.SecuredUser;
import com.example.minorproject1.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinorProject1Application implements CommandLineRunner {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MinorProject1Application.class, args);
    }

    @Autowired
    AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
//        Admin admin = Admin.builder()
//                .name("Mani")
//                .email("mani@gmail.com")
//                .securedUser(SecuredUser.builder()
//                        .username("mani")
//                        .password("admin123")
//                        .build())
//                .build();
//
//        adminService.create(admin);
    }
}
