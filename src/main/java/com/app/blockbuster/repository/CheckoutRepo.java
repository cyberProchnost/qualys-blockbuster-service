package com.app.blockbuster.repository;

import com.app.blockbuster.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckoutRepo extends JpaRepository<Checkout, Long> {
    Optional<Checkout> findByUserIdAndMovieId(Long userId, Long movieId);

    @Query("select c from Checkout c where c.movieId in ?1")
    Optional<List<Checkout>> getAllUserWithSameMovieGenre(List<Long> movies);
}
