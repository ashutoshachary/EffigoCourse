import product.Pants;
import product.Product;
import product.Shirt;
import product.Shirt.Size;

public class Main {

    public static void main(String[] args) {

        Shirt shirt = new Shirt();
        shirt.setSize(Size.SMALL);
        shirt.setBrand("NIKE");
        shirt.setPrice(49.99);
        shirt.setColor("WHITE");
        shirt.fold();

        Pants pants = new Pants();
        pants.setWaist(32);
        pants.setLength(38);
        pants.setBrand("LEVIS");
        pants.setPrice(69.99);
        pants.setColor("BLUE");
        pants.fold();

    }

    public static void productStore(Product product) {
        System.out.println("Product Brand: " + product.getBrand());
        System.out.println("Product Name: " + product.getClass().getSimpleName());
        System.out.println("Product price $: " + product.getPrice());
        System.out.println("-----------------------------");

    }

}
