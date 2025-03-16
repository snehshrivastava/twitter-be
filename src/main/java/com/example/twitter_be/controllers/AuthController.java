package com.example.twitter_be.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.twitter_be.config.JwtProvider;
import com.example.twitter_be.dtos.AuthResponse;
import com.example.twitter_be.exceptions.UserException;
import com.example.twitter_be.models.User;
import com.example.twitter_be.models.Verify;
import com.example.twitter_be.repository.UserRepository;
import com.example.twitter_be.service.CustomUserDetailService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();

        User isEmailUser = userRepository.findByEmail(email);
        if (isEmailUser != null) {
            throw new UserException("Email already exists");
        }
        User user1 = new User();
        user1.setEmail(email);
        user1.setPassword(passwordEncoder.encode(password));
        user1.setFullName(fullName);
        user1.setVerify(new Verify());
        User savedUser = userRepository.save(user1);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setStatus(true);

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();

        Authentication authentication = authenticate(email, password);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
