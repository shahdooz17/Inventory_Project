package Admin;

import Database.Database;
import Database.InterFace;
import users.Admin;
import users.User;

import java.util.ArrayList;
import java.util.UUID;

public class Category extends User {


    private String name;

    // Constructor
    public Category() {
        this("test");
    }
    public Category(String username) {
        super.setID(UUID.randomUUID().toString());
        this.name = username;
    }


    // Getter methods
    public String getUsername() {
        return this.name;
    }



    public void setname(String username) {
        this.name = name;
    }


    @Override
    public void add() throws Exception {
        String Data = Database.encrypt(this.toArray());
        Database myDB = new Database("categories");
        myDB.appendText(Data);
    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Category oldCat)){
            throw new Exception("Invalid Admin");
        }
        Database myDB = new Database("admins");
        String oldCatData = Database.encrypt(oldCat.toArray());
        String CatData = Database.encrypt(this.toArray());
        myDB.updateText(oldCatData, CatData);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Category oldCat)){
            throw new Exception("Invalid Category");
        }
        Database myDB = new Database("categories");
        String oldCatData = Database.encrypt(oldCat.toArray());
        myDB.removeText(oldCatData);
    }

    @Override
    public boolean select() throws Exception {
        Database myDB = new Database("categories");
        ArrayList<String> myData = myDB.readText();
        for (int i = 0; i<myData.size(); i++) {
            ArrayList<String> users = Database.decrypt(myData.get(i));
            if(users.get(3).equals(this.name)) {
                super.setID(users.get(0));
                super.setName(users.get(1));
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> toArray() {
        ArrayList<String> data = new ArrayList<>();
        data.add(super.getID());
        data.add(super.getName());
        return data;
    }
}
