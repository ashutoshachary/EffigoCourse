import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = { "ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra" };

    public static String[] gallows = { "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" + // if you were wondering, the only way to print '\' is with a trailing escape
                                  // character, which also happens to be '\'
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n" };
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int misses = 6;
        int count = 0;
        int flag = 0;

        int r = randomWord();
        String[] myMisses = new String[6];
        int len = words[r].length();
        String[] chars = new String[len];
        String[] strArr = words[r].split("");

        for (int i = 0; i < len; i++) {
            chars[i] = "_";
        }

        while (misses > 0) {

            System.out.println(gallows[count]);
            System.out.print("\n Word: ");
            showWord(chars);
            System.out.print("\n Misses: ");
            showWord((myMisses));

            System.out.print("\nGuess: ");
            String guess = sc.nextLine();
            if (guess.length() != 1) {
                System.out.println("Please enter a single character.");
                guess = sc.nextLine();
            }

            if (Arrays.asList(strArr).contains(guess)) {
                System.out.println("Correct!");

                for (int i = 0; i < strArr.length; i++) {
                    if (strArr[i].equals(guess)) {
                        chars[i] = guess;
                    }
                }
            } else {
                System.out.println("Incorrect!");
                myMisses[flag] = guess;
                flag++;
                if (flag == 6) {
                    System.out.println(gallows[gallows.length - 1]);
                    System.out.println("\nYou lost! The word was: " + words[r]);
                    System.exit(0);
                }
                misses--;
                count++;
            }
            if (isNull(chars)) {
                System.out.println("\nYou won! The word was: " + words[r]);
                System.exit(0);
            }

            // skip

        }

    }

    public static int randomWord() {
        return (int) (Math.random() * words.length);
    }

    public static void showWord(String[] words) {
        System.out.print("\t");
        for (int i = 0; i < words.length; i++) {
            if (words[i] == null)
                continue;
            System.out.print(words[i] + " ");
        }
        System.out.println();
    }

    public static boolean isNull(String[] words1) {
        for (String s : words1) {
            if (s == "_")
                return false;
        }
        return true;
    }

}
