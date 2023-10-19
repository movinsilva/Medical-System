import authentication.Login;
import authentication.Register;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Medical System!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Please select an option: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                Login.loginUser();
                break;
            case 2:
                Register.selectAccount();
                break;
            case 3:
                System.out.println("Exiting the program.");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }


    }
}