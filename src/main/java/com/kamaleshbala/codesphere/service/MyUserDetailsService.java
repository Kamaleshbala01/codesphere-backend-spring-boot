package com.kamaleshbala.codesphere.service;

import com.kamaleshbala.codesphere.model.UserModel;
import com.kamaleshbala.codesphere.model.UserPrinciple;
import com.kamaleshbala.codesphere.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username){
        UserModel user = userRepo.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User not found");
        return new UserPrinciple(user);
    }

}
