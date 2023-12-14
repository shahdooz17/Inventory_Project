package users;

import Database.Database;
import Main.Main;
import product.OrderManageMenu;
import product.Product;
import product.ProductMenu;
import Admin.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {
    public static void adminMenu(Admin myAdmin) {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Manage Clients");
                System.out.println("(2) Manage Suppliers");
                System.out.println("(3) Manage Admins");
                System.out.println("(4) Manage Categories");
                System.out.println("(5) Manage Products");
                System.out.println("(6) Manage Offers");
                System.out.println("(7) Manage Orders");
                System.out.println("(8) profit report");
                System.out.println("(9) Logout");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        ManageClientsMenu.ManageClientsMenu();
                        containueInput = false;
                    }
                    case 2 -> {
                        ManageSuppliersMenu.ManageSuppliersMenu();
                        containueInput = false;
                    }
                    case 3 -> {
                        ManageAdminsMenu.ManageAdminMenu();
                        containueInput = false;
                    }
                    case 4 -> {
                        CategoryMenu.categoryMenu();
                        containueInput = false;
                    }
                    case 5 -> {
                        ProductMenu.productMenu();
                        containueInput = false;
                    }
                    case 6 -> {
                        ManageOffersMenu.ManageOffersMenu();
                        containueInput = false;
                    }
                    case 7 -> {
                        OrderManageMenu.OrderManageMenu2();
                        containueInput = false;
                    }
                    case 8 -> {
                        Database myDB = new Database("totalEarning");
                        ArrayList<String> totalEarning = myDB.readText();
                        System.out.println("Total Earning is: " + totalEarning.get(0));
                    }
                    case 9 -> {
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
}