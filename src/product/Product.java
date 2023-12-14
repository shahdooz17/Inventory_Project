package product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import Database.InterFace;
import Database.Database;
import Admin.Category;
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
        if(!name.matches("^[a-zA-Z]+$"))
            throw new Exception("Invalid name");
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) throws Exception {
        if(category.isEmpty()) {
            throw new Exception("invalid category");
        }
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) throws Exception {
        if(price < 0) {
            throw new Exception("invalid price");
        }
        this.price = price;
    }

    public String getProductionDate() {
        return this.productionDate;
    }

    public void setProductionDate(String productionDate) throws Exception {
        if(!productionDate.matches("^\\d{1,2}\\-\\d{1,2}\\-\\d{3,4}$")) {
            throw new Exception("invalid Date");
        }
        this.productionDate = productionDate;
    }

    public String getExpirationDate() throws Exception {

        if(!productionDate.matches("^\\d{1,2}\\-\\d{1,2}\\-\\d{3,4}$")) {
            throw new Exception("invalid Date");
        }
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
    public Object select(String id) throws Exception {
        ArrayList<Product> myData = this.getAll();
        Product data = new Product();
        for (Product oneData: myData) {
            if(oneData.getID().equals(id)) {
                data = oneData;
            }
        }
        return data;
    }

    public ArrayList<Product> filter(String key, String value) throws Exception {
        ArrayList<Product> myData = this.getAll();
        ArrayList<Product> myReturnData = new ArrayList<>();
        switch(key) {
            case "name" -> {
                for (Product oneData : myData) {
                    if (oneData.getName().equals(value)) {
                        myReturnData.add(oneData);
                    }
                }
            }
            case "production" -> {
                for (Product oneData : myData) {
                    if (oneData.getProductionDate().equals(value)) {
                        myReturnData.add(oneData);
                    }
                }
            }
            case "expiration" -> {
                for (Product oneData : myData) {
                    if (oneData.getExpirationDate().equals(value)) {
                        myReturnData.add(oneData);
                    }
                }
            }
            case "categories" -> {
                for (Product oneData : myData) {
                    Category myCat = new Category();
                    myCat.select(oneData.getCategory());
                    if (myCat.getName().equals(value)) {
                        myReturnData.add(oneData);
                    }
                }
            }
        }
        return myReturnData;
    }
    @Override
    public ArrayList<Product> getAll() throws Exception {
        ArrayList<Product> data = new ArrayList<>();
        Database myDB = new Database("products");
        ArrayList<String> allData = myDB.readText();
        for (String oneData : allData) {
            Product myData = new Product();
            ArrayList<String> decryptedData = Database.decrypt(oneData);
            myData.setID(decryptedData.get(0));
            myData.setName(decryptedData.get(1));
            myData.setCategory(decryptedData.get(2));
            myData.setPrice(Double.parseDouble(decryptedData.get(3)));
            myData.setProductionDate(decryptedData.get(4));
            myData.setExpirationDate(decryptedData.get(5));
            myData.setQuantity(Integer.parseInt(decryptedData.get(6)));
            data.add(myData);
        }
        return data;
    }
    public boolean isNearExpiration(String daysBeforeExpiration) throws ParseException {
        long currentTime = System.currentTimeMillis();
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(daysBeforeExpiration);
        long expirationTime = date1.getTime();
        long difference = expirationTime - currentTime;
        long daysDifference = difference / (1000 * 60 * 60 * 24);

        // Adjust the condition to check if the daysDifference is less than or equal to the specified threshold
        int daysThreshold = 7; // Change this value to your desired threshold
        return daysDifference <= daysThreshold;
    }
}
