package Product;

import java.text.ParseException;
import java.util.Scanner;

public class ProductManagementMenu {
    ProductManagement productManagement = ProductManagement.getProductManagement();
    Scanner scanner = new Scanner(System.in);

    public void displaymenu() {
        System.out.println("PRODUCT MENU");
        System.out.println("1. add product limited");
        System.out.println("2. remove product");
        System.out.println("3. search products by name");
        System.out.println("4. search product by id");
        System.out.println("5. change product information");
        System.out.println("6. display products");
        System.out.println("7. save to file");
        System.out.println("8. read form file");
        System.out.println("9. sort product by price ");
        System.out.println("0. exit");
    }

    public void menu() throws ParseException {
        int choice = -1;
        while (choice != 0) {
            displaymenu();
            System.out.println("enter choice");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    searchByName();
                    break;
                case 4:
                    searchById();
                    break;
                case 5:
                    update();
                    break;
                case 6:
                    display();
                    break;
                case 7:
                    productManagement.saveToFile();
                    break;
                case 8:
                    productManagement.readFormFile();
                    break;
                case 9:
                    productManagement.productSortList();
                default:
                    break;
            }
        }
    }

    public void add() throws ParseException {
        System.out.println("Enter product ID");
        String id = scanner.nextLine();
        while (productManagement.searchById(id) != null) {
            System.out.println(" product: " + productManagement.getNameProductFormId(id) + " had amount of "+productManagement.searchById(id).getQuantity()+" enter amount of quantity want to add");
            int q=scanner.nextInt();scanner.nextLine();
            productManagement.sameIdProduct(id,q);
            System.out.println("enter new Id or old to add more");
            id = scanner.nextLine();
        }
        System.out.println("Enter product name");
        String name = scanner.nextLine();
        System.out.println("Enter product type");
        String type = scanner.nextLine();
        System.out.println("Enter quantity");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter product price");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("enter Product limited Date");
        String limited = scanner.next();
        Product newProduct = new Product(id, name, type, quantity, price, limited);
        productManagement.add(newProduct);

    }

    public void remove() {
        System.out.println("Enter product ID");
        String id = scanner.nextLine();
        if (productManagement.remove(id)) {
            System.out.println("remove finish");
        } else {
            System.out.println("not found ID to remove");
        }
    }

    public void searchByName() {
        System.out.println("Enter products name");
        String name = scanner.nextLine();
        System.out.println(productManagement.searchByName(name));
    }

    public void searchById() {
        System.out.println("Enter product ID");
        String id = scanner.nextLine();
        productManagement.searchById(id);
    }

    public void update() {
        System.out.println("Enter products ID");
        String id = scanner.nextLine();
        if (productManagement.searchById(id) == null) {
            System.out.println("ID not found enter again");
            id = scanner.nextLine();
        }
        System.out.println("enter new quantity");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("enter new price");
        double price = scanner.nextDouble();
        scanner.nextLine();
        productManagement.updatePro(id, quantity, price);
    }

    public void display() {
        System.out.println(productManagement.toString());
    }
}
