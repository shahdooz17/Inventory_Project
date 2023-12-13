package users;

import Database.Database;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Auth {
    public static boolean login(boolean isAdmin) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter Username: ");
            String username = input.nextLine();
            System.out.println("Enter Password: ");
            String password = input.nextLine();
            if(isAdmin) {
                try {
                    Admin myAdmin = new Admin();
                    myAdmin.setUsername(username);
                    if(myAdmin.select()) {
                        if(myAdmin.getPassword() == password) {
                            return true;
                        }
                        throw new Exception("Invalid password");
                    }
                    throw new Exception("Invalid username");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }


            } else {
                try {
                    Database myDB = new Database("clients");
                    ArrayList<String> myData = myDB.readText();
                    for (int i = 0; i<myData.size(); i++) {
                        ArrayList<String> users = myDB.decrypt(myData.get(i));
                        if(users.get(3).equals(username)) {
                            if(users.get(4).equals(password)) {
                                return true;
                            } else {
                                throw new Exception("Wrong password");
                            }
                        }
                    }
                    return false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
        return false;
    }
}
