package product;


import Main.Main;

import java.util.ArrayList;
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
                System.out.println("(4) View Product");
                System.out.println("(5) Search Products");
                System.out.println("(6) View All Products");
                System.out.println("(7) Back");
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
                    case 5->{
                        containueInput = false;
                        ProductMenu.searchProducts();
                    }
                    case 6 -> {
                        containueInput = false;
                        ProductMenu.allProducts();
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
                myP = (Product) myP.select(inputIdentifier);

                boolean isSelected = !myP.getName().equals("test");
                if(isSelected) {
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
                oldProduct = (Product) oldProduct.select(myP.getID());
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
                System.out.println("Enter the ID of the product to select:");
                String productId = input.nextLine();

                Product selectedProduct = new Product();
                selectedProduct = (Product) selectedProduct.select(productId);

                boolean isSelected = !selectedProduct.getName().equals("test");
                if (isSelected) {
                    printProduct(selectedProduct);
                } else {
                    System.out.println("Product not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
    public static void searchProducts() {
        boolean ontinueInput2 = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                Product selectedProduct = new Product();
                System.out.println("Search with: ");
                System.out.println("(1) Name");
                System.out.println("(2) Production Date");
                System.out.println("(3) Expiration Date");
                System.out.println("(4) Category name");

                int option = input.nextInt();
                input.nextLine(); // clear buffer
                switch (option) {
                    case 1 -> {
                        ontinueInput2 =false;
                        System.out.println("Enter name: ");
                        String name = input.nextLine();
                        ArrayList<Product> returnedData = selectedProduct.filter("name", name);
                        for (Product product: returnedData) {
                            printProduct(product);
                        }
                    }
                    case 2 -> {
                        ontinueInput2 =false;
                        System.out.println("Enter date (dd-mm-yyyy): ");
                        String name = input.nextLine();
                        ArrayList<Product> returnedData = selectedProduct.filter("production", name);
                        for (Product product: returnedData) {
                            printProduct(product);
                        }
                    }
                    case 3 -> {
                        ontinueInput2 =false;
                        System.out.println("Enter date (dd-mm-yyyy): ");
                        String name = input.nextLine();
                        ArrayList<Product> returnedData = selectedProduct.filter("expiration", name);
                        for (Product product: returnedData) {
                            printProduct(product);
                        }
                    }
                    case 4 -> {
                        ontinueInput2 =false;
                        System.out.println("Enter category name: ");
                        String name = input.nextLine();
                        ArrayList<Product> returnedData = selectedProduct.filter("categories", name);
                        for (Product product: returnedData) {
                            printProduct(product);
                        }
                    }
                    default -> {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (ontinueInput2);
    }
    private static void printProduct(Product selectedProduct) throws Exception {
        System.out.println("Product ID: " + selectedProduct.getID());
        System.out.println("Product Name: " + selectedProduct.getName());
        System.out.println("Product Category: " + selectedProduct.getCategory());
        System.out.println("Product Price: " + selectedProduct.getPrice());
        System.out.println("Production Date: " + selectedProduct.getProductionDate());
        System.out.println("Expiration Date: " + selectedProduct.getExpirationDate());
        System.out.println("Product Quantity: " + selectedProduct.getQuantity());
        System.out.print("\n");
    }
    public static void allProducts() {
            try {
                Product selectedProduct = new Product();
                ArrayList<Product> data = selectedProduct.getAll();
                for (Product product: data) {
                    printProduct(product);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
