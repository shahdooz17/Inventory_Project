package users;

import Database.Database;

import java.util.ArrayList;
import java.util.UUID;

public class Supplier extends User{
    private String username;
    private String password;

    // Constructor
    public Supplier() {
        this("test", "test");
    }
    public Supplier(String username, String password) {
        super.setID(UUID.randomUUID().toString());
        this.username = username;
        this.password = password;
    }


    // Getter methods
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void add() throws Exception {
        // Implement the add method for admin
        String SupplierData = Database.encrypt(this.toArray());
        Database myDB = new Database("suppliers");
        myDB.appendText(SupplierData);
    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Supplier oldSupplier)){
            throw new Exception("Invalid Admin");
        }
        Database myDB = new Database("suppliers");
        String oldSupplierData = Database.encrypt(oldSupplier.toArray());
        String SupplierData = Database.encrypt(this.toArray());
        myDB.updateText(oldSupplierData,SupplierData);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Admin oldSupplier)){
            throw new Exception("Invalid Supplier");
        }
        Database myDB = new Database("suppliers");
        String oldSupplierData = Database.encrypt(oldSupplier.toArray());
        myDB.removeText(oldSupplierData);
    }

    @Override
    public boolean select() throws Exception {
        Database myDB = new Database("suppliers");
        ArrayList<String> myData = myDB.readText();
        for (int i = 0; i<myData.size(); i++) {
            ArrayList<String> users = Database.decrypt(myData.get(i));
            if(users.get(3).equals(this.username)) {
                super.setID(users.get(0));
                super.setName(users.get(1));
                super.setAge(Integer.parseInt(users.get(2)));
                this.setUsername(users.get(3));
                this.setPassword(users.get(4));
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
        data.add(Integer.toString(super.getAge()));
        data.add(this.username);
        data.add(this.password);
        return data;
    }
}
