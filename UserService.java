import java.util.ArrayList;
import java.util.Scanner;

public class UserService {

    ArrayList<User> users = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void register() {

        System.out.println("\n===== USER REGISTRATION =====");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = new User(name, username, password);

        users.add(user);

        System.out.println("Registration successful!");
    }

    public boolean login() {

        System.out.println("\n===== USER LOGIN =====");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (User user : users) {

            if (user.username.equals(username)
                    && user.password.equals(password)) {

                System.out.println("Login successful!");
                System.out.println("Welcome " + user.name);

                return true;
            }
        }

        System.out.println("Invalid username or password.");

        return false;
    }
}