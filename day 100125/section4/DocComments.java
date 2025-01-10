public class DocComments {
    public static void main(String[] args) {
        greet();
        printName("Ashu", "10");
        System.out.println(areaCalc(3.4, 6.20));

    }

    public static void greet() {
        System.out.println("Hii");
    }

    public static void printName(String name, String age) {
        System.out.println("Your name is: " + name + ", age: " + age);
    }

    public static double areaCalc(double num1, double num2) {
        return num1 * num2;
    }
}
