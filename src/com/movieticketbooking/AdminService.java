package com.movieticketbooking;

import java.util.Scanner;

public class AdminService {

    Scanner sc = new Scanner(System.in);

    MovieService movieService;

    public AdminService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void adminMenu() {

        while (true) {

            System.out.println("\n===== ADMIN MANAGEMENT =====");

            System.out.println("1. View Movies");
            System.out.println("2. Add Movie");
            System.out.println("3. Delete Movie");
            System.out.println("0. Back to Main Menu");

            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    movieService.showMovies();
                    break;

                case 2:
                    addMovie();
                    break;

                case 3:
                    deleteMovie();
                    break;

                case 0:
                    System.out.println(
                        "Returning to Main Menu..."
                    );
                    return;

                default:
                    System.out.println(
                        "Invalid choice!"
                    );
            }
        }
    }

    public void addMovie() {

        System.out.println("\n===== ADD MOVIE =====");

        System.out.print("Enter Movie ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Movie Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter Language: ");
        String language = sc.nextLine();

        System.out.print("Enter Ticket Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        Movie movie = new Movie(
            id,
            name,
            genre,
            language,
            price
        );

        movieService.movies.add(movie);

        System.out.println(
            "\nMovie added successfully!"
        );
    }

    public void deleteMovie() {

        System.out.println("\n===== DELETE MOVIE =====");

        System.out.print("Enter Movie ID to delete: ");

        int id = sc.nextInt();
        sc.nextLine();

        Movie movieToDelete = null;

        for (Movie movie : movieService.movies) {

            if (movie.id == id) {

                movieToDelete = movie;
                break;
            }
        }

        if (movieToDelete != null) {

            movieService.movies.remove(movieToDelete);

            System.out.println(
                "\nMovie deleted successfully!"
            );

        } else {

            System.out.println(
                "\nMovie ID not found!"
            );
        }
    }
}
