import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        // 1. filter: uses the method startsWith to remove Spam emails.

        // 2. forEach(): prints every element in the stream.
        try {
            Path path = Paths.get("emails.txt");

            Files.lines(path)
                    .filter(s -> !s.startsWith("Spam"))
                    .forEach(email -> System.out.println(email));
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());

        }

    }
}
