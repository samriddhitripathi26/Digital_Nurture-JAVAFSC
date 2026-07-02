package Week1.DSA.EcommerceSearchFunction;

import java.util.Arrays;
import java.util.Comparator;

public class SearchDemo {

    public static Product linearSearch(Product[] products, String name) {

        for (Product p : products) {
            if (p.productName.equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String name) {

        int low = 0;
        int high = products.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int cmp = products[mid].productName.compareToIgnoreCase(name);

            if (cmp == 0)
                return products[mid];

            else if (cmp < 0)
                low = mid + 1;

            else
                high = mid - 1;
        }

        return null;
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(1, "Laptop", "Electronics"),
                new Product(2, "Mobile", "Electronics"),
                new Product(3, "Shoes", "Fashion"),
                new Product(4, "Watch", "Accessories")
        };

        Product p1 = linearSearch(products, "Shoes");

        if (p1 != null)
            System.out.println("Linear Search Found: " + p1.productName);

        Arrays.sort(products,
                Comparator.comparing(product -> product.productName));

        Product p2 = binarySearch(products, "Shoes");

        if (p2 != null)
            System.out.println("Binary Search Found: " + p2.productName);
    }
}
