import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExampleOne {
    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("greetings.txt");
            Scanner sc = new Scanner(fis);
            System.out.println(sc.nextLine());
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file" + e.getMessage());
        } finally {
            System.out.println("Program finished");
        }

    }

}