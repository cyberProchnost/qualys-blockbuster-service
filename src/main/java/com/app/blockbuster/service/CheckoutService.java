package com.app.blockbuster.service;

import com.app.blockbuster.entity.Checkout;
import com.app.blockbuster.entity.Movie;
import com.app.blockbuster.exception.CannotIssueSameMovieException;
import com.app.blockbuster.exception.InventoryUnavailableException;
import com.app.blockbuster.exception.MovieNotFoundException;
import com.app.blockbuster.model.CheckoutDTO;
import com.app.blockbuster.repository.CheckoutRepo;
import com.app.blockbuster.repository.MovieInventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckoutService {
    @Autowired
    private MovieInventoryRepo movieInventoryRepo;
    @Autowired
    private CheckoutRepo checkoutRepo;

    public CheckoutDTO issue(CheckoutDTO checkoutDTO) {
        Optional<Movie> byName = movieInventoryRepo.findByName(checkoutDTO.getMovieName().toLowerCase());
        if (!byName.isPresent())
            throw new MovieNotFoundException("Movie not found");
        Movie movie = byName.get();
        Optional<Checkout> byUserId = checkoutRepo.findByUserIdAndMovieId(checkoutDTO.getUserId(), movie.getId());
        if (byUserId.isPresent())
            throw new CannotIssueSameMovieException("Cannot issue same movie disc");
        if(movie.getQuantity() == 0)
            throw new InventoryUnavailableException("Zero disc available for this movie");
        synchronized (this) {
            Checkout checkout = Checkout.builder().movieId(movie.getId()).userId(checkoutDTO.getUserId()).build();
            checkoutRepo.save(checkout);
            movie.setQuantity(movie.getQuantity() - 1);
            movieInventoryRepo.save(movie);
        }

        return CheckoutDTO.builder().movieName(movie.getName()).userId(checkoutDTO.getUserId()).build();
    }

    public CheckoutDTO release(CheckoutDTO checkoutDTO) {
        Optional<Movie> byName = movieInventoryRepo.findByName(checkoutDTO.getMovieName().toLowerCase());
        if (!byName.isPresent())
            throw new MovieNotFoundException("Movie not found");
        Movie movie = byName.get();
        Optional<Checkout> byUserId = checkoutRepo.findByUserIdAndMovieId(checkoutDTO.getUserId(), movie.getId());
        if (!byUserId.isPresent())
            throw new CannotIssueSameMovieException("No movie found on your name");
        Checkout checkout = byUserId.get();
        checkoutRepo.delete(checkout);
        movie.setQuantity(movie.getQuantity() + 1);
        movieInventoryRepo.save(movie);
        return CheckoutDTO.builder().movieName(movie.getName()).userId(checkoutDTO.getUserId()).build();
    }
}
