package product;

import Main.Main;
import users.Admin;
import users.ManageAdminsMenu;
import users.ManageSuppliersMenu;
import users.Supplier;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderManageMenu {
    public static void OrderManageMenu2() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Approve Order");
                System.out.println("(2) Update Order");
                System.out.println("(3) Delete Order");
                System.out.println("(4) View Order");
                System.out.println("(5) View All Orders");
                System.out.println("(6) Back");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        containueInput = false;
                        OrderManageMenu.approveAdminOrder();
                    }
                    case 2->{
                        containueInput = false;
                        OrderManageMenu.UpdateOrder();
                    }
                    case 3->{
                        containueInput = false;
                        OrderManageMenu.DeleteOrder();
                    }
                    case 4->{
                        containueInput = false;
                        OrderManageMenu.selectOrder();
                    }
                    case 5 -> {
                        containueInput = false;
                        OrderManageMenu.allOrders();
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
    public static void approveAdminOrder() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Order myP = new Order();
        do {
            try {
                System.out.println("Enter ID:");
                String inputIdentifier = input.nextLine();
                Order.approveOrder(inputIdentifier);
                System.out.println("approved successfully");
                OrderManageMenu.OrderManageMenu2();
                containueInput = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    public static void DeleteOrder() {

        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Order myP = new Order();
        do {
            try {
                System.out.println("Enter ID:");
                String inputIdentifier = input.nextLine();
                myP = (Order) myP.select(inputIdentifier);

                boolean isSelected = !myP.getproduct().equals("test");
                if(isSelected) {
                    myP.delete(myP);
                    System.out.println("deleted successfully");
                    OrderManageMenu.OrderManageMenu2();
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

    public static void UpdateOrder() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Order myP = new Order();
        do {
            try {
                System.out.println("Enter ID:");
                String productId = input.nextLine();
                myP.setID(productId);
                System.out.println("Enter Product Id:");
                myP.setproduct(input.nextLine());
                System.out.println("Enter Client id:");
                myP.setClient(input.nextLine());
                System.out.println("Enter Status: ");
                myP.setStatus(input.nextLine());
                System.out.println("Enter Bill Id: ");
                myP.setBill(input.nextLine());
                Order old = new Order();
                old = (Order) old.select(myP.getID());
                myP.update(old);
                System.out.println("updated successfully");
                OrderManageMenu.OrderManageMenu2();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    private static void selectOrder() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Enter the ID: ");
                String id = input.nextLine();

                Order selected = new Order();
                selected = (Order) selected.select(id);

                boolean isSelected = !selected.getproduct().equals("test");
                if (isSelected) {
                    printClient(selected);
                    OrderManageMenu.OrderManageMenu2();
                } else {
                    System.out.println("not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
    private static void printClient(Order selected) {
        System.out.println("ID: " + selected.getID());
        System.out.println("Product id: " + selected.getproduct());
        System.out.println("Client id: " + selected.getClient());
        System.out.println("Bill id: " + selected.getBill());
        System.out.println("Status: " + selected.getStatus());
        System.out.print("\n");
    }
    public static void allOrders() {
        try {
            Order selected = new Order();
            ArrayList<Order> data = selected.getAll();
            for (Order one: data) {
                printClient(one);
            }
            OrderManageMenu.OrderManageMenu2();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
