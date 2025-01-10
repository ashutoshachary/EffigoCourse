import java.util.Scanner;

public class Pokerito {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        /*
         * Task 2: Explain the rules
         * 
         * >>Let's play Pokerito. Type anything when you're ready.
         * |
         * >>It's like Poker, but a lot simpler.
         * >> (new line)
         * >> • There are two players, you and the computer.
         * >> • The dealer will give each player one card.
         * >> • Then, the dealer will draw five cards (the river)
         * >> • The player with the most river matches wins!
         * >> • If the matches are equal, everyone's a winner!
         * >> (new line)
         * >> • Ready? Type anything if you are.
         * |
         */

        /*
         * Task 3: Present the user with a card
         * println 'Here's your card:'
         * <show card>
         * <new line>
         * println 'Here's the computer's card:'
         * <show computer's card>
         */

        int yourMatches = 0;
        int computerMatches = 0;

        /**
         * Task 4 - Draw five cards
         * 
         * • print: Now, the dealer will draw five cards. Press enter to continue.
         * • The dealer will draw a card every time the user presses enter.
         * • Before you draw a card, print the order it was drawn in:
         * Card 1
         * <2 new lines>
         * <print card>
         * Card 2
         * <2 new lines>
         * <print card>
         * ...
         */

        /**
         * Task 5 - Get the winner
         * 
         * • Count your number of matches.
         * • Count the computer's number of matches.
         * • print: Your number of matches: <yourMatches>
         * • print: Computer number of matches: <computerMatches>
         * 
         * • If you have more matches, print: You win!.
         * • If the computer has more matches, print: The computer wins!
         * • If the matches are equal, print: everyone wins!.
         */
        int userChoice = randomCard();
        System.out.println("Here's your card:");
        printtheCard(userChoice);
        int computerChoice = randomCard();
        System.out.println("Here's the computer's card:");
        printtheCard(computerChoice);

        for (int j = 0; j < 5; j++) {
            System.out.println("Now, the dealer will draw five cards. Press enter to continue.");
            scan.nextLine();
            int dealerChoiece = randomCard();
            printtheCard(dealerChoiece);
            if (dealerChoiece == userChoice) {
                yourMatches++;
            }
            if (dealerChoiece == computerChoice) {
                computerMatches++;
            }

        }
        if (yourMatches == computerMatches) {
            System.out.println("Drawn");
        } else if (yourMatches > computerMatches) {
            System.out.println("You win!");
        } else {
            System.out.println("The computer wins!");
        }

        scan.close();
    }

    /**
     * Task 1
     * 
     * Function name – randomCard
     * 
     * @return (String)
     * 
     *         Inside the function:
     *         1. Gets a random number between 1 and 13.
     *         2. Returns a card that matches the random number (get the String
     *         values from cards.text).
     */
    public static void printtheCard(int key) {
        // Implement this function
        switch (key) {
            case 1:
                System.out.println("   _____\n" +
                        "  |A _  |\n" +
                        "  | ( ) |\n" +
                        "  |(_'_)|\n" +
                        "  |  |  |\n" +
                        "  |____V|\n");
                break;
            case 2:
                System.out.println(
                        "   _____\n" +
                                "  |2    |\n" +
                                "  |  o  |\n" +
                                "  |     |\n" +
                                "  |  o  |\n" +
                                "  |____Z|\n");
                break;
            case 3:
                System.out.println(
                        "   _____\n" +
                                "  |3    |\n" +
                                "  | o o |\n" +
                                "  |     |\n" +
                                "  |  o  |\n" +
                                "  |____E|\n");
                break;
            case 4:
                System.out.println(
                        "   _____\n" +
                                "  |4    |\n" +
                                "  | o o |\n" +
                                "  |     |\n" +
                                "  | o o |\n" +
                                "  |____h|\n");
                break;
            case 5:
                System.out.println(
                        "   _____ \n" +
                                "  |5    |\n" +
                                "  | o o |\n" +
                                "  |  o  |\n" +
                                "  | o o |\n" +
                                "  |____S|\n");
                break;
            case 6:
                System.out.println(
                        "   _____ \n" +
                                "  |6    |\n" +
                                "  | o o |\n" +
                                "  | o o |\n" +
                                "  | o o |\n" +
                                "  |____6|\n");
                break;
            case 7:
                System.out.println(
                        "   _____ \n" +
                                "  |7    |\n" +
                                "  | o o |\n" +
                                "  |o o o|\n" +
                                "  | o o |\n" +
                                "  |____7|\n");
                break;
            case 8:
                System.out.println(
                        "   _____ \n" +
                                "  |8    |\n" +
                                "  |o o o|\n" +
                                "  | o o |\n" +
                                "  |o o o|\n" +
                                "  |____8|\n");
                break;
            case 9:
                System.out.println(
                        "   _____ \n" +
                                "  |9    |\n" +
                                "  |o o o|\n" +
                                "  |o o o|\n" +
                                "  |o o o|\n" +
                                "  |____9|\n");
                break;
            case 10:
                System.out.println(
                        "   _____ \n" +
                                "  |10  o|\n" +
                                "  |o o o|\n" +
                                "  |o o o|\n" +
                                "  |o o o|\n" +
                                "  |___10|\n");
                break;
            case 11:
                System.out.println(
                        "   _____\n" +
                                "  |J  ww|\n" +
                                "  | o {)|\n" +
                                "  |o o% |\n" +
                                "  | | % |\n" +
                                "  |__%%[|\n");
                break;
            case 12:
                System.out.println(

                        "   _____\n" +
                                "  |Q  ww|\n" +
                                "  | o {(|\n" +
                                "  |o o%%|\n" +
                                "  | |%%%|\n" +
                                "  |_%%%O|\n");
                break;
            case 13:
                System.out.println(
                        "   _____\n" +
                                "  |K  WW|\n" +
                                "  | o {)|\n" +
                                "  |o o%%|\n" +
                                "  | |%%%|\n" +
                                "  |_%%%>|\n");
                break;

            default:
                System.out.println("Invalid input");
                break;
        }
    }

    public static int randomCard() {
        // Implement this function
        int random = (int) (Math.random() * 13) + 1;

        return random;
    }

}
