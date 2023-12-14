package users;

import Main.Main;
import product.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientMenu {
    public static void clientMenu(Client myClient) {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Edit profile");
                System.out.println("(2) Place Order");
                System.out.println("(3) View your orders");
                System.out.println("(4) View products");
                System.out.println("(5) Search in products");
                System.out.println("(6) Get order bill");
                System.out.println("(7) Logout");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        editProfile(myClient);
                        containueInput = false;
                    }
                    case 2 -> {
                        placeOrder(myClient);
                        containueInput = false;
                    }
                    case 3 -> {
                        viewOrder(myClient);
                        containueInput = false;
                    }
                    case 4 -> {
                        ClientMenu.viewProducts(myClient);
                        containueInput = false;
                    }
                    case 5 -> {
                        ClientMenu.searchProducts(myClient);
                        containueInput = false;
                    }
                    case 6 -> {
                        ClientMenu.getOrderBill(myClient);
                        containueInput = false;
                    }
                    case 7 -> {
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

    private static void editProfile(Client myClient) {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                Client myC = new Client();
                myC = (Client) myC.select(myClient.getUsername());
                System.out.println("Enter name: ");
                String name = input.nextLine();
                System.out.println("Enter new password: ");
                String pass = input.nextLine();
                System.out.println("Enter new email: ");
                String email = input.nextLine();
                myC.setName(name);
                myC.setEmail(email);
                myC.setPassword(pass);
                myC.update(myClient);
                System.out.println("Updated");
                clientMenu(myClient);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    private static void placeOrder(Client myClient) {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("Enter product id: ");
                String product = input.nextLine();
                Product myProduct = new Product();
                myProduct = (Product) myProduct.select(product);
                if(myProduct.getName().equals("test")) {
                    throw new Exception("Invalid Product");
                }
                if(myProduct.isNearExpiration(myProduct.getExpirationDate())) {
                    System.out.println("Warning this product will expire soon!");
                }
                if(myProduct.getQuantity() <= 10) {
                    System.out.println("Warning this product will be out of stock soon!");
                }
                Order myOrder = new Order(product, myClient.getID(), "none", "pending");
                myOrder.add();
                System.out.println("Placed Order");
                clientMenu(myClient);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    private static void viewOrder(Client myClient) {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                Order myOrder = new Order();
                ArrayList<Order> orders = myOrder.getClientOrders(myClient.getID());
                for (Order order:orders) {
                    Product product = new Product();
                    product = (Product) product.select(order.getproduct());
                    System.out.println("Order id: " + order.getID());
                    System.out.println("Product id: " + order.getproduct());
                    System.out.println("Product name: " + product.getName());
                    System.out.println("Order status: " + order.getStatus());
                    System.out.println("Order bill id: " + order.getBill());
                }
                clientMenu(myClient);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    private static void viewProducts(Client myClient) {
        ProductMenu.allProducts();
        clientMenu(myClient);
    }

    private static void searchProducts(Client myClient) {
        ProductMenu.searchProducts();
        clientMenu(myClient);
    }
    private static void getOrderBill (Client myClient) {
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
                    if(selected.getBill().equals("none")) {
                        throw new Exception("Not Approved");
                    }
                    Bill myBill = new Bill();
                    myBill = (Bill) myBill.select(selected.getBill());
                    System.out.println("ID: " + myBill.getID());
                    System.out.println("Product id: " + myBill.getproduct());
                    System.out.println("Client id: " + myBill.getClient());
                    System.out.println("Total price: " + myBill.getTotal());
                    ClientMenu.clientMenu(myClient);
                } else {
                    System.out.println("not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
}
