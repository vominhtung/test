package Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagement {
    private static CustomerManagement customerManagement = new CustomerManagement();

    public static CustomerManagement getCustomerManagement() {
        return customerManagement;
    }

    List<Customer> customerList;

    public CustomerManagement() {
        customerList = new ArrayList<>();
        readFormFile();
    }

    public void customerSortList() {
        CustomerComparator customerComparator = new CustomerComparator();
        customerList.sort(customerComparator);
        System.out.println(customerList + "\n");
        saveToFile();
    }

    public void addCus(Customer c) {
        customerList.add(c);
        saveToFile();
    }

    public Customer searchById(String id) {
        for (Customer c : customerList) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public boolean remove(String id) {
        Customer c = searchById(id);
        if (c != null) {
            customerList.remove(c);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Customer> searchByName(String name) {
        List<Customer> customerList1 = new ArrayList<>();
        for (Customer c : customerList) {
            if (c.getName().equals(name)) {
                customerList1.add(c);
            }
        }
        return customerList1;
    }

    public String toString() {
        saveToFile();
        String result = "";
        for (Customer c : customerList) {
            result += c + "\n";
        }
        return result;
    }

    public void updateCus(String id, String name, String adress) {
        Customer customer = searchById(id);
        if (customer != null) {
            customer.setName(name);
            customer.setAdress(adress);
        }
        saveToFile();
    }

    public Customer handleLine(String line) {
        String[] strings = line.split(",");
        return new Customer(strings[0], strings[1], strings[2], strings[3]);
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter("customer.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Customer c : customerList) {
                bufferedWriter.write(c.tofile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFormFile() {
        customerList.clear();
        try {
            FileReader fileReader = new FileReader("customer.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Customer customer = handleLine(line);
                customerList.add(customer);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(customerList);
    }

}