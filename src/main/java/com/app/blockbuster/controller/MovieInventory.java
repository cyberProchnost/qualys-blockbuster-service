package com.app.blockbuster.controller;

import com.app.blockbuster.entity.User;
import com.app.blockbuster.enums.Role;
import com.app.blockbuster.exception.UserNotAuthorisedException;
import com.app.blockbuster.model.MovieDTO;
import com.app.blockbuster.service.MovieInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieInventory {
    @Autowired
    private MovieInventoryService inventoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> create(@RequestBody MovieDTO movie, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("user");
        if (user.getRole() == Role.USER)
            throw new UserNotAuthorisedException("User not authorized to perform this action");
        inventoryService.create(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> read(@PathVariable("id") Long id) {
        MovieDTO movieDTO = inventoryService.read(id);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> update(@PathVariable("id") Long id, @RequestBody MovieDTO movie) {
        return new ResponseEntity<>(new MovieDTO(), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDTO>> list(@RequestParam(value = "name", required = false) String name) {
        if (name != null) return new ResponseEntity<>(inventoryService.listByName(name), HttpStatus.OK);
        return new ResponseEntity<>(inventoryService.list(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new MovieDTO(), HttpStatus.OK);
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<MovieDTO> updateQuantity(@PathVariable("id") Long id, @RequestBody MovieDTO movie, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("user");
        if (user.getRole() == Role.USER)
            throw new UserNotAuthorisedException("User not authorized to perform this action");
        MovieDTO updatedQuantity = inventoryService.updateQuantity(id, movie);
        return new ResponseEntity<>(updatedQuantity, HttpStatus.OK);
    }
}
