public class Parameters {
    public static void main(String[] args) {
        clculateArea(2.8, 3.6);
        clculateArea(4.2, 5.7);
        clculateArea(6.1, 7.9); // argument
    }

    public static void clculateArea(double x, double y) { // parameter
        double area = x * y;
        System.out.println("The area of the rectangle is: " + area);
    }

}
