package com.example.minorproject1.repositories;

import com.example.minorproject1.models.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SecuredUser,Integer> {

      SecuredUser findByusername(String username);
}
