package com.pratk.amigos.controller;

import com.pratk.amigos.exceptionhandling.UserException;
import com.pratk.amigos.model.User;
import com.pratk.amigos.repository.UserRepository;
import com.pratk.amigos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws UserException {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/signin")
    public ResponseEntity<User> signIn(Authentication auth) throws BadCredentialsException {
        Optional<User> userByName = userRepository.findByEmail(auth.getName());
        if(userByName.isPresent()){
            return new ResponseEntity<>(userByName.get(), HttpStatus.ACCEPTED);
        }
        throw new BadCredentialsException("Invalid username or password..");
    }
}
