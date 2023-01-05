package Customer;

import java.util.Scanner;

public class CustomerManagementMenu {
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("CUSTOMER MENU");
        System.out.println("1. Add Customer");
        System.out.println("2. Remove Customer");
        System.out.println("3. Search Customer by ID");
        System.out.println("4. Search Customers by Name");
        System.out.println("5. Display list Customer");
        System.out.println("6. Save to file CSV");
        System.out.println("7. Read form file CSV");
        System.out.println("8. fix customer information");
        System.out.println("9. sort customer list decease");
        System.out.println("0. eixt customer menu");

    }

    public void menu() {
        int choice = -1;
        while (choice != 0) {
            displayMenu();
            System.out.println("Enter choice number");
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
                    searchById();
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    displayAll();
                    break;
                case 6:
                    customerManagement.saveToFile();
                    break;
                case 7:
                    customerManagement.readFormFile();
                    break;
                case 8:
                    fix();
                    break;
                case 9:
                    customerManagement.customerSortList();
                case 0:
                    break;
                default:
                    break;
            }
        }
    }

    public void add() {
        displayAll();
        System.out.println(" customer ID");
        String id = scanner.nextLine();
        while (CustomerManagement.getCustomerManagement().searchById(id) != null) {
            System.out.println("ID alredy exit.Enter again");
            id = scanner.nextLine();
        }
        System.out.println("customer name");
        String name = scanner.nextLine();
        System.out.println("customer phone");
        String phone = scanner.nextLine();
        phone = this.checkPhoneNumber(phone);
        System.out.println("customer adress");
        String adress = scanner.nextLine();
        Customer newCustomer = new Customer(id, name, phone, adress);
        customerManagement.addCus(newCustomer);
    }

    public void remove() {
        displayAll();
        System.out.println(" customer ID");
        String id = scanner.nextLine();
        if (customerManagement.remove(id)) {
            System.out.println("Customer already remove ");
        } else {
            System.out.println("not found ID to remove");
        }
    }

    public void searchById() {
        displayAll();
        System.out.println(" customer ID");
        String id = scanner.nextLine();
        System.out.println(customerManagement.searchById(id));
    }

    public void searchByName() {
        displayAll();
        System.out.println("Customer name");
        String name = scanner.nextLine();
        System.out.println(customerManagement.searchByName(name));
    }

    public void displayAll() {
        System.out.println(customerManagement.toString());
    }

    public void fix() {
        displayAll();
        System.out.println(" customer ID");
        String id = scanner.nextLine();

        System.out.println("Enter new Name");
        String name = scanner.nextLine();
        System.out.println("Enter new adress");
        String adress = scanner.nextLine();
        customerManagement.updateCus(id, name, adress);

    }

    private String checkPhoneNumber(String phoneNumber) {
        for (boolean isPhoneNumber = checkValidate(phoneNumber); !isPhoneNumber; isPhoneNumber = this.checkValidate(phoneNumber)) {
            System.out.println("Số điện thoại nhập chưa đúng định dạng");
            System.out.println("Vui lòng nhập lại theo hướng dẫn sau: ");
            System.out.println("VD: 0Yxxxxxxxx hoặc 0Yxxxxxxxxx");
            System.out.println("Với Y: là các số 9,8,7,3,2,6");
            System.out.println("Với x: gồm 8 hoặc 9 chữ số từ 0-9");
            phoneNumber = this.scanner.nextLine();
        }

        return phoneNumber;
    }

    private boolean checkValidate(String regex) {
        ValidPhone validatePhone = new ValidPhone();
        return validatePhone.validate(regex);
    }
}