package com.app.blockbuster.controller;

import com.app.blockbuster.config.JwtGeneratorInterface;
import com.app.blockbuster.entity.User;
import com.app.blockbuster.model.UserDTO;
import com.app.blockbuster.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private UserManagementService registrationService;

    @Autowired
    private JwtGeneratorInterface jwtGeneratorInterface;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody UserDTO user) {
        registrationService.register(user);
        return new ResponseEntity<String>("User created successfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserDTO user) {
        User loggedInUser = registrationService.login(user);
        return new ResponseEntity<>(jwtGeneratorInterface.generateToken(loggedInUser), HttpStatus.OK);
    }
}
