package com.app.blockbuster.repository;

import com.app.blockbuster.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieInventoryRepo extends JpaRepository<Movie, Long> {
    @Query("select m from Movie m where lower(m.name) = ?1")
    Optional<Movie> findByName(String name);

    @Query("select m from Movie m where m.name like %?1%")
    Optional<List<Movie>> listByName(String name);

    Optional<List<Movie>> findAllByGenre(String genre);
}
