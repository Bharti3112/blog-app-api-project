package com.blogapplication.api.Security;

import com.blogapplication.api.entities.Users;
import com.blogapplication.api.exceptions.ResourceNotFoundException;
import com.blogapplication.api.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user","email:" + username,0));
        return users;
    }
}
