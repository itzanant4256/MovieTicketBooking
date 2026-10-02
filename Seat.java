public class Seat {

    int seatNumber;
    boolean booked;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    public void displaySeat() {

        if (booked) {
            System.out.print("[X] ");
        } else {
            System.out.print("[" + seatNumber + "] ");
        }

    }
}