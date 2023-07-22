package com.example.minorproject1.services;

import com.example.minorproject1.models.SecuredUser;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.repositories.StudentRepository;
import com.example.minorproject1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    public void create(Student student) {
        SecuredUser securedUser = student.getSecuredUser();
        securedUser = userService.create(securedUser, Constants.STUDENT_USER);
        student.setSecuredUser(securedUser);
        studentRepository.save(student);
    }

    public Student getStudent(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
}
