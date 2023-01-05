package Order;

import Customer.CustomerManagement;
import Product.Product;
import Product.ProductManagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Invoice {
    private String invoiceID;
    private String customerID;
    private String productID;
    private String cusName;
    private Date date;
    private Map<String, Integer> hashMapa;
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    ProductManagement productManagement = ProductManagement.getProductManagement();

    public Invoice(String invoiceID, String customerID) {
        this.invoiceID = invoiceID;
        this.customerID = customerID;
        this.date = new Date();
        this.hashMapa = new HashMap<>();
    }

    public Invoice(Date date, String invoiceID, String customerID) {
        this.invoiceID = invoiceID;
        this.customerID = customerID;
        this.date = date;
        this.hashMapa=new HashMap<>();
    }

    public Invoice() {
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Date getDate() {
        return this.date;
    }

    public Map<String, Integer> getHashMap() {
        return hashMapa;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void addProduct(String productID, Integer quantity) {
        getHashMap().put(productID, quantity);
    }

    public double getsubtotal(String productID, int quantity) {
        Product product = productManagement.searchById(productID);
        double result;
        double p = product.getPrice();
        result = p * quantity;
        return result;
    }

    public double getTotal() {
        double total = 0;
        for (String key : getHashMap().keySet()) {
            total += getsubtotal(key, getHashMap().get(key));
        }
        return total;
    }

    public String getProductInformation() {
        StringBuilder result = new StringBuilder();
        for (String key : hashMapa.keySet()) {
            result.append(ProductManagement.getProductManagement().searchById(key).getNameProduct()).append(",").append(ProductManagement.getProductManagement().searchById(key).getQuantity());
        }
        return result.toString();
    }

    public String stringCreatedDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(getDate());
    }

    @Override
    public String toString() {
        return "ID invoice " + invoiceID + "\n" +
                "Invoice Date " + stringCreatedDay() + "\n" +
                "customer ID " + customerManagement.searchById(getCustomerID()) + "\n" +
                "customer phone " + customerManagement.searchById(getCustomerID()).getPhone() + "\n" +
                getProductInformation() + "\n" +
                " Total " + getTotal();
    }

    public String toFile() {
        return stringCreatedDay() + "," + invoiceID + "," + customerID + "," + getProductInformation() + "," + getTotal();
    }
}
