import java.util.Scanner;

public class RockPaperSissor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the\n 0 for Rock \n1 for Paper \n2 for Sissor");
        int userChoice = scanner.nextInt();
        int computerChoice = getRndom();

        if (compaire(userChoice, computerChoice)) {
            System.out.println("You Win");
        } else {
            System.out.println("Computer Win");
        }
        System.out.println(computerChoice);

    }

    public static int getRndom() {
        return (int) (Math.random() * 3);
    }

    public static boolean compaire(int userChoice, int computerChoice) {
        if (userChoice == computerChoice) {
            return false;
        } else if ((userChoice == 0 && computerChoice == 2) || (userChoice == 1 && computerChoice == 0)
                || (userChoice == 2 && computerChoice == 1)) {
            return true;
        } else {
            return false;
        }
    }
}
