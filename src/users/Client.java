package users;

import Database.Database;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.UUID;

public class Client extends User {

    private String username;
    private String password;
    private String email;

    // Constructor
    public Client() {
        this("test", "test", "test@gmail.com");
    }
    public Client(String username, String password, String email) {
        super.setID(UUID.randomUUID().toString());
        this.username = username;
        this.password = password;
        this.email = email;
    }


    // Getter methods
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    public String getEmail() {
        return this.email;
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
    public void setEmail(String email) {
        if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
            throw new InputMismatchException("Invalid email");
        this.email = email;
    }

    @Override
    public void add() throws Exception {
        // Implement the add method for admin
        String data = Database.encrypt(this.toArray());
        Database myDB = new Database("clients");
        myDB.appendText(data);
    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Client oldClient)){
            throw new Exception("Invalid Client");
        }
        Database myDB = new Database("clients");
        String oldData = Database.encrypt(oldClient.toArray());
        String data = Database.encrypt(this.toArray());
        myDB.updateText(oldData, data);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Client oldClient)){
            throw new Exception("Invalid Client");
        }
        Database myDB = new Database("clients");
        String oldData = Database.encrypt(oldClient.toArray());
        myDB.removeText(oldData);
    }

    @Override
    public Object select(String username) throws Exception {
        ArrayList<Client> myData = this.getAll();
        Client data = new Client();
        for (Client client: myData) {
            if(client.getUsername().equals(username)) {
                data = client;
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
        data.add(this.email);
        return data;
    }

    @Override
    public ArrayList<Client> getAll() throws Exception {
        ArrayList<Client> data = new ArrayList<>();
        Database myDB = new Database("clients");
        ArrayList<String> clients = myDB.readText();
        for (String client : clients) {
            Client myClient = new Client();
            ArrayList<String> adminData = Database.decrypt(client);
            myClient.setID(adminData.get(0));
            myClient.setName(adminData.get(1));
            myClient.setAge(Integer.parseInt(adminData.get(2)));
            myClient.setUsername(adminData.get(3));
            myClient.setPassword(adminData.get(4));
            myClient.setEmail(adminData.get(5));
            data.add(myClient);
        }
        return data;
    }
}