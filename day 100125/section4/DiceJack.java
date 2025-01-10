import java.util.Scanner;

public class DiceJack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int res1 = rollTheDice();
        int res2 = rollTheDice();
        int res3 = rollTheDice();

        System.out.println("ENTER 3 NUMBERS ");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();
        System.out.println("First roll: " + res1);
        System.out.println("Second roll: " + res2);
        System.out.println("Third roll: " + res3);

        if (areLessThan1(num1, num2, num3) || aregreaterThan6(num1, num2, num3)) {
            System.out.println("Invalid input");
            System.exit(0);
        }
        int computer = res1 + res2 + res3;
        int user = num1 + num2 + num3;
        if (ownGame(user, computer)) {
            System.out.println("You win");
        } else {
            System.out.println("Computer wins");
        }

    }

    public static boolean areLessThan1(int num1, int num2, int num3) {
        return num1 < 1 || num2 < 1 || num3 < 1;
    }

    public static boolean aregreaterThan6(int num1, int num2, int num3) {
        return num1 > 6 || num2 > 6 || num3 > 6;
    }

    public static boolean ownGame(int user, int computer) {
        return user >= computer ? (boolean) true : (boolean) false;
    }

    public static int rollTheDice() {
        double res = Math.random() * 6 + 1;
        return (int) res;
    }
}
