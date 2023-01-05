package Customer;

public class Customer {
    private String name;
    private String id;
    private String adress;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Customer(String id, String name, String phone, String adress) {
        this.name = name;
        this.id = id;
        this.adress = adress;
        this.phone = phone;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String tofile() {
        return id + "," + name + "," + phone + "," + adress;
    }
}
