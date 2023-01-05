package Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private String id;
    private String nameProduct;
    private String type;
    private int quantity;
    private double price;
    private String limitedDate;

    public Product(){}

    public String getLimitedDate() {
        return limitedDate;
    }

    public void setLimitedDate(String limitedDate) {
        this.limitedDate = limitedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(String id, String nameProduct, String type, int quantity, double price) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }
    public Product(String id, String nameProduct, String type, int quantity, double price, String limitedDate) throws ParseException {
        this.id = id;
        this.nameProduct = nameProduct;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.limitedDate = limitedDate;
        checkvalidDate();
    }

    public boolean checkvalidDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        simpleDateFormat.format(date);
        if (simpleDateFormat.parse(String.valueOf(limitedDate)).getTime() <= date.getTime()) {

            return false;
        } else

            return true;
    }
    public String stringCreatedDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(getLimitedDate());
    }
    @Override
    public String toString() {

        return "Product{" +
                "id='" + id + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", limited date=" + getLimitedDate() +
                '}';
    }

    public String toFile() {
        return id + "," + nameProduct + "," + type + "," + quantity + "," + price+","+limitedDate;
    }
}
