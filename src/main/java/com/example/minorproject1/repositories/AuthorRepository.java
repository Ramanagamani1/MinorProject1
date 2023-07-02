package com.example.minorproject1.repositories;

import com.example.minorproject1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findByEmail(String email);
}
