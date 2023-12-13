package product;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import Database.InterFace;
import Database.Database;
import users.Admin;

public class Product implements InterFace {
    private String id;
    private String name;
    private String category;
    private double price;
    private String productionDate;
    private String expirationDate;
    private int quantity;

    public Product() {
        this("test", "test", 0, LocalDate.now().toString(), LocalDate.now().toString(), 0);
    }

    public Product(String name, String category, double price, String productionDate, String expirationDate, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.category = category;
        this.price = price;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }


    public String getID() {
        return this.id;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws Exception {
        if(!name.matches("[a-zA-Z]+"))
            throw new Exception("Invalid name");
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getProductionDate() {
        return this.productionDate;
    }

    public void setProductionDate(String productionDate){
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate){
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public void add() throws Exception {
        String poductData = Database.encrypt(this.toArray());
        Database myDB = new Database("products");
        myDB.appendText(poductData);

    }

    @Override
    public void update(Object oldObj) throws Exception {
        if(!(oldObj instanceof Product oldProduct)){
            throw new Exception("Invalid Products");
        }
        Database myDB = new Database("products");
        String oldProductData = Database.encrypt(oldProduct.toArray());
        String productData = Database.encrypt(this.toArray());
        myDB.updateText(oldProductData, productData);
    }

    @Override
    public void delete(Object oldObj) throws Exception {
        if(!(oldObj instanceof Product oldProduct)){
            throw new Exception("Invalid Products");
        }
        Database myDB = new Database("products");
        String oldProductData = Database.encrypt(oldProduct.toArray());
        myDB.removeText(oldProductData);
    }

    @Override
    public ArrayList<String> toArray() {
        ArrayList<String> myProduct = new ArrayList<>();
        myProduct.add(this.id);
        myProduct.add(this.name);
        myProduct.add(this.category);
        myProduct.add(Double.toString(this.price));
        myProduct.add(this.productionDate);
        myProduct.add(this.expirationDate);
        myProduct.add(Integer.toString(this.quantity));
        return myProduct;
    }
    @Override
    public boolean select() throws Exception {
        Database myDB = new Database("products");
        ArrayList<String> myData = myDB.readText();
        for (int i = 0; i<myData.size(); i++) {
            ArrayList<String> products = Database.decrypt(myData.get(i));
            if(products.get(0).equals(this.id)) {
                this.setID(products.get(0));
                this.setName(products.get(1));
                this.setCategory(products.get(2));
                this.setPrice(Double.parseDouble(products.get(3)));
                this.setProductionDate(products.get(4));
                this.setExpirationDate(products.get(5));
                this.setQuantity(Integer.parseInt(products.get(6)));
                return true;
            }
        }
        return false;
    }
}
