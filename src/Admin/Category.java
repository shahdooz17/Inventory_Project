package Admin;

import Database.Database;
import Database.InterFace;
import product.Product;
import users.Admin;
import users.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.UUID;

public class Category implements InterFace {


    private String name;
    private String id;
    public String getID() {
        return this.id;
    }
    public void setID(String id) {
        this.id = id;
    }

    // Constructor
    public Category() {
        this("test");
    }
    public Category(String name) {
        this.setID(UUID.randomUUID().toString());
        this.name = name;
    }


    // Getter methods
    public String getName() {
        return this.name;
    }

    public void setName(String name) throws InputMismatchException {
        if(!name.matches("^[a-zA-Z]+$"))
            throw new InputMismatchException("Invalid name");
        this.name = name;
    }


    @Override
    public void add() throws Exception {
        String catData = Database.encrypt(this.toArray());
        Database myDB = new Database("category");
        myDB.appendText(catData);

    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Category oldCategory)){
            throw new Exception("Invalid Category");
        }
        Database myDB = new Database("category");
        String oldCategoryData = Database.encrypt(oldCategory.toArray());
        String CategoryData = Database.encrypt(this.toArray());
        myDB.updateText(oldCategoryData, CategoryData);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Category oldCategory)){
            throw new Exception("Invalid Category");
        }
        Database myDB = new Database("category");
        String oldCategoryData = Database.encrypt(oldCategory.toArray());
        myDB.removeText(oldCategoryData);
    }

    @Override
    public ArrayList<String> toArray() {
        ArrayList<String> myCategory = new ArrayList<>();
        myCategory.add(this.id);
        myCategory.add(this.name);
        return myCategory;
    }
    @Override
    public Object select(String id) throws Exception {
        ArrayList<Category> myData = this.getAll();
        Category data = new Category();
        for (Category oneData: myData) {
            if(oneData.getID().equals(id)) {
                data = oneData;
            }
        }
        return data;
    }

    @Override
    public ArrayList<Category> getAll() throws Exception {
        ArrayList<Category> data = new ArrayList<>();
        Database myDB = new Database("category");
        ArrayList<String> allData = myDB.readText();
        for (String oneData : allData) {
            Category myData = new Category();
            ArrayList<String> decryptedData = Database.decrypt(oneData);
            myData.setID(decryptedData.get(0));
            myData.setName(decryptedData.get(1));
            data.add(myData);
        }
        return data;
    }
}
