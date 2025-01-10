public class BreakAndContinueExamples {

    public static void main(String[] args) {
        // Example 1: Print numbers from 1 to 10, but stop when the number 5 is reached
        // (using break)
        // TODO: Write a loop that prints numbers from 1 to 10, but stops when the
        // number 5 is reached using the 'break' statement
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                break;
            }
            System.out.println(i);
        }

        // Example 2: Print numbers from 1 to 10, but skip the number 5 (using continue)
        // TODO: Write a loop that prints numbers from 1 to 10, but skips the number 5
        // using the 'continue' statement
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                continue;
            }
            System.out.println(i);
        }
        // Example 3: Find the smallest number greater than 20 that is divisible by 3
        // (using break)

        // TODO: Write a loop that finds the smallest number greater than 20 that is
        // divisible by 3 using the 'break' statement
        int smallestNumber = 21;
        while (smallestNumber <= 20) {
            if (smallestNumber % 3 == 0) {
                break;
            }
            smallestNumber++;
        }
        System.out.println("The smallest number greater than 20 that is divisible by 3 is: " + smallestNumber);

        // Example 5: Find the sum of all even numbers from 1 to 20 (using continue)
        // TODO: Write a loop that calculates the sum of all even numbers from 1 to 20
        // using the 'continue' statement
        int sum = 0;
        for (int i = 1; i <= 20; i++) {
            if (i % 2!= 0) {
                continue;
            }
            sum += i;
        }

        
    }
}
