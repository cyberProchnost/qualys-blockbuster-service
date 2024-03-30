package com.app.blockbuster.config;

import com.app.blockbuster.entity.Movie;
import com.app.blockbuster.entity.User;
import com.app.blockbuster.enums.Role;
import com.app.blockbuster.repository.MovieInventoryRepo;
import com.app.blockbuster.repository.UserManagement;
import com.app.blockbuster.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitialData implements CommandLineRunner {
    @Autowired
    private MovieInventoryRepo inventoryRepo;

    @Autowired
    private UserManagement userManagement;

    @Override
    public void run(String... args) throws Exception {
        Movie movie1 = Movie.builder().name("The Shawshank Redemption").genre("Drama").actor("Tim Robbins").rating(9.3d).releaseYear("1994").quantity(5).build();
        Movie movie2 = Movie.builder().name("The Godfather").genre("Crime").actor("Daniel Brühl").rating(9.2d).releaseYear("1972").quantity(6).build();
        Movie movie3 = Movie.builder().name("The Dark Knight").genre("Action").actor("Christian Bale").rating(9.0d).releaseYear("2008").quantity(10).build();
        Movie movie4 = Movie.builder().name("12 Angry Men").genre("Drama").actor("Henry Fonda").rating(8.9d).releaseYear("1957").quantity(2).build();
        Movie movie5 = Movie.builder().name("Schindler's List").genre("Biography").actor("Liam Neeson").rating(8.9d).releaseYear("1993").quantity(3).build();
        Movie movie6 = Movie.builder().name("The Lord of the Rings: The Return of the King").genre("Adventure").actor("Elijah Wood").rating(8.9d).releaseYear("2003").quantity(5).build();
        Movie movie7 = Movie.builder().name("Pulp Fiction").genre("Crime").actor("John Travolta").rating(8.9d).releaseYear("1994").quantity(6).build();
        Movie movie8 = Movie.builder().name("Fight Club").genre("Drama").actor("Brad Pitt").rating(8.8d).releaseYear("1999").quantity(2).build();
        Movie movie9 = Movie.builder().name("Forrest Gump").genre("Drama").actor("Tom Hanks").rating(8.8d).releaseYear("1994").quantity(7).build();
        Movie movie10 = Movie.builder().name("Inception").genre("Action").actor("Leonardo DiCaprio").rating(8.8d).releaseYear("2010").quantity(7).build();
        Movie movie11 = Movie.builder().name("The Matrix").genre("Action").actor("Keanu Reeves").rating(8.7d).releaseYear("1999").quantity(4).build();
        Movie movie12 = Movie.builder().name("The Silence of the Lambs").genre("Crime").actor("Jodie Foster").rating(8.6d).releaseYear("1991").quantity(1).build();
        Movie movie13 = Movie.builder().name("The Departed").genre("Crime").actor("Leonardo DiCaprio").rating(8.5d).releaseYear("2006").quantity(6).build();
        Movie movie14 = Movie.builder().name("The Lion King").genre("Animation").actor("Matthew Broderick").rating(8.5d).releaseYear("1994").quantity(7).build();
        Movie movie15 = Movie.builder().name("Gladiator").genre("Action").actor("Russell Crowe").rating(8.5d).releaseYear("2000").quantity(5).build();
        Movie movie16 = Movie.builder().name("The Prestige").genre("Drama").actor("Hugh Jackman").rating(8.5d).releaseYear("2006").quantity(5).build();
        Movie movie17 = Movie.builder().name("Saving Private Ryan").genre("Drama").actor("Tom Hanks").rating(8.5d).releaseYear("1998").quantity(3).build();
        Movie movie18 = Movie.builder().name("The Green Mile").genre("Crime").actor("Tom Hanks").rating(8.5d).releaseYear("1999").quantity(6).build();
        Movie movie19 = Movie.builder().name("The Godfather: Part II").genre("Crime").actor("Al Pacino").rating(8.5d).releaseYear("1974").quantity(6).build();
        Movie movie20 = Movie.builder().name("The Lord of the Rings: The Fellowship of the Ring").genre("Adventure").actor("Elijah Wood").rating(8.8d).releaseYear("2001").quantity(7).build();
        Movie movie21 = Movie.builder().name("The Godfather: Part III").genre("Crime").actor("Al Pacino").rating(7.6d).releaseYear("1990").quantity(8).build();
        Movie movie22 = Movie.builder().name("The Dark Knight Rises").genre("Action").actor("Christian Bale").rating(8.4d).releaseYear("2012").quantity(3).build();
        Movie movie23 = Movie.builder().name("The Lord of the Rings: The Two Towers").genre("Adventure").actor("Elijah Wood").rating(8.7d).releaseYear("2002").quantity(6).build();
        Movie movie24 = Movie.builder().name("Se7en").genre("Crime").actor("Morgan Freeman").rating(8.6d).releaseYear("1995").quantity(2).build();
        Movie movie25 = Movie.builder().name("Django Unchained").genre("Drama").actor("Jamie Foxx").rating(8.4d).releaseYear("2012").quantity(5).build();
        Movie movie26 = Movie.builder().name("Interstellar").genre("Adventure").actor("Matthew McConaughey").rating(8.6d).releaseYear("2014").quantity(6).build();
        Movie movie27 = Movie.builder().name("City of God").genre("Crime").actor("Alexandre Rodrigues").rating(8.6d).releaseYear("2002").quantity(4).build();
        Movie movie28 = Movie.builder().name("The Usual Suspects").genre("Crime").actor("Kevin Spacey").rating(8.5d).releaseYear("1995").quantity(2).build();
        Movie movie29 = Movie.builder().name("The Shining").genre("Horror").actor("Jack Nicholson").rating(8.4d).releaseYear("1980").quantity(4).build();
        Movie movie30 = Movie.builder().name("The Sixth Sense").genre("Drama").actor("Bruce Willis").rating(8.1d).releaseYear("1999").quantity(5).build();
        Movie movie31 = Movie.builder().name("The Departed").genre("Crime").actor("Leonardo DiCaprio").rating(8.5d).releaseYear("2006").quantity(10).build();
        Movie mm[] = new Movie[]{movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10,
                movie11, movie12, movie13, movie14, movie15, movie16, movie17, movie18, movie19, movie20,
                movie21, movie22, movie23, movie24, movie25, movie26, movie27, movie28, movie29, movie30, movie31};
        List<Movie> movies = new ArrayList<>(Arrays.asList(mm));
        inventoryRepo.saveAll(movies);

        User user = User.builder().name("admin user").email("admin@gmail.com").password(PasswordUtils.encrypt("admin")).role(Role.ADMIN).build();
        userManagement.save(user);
    }
}
