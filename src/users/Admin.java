package users;

import Database.Database;

import java.util.ArrayList;
import java.util.UUID;

public class Admin extends User {

    private String username;
    private String password;

    // Constructor
    public Admin() {
        this("test", "test");
    }
    public Admin(String username, String password) {
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
        String adminData = Database.encrypt(this.toArray());
        Database myDB = new Database("admins");
        myDB.appendText(adminData);
    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Admin oldAdmin)){
            throw new Exception("Invalid Admin");
        }
        Database myDB = new Database("admins");
        String oldAdminData = Database.encrypt(oldAdmin.toArray());
        String adminData = Database.encrypt(this.toArray());
        myDB.updateText(oldAdminData, adminData);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Admin oldAdmin)){
            throw new Exception("Invalid Admin");
        }
        Database myDB = new Database("admins");
        String oldAdminData = Database.encrypt(oldAdmin.toArray());
        myDB.removeText(oldAdminData);
    }

    @Override
    public Object select(String username) throws Exception {
        ArrayList<Admin> myData = this.getAll();
        Admin data = new Admin();
        for (Admin admin: myData) {
            if(admin.getUsername().equals(username)) {
                data = admin;
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
    public ArrayList<Admin> getAll() throws Exception {
        ArrayList<Admin> data = new ArrayList<>();
        Database myDB = new Database("admins");
        ArrayList<String> admins = myDB.readText();
        for (String admin : admins) {
            Admin myAdmin = new Admin();
            ArrayList<String> adminData = Database.decrypt(admin);
            myAdmin.setID(adminData.get(0));
            myAdmin.setName(adminData.get(1));
            myAdmin.setAge(Integer.parseInt(adminData.get(2)));
            myAdmin.setUsername(adminData.get(3));
            myAdmin.setPassword(adminData.get(4));
            data.add(myAdmin);
        }
        return data;
    }
}