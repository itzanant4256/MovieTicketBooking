package com.movieticketbooking;

import java.util.ArrayList;

public class Booking {

    int bookingId;
    String customerName;
    String movieName;
    String theatreName;
    String showTime;
    ArrayList<Integer> seats;
    double totalPrice;

    public Booking(
            int bookingId,
            String customerName,
            String movieName,
            String theatreName,
            String showTime,
            ArrayList<Integer> seats,
            double totalPrice) {

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.showTime = showTime;
        this.seats = seats;
        this.totalPrice = totalPrice;
    }

    public void displayBooking() {

        System.out.println("\n==============================");
        System.out.println("      BOOKING CONFIRMED");
        System.out.println("==============================");

        System.out.println("Booking ID : " + bookingId);
        System.out.println("Customer   : " + customerName);
        System.out.println("Movie      : " + movieName);
        System.out.println("Theatre    : " + theatreName);
        System.out.println("Show Time  : " + showTime);
        System.out.println("Seats      : " + seats);
        System.out.println("Total      : Rs." + totalPrice);

        System.out.println("==============================");
    }
}