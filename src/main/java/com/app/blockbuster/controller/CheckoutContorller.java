package com.app.blockbuster.controller;

import com.app.blockbuster.entity.User;
import com.app.blockbuster.enums.Role;
import com.app.blockbuster.exception.UserNotAuthorisedException;
import com.app.blockbuster.model.CheckoutDTO;
import com.app.blockbuster.model.MovieDTO;
import com.app.blockbuster.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/checkout")
public class CheckoutContorller {
    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/issue")
    public ResponseEntity<CheckoutDTO> issue(@RequestBody CheckoutDTO checkoutDTO, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("user");
        if (user.getRole() == Role.ADMIN)
            throw new UserNotAuthorisedException("User not authorized to perform this action");
        checkoutDTO.setUserId(user.getId());
        CheckoutDTO movieDTO = checkoutService.issue(checkoutDTO);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<CheckoutDTO> release(@RequestBody CheckoutDTO checkoutDTO, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("user");
        if (user.getRole() == Role.ADMIN)
            throw new UserNotAuthorisedException("User not authorized to perform this action");
        checkoutDTO.setUserId(user.getId());
        CheckoutDTO movieDTO = checkoutService.release(checkoutDTO);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }
}
