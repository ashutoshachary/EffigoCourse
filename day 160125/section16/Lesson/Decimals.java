import java.math.BigDecimal;

public class Decimals {
    public static void main(String[] args) {

        BigDecimal x1 = new BigDecimal("0.1");
        BigDecimal x2 = new BigDecimal("0.2");

        double x = 0.1;

        System.out.println(x);

        double y = 0.2;
        double z = x + y;

        System.out.println(z);
        System.out.println(x1.add(x2));

    }

}