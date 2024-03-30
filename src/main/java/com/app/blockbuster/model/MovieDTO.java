package com.app.blockbuster.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO implements Comparable<MovieDTO> {
    private Long id;
    private String name;
    private String genre;
    private double rating;
    private String actor;
    private String releaseYear;
    private int quantity;

    @Override
    public int compareTo(MovieDTO movie) {
        if (rating == movie.getRating()) return 0;
        else if (rating > movie.rating) return 1;
        else return -1;
    }
}
