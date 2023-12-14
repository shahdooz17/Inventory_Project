package Admin;

import Database.Database;
import Database.InterFace;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.UUID;

public class Offer implements InterFace {


    private String product;
    private String id;
    private String startDate;
    private String endDate;
    private int percent;
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
    public String getstartDate() {
        return this.startDate;
    }
    public void setstartDate(String startDate) throws Exception {

        if(!startDate.matches("^\\d{1,2}\\-\\d{1,2}\\-\\d{3,4}$")) {
            throw new Exception("invalid Date");
        }
        this.startDate = startDate;
    }
    public String getendDate() {
        return this.endDate;
    }
    public void setendDate(String endDate) throws Exception {
        if(!endDate.matches("^\\d{1,2}\\-\\d{1,2}\\-\\d{3,4}$")) {
            throw new Exception("invalid Date");
        }
        this.endDate = endDate;
    }
    public int getPercent() {

        return this.percent;
    }
    public void setPercent(int percent) throws Exception {

        if(percent <0 || percent>100) {
            throw new Exception("invalid percent");
        }this.percent = percent;
    }

    // Constructor
    public Offer() {
        this("test", "00-00-0000","00-00-0000");
    }
    public Offer(String product, String startDate, String endDate) {
        this.setID(UUID.randomUUID().toString());
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    @Override
    public void add() throws Exception {
        String catData = Database.encrypt(this.toArray());
        Database myDB = new Database("offer");
        myDB.appendText(catData);

    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Offer oldoffer)){
            throw new Exception("Invalid offer");
        }
        Database myDB = new Database("offer");
        String oldoldoffer = Database.encrypt(oldoffer.toArray());
        String CategoryData = Database.encrypt(this.toArray());
        myDB.updateText(oldoldoffer, CategoryData);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Offer oldoffer)){
            throw new Exception("Invalid offer");
        }
        Database myDB = new Database("offer");
        String oldCategoryData = Database.encrypt(oldoffer.toArray());
        myDB.removeText(oldCategoryData);
    }

    @Override
    public ArrayList<String> toArray() {
        ArrayList<String> myOffer = new ArrayList<>();
        myOffer.add(this.id);
        myOffer.add(this.product);
        myOffer.add(this.startDate);
        myOffer.add(this.endDate);
        myOffer.add(Integer.toString(this.percent));
        return myOffer;
    }
    @Override
    public Object select(String id) throws Exception {
        ArrayList<Offer> myData = this.getAll();
        Offer data = new Offer();
        for (Offer oneData: myData) {
            if(oneData.getID().equals(id)) {
                data = oneData;
            }
        }
        return data;
    }

    @Override
    public ArrayList<Offer> getAll() throws Exception {
        ArrayList<Offer> data = new ArrayList<>();
        Database myDB = new Database("offer");
        ArrayList<String> allData = myDB.readText();
        for (String oneData : allData) {
            Offer myData = new Offer();
            ArrayList<String> decryptedData = Database.decrypt(oneData);
            myData.setID(decryptedData.get(0));
            myData.setproduct(decryptedData.get(1));
            myData.setstartDate(decryptedData.get(2));
            myData.setendDate(decryptedData.get(3));
            myData.setPercent(Integer.parseInt(decryptedData.get(4)));
            data.add(myData);
        }
        return data;
    }

    public String isProductHasOffer(String id) throws Exception {
        ArrayList<Offer> myData = this.getAll();
        String data = "";
        for (Offer oneData: myData) {
            if(oneData.getproduct().equals(id)) {
                data = oneData.getID();
                break;
            }
        }
        return data;
    }
}
