import java.util.ArrayList;
import java.util.Scanner;

public class SeatService {

    ArrayList<Seat> seats = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public SeatService() {

        for (int i = 1; i <= 20; i++) {
            seats.add(new Seat(i));
        }

    }

    public void showSeats() {

        System.out.println("\n===== SEAT AVAILABILITY =====");
        System.out.println("[X] = Booked");

        for (Seat seat : seats) {

            seat.displaySeat();

            if (seat.seatNumber % 5 == 0) {
                System.out.println();
            }
        }
    }

    public ArrayList<Integer> selectSeats() {

        showSeats();

        System.out.print("\nHow many seats do you want? ");
        int numberOfSeats = sc.nextInt();
        sc.nextLine();

        ArrayList<Integer> selectedSeats = new ArrayList<>();

        for (int i = 1; i <= numberOfSeats; i++) {

            System.out.print("Enter seat number " + i + ": ");
            int seatNumber = sc.nextInt();
            sc.nextLine();

            if (seatNumber < 1 || seatNumber > 20) {

                System.out.println("Invalid seat number!");
                i--;
                continue;
            }

            Seat selectedSeat = seats.get(seatNumber - 1);

            if (selectedSeat.booked) {

                System.out.println(
                    "Seat " + seatNumber + " is already booked!"
                );

                i--;
                continue;
            }

            if (selectedSeats.contains(seatNumber)) {

                System.out.println(
                    "You already selected this seat!"
                );

                i--;
                continue;
            }

            selectedSeats.add(seatNumber);
            selectedSeat.booked = true;

            System.out.println(
                "Seat " + seatNumber + " selected!"
            );
        }

        System.out.println(
            "\nSelected Seats: " + selectedSeats
        );

        return selectedSeats;
    }
}