package com.example.minorproject1.repositories;

import com.example.minorproject1.models.Student;
import com.example.minorproject1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentCacheRepository {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public Student get(Integer studentId) {
        Object res = redisTemplate.opsForValue().get(getKey(studentId));
        return res != null ? (Student) res : null;
    }

    public void set(Student student) {
        redisTemplate.opsForValue().set(getKey(student.getId()),student);
    }

    public String getKey(Integer studentId) {
        return Constants.STUDENT_KEY_PREFIX+studentId;
    }
}
