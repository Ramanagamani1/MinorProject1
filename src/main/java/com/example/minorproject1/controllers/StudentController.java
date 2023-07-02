package com.example.minorproject1.controllers;

import com.example.minorproject1.dtos.CreateStudentRequest;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public void create(@RequestBody @Valid CreateStudentRequest createStudentRequest) {
        studentService.create(createStudentRequest.to());
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam("id") Integer studentId) {
        return studentService.getStudent(studentId);
    }
}
