public class NestedLoops {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("* ");
            }
            System.out.println(); // To print a new line after each row of outer loop. 10 rows will be printed.
        }
    }
}
