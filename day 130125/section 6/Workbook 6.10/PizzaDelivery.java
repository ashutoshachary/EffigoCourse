import java.util.Scanner;

public class PizzaDelivery {
    public static void main(String[] args) {

        // See instructions on Learn the Part (Workbook 6.10)

        Scanner scan = new Scanner(System.in);

        System.out.println("How many pizza toppings do you want?");
        int numToppings = scan.nextInt();
        scan.nextLine(); // Throwaway nextLine() to avoid nextLine() trap. Since it comes after
                         // nextInt(), it will just get skipped anyway.

        System.out.println("Great, enter each topping!");

        String[] toppings = new String[numToppings]; // Length of array equals what the user inputs.
        for (int i = 0; i < numToppings; i++) {
            System.out.print(i + " . ");
            toppings[i] = scan.nextLine();
        }

        // Task 1 here

        System.out.println("\nThank you! Here are the toppings you ordered");

        // Task 2 here
        for (int i = 0; i < numToppings; i++) {
            System.out.println((i) + ". " + toppings[i]);
        }

        System.out.println("\nPress anything to confirm your order.");
        scan.nextLine();
        System.out.println("Great, a driver is on the way!");

        scan.close();

    }
}
