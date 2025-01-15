import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ExampleTwo {
    public static void main(String[] args) {
        try {
            readFile("greetings.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            System.out.println("Finally block executed");
        }

    }

    public static void readFile(String fileName) throws FileNotFoundException {
        FileInputStream fis1 = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fis1);
        System.out.println(scanner.nextLine());
        scanner.close();
    }
}