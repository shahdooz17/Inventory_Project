package users;

import Database.Database;

import java.util.ArrayList;
import java.util.UUID;

public class Client extends User{
    private String username;
    private String password;
    private String email;

    // Constructor
    public Client() {
        this("test", "test");
    }
    public Client(String username, String password) {
        super.setID(UUID.randomUUID().toString());
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String password) {this.email=email;}

    @Override
    public void add() throws Exception {
        String clientData = Database.encrypt(this.toArray());
        Database myDB = new Database("clients");
        myDB.appendText(clientData);
    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Admin oldClient)){
            throw new Exception("Invalid Client");
        }
        Database myDB = new Database("clients");
        String oldClientData = Database.encrypt(oldClient.toArray());
        String clientData = Database.encrypt(this.toArray());
        myDB.updateText(oldClientData, clientData);

    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Client oldClient)){
            throw new Exception("Invalid Client");
        }
        Database myDB = new Database("clients");
        String oldClientData = Database.encrypt(oldClient.toArray());
        myDB.removeText(oldClientData);
    }

    @Override
    public boolean select() throws Exception {
        Database myDB = new Database("clients");
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