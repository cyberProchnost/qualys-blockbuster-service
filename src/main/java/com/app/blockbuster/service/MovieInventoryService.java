package com.app.blockbuster.service;

import com.app.blockbuster.entity.Checkout;
import com.app.blockbuster.entity.Movie;
import com.app.blockbuster.entity.User;
import com.app.blockbuster.exception.MovieAlreadyPresentException;
import com.app.blockbuster.exception.MovieNotFoundException;
import com.app.blockbuster.model.MovieDTO;
import com.app.blockbuster.repository.CheckoutRepo;
import com.app.blockbuster.repository.MovieInventoryRepo;
import com.app.blockbuster.repository.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieInventoryService {
    @Autowired
    private MovieInventoryRepo inventoryRepo;

    @Autowired
    private CheckoutRepo checkoutRepo;
    @Autowired
    private UserManagement userManagement;

    @Autowired
    private SendEmail sendEmail;

    public MovieDTO create(MovieDTO movie) {
        Optional<Movie> optionalMovie = inventoryRepo.findByName(movie.getName().toLowerCase());
        if (optionalMovie.isPresent()) {
            throw new MovieAlreadyPresentException("Movie Already Present");
        }
        Movie movie1 = Movie.builder().name(movie.getName()).genre(movie.getGenre()).rating(movie.getRating())
                .actor(movie.getActor()).releaseYear(movie.getReleaseYear()).quantity(movie.getQuantity() == 0 ? 1 : movie.getQuantity())
                .build();
        Optional<List<Movie>> allByGenre = inventoryRepo.findAllByGenre(movie.getGenre());
        Movie savedMovie = inventoryRepo.save(movie1);
        movie.setId(savedMovie.getId());
        movie.setQuantity(savedMovie.getQuantity());
        if (allByGenre.isPresent() && !allByGenre.get().isEmpty()) {
            List<Movie> movies = allByGenre.get();
            List<Long> collected = movies.stream().map(Movie::getId).collect(Collectors.toList());
            Optional<List<Checkout>> usersCheckouts = checkoutRepo.getAllUserWithSameMovieGenre(collected);
            if (usersCheckouts.isPresent() && !usersCheckouts.get().isEmpty()) {
                List<Checkout> checkouts = usersCheckouts.get();
                Set<Long> users = checkouts.stream().map(Checkout::getUserId).collect(Collectors.toSet());
                Optional<List<User>> usersById = userManagement.getUsersById(users);
                if (usersById.isPresent() && !usersById.get().isEmpty()) {
                    //email address of users with same genre test as added in new movie
                    List<String> userList = usersById.get().stream().map(User::getEmail).collect(Collectors.toList());
                    sendEmail.send(userList);
                }
            }
        }
        return movie;
    }

    public MovieDTO read(Long id) {
        Optional<Movie> optionalMovie = inventoryRepo.findById(id);
        if (!optionalMovie.isPresent()) {
            throw new MovieNotFoundException("Movie not found");
        }
        Movie movie = optionalMovie.get();
        MovieDTO movieDTO = MovieDTO.builder().id(movie.getId()).actor(movie.getActor()).genre(movie.getGenre())
                .name(movie.getName()).quantity(movie.getQuantity()).rating(movie.getRating()).releaseYear(movie.getReleaseYear()).build();
        return movieDTO;
    }

    public ResponseEntity<MovieDTO> update(Long id, MovieDTO movie) {
        return new ResponseEntity<>(new MovieDTO(), HttpStatus.OK);
    }

    public List<MovieDTO> list() {
        List<Movie> movies = inventoryRepo.findAll();
        return movies.stream().map(movie ->
                MovieDTO.builder().id(movie.getId()).actor(movie.getActor()).genre(movie.getGenre())
                        .name(movie.getName()).quantity(movie.getQuantity()).rating(movie.getRating()).
                        releaseYear(movie.getReleaseYear()).build()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public List<MovieDTO> listByName(String name) {
        Optional<List<Movie>> movieList = inventoryRepo.listByName(name);
        if (!movieList.isPresent())
            throw new MovieNotFoundException("Movie not found");
        List<Movie> movies = movieList.get();
        return movies.stream().map(movie ->
                MovieDTO.builder().id(movie.getId()).actor(movie.getActor()).genre(movie.getGenre())
                        .name(movie.getName()).quantity(movie.getQuantity()).rating(movie.getRating()).
                        releaseYear(movie.getReleaseYear()).build()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public ResponseEntity<MovieDTO> delete(Long id) {
        return new ResponseEntity<>(new MovieDTO(), HttpStatus.OK);
    }

    public MovieDTO updateQuantity(Long id, MovieDTO movie) {
        Optional<Movie> movieOptional = inventoryRepo.findById(id);
        if (!movieOptional.isPresent())
            throw new MovieNotFoundException("Movie not found");
        Movie movie1 = movieOptional.get();
        movie1.setQuantity(movie.getQuantity());
        Movie saved = inventoryRepo.save(movie1);
        movie = MovieDTO.builder().id(saved.getId()).actor(saved.getActor()).genre(saved.getGenre())
                .name(saved.getName()).quantity(saved.getQuantity()).rating(saved.getRating()).releaseYear(saved.getReleaseYear()).build();
        return movie;
    }
}
