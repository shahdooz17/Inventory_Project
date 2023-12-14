package users;

import Main.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageClientsMenu {
    public static void ManageClientsMenu() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Add Client");
                System.out.println("(2) Update Client");
                System.out.println("(3) Delete Client");
                System.out.println("(4) View Client");
                System.out.println("(5) View All Client");
                System.out.println("(6) Back");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        containueInput = false;
                        ManageClientsMenu.addClient();
                    }
                    case 2->{
                        containueInput = false;
                        ManageClientsMenu.UpdateClient();
                    }
                    case 3->{
                        containueInput = false;
                        ManageClientsMenu.DeleteClient();
                    }
                    case 4->{
                        containueInput = false;
                        ManageClientsMenu.selectClient();
                    }
                    case 5 -> {
                        containueInput = false;
                        ManageClientsMenu.allClient();
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
    public static void addClient() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Client myP = new Client();
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
                System.out.println("Enter email: ");
                myP.setEmail(input.nextLine());
                myP.add();
                System.out.println("Added with id: " + myP.getID());
                ManageClientsMenu.ManageClientsMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    public static void DeleteClient() {

        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Client myP = new Client();
        do {
            try {
                System.out.println("Enter ID:");
                String inputIdentifier = input.nextLine();
                myP = (Client) myP.select(inputIdentifier);

                boolean isSelected = !myP.getName().equals("test");
                if(isSelected) {
                    myP.delete(myP);
                    System.out.println("deleted successfully");
                    ManageClientsMenu.ManageClientsMenu();
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

    public static void UpdateClient() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Client myP = new Client();
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
                System.out.println("Enter email: ");
                myP.setEmail(input.nextLine());
                Client old = new Client();
                old = (Client) old.select(myP.getID());
                myP.update(old);
                System.out.println("updated successfully");
                ManageClientsMenu.ManageClientsMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    private static void selectClient() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Enter the ID: ");
                String id = input.nextLine();

                Client selected = new Client();
                selected = (Client) selected.select(id);

                boolean isSelected = !selected.getName().equals("test");
                if (isSelected) {
                    printClient(selected);
                    ManageClientsMenu.ManageClientsMenu();
                } else {
                    System.out.println("not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
    private static void printClient(Client selected) {
        System.out.println("ID: " + selected.getID());
        System.out.println("Name: " + selected.getName());
        System.out.println("Age: " + selected.getAge());
        System.out.println("Username: " + selected.getUsername());
        System.out.println("Password: " + selected.getPassword());
        System.out.println("email: " + selected.getEmail());
        System.out.print("\n");
    }
    public static void allClient() {
        try {
            Client selected = new Client();
            ArrayList<Client> data = selected.getAll();
            for (Client one: data) {
                printClient(one);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
