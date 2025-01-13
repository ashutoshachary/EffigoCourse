public class Grocer {
    public static void main(String[] args) {

        // Instructions for this workbook are on Learn the Part (Workbook 6.3).
        String[] products = { "apples", "bananas", "candy", "chocolate", "coffee", "tea" };
        System.out.println("\nDo you sell coffee?");
        int count = 0;
        for (String string : products) {

            if (string.equals("coffee")) {
                System.out.println("\nWe have that in aisle: " + count);
            }
            count++;
        }

    }
}
