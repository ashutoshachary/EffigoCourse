public class SwitchStatements {
    public static void main(String[] args) {

        // Section 1: Weather conditions
        String weather = "sunny";
        // Determine what to wear based on the weather (e.g., sunny, cloudy, rainy,
        // snowy)
        // Decide whether to use if-else or switch
        switch (weather) {
            case "sunny":
                System.out.println(" Wear a t-shirt and shorts");
                break;

            case "cloudy":
                System.out.println(" wear any thing ");
                break;

            case "rainy":
                System.out.println("Wear a raincoat and umbrella");
                break;

            case "snowy":
                System.out.println("Wear  s");
                break;

            default:
                System.out.println("Wear any thing");
                break;
        }

        // Section 2: User role
        int role = 2;
        switch (role) {
            case 1:
                System.out.println(" Employee");
                break;

            case 2:
                System.out.println("Mgr ");
                break;

            case 3:
                System.out.println("Ceo");
                break;

            default:
                System.out.println("Boss");
                break;
        }
        // Determine user access level based on the role (e.g., 1: admin, 2: editor, 3:
        // user)
        // Decide whether to use if-else or switch

        // Section 3: Temperature and humidity
        int temperature = 75;
        int humidity = 65;
        if (temperature > 50 && humidity > 50) {
            System.out.println("Not Comfortable weather");
        } else {
            System.out.println("Comfortable weather");
        }

        // Determine the comfort level based on both temperature and humidity (e.g., too
        // hot, too cold, comfortable)
        // Decide whether to use if-else or switch

        // Section 5: Age and income
        int age = 25;
        int income = 50000;
        if (age >= 18 && income > 20000) {
            System.out.println(" Eligible for Credit card plan");
        } else {
            System.out.println("Not Eligible for Creditcard plan");
        }
        // Determine eligibility for a specific credit card based on age and income
        // Decide whether to use if-else or switch

        // Section 1: Traffic light colors
        String lightColor = "green";
        switch (lightColor) {
            case "green":
                System.out.println("Green color");
                break;
            case "yellow":
                System.out.println("Yellow color");
                break;
            case "blue":
                System.out.println("Blue color");
                break;

            default:
                System.out.println(" No color");
                break;
        }
        // Determine what action to take based on the traffic light color (e.g., green,
        // yellow, red)
        // Decide whether to use if-else or switch

        // Section 2: Exam grade
        int grade = 85;
        // Determine the letter grade based on the numeric grade (e.g., A, B, C, D, F)
        // Decide whether to use if-else or switch

        // Section 3: Browser type
        String browser = "Chrome";
        // Check if the browser is one of the following: Chrome, Firefox, Safari, Edge,
        // or Opera
        // Decide whether to use if-else or switch

    }
}
