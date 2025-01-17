package src.main;

import java.text.DecimalFormat;

public class Main {
    public static double[] prices = new double[] { 2.33, 1.32, 4.32, 11.33 };

    public static void main(String[] args) {

    }

    public static double getSubtotal() {
        double subtotal = 0;
        for (double price : prices) {
            subtotal += price;
        }
        return subtotal;
    }

    public static double getTax(double subtotal) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(subtotal * 0.13));
    }

}
