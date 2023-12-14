package users;

import Main.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageAdminsMenu {
    public static void ManageAdminMenu() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Add Admin");
                System.out.println("(2) Update Admin");
                System.out.println("(3) Delete Admin");
                System.out.println("(4) View Admin");
                System.out.println("(5) View All Admin");
                System.out.println("(6) Back");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        containueInput = false;
                        ManageAdminsMenu.addAdmin();
                    }
                    case 2->{
                        containueInput = false;
                        ManageAdminsMenu.UpdateAdmin();
                    }
                    case 3->{
                        containueInput = false;
                        ManageAdminsMenu.DeleteAdmin();
                    }
                    case 4->{
                        containueInput = false;
                        ManageAdminsMenu.selectAdmin();
                    }
                    case 5 -> {
                        containueInput = false;
                        ManageAdminsMenu.allAdmins();
                    }
                    case 6 -> {
                        Main.main(null);
                        containueInput = false;
                    }
                    default -> throw new Exception("Invalid input");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    public static void addAdmin() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Admin myP = new Admin();
        do {
            try {
                System.out.println("Enter name:");
                myP.setName(input.nextLine());
                System.out.println("Enter age:");
                myP.setAge(input.nextInt());
                input.nextLine();
                System.out.println("Enter username: ");
                myP.setUsername(input.nextLine());
                System.out.println("Enter password: ");
                myP.setPassword(input.nextLine());
                myP.add();
                System.out.println("Added with id: " + myP.getID());
                ManageAdminsMenu.ManageAdminMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    public static void DeleteAdmin() {

        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Admin myP = new Admin();
        do {
            try {
                System.out.println("Enter ID:");
                String inputIdentifier = input.nextLine();
                myP = (Admin) myP.select(inputIdentifier);

                boolean isSelected = !myP.getName().equals("test");
                if(isSelected) {
                    myP.delete(myP);
                    System.out.println("deleted successfully");
                    ManageAdminsMenu.ManageAdminMenu();
                    containueInput = false;
                } else {
                    throw new Exception("invalid id");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);

    }

    public static void UpdateAdmin() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Admin myP = new Admin();
        do {
            try {
                System.out.println("Enter ID:");
                String productId = input.nextLine();
                myP.setID(productId);
                System.out.println("Enter name:");
                myP.setName(input.nextLine());
                System.out.println("Enter age:");
                myP.setAge(input.nextInt());
                input.nextLine();
                System.out.println("Enter username: ");
                myP.setUsername(input.nextLine());
                System.out.println("Enter password: ");
                myP.setPassword(input.nextLine());
                Admin old = new Admin();
                old = (Admin) old.select(myP.getID());
                myP.update(old);
                System.out.println("updated successfully");
                ManageAdminsMenu.ManageAdminMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    private static void selectAdmin() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Enter the ID: ");
                String id = input.nextLine();

                Admin selected = new Admin();
                selected = (Admin) selected.select(id);

                boolean isSelected = !selected.getName().equals("test");
                if (isSelected) {
                    printAdmin(selected);
                    ManageAdminsMenu.ManageAdminMenu();
                } else {
                    System.out.println("not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
    private static void printAdmin(Admin selected) {
        System.out.println("ID: " + selected.getID());
        System.out.println("Name: " + selected.getName());
        System.out.println("Age: " + selected.getAge());
        System.out.println("Username: " + selected.getUsername());
        System.out.println("Password: " + selected.getPassword());
        System.out.print("\n");
    }
    public static void allAdmins() {
        try {
            Admin selected = new Admin();
            ArrayList<Admin> data = selected.getAll();
            for (Admin one: data) {
                printAdmin(one);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
