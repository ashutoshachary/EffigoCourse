import java.util.Scanner;

public class JavaPedia {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("**********Javapedia**********");
        int col = 3;
        System.err.print("Enter how many historical figures will you register ? ");
        int row = scanner.nextInt();
        String[][] historicalFigures = new String[row][col];
        String[] model = { "Name", "Date of Birth", "Occupation" };
        scanner.nextLine();
        System.out.println("Enter the details of each historical figure: \n");

        for (int i = 0; i < row; i++) {
            System.out.println("\t\tFigure : " + (i + 1));
            for (int j = 0; j < col; j++) {
                System.out.print("\t\t  - " + model[j] + ": ");
                historicalFigures[i][j] = scanner.nextLine();
            }
        }

        System.out.println("\nThese are the values you stored:");
        printPage(historicalFigures);

        System.out.print("Who do you want information on? ");

        String searchName = scanner.nextLine();

        for (int i = 0; i < historicalFigures.length; i++) {
            if (historicalFigures[i][0].equalsIgnoreCase(searchName)) {
                for (int j = 0; j < col; j++) {
                    System.out.println("\t\t" + model[j] + ": " + historicalFigures[i][j]);

                }
                break;
            }
        }

    }

    public static void printPage(String[][] pageName) {
        for (int i = 0; i < pageName.length; i++) {
            System.out.print("\t\t");
            for (int j = 0; j < pageName[i].length; j++) {
                System.out.print(pageName[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
