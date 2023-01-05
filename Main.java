import Customer.Customer;
import Customer.CustomerManagement;
import Customer.CustomerManagementMenu;
import Order.InvoiceManagementMenu;
import Product.ProductManagementMenu;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        CustomerManagementMenu customerManagementMenu = new CustomerManagementMenu();
        ProductManagementMenu productManagementMenu = new ProductManagementMenu();
        InvoiceManagementMenu invoiceManagementMenu = new InvoiceManagementMenu();
        while (choice != 0) {
            System.out.println("1.Customer");
            System.out.println("2.Product");
            System.out.println("3.Invoice");
            System.out.println("0.Exit");
            System.out.println("enter choice");
            choice = scanner.nextInt();scanner.nextLine();
            switch (choice) {
                case 1:
                    customerManagementMenu.menu();
                    break;
                case 2:
                    productManagementMenu.menu();
                    break;
                case 3:
                    invoiceManagementMenu.menu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("nhap lai");
                    break;
            }
        }
    }
}