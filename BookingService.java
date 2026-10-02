import java.util.ArrayList;
import java.util.Scanner;

public class BookingService {

    ArrayList<Booking> bookings = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    int bookingCounter = 1001;

    // 4.5 Ticket Booking
    public void bookTicket(
            String customerName,
            String movieName,
            String theatreName,
            String showTime,
            ArrayList<Integer> seats,
            double ticketPrice) {

        double totalPrice = ticketPrice * seats.size();

        System.out.println("\n===== TICKET DETAILS =====");

        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Show Time: " + showTime);
        System.out.println("Seats: " + seats);
        System.out.println("Number of Tickets: " + seats.size());
        System.out.println("Total Price: Rs." + totalPrice);

        System.out.print("\nProceed to payment? (yes/no): ");

        String answer = sc.nextLine();

        if (answer.equalsIgnoreCase("yes")) {

            Payment payment = new Payment();

            boolean success = payment.makePayment(totalPrice);

            if (success) {

                Booking booking = new Booking(
                    bookingCounter++,
                    customerName,
                    movieName,
                    theatreName,
                    showTime,
                    seats,
                    totalPrice
                );

                bookings.add(booking);

                System.out.println("\nBooking successful!");

                booking.displayBooking();

            } else {

                System.out.println(
                    "Payment failed. Booking cancelled."
                );
            }

        } else {

            System.out.println(
                "Booking cancelled."
            );
        }
    }

    // 4.6 Booking Confirmation
    public void viewBookings() {

        System.out.println("\n===== BOOKING CONFIRMATION =====");

        if (bookings.isEmpty()) {

            System.out.println("No bookings available.");

            return;
        }

        for (Booking booking : bookings) {

            booking.displayBooking();
        }
    }

    // 4.6 Booking Cancellation
    public void cancelBooking() {

        System.out.println("\n===== CANCEL BOOKING =====");

        if (bookings.isEmpty()) {

            System.out.println(
                "No bookings available to cancel."
            );

            return;
        }

        System.out.print("Enter Booking ID: ");

        int bookingId = sc.nextInt();
        sc.nextLine();

        Booking bookingToCancel = null;

        for (Booking booking : bookings) {

            if (booking.bookingId == bookingId) {

                bookingToCancel = booking;

                break;
            }
        }

        if (bookingToCancel != null) {

            System.out.println("\nBooking Found!");

            bookingToCancel.displayBooking();

            System.out.print(
                "\nAre you sure you want to cancel? (yes/no): "
            );

            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("yes")) {

                bookings.remove(bookingToCancel);

                System.out.println(
                    "\nBooking cancelled successfully!"
                );

                System.out.println(
                    "Booking ID " + bookingId +
                    " has been cancelled."
                );

            } else {

                System.out.println(
                    "\nCancellation stopped."
                );
            }

        } else {

            System.out.println(
                "\nBooking ID not found!"
            );
        }
    }
}