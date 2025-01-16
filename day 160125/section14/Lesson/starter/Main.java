import product.Pants;
import product.Product;
import product.Shirt;
import product.Shirt.Size;

public class Main {

    public static void main(String[] args) {

        Shirt shirt = new Shirt(49.99, "WHITE", "NIKE", Size.SMALL);

        shirt.fold();
        productStore(shirt);
        shirt.wear();
        shirt.toString();

        Pants pants = new Pants(79.99, "WHITE", "NIKE", 12, 12);

        pants.fold();
        productStore(pants);
        pants.wear();

    }

    public static void productStore(Product product) {
        System.out.println("Product Brand: " + product.getBrand());
        System.out.println("Product Name: " + product.getClass().getSimpleName());
        System.out.println("Product price $: " + product.getPrice());
        System.out.println("-----------------------------");

    }

}
