package users;

import product.Product;
import product.ProductMenu;

import java.util.Scanner;

public class AdminMenu {
    private Admin admin;

    public AdminMenu(Admin admin) {
        this.admin = admin;
    }

    public void displayMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome, " + admin.getUsername() + "!");

        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Please enter your choice:\n"
                    + "(1) to Add User\n"
                    + "(2) to Update User\n"
                    + "(3) to Remove User\n"
                    + "(4) to Select Option");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    try {
                        this.addAdmin();
                    } catch (Exception e) {
                        System.out.println("An error occurred while adding the user.");
                    }
                    validChoice = true;
                    break;
                case 2:
                    try {
                        //admin.update();
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the user.");
                    }
                    validChoice = true;
                    break;
                case 3:
                    try {
                        //admin.remove();
                    } catch (Exception e) {
                        System.out.println("An error occurred while removing the user.");
                    }
                    validChoice = true;
                    break;
                case 4:
                    try {
                        //admin.select();
                    } catch (Exception e) {
                        System.out.println("An error occurred while selecting the option.");
                    }
                    validChoice = true;
                    break;
                default:
                    System.out.println("Unknown choice");
                    break;
            }
        }
    }

    private void addAdmin() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Product myP = new Product();
        do {
            try {
                System.out.println("Enter Product name:");
                myP.setName(input.nextLine());
                System.out.println("Enter Product category:");
                myP.setCategory(input.nextLine());
                System.out.println("Enter Product price:");
                myP.setPrice(input.nextDouble());
                input.nextLine(); // clear buffer
                System.out.println("Enter Product productionDate (DD-MM-YYYY):");
                myP.setProductionDate(input.nextLine());
                System.out.println("Enter Product expirationDate (DD-MM-YYYY):");
                myP.setExpirationDate(input.nextLine());
                System.out.println("Enter Product quantity:");
                myP.setQuantity(input.nextInt());
                input.nextLine(); // clear buffer
                myP.add();
                System.out.println("Added product with id: " + myP.getID());
                ProductMenu.productMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
}