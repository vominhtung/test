package Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    public int compare(Product o1, Product o2) {
        if (o1.getPrice() == 0) {
            return 1;
        }
        if (o2.getPrice() == 0) {
            return -1;
        }
        if (o1.getPrice()==(o2.getPrice())) {
            return 0;
        }
        return Double.compare( o1.getPrice(),o2.getPrice());

    }
}