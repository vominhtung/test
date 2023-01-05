package Order;

import Customer.CustomerManagement;
import Product.Product;
import Product.ProductManagement;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class InvoiceManagementMenu {
    InvoiceManagement invoiceManagement = InvoiceManagement.getInvoiceManagement();
    ProductManagement productManagement = ProductManagement.getProductManagement();
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("Invoice Menu");
        System.out.println("1. add invoice");
        System.out.println("2. remove invoice ");
        System.out.println("3. search invoice ID");
        System.out.println("4. display All");
        System.out.println("5. save to file");
        System.out.println("6. read form file");
        System.out.println("0. exit");
    }

    public void menu() {
        int choice = -1;
        while (choice != 0) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    try {
                        add();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> remove();
                case 3 -> searchID();
                case 4 -> dislayall();
                case 5 -> invoiceManagement.saveToFile();
                case 6 -> invoiceManagement.readFormFile();
                case 0 -> System.exit(0);
                default -> {break;}
            }
        }
    }

    public void add() throws ParseException {
        System.out.println("Enter Invoice Id");
        String invoiceId = scanner.nextLine();
        System.out.println("enter customer Id");
        String idcus = scanner.nextLine();
        while (customerManagement.searchById(idcus) == null) {
            System.out.println("Id not found");
            idcus = scanner.nextLine();
        }
        Date createdDay = new Date();
        Invoice newInvoice = new Invoice(createdDay, invoiceId, idcus);
        int choice = -1;
        while (choice != 0) {
            System.out.println("Enter product Id");
            String proId = scanner.nextLine();
            while (!productManagement.searchById(proId).checkvalidDate()) {
                System.out.println("limited time over.choice something else");
                proId = scanner.nextLine();
            }
            System.out.println("Enter quantity");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            Product p = productManagement.searchById(proId);
            if (p.getQuantity() - quantity > 0) {
                p.setQuantity(p.getQuantity() - quantity);
            } else {
                System.out.println("there only " + p.getQuantity() + " left");
            }
            newInvoice.addProduct(proId, quantity);
            System.out.println("1.add more product");
            System.out.println("0.exit");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        invoiceManagement.add(newInvoice);
    }


    public void remove() {
        System.out.println("enter invoice ID need to remove");
        String ivoiceId = scanner.nextLine();
        if (invoiceManagement.remove(ivoiceId)) {
            System.out.println("remove");
        } else
            System.out.println("not found id invoice need romive");
    }

    public void searchID() {
        System.out.println("Enter invoice ID");
        String invoiceID = scanner.nextLine();
        System.out.println(invoiceManagement.searchById(invoiceID));
    }

    public void dislayall() {
        System.out.println(invoiceManagement.toString());
    }

}
