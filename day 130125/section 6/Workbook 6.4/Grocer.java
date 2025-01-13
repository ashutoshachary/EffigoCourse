import java.util.Scanner;

public class Grocer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Instructions for this workbook are on Learn the Part (Workbook
                                                  // 6.4).

        String[] store = { "apples", "bananas", "candy", "chocolate", "coffee", "tea" };
        System.out.println("\nWelcome to Java Grocers. ");
        System.out.println("What can I help you find?\n");

        System.out.println("Please enter your search: ");
        String userInput = scanner.nextLine().toLowerCase();

        for (int i = 0; i < store.length; i++) {

            if (store[i].equals(userInput)) {
                System.out.println("\nWe have that in aisle: " + i);
            }

        }

        scanner.close();

        // Compare your result to what's on Learn the Part.

    }
}
