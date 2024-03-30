package com.app.blockbuster.service;

import com.app.blockbuster.entity.User;
import com.app.blockbuster.enums.Role;
import com.app.blockbuster.exception.InvalidLoginCredentialsException;
import com.app.blockbuster.exception.UserAlreadyPresentException;
import com.app.blockbuster.model.UserDTO;
import com.app.blockbuster.repository.UserManagement;
import com.app.blockbuster.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementService {
    @Autowired
    private UserManagement registration;

    public void register(UserDTO user) {
        Optional<User> userByEmail = registration.getUserByEmail(user.getEmail());
        if (userByEmail.isPresent())
            throw new UserAlreadyPresentException("User With email id is present");
        User user1 = User.builder().name(user.getName()).email(user.getEmail()).
                password(PasswordUtils.encrypt(user.getPassword())).role(Role.USER).build();
        registration.save(user1);
    }

    public User login(UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getPassword() == null)
            throw new InvalidLoginCredentialsException("Invalid Username/Password");
        Optional<User> userOptional = registration.findByEmailAndPassword(userDTO.getEmail(), PasswordUtils.encrypt(userDTO.getPassword()));
        if (!userOptional.isPresent())
            throw new InvalidLoginCredentialsException("Invalid Username/Password");
        return userOptional.get();
    }
}
