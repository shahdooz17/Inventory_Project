package users;

import Database.Database;
import Main.Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Auth {
    public static void login(boolean isAdmin) {
        Scanner input = new Scanner(System.in);
        boolean containueInput = true;
        do {
            try {
                System.out.println("Enter Username: ");
                String username = input.nextLine();
                System.out.println("Enter Password: ");
                String password = input.nextLine();
                if (isAdmin) {
                    Admin myAdmin = new Admin();
                    myAdmin = (Admin) myAdmin.select(username);
                    if (!myAdmin.getUsername().equals("test")) {
                        if (myAdmin.getPassword().equals(password)) {
                            AdminMenu.adminMenu(myAdmin);
                            containueInput = false;
                        }
                        throw new Exception("Invalid password");
                    }
                    throw new Exception("Invalid username");


                } else {
                    Client myClient = new Client();
                    myClient = (Client) myClient.select(username);
                    if (!myClient.getUsername().equals("test")) {
                        if (myClient.getPassword().equals(password)) {
                            ClientMenu.clientMenu(myClient);
                            containueInput = false;
                        }
                        throw new Exception("Invalid password");
                    }
                    throw new Exception("Invalid username");

                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (containueInput);
    }


    public static void signup() {
        Scanner input = new Scanner(System.in);
        boolean containueInput = true;
        do {
            try {
                System.out.println("Enter Username: ");
                String username = input.nextLine();
                System.out.println("Enter Password: ");
                String password = input.nextLine();
                System.out.println("Enter name: ");
                String name = input.nextLine();
                System.out.println("Enter email: ");
                String email = input.nextLine();
                System.out.println("Enter age: ");
                int age = input.nextInt();
                input.nextLine();
                Client myClient = new Client();
                myClient.setName(name);
                myClient.setEmail(email);
                myClient.setPassword(password);
                myClient.setUsername(username);
                myClient.setAge(age);
                myClient.add();
                containueInput = false;
                Main.main(null);

            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (containueInput);
    }
}
