package com.example.minorproject1.services;

import com.example.minorproject1.models.Student;
import com.example.minorproject1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void create(Student student) {
        studentRepository.save(student);
    }

    public Student getStudent(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
}
