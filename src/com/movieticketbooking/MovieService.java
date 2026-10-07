package com.movieticketbooking;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieService {

    ArrayList<Movie> movies = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public MovieService() {

        movies.add(new Movie(
            1, "Avengers", "Action", "English", 200
        ));

        movies.add(new Movie(
            2, "Inception", "Sci-Fi", "English", 180
        ));

        movies.add(new Movie(
            3, "3 Idiots", "Comedy", "Hindi", 150
        ));

        movies.add(new Movie(
            4, "Interstellar", "Sci-Fi", "English", 220
        ));

        movies.add(new Movie(
            5, "Dangal", "Sports", "Hindi", 170
        ));
    }

    public void showMovies() {

        System.out.println("\n===== AVAILABLE MOVIES =====");

        for (Movie movie : movies) {
            movie.displayMovie();
        }
    }

    public void searchMovie() {

        System.out.println("\n===== MOVIE SEARCH =====");

        System.out.print("Enter movie name: ");

        String search = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Movie movie : movies) {

            if (movie.name.toLowerCase().contains(search)) {

                movie.displayMovie();

                found = true;
            }
        }

        if (!found) {
            System.out.println("Movie not found!");
        }
    }
}