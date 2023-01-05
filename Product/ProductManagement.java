package Product;

import Customer.Customer;

import javax.imageio.IIOException;
import java.io.*;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductManagement {
    private static ProductManagement productManagement;

    static {
        try {
            productManagement = new ProductManagement();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductManagement getProductManagement() {
        return productManagement;
    }

    private List<Product> productList;

    public ProductManagement() throws ParseException {
        productList = new ArrayList<>();
        readFormFile();
    }

    public void add(Product p) {
        productList.add(p);
        saveToFile();
    }

    public Product searchById(String id) {
        Product product = searchById(id);
        for (Product p : productList) {
            if (p.getId().equals(id)) {
                product = p;

            }
        }
        return product;
    }

    public boolean remove(String id) {

        Product p = searchById(id);
        if (p != null) {
            productList.remove(p);
            saveToFile();
            return true;

        }
        return false;
    }

    public List<Product> searchByName(String name) {
        saveToFile();
        List<Product> products = new ArrayList<>();
        for (Product p : productList) {
            if (p.getNameProduct().equals(name)) {
                products.add(p);
            }
        }
        return products;
    }

    public void updatePro(String id, int quantity, double price) {

        Product product = searchById(id);
        if (product != null) {
            product.setQuantity(quantity);
            product.setPrice(price);
            saveToFile();
        }
    }

    public void productSortList() {
        ProductComparator productComparator = new ProductComparator();
        productList.sort(productComparator);
        System.out.println(productList);
        saveToFile();

    }

    public boolean sameIdProduct(String id, int quantiyu) {
        Product product = searchById(id);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantiyu);
            return true;
        } else
            return false;
    }

    public String getNameProductFormId(String id) {
        return ProductManagement.getProductManagement().searchById(id).getNameProduct();
    }

    public Product handle(String line) throws ParseException, ArrayIndexOutOfBoundsException {
        String[] strings = line.split(",");
        return new Product(strings[0], strings[1], strings[2], Integer.parseInt(strings[3]), Double.parseDouble(strings[4]), strings[5]);
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter("product.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Product p : productList) {
                bufferedWriter.write(p.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFormFile() {
        try {
            FileReader fileReader = new FileReader("product.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Product product = handle(line);
                productList.add(product);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        System.out.println(productList);
    }

    public String toString() {
        String result = "";
        for (Product p : productList) {
            result += p + "\n";
        }
        return result;
    }
}