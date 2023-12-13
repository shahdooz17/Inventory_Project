package product;


import java.util.Scanner;
public class ProductMenu {
    public static void productMenu() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Add Product");
                System.out.println("(2) Update Product");
                System.out.println("(3) Delete Product");
                System.out.println("(4) Search Product");
                System.out.println("(5) Back");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        containueInput = false;
                        ProductMenu.addProduct();
                    }
                    case 2->{
                        containueInput = false;
                        ProductMenu.UpdateProduct();
                    }
                    case 3->{
                        containueInput = false;
                        ProductMenu.DeleteProduct();
                    }
                    case 4->{
                        containueInput = false;
                        ProductMenu.selectProductMenu();
                    }
                    case 5 -> {
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
    public static void addProduct() {
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

    public static void DeleteProduct() {

        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Product myP = new Product();
        do {
            try {
                System.out.println("Enter Product ID:");
                String inputIdentifier = input.nextLine();
                myP.setID(inputIdentifier);
                if(myP.select()) {
                    myP.delete(myP);
                    System.out.println("Product deleted successfully");
                    ProductMenu.productMenu();
                    containueInput = false;
                } else {
                    throw new Exception("No product with that id");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);

    }

    public static void UpdateProduct() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Product myP = new Product();
        do {
            try {
                System.out.println("Enter Product ID:");
                String productId = input.nextLine();
                myP.setID(productId);
                System.out.println("Enter Updated Product name:");
                myP.setName(input.nextLine());
                System.out.println("Enter Updated Product category:");
                myP.setCategory(input.nextLine());
                System.out.println("Enter Updated Product price:");
                myP.setPrice(input.nextDouble());
                input.nextLine(); // clear buffer
                System.out.println("Enter Updated Product productionDate (DD-MM-YYYY):");
                myP.setProductionDate(input.nextLine());
                System.out.println("Enter Updated Product expirationDate (DD-MM-YYYY):");
                myP.setExpirationDate(input.nextLine());
                System.out.println("Enter Updated Product quantity:");
                myP.setQuantity(input.nextInt());
                input.nextLine(); // clear buffer
                Product oldProduct = new Product();
                oldProduct.setID(myP.getID());
                oldProduct.select();
                myP.update(oldProduct);
                System.out.println("Product updated successfully");
                ProductMenu.productMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    private static void selectProductMenu() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Select Product Menu");
                System.out.println("--------------------");
                System.out.println("1. Search for a Product");
                System.out.println("2. Back to Main Menu");
                System.out.println("--------------------");

                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("Enter the ID of the product to select:");
                        String productId = input.nextLine();

                        Product selectedProduct = new Product();
                        selectedProduct.setID(productId);

                        boolean isSelected = selectedProduct.select();
                        if (isSelected) {
                            System.out.println("Product selected successfully.");
                            System.out.println("Product ID: " + selectedProduct.getID());
                            System.out.println("Product Name: " + selectedProduct.getName());
                            System.out.println("Product Category: " + selectedProduct.getCategory());
                            System.out.println("Product Price: " + selectedProduct.getPrice());
                            System.out.println("Production Date: " + selectedProduct.getProductionDate());
                            System.out.println("Expiration Date: " + selectedProduct.getExpirationDate());
                            System.out.println("Product Quantity: " + selectedProduct.getQuantity());
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;
                    case 2:
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
