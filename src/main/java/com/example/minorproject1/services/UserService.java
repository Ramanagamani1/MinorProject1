package com.example.minorproject1.services;

import com.example.minorproject1.models.SecuredUser;
import com.example.minorproject1.repositories.UserRepository;
import com.example.minorproject1.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByusername(username);
    }

    public SecuredUser create(SecuredUser securedUser, String usertype) {
        String password = passwordEncoder.encode(securedUser.getPassword());
        String authorities = Utils.getAuthoritiesForUser().get(usertype);

        securedUser.setPassword(password);
        securedUser.setAuthorities(authorities);
        return userRepository.save(securedUser);
    }
}
