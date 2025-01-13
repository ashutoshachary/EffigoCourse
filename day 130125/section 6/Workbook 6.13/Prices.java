import java.util.Arrays;

public class Prices {
    public static void main(String[] args) {
        double[][] arr = new double[3][5];
        arr[0][0] = 12.99;
        arr[0][1] = 9.99;
        arr[0][2] = 11.99;
        arr[0][3] = 8.99;
        arr[0][4] = 11.49;
        arr[1][0] = 0.99;
        arr[1][1] = 1.99;
        arr[1][2] = 2.99;
        arr[1][3] = 3.99;
        arr[1][4] = 4.99;
        arr[2][0] = 5.99;
        arr[2][1] = 6.99;
        arr[2][2] = 7.99;
        arr[2][3] = 8.99;
        arr[2][4] = 9.99;

        double[][] prices = {
                { 12.99, 8.99, 9.99, 10.49, 11.99 },
                { 0.99, 1.99, 2.49, 1.49, 2.99 },
                { 8.99, 7.99, 9.49, 9.99, 10.99 }
        };

        System.out.println("1st prices:" + Arrays.toString(prices[0]));
        System.out.println("2nd prices:" + Arrays.toString(prices[1]));
        System.out.println("3rd prices:" + Arrays.toString(prices[2]));

        for (int i = 0; i < prices.length; i++) {
            switch (i) {
                case 0:
                    System.out.print("Baking: ");
                    break;
                case 1:
                    System.out.print("Beverage: ");
                    break;
                case 2:
                    System.out.print("Cereals: ");
                    break;
            }
            for (int j = 0; j < prices[i].length; j++) {
                System.out.print(prices[i][j] + " ");
            }
            System.out.println();
        }
    }
}
