package Main;

import java.util.Scanner;
import product.*;
import Admin.CategoryMenu;
import users.Auth;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to The supermarket please choose Your option");

        System.out.println("(1) Login Client");
        System.out.println("(2) Login Admin");
        System.out.println("(3) Sign up");


        boolean continueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        continueInput = false;
                        Auth.login(false);
                    }
                    case 2 -> {
                        continueInput = false;
                        Auth.login(true);
                    }
                    case 3 -> {
                        continueInput = false;
                        Auth.signup();
                    }
                    default -> throw new Exception("Invalid input");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while (continueInput);
    }
}