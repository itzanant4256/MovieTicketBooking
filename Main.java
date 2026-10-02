import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();
        MovieService movieService = new MovieService();
        ShowService showService = new ShowService();
        SeatService seatService = new SeatService();
        BookingService bookingService = new BookingService();
        AdminService adminService = new AdminService(movieService);

        while (true) {

            System.out.println(
                "\n===== ONLINE MOVIE TICKET BOOKING ====="
            );

            System.out.println("1. User Registration");
            System.out.println("2. User Login");
            System.out.println("3. Search Movies");
            System.out.println("4. Select Theatre & Show");
            System.out.println("5. Select Seats");
            System.out.println("6. Book Ticket");
            System.out.println("7. Booking Confirmation");
            System.out.println("8. Cancel Booking");
            System.out.println("9. Admin Management");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    userService.register();

                    break;

                case 2:

                    userService.login();

                    break;

                case 3:

                    movieService.searchMovie();

                    break;

                case 4:

                    showService.selectTheatreAndShow();

                    break;

                case 5:

                    seatService.selectSeats();

                    break;

                case 6:

                    System.out.println(
                        "\n===== TICKET BOOKING ====="
                    );

                    System.out.print(
                        "Enter customer name: "
                    );

                    String customerName = sc.nextLine();

                    System.out.print(
                        "Enter movie name: "
                    );

                    String movieName = sc.nextLine();

                    System.out.print(
                        "Enter theatre name: "
                    );

                    String theatreName = sc.nextLine();

                    System.out.print(
                        "Enter show time: "
                    );

                    String showTime = sc.nextLine();

                    System.out.print(
                        "How many seats? "
                    );

                    int numberOfSeats = sc.nextInt();

                    ArrayList<Integer> selectedSeats =
                        new ArrayList<>();

                    for (int i = 1; i <= numberOfSeats; i++) {

                        System.out.print(
                            "Enter seat number " + i + ": "
                        );

                        int seatNumber = sc.nextInt();

                        selectedSeats.add(seatNumber);
                    }

                    System.out.print(
                        "Enter ticket price: "
                    );

                    double ticketPrice = sc.nextDouble();

                    sc.nextLine();

                    bookingService.bookTicket(
                        customerName,
                        movieName,
                        theatreName,
                        showTime,
                        selectedSeats,
                        ticketPrice
                    );

                    break;

                case 7:

                    bookingService.viewBookings();

                    break;

                case 8:

                    bookingService.cancelBooking();

                    break;

                case 9:
                    adminService.adminMenu();

                    break;

                case 0:

                    System.out.println(
                        "\nThank you for using the system!"
                    );

                    sc.close();

                    return;

                default:

                    System.out.println(
                        "\nInvalid choice!"
                    );
            }
        }
    }
}