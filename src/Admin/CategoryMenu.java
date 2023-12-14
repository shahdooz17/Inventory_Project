package Admin;


import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryMenu {
    public static void categoryMenu() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("(1) Add Category");
                System.out.println("(2) Update Category");
                System.out.println("(3) Delete Category");
                System.out.println("(4) View Category");
                System.out.println("(5) Back");
                int option = input.nextInt();
                switch (option) {
                    case 1 -> {
                        containueInput = false;
                        CategoryMenu.addCategory();
                    }
                    case 2->{
                        containueInput = false;
                        CategoryMenu.UpdateCategory();
                    }
                    case 3->{
                        containueInput = false;
                        CategoryMenu.DeleteCategory();
                    }
                    case 4->{
                        containueInput = false;
                        CategoryMenu.selectCategory();
                    }
                    case 5 -> {
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
    public static void addCategory() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Category myP = new Category();
        do {
            try {
                System.out.println("Enter Category name:");
                myP.setName(input.nextLine());
                myP.add();
                System.out.println("Added category with id: " + myP.getID());
                CategoryMenu.categoryMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }

    public static void DeleteCategory() {

        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Category myP = new Category();
        do {
            try {
                System.out.println("Enter Category ID:");
                String inputIdentifier = input.nextLine();
                myP = (Category) myP.select(inputIdentifier);

                boolean isSelected = !myP.getName().equals("test");
                if(isSelected) {
                    myP.delete(myP);
                    System.out.println("Category deleted successfully");
                    CategoryMenu.categoryMenu();
                    containueInput = false;
                } else {
                    throw new Exception("No Category with that id");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);

    }

    public static void UpdateCategory() {
        boolean containueInput = true;
        Scanner input = new Scanner(System.in);
        Category myP = new Category();
        do {
            try {
                System.out.println("Enter Category ID:");
                String catId = input.nextLine();
                myP.setID(catId);
                System.out.println("Enter Updated Category name:");
                myP.setName(input.nextLine());
                Category oldCat = new Category();
                oldCat = (Category) oldCat.select(myP.getID());
                myP.update(oldCat);
                System.out.println("Category updated successfully");
                CategoryMenu.categoryMenu();
                containueInput = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (containueInput);
    }
    private static void selectCategory() {
        boolean continueInput = true;
        Scanner input = new Scanner(System.in);

        do {
            try {
                System.out.println("Enter the ID of the category to select:");
                String categoryId = input.nextLine();

                Category selectedcategory = new Category();
                selectedcategory = (Category) selectedcategory.select(categoryId);

                boolean isSelected = !selectedcategory.getName().equals("test");
                if (isSelected) {
                    System.out.println("category selected successfully.");
                    System.out.println("category ID: " + selectedcategory.getID());
                    System.out.println("category Name: " + selectedcategory.getName());
                    continueInput = false;
                    CategoryMenu.categoryMenu();
                } else {
                    System.out.println("category not found.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine(); // clear buffer
            }
        } while (continueInput);
    }
}
