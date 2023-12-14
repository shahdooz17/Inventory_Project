package users;

import Main.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageSuppliersMenu {
    public static void ManageSuppliersMenu() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Add Supplier");
                System.out.println("(2) Update Supplier");
                System.out.println("(3) Delete Supplier");
                System.out.println("(4) View Supplier");
                System.out.println("(5) View All Supplier");
                System.out.println("(6) Back");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        containueInput = false;
                        ManageSuppliersMenu.addSupplier();
                    }
                    case 2->{
                        containueInput = false;
                        ManageSuppliersMenu.UpdateSupplier();
                    }
                    case 3->{
                        containueInput = false;
                        ManageSuppliersMenu.DeleteSupplier();
                    }
                    case 4->{
                        containueInput = false;
                        ManageSuppliersMenu.selectSupplier();
                    }
                    case 5 -> {
                        containueInput = false;
                        ManageSuppliersMenu.allSuppliers();
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
    public static void addSupplier() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Supplier myP = new Supplier();
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
                ManageSuppliersMenu.ManageSuppliersMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    public static void DeleteSupplier() {

        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Supplier myP = new Supplier();
        do {
            try {
                System.out.println("Enter ID:");
                String inputIdentifier = input.nextLine();
                myP = (Supplier) myP.select(inputIdentifier);

                boolean isSelected = !myP.getName().equals("test");
                if(isSelected) {
                    myP.delete(myP);
                    System.out.println("deleted successfully");
                    ManageSuppliersMenu.ManageSuppliersMenu();
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

    public static void UpdateSupplier() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Supplier myP = new Supplier();
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
                Supplier old = new Supplier();
                old = (Supplier) old.select(myP.getID());
                myP.update(old);
                System.out.println("updated successfully");
                ManageSuppliersMenu.ManageSuppliersMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    private static void selectSupplier() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Enter the ID: ");
                String id = input.nextLine();

                Supplier selected = new Supplier();
                selected = (Supplier) selected.select(id);

                boolean isSelected = !selected.getName().equals("test");
                if (isSelected) {
                    printClient(selected);
                    ManageSuppliersMenu.ManageSuppliersMenu();
                } else {
                    System.out.println("not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
    private static void printClient(Supplier selected) {
        System.out.println("ID: " + selected.getID());
        System.out.println("Name: " + selected.getName());
        System.out.println("Age: " + selected.getAge());
        System.out.println("Username: " + selected.getUsername());
        System.out.println("Password: " + selected.getPassword());
        System.out.print("\n");
    }
    public static void allSuppliers() {
        try {
            Supplier selected = new Supplier();
            ArrayList<Supplier> data = selected.getAll();
            for (Supplier one: data) {
                printClient(one);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
