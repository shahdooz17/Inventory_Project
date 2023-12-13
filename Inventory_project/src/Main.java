import java.util.Scanner;
import product.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to The supermarket please choose Your option");

        System.out.println("(1) Product menu");
        System.out.println("(2) Client menu");
        System.out.println("(3) Supplier menu");

        boolean continueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        continueInput = false;
                        product.ProductMenu.productMenu();
                    }
                    default -> throw new Exception("Invalid input");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while (continueInput);
    }
}
