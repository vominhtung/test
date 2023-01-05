package Order;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceManagement {
    private static InvoiceManagement invoiceManagement = new InvoiceManagement();

    public static InvoiceManagement getInvoiceManagement() {
        return invoiceManagement;
    }

    List<Invoice> invoiceManagementList;

    public InvoiceManagement() {
        invoiceManagementList = new ArrayList<>();
    }

    public void add(Invoice i) {
        invoiceManagementList.add(i);
        saveToFile();
    }

    public Invoice searchById(String id) {
        for (Invoice i : invoiceManagementList) {
            if (i.getInvoiceID().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public String toString() {
        String result = "";
        for (Invoice i : invoiceManagementList) {
            result += i + "\n";
        }
        return result;
    }

    public boolean remove(String id) {
        Invoice i = searchById(id);
        if (i != null) {
            invoiceManagementList.remove(i);
            return true;
        } else
            return false;
    }

    public Invoice handle(String line) throws ParseException {
        String[] strings = line.split(",");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Invoice invoice = new Invoice(simpleDateFormat.parse(strings[0]), strings[1], strings[2]);
        for (int i = 3; i <= strings.length ; i+=2) {
            invoice.addProduct(strings[i], Integer.parseInt(strings[i + 1]));
        }
        return invoice;
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter("invoice.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Invoice i : invoiceManagementList) {
                bufferedWriter.write(i.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFormFile() {
        invoiceManagementList.clear();
        try {
            FileReader fileReader = new FileReader("invoice.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Invoice invoice = handle(line);
                invoiceManagementList.add(invoice);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        System.out.println(invoiceManagementList);
    }
}
