package com.example.minorproject1.controllers;

import com.example.minorproject1.dtos.CreateStudentRequest;
import com.example.minorproject1.models.SecuredUser;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.services.StudentService;
import com.example.minorproject1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    //unsecured
    @PostMapping("/student")
    public void create(@RequestBody @Valid CreateStudentRequest createStudentRequest) {
        studentService.create(createStudentRequest.to());
    }

    // accessible by student
    @GetMapping("/student")
    public Student getStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        Integer studentId = securedUser.getStudent().getId();
        return studentService.getStudent(studentId);
    }

    //accessible by admin
    @GetMapping("/student-by-id/{id}")
    public Student getStudentById(@PathVariable("id") Integer studentId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();

        for(GrantedAuthority grantedAuthority: securedUser.getAuthorities()) {
            String[] authorities = grantedAuthority.getAuthority().split(Constants.DELIMITER);
            boolean isCalledByAdmin = Arrays.stream(authorities).anyMatch(x->Constants.STUDENT_INFO_AUTHORITY.equals(x));
            if (isCalledByAdmin) {
                return studentService.getStudent(studentId);
            }
        }
        throw new Exception("User is not authorized to do this");
    }
}
