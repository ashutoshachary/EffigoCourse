import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Double> prices = Arrays.asList(55.99, 33.99, 88.99, 11.49, 55.99, 111.99, 77.99);

        // TODO
        // 1. Sort the prices in decending order
        // 2. Print the prices
        prices.sort(
                (right, left) -> {
                    return left.compareTo(right);
                });
        System.out.println("\nPrices\n------");
        for (Double price : prices) {
            System.out.println(price);
        }

        List<String> books = Arrays.asList(
                "To Kill a Mockingbird",
                "The Great Gatsby",
                "Pride and Prejudice",
                "The Catcher in the Rye",
                "The Alchemist",
                "One Hundred Years of Solitude",
                "Moby-Dick",
                "The Brothers Karamazov",
                "The Lord of the Rings",
                "The Picture of Dorian Gray");

        // TODO
        // 1. Sort the books in alphabetical order
        // 2. Print the books
        books.sort(
                (right, left) -> {
                    return right.compareTo(left);
                });

        System.out.println("\nLibrary\n--------");
        for (String book : books) {
            System.out.println(book);
        }

        // TODO

    }
}