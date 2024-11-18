package com.blogapplication.api.controllers;

import com.blogapplication.api.Security.JWTTokenHelper;
import com.blogapplication.api.entities.Users;
import com.blogapplication.api.exceptions.APIExceptions;
import com.blogapplication.api.payloads.JWTAuthRequest;
import com.blogapplication.api.payloads.JWTAuthResponse;
import com.blogapplication.api.payloads.UserDTO;
import com.blogapplication.api.repositories.UserRepo;
import com.blogapplication.api.services.UserServices;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private UserServices userService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> createToken(
            @RequestBody JWTAuthRequest jwtAuthRequest
            )
{
this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
String token = this.jwtTokenHelper.generateToken(userDetails);
JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
jwtAuthResponse.setToken(token);
return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.OK);
}

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
         try {
             this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
         } catch (BadCredentialsException e) {
             System.out.println("Invalid Detials !!");
             throw new APIExceptions("Invalid username or password !!");
         }
    }
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    // register new user api

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDto) {
        UserDTO registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.CREATED);
    }


}
