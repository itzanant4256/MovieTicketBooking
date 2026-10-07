package com.movieticketbooking;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowService {

    ArrayList<Theatre> theatres = new ArrayList<>();
    ArrayList<Show> shows = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public ShowService() {

        theatres.add(new Theatre(
            1, "PVR Cinemas", "City Mall"
        ));

        theatres.add(new Theatre(
            2, "INOX", "Central Mall"
        ));

        theatres.add(new Theatre(
            3, "Cinepolis", "Park Street"
        ));

        shows.add(new Show(
            1, "Avengers", "PVR Cinemas", "10:00 AM"
        ));

        shows.add(new Show(
            2, "Avengers", "PVR Cinemas", "2:00 PM"
        ));

        shows.add(new Show(
            3, "Inception", "INOX", "6:00 PM"
        ));

        shows.add(new Show(
            4, "3 Idiots", "Cinepolis", "9:00 PM"
        ));
    }

    public void selectTheatreAndShow() {

        System.out.println("\n===== THEATRES =====");

        for (Theatre theatre : theatres) {
            theatre.displayTheatre();
        }

        System.out.print("\nEnter Theatre ID: ");
        int theatreId = sc.nextInt();
        sc.nextLine();

        Theatre selectedTheatre = null;

        for (Theatre theatre : theatres) {

            if (theatre.id == theatreId) {
                selectedTheatre = theatre;
                break;
            }
        }

        if (selectedTheatre == null) {
            System.out.println("Invalid Theatre ID!");
            return;
        }

        System.out.println(
            "\n===== AVAILABLE SHOWS ====="
        );

        boolean found = false;

        for (Show show : shows) {

            if (show.theatreName.equals(
                    selectedTheatre.name)) {

                show.displayShow();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No shows available.");
            return;
        }

        System.out.print("\nEnter Show ID: ");
        int showId = sc.nextInt();
        sc.nextLine();

        for (Show show : shows) {

            if (show.id == showId &&
                show.theatreName.equals(
                    selectedTheatre.name)) {

                System.out.println(
                    "\nShow selected successfully!"
                );

                System.out.println(
                    "Movie: " + show.movieName
                );

                System.out.println(
                    "Theatre: " + show.theatreName
                );

                System.out.println(
                    "Time: " + show.time
                );

                return;
            }
        }

        System.out.println("Invalid Show ID!");
    }
}