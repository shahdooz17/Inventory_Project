package Admin;

import Main.Main;
import users.Admin;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageOffersMenu {
    public static void ManageOffersMenu() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Add Offer");
                System.out.println("(2) Update Offer");
                System.out.println("(3) Delete Offer");
                System.out.println("(4) View Offer");
                System.out.println("(5) View All Offers");
                System.out.println("(6) Back");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        containueInput = false;
                        ManageOffersMenu.addOffer();
                    }
                    case 2->{
                        containueInput = false;
                        ManageOffersMenu.UpdateOffer();
                    }
                    case 3->{
                        containueInput = false;
                        ManageOffersMenu.DeleteOffer();
                    }
                    case 4->{
                        containueInput = false;
                        ManageOffersMenu.selectOffer();
                    }
                    case 5 -> {
                        containueInput = false;
                        ManageOffersMenu.allOffers();
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
    public static void addOffer() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Offer myP = new Offer();
        do {
            try {
                System.out.println("Enter Product id:");
                myP.setproduct(input.nextLine());
                System.out.println("Enter Start date (dd-mm-yyyy): ");
                myP.setstartDate(input.nextLine());
                System.out.println("Enter end date (dd-mm-yyyy): ");
                myP.setendDate(input.nextLine());
                myP.add();
                System.out.println("Added with id: " + myP.getID());
                ManageOffersMenu.ManageOffersMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    public static void DeleteOffer() {

        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Offer myP = new Offer();
        do {
            try {
                System.out.println("Enter ID:");
                String inputIdentifier = input.nextLine();
                myP = (Offer) myP.select(inputIdentifier);

                boolean isSelected = !myP.getproduct().equals("test");
                if(isSelected) {
                    myP.delete(myP);
                    System.out.println("deleted successfully");
                    ManageOffersMenu.ManageOffersMenu();
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

    public static void UpdateOffer() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Offer myP = new Offer();
        do {
            try {
                System.out.println("Enter ID:");
                String productId = input.nextLine();
                myP.setID(productId);
                System.out.println("Enter Product id:");
                myP.setproduct(input.nextLine());
                System.out.println("Enter Start date (dd-mm-yyyy): ");
                myP.setstartDate(input.nextLine());
                System.out.println("Enter end date (dd-mm-yyyy): ");
                myP.setendDate(input.nextLine());
                Offer old = new Offer();
                old = (Offer) old.select(myP.getID());
                myP.update(old);
                System.out.println("updated successfully");
                ManageOffersMenu.ManageOffersMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    private static void selectOffer() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Enter the ID: ");
                String id = input.nextLine();

                Offer selected = new Offer();
                selected = (Offer) selected.select(id);

                boolean isSelected = !selected.getproduct().equals("test");
                if (isSelected) {
                    printOffer(selected);
                    ManageOffersMenu.ManageOffersMenu();
                } else {
                    System.out.println("not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
    private static void printOffer(Offer selected) {
        System.out.println("ID: " + selected.getID());
        System.out.println("Product id: " + selected.getproduct());
        System.out.println("Start date: " + selected.getstartDate());
        System.out.println("End date: " + selected.getendDate());
        System.out.println("Discount percentage: " + selected.getPercent());
        System.out.print("\n");
    }
    public static void allOffers() {
        try {
            Offer selected = new Offer();
            ArrayList<Offer> data = selected.getAll();
            for (Offer one: data) {
                printOffer(one);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
