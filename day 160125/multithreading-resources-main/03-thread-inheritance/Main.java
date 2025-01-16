public class Main {

    public static void main(String[] args) {
        // Creating the first athlete thread
        AthleteThread firstAthete = new AthleteThread("Ram", Thread.MIN_PRIORITY, 84291);
        // Creating the second athlete thread
        AthleteThread secondAthlete = new AthleteThread("Sam", Thread.MAX_PRIORITY, 43114);

        firstAthete.start();
        secondAthlete.start();
    }

}