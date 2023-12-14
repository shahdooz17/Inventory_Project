package users;

import Database.Database;

import java.util.ArrayList;
import java.util.UUID;

public class Supplier extends User {
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

    public void setUsername(String username) throws Exception {
        if(username.isEmpty()) {
            throw new Exception("invalid username");
        }
        this.username = username;
    }

    public void setPassword(String password) throws Exception {
        if(password.isEmpty()) {
            throw new Exception("invalid password");
        }
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
    public Object select(String username) throws Exception {
        ArrayList<Supplier> myData = this.getAll();
        Supplier data = new Supplier();
        for (Supplier supplier: myData) {
            if(supplier.getUsername().equals(username)) {
                data = supplier;
            }
        }
        return data;
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
    @Override
    public ArrayList<Supplier> getAll() throws Exception {
        ArrayList<Supplier> data = new ArrayList<>();
        Database myDB = new Database("suppliers");
        ArrayList<String> suppliers = myDB.readText();
        for (String admin : suppliers) {
            Supplier mySupplier = new Supplier();
            ArrayList<String> supplierData = Database.decrypt(admin);
            mySupplier.setID(supplierData.get(0));
            mySupplier.setName(supplierData.get(1));
            mySupplier.setAge(Integer.parseInt(supplierData.get(2)));
            mySupplier.setUsername(supplierData.get(3));
            mySupplier.setPassword(supplierData.get(4));
            data.add(mySupplier);
        }
        return data;
    }
}
