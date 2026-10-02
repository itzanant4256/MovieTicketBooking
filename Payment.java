import java.util.Scanner;

public class Payment {

    Scanner sc = new Scanner(System.in);

    public boolean makePayment(double amount) {

        System.out.println("\n===== ONLINE PAYMENT =====");

        System.out.println("Amount to Pay: Rs." + amount);

        System.out.println("\nSelect Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Debit/Credit Card");
        System.out.println("3. Cash");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                System.out.print("Enter UPI ID: ");
                String upi = sc.nextLine();

                System.out.println(
                    "Processing UPI payment..."
                );

                System.out.println(
                    "Payment Successful!"
                );

                return true;

            case 2:
                System.out.print("Enter Card Number: ");
                String card = sc.nextLine();

                System.out.println(
                    "Processing card payment..."
                );

                System.out.println(
                    "Payment Successful!"
                );

                return true;

            case 3:
                System.out.println(
                    "Cash payment selected."
                );

                System.out.println(
                    "Payment Successful!"
                );

                return true;

            default:
                System.out.println(
                    "Invalid payment method!"
                );

                return false;
        }
    }
}