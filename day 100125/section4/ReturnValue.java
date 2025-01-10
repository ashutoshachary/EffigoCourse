public class ReturnValue {
    public static void main(String[] args) {
        double result = addNumbers1(12.3, 3.4);
        System.out.println((result));
        String resultLan = explainArea("Spanish");
        System.out.println(resultLan);
        double area = areaCal(3, 4);
        System.out.println(area);
    }

    public static int addNumbers(int num1, int num2) {
        int sum = num1 + num2;
        return sum;
    }

    public static double addNumbers1(double num1, double num2) {
        double sum = num1 + num2;
        return sum;
    }

    public static double areaCal(double num1, double num2) {
        if (num1 <= 0 || num2 <= 0) {
            System.out.println("Invalid value for area calculation");
            System.exit(0);

        }
        double area = num1 * num2;
        return area;
    }

    public static String explainArea(String language) {
        switch (language) {
            case "English":
                return "The area of a circle is calculated using the formula A = π * r^2, where A is the area and r is the radius.";

            case "French":
                return "La surface est egale a la longueur * la largeur";

            case "Spanish":
                return "area es igual a largo * ancho";

            default:
                return "Language error";

        }

    }
}
