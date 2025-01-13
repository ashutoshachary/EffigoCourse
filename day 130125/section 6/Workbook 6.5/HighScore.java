public class HighScore {
    public static void main(String[] args) {

        int highScore = 0;

        // Instructions for this workbook are on Learn the Part (Workbook 6.5).

        int[] randomArray = new int[10];

        for (int i = 0; i < 10; i++) {
            randomArray[i] = generateRandom();
        }

        System.out.print("Here are the random scores: ");
        for (int i = 0; i < randomArray.length; i++) {
            System.out.print(randomArray[i] + " ");

        }

        for (int i = 0; i < randomArray.length; i++) {
            if (randomArray[i] >= highScore) {
                highScore = randomArray[i];
            }
        }

        System.out.println("\n\nThe highest score is: " + highScore + ". Give that man a cookie!");

    }

    public static int generateRandom() {
        return (int) (Math.random() * 50000);
    }

}
