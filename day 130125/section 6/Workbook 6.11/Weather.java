import java.util.Arrays;

public class Weather {
    public static void main(String[] args) {

        // The instructions for this workbook are on Learn the Part (Workbook 6.11)
        double[] celsius = { 12.5, 14.5, 17.0, 21.0, 23.0, 18.5, 20.0 };
        double[] fahrenheit = celciusToFahrenheit(celsius);

        System.out.println("Celsius\t" + Arrays.toString(celsius));

        System.out.println("Fahrenheit\t" + Arrays.toString(fahrenheit));

    }

    public static double[] celciusToFahrenheit(double[] celsius) {
        double[] fahrenheit = new double[celsius.length];
        for (int i = 0; i < celsius.length; i++) {
            fahrenheit[i] = (celsius[i] / 5) * 9 + 32;
        }
        return fahrenheit;
    }

}
