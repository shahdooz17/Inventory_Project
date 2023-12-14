package product;

import Admin.Offer;
import Database.Database;
import Database.InterFace;

import java.util.ArrayList;
import java.util.UUID;

public class Order implements InterFace {
    private String product;
    private String id;
    private String client;
    private String bill;
    private String status;
    public String getID() {
        return this.id;
    }
    public void setID(String id) {
        this.id = id;
    }
    public String getproduct() {
        return this.product;
    }
    public void setproduct(String product) throws Exception {

        if(product.isEmpty()) {
            throw new Exception("invalid product");
        }
        this.product = product;
    }
    public String getClient() {
        return this.client;
    }
    public void setClient(String client) throws Exception {

        if(client.isEmpty()) {
            throw new Exception("invalid client");
        }this.client = client;
    }
    public String getBill() {
        return this.bill;
    }
    public void setBill(String bill) throws Exception {

        if(bill.isEmpty()) {
            throw new Exception("invalid bill");
        }this.bill = bill;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) throws Exception {

        if(status.isEmpty()) {
            throw new Exception("invalid Status");
        }this.status = status;
    }

    // Constructor
    public Order() {
        this("test", "test","none", "pending");
    }
    public Order(String product, String client, String bill, String status) {
        this.setID(UUID.randomUUID().toString());
        this.product = product;
        this.client = client;
        this.bill = bill;
        this.status = status;
    }



    @Override
    public void add() throws Exception {
        String catData = Database.encrypt(this.toArray());
        Database myDB = new Database("order");
        myDB.appendText(catData);

    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Order oldObjInher)){
            throw new Exception("Invalid order");
        }
        Database myDB = new Database("order");
        String oldoldobj = Database.encrypt(oldObjInher.toArray());
        String objjnew = Database.encrypt(this.toArray());
        myDB.updateText(oldoldobj, objjnew);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Order oldObjInher)){
            throw new Exception("Invalid order");
        }
        Database myDB = new Database("order");
        String oldoldobj = Database.encrypt(oldObjInher.toArray());
        myDB.removeText(oldoldobj);
    }

    @Override
    public ArrayList<String> toArray() {
        ArrayList<String> objjj = new ArrayList<>();
        objjj.add(this.id);
        objjj.add(this.product);
        objjj.add(this.client);
        objjj.add(this.bill);
        objjj.add(this.status);
        return objjj;
    }
    @Override
    public Object select(String id) throws Exception {
        ArrayList<Order> myData = this.getAll();
        Order data = new Order();
        for (Order oneData: myData) {
            if(oneData.getID().equals(id)) {
                data = oneData;
            }
        }
        return data;
    }

    @Override
    public ArrayList<Order> getAll() throws Exception {
        ArrayList<Order> data = new ArrayList<>();
        Database myDB = new Database("order");
        ArrayList<String> allData = myDB.readText();
        for (String oneData : allData) {
            Order myData = new Order();
            ArrayList<String> decryptedData = Database.decrypt(oneData);
            myData.setID(decryptedData.get(0));
            myData.setproduct(decryptedData.get(1));
            myData.setClient(decryptedData.get(2));
            myData.setBill(decryptedData.get(3));
            myData.setStatus(decryptedData.get(4));
            data.add(myData);
        }
        return data;
    }

    public ArrayList<Order> getClientOrders(String id) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Order> data = this.getAll();
        for (Order order: data) {
            if(order.getClient().equals(id)) {
                orders.add(order);
            }
        }
        return orders;
    }

    public static void approveOrder(String id) throws Exception {
        Order myOrder = new Order();
        myOrder = (Order) myOrder.select(id);
        if(myOrder.getproduct().equals("test")) {
            throw new Exception("Invalid order");
        }
        if(myOrder.getStatus().equals("approved")){
            return;
        }
        myOrder.setStatus("approved");
        Product myP = new Product();
        myP = (Product) myP.select(myOrder.getproduct());
        Offer myOffer = new Offer();
        if(!myOffer.isProductHasOffer(myP.getID()).isEmpty()) {
            myOffer = (Offer) myOffer.select(myOffer.isProductHasOffer(myP.getID()));
            myP.setPrice(myP.getPrice() - (myP.getPrice() * ((double) myOffer.getPercent() /100)));
        }
        Bill myBill = new Bill(myOrder.getproduct(), myOrder.getClient(), myP.getPrice());
        Database myDB = new Database("totalEarning");
        ArrayList<String> totalEarning = myDB.readText();
        double nowPrice=Double.parseDouble(totalEarning.get(0));
        nowPrice += myP.getPrice();
        myDB.writeFile(
                Double.toString(nowPrice));
        myBill.add();
        myOrder.setBill(myBill.getID());
        Order myOldOrder = new Order();
        myOldOrder = (Order) myOldOrder.select(myOrder.getID());
        myOrder.update(myOldOrder);
    }
}