package com.kamaleshbala.codesphere.service;


import com.kamaleshbala.codesphere.model.UserModel;
import com.kamaleshbala.codesphere.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public UserModel register(UserModel user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public String login(UserModel user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()) return jwtService.generateToken(user.getUsername());
        return null;
    }

    public List<UserModel> getAllUser() {
        return userRepo.findAll();
    }
}
