package product;

import Database.Database;
import Database.InterFace;

import java.util.ArrayList;
import java.util.UUID;

public class Bill implements InterFace {
    private String product;
    private String id;
    private String client;
    private double total;
    public String getID() {
        return this.id;
    }
    public void setID(String id) {
        this.id = id;
    }
    public String getproduct() {
        return this.product;
    }
    public void setproduct(String product) {
        this.product = product;
    }
    public String getClient() {
        return this.client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public double getTotal() {
        return this.total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    // Constructor
    public Bill() {
        this("test", "test",0.0);
    }
    public Bill(String product, String client, double total) {
        this.setID(UUID.randomUUID().toString());
        this.product = product;
        this.client = client;
        this.total = total;
    }



    @Override
    public void add() throws Exception {
        String catData = Database.encrypt(this.toArray());
        Database myDB = new Database("bill");
        myDB.appendText(catData);

    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Bill oldObjInher)){
            throw new Exception("Invalid bill");
        }
        Database myDB = new Database("bill");
        String oldoldobj = Database.encrypt(oldObjInher.toArray());
        String objjnew = Database.encrypt(this.toArray());
        myDB.updateText(oldoldobj, objjnew);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Bill oldObjInher)){
            throw new Exception("Invalid bill");
        }
        Database myDB = new Database("bill");
        String oldoldobj = Database.encrypt(oldObjInher.toArray());
        myDB.removeText(oldoldobj);
    }

    @Override
    public ArrayList<String> toArray() {
        ArrayList<String> objjj = new ArrayList<>();
        objjj.add(this.id);
        objjj.add(this.product);
        objjj.add(this.client);
        objjj.add(Double.toString(this.total));
        return objjj;
    }
    @Override
    public Object select(String id) throws Exception {
        ArrayList<Bill> myData = this.getAll();
        Bill data = new Bill();
        for (Bill oneData: myData) {
            if(oneData.getID().equals(id)) {
                data = oneData;
            }
        }
        return data;
    }

    @Override
    public ArrayList<Bill> getAll() throws Exception {
        ArrayList<Bill> data = new ArrayList<>();
        Database myDB = new Database("bill");
        ArrayList<String> allData = myDB.readText();
        for (String oneData : allData) {
            Bill myData = new Bill();
            ArrayList<String> decryptedData = Database.decrypt(oneData);
            myData.setID(decryptedData.get(0));
            myData.setproduct(decryptedData.get(1));
            myData.setClient(decryptedData.get(2));
            myData.setTotal(Double.parseDouble(decryptedData.get(3)));
            data.add(myData);
        }
        return data;
    }
}