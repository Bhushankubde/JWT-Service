package com.learning.service;

import com.learning.model.User;
import com.learning.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;
    
    @Autowired
    private AuthenticationManager authManger;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    public User register (User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(User user) {
        Authentication authenticate = authManger.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authenticate.isAuthenticated()) {
            System.out.println("Token:-"+jwtService.generateToken(user.getUsername()));
            return jwtService.generateToken(user.getUsername());
        }
        return "failed";
    }
}
