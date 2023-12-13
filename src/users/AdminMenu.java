package users;

import product.Product;
import product.ProductMenu;

import java.util.Scanner;

public class AdminMenu {
    private static Admin admin;

    public AdminMenu(Admin admin) {
        this.admin = admin;
    }

    public static void displayMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome" + admin.getUsername() + "!");
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
                        AdminMenu.addAdmin();
                    } catch (Exception e) {
                        System.out.println("An error occurred while adding the user.");
                    }
                    validChoice = true;
                    break;
                case 2:
                    try {
                        AdminMenu.updateAdminMenu();
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the user.");
                    }
                    validChoice = true;
                    break;
                case 3:
                    try {
                        AdminMenu.deleteAdminMenu();
                    } catch (Exception e) {
                        System.out.println("An error occurred while removing the user.");
                    }
                    validChoice = true;
                    break;
                case 4:
                    try {
                        AdminMenu.selectAdminMenu();
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

    private static void addAdmin() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Admin MyAdmin = new Admin();
        do {
            try {
                System.out.println("Enter Admin name:");
                MyAdmin.setUsername(input.nextLine());
                input.nextLine(); // clear buffer
                MyAdmin.add();
                System.out.println("Added admin with id: " +MyAdmin.getID());
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    private static void updateAdminMenu() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Update Admin Menu");
                System.out.println("------------------");
                System.out.println("1. Update Admin Name");
                System.out.println("2. Update Admin Password");
                System.out.println("3. Back to Main Menu");
                System.out.println("------------------");

                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("Enter new Admin name:");
                        String newUsername = input.nextLine();
                        admin.setUsername(newUsername);
                        admin.update(admin);
                        System.out.println("Admin name updated successfully.");
                        break;
                    case 2:
                        System.out.println("Enter new Admin password:");
                        String newPassword = input.nextLine();
                        admin.setPassword(newPassword);
                        admin.update(admin);
                        System.out.println("Admin password updated successfully.");
                        break;
                    case 3:
                        continueInput = false;
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }

    private static void deleteAdminMenu() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Delete Admin Menu");
                System.out.println("------------------");
                System.out.println("1. Delete Admin");
                System.out.println("0. Back to Main Menu");
                System.out.println("------------------");

                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("Enter the ID of the admin to delete:");
                        String adminId = input.nextLine();
                        input.nextLine(); // clear buffer

                        // Create a new Admin object with the given ID
                        Admin adminToDelete = new Admin();
                        adminToDelete.setID(adminId);

                        // Call the delete method
                        adminToDelete.delete(adminToDelete);
                        System.out.println("Admin deleted successfully.");
                        break;
                    case 0:
                        continueInput = false;
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }

    private static void selectAdminMenu() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Select Admin Menu");
                System.out.println("------------------");
                System.out.println("1. Select Admin");
                System.out.println("0. Back to Main Menu");
                System.out.println("------------------");

                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("Enter the username of the admin to select:");
                        String adminUsername = input.nextLine();

                        // Create a new Admin object to store the selected admin data
                        Admin selectedAdmin = new Admin();
                        selectedAdmin.setUsername(adminUsername);

                        // Call the select method
                        boolean isSelected = selectedAdmin.select();
                        if (isSelected) {
                            System.out.println("Admin selected successfully.");
                            System.out.println("Admin ID: " + selectedAdmin.getID());
                            System.out.println("Admin Name: " + selectedAdmin.getName());
                            System.out.println("Admin Age: " + selectedAdmin.getAge());
                            System.out.println("Admin Username: " + selectedAdmin.getUsername());
                            System.out.println("Admin Password: " + selectedAdmin.getPassword());
                        } else {
                            System.out.println("Admin not found.");
                        }
                        break;
                    case 0:
                        continueInput = false;
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
}