import java.util.Arrays;

public class ReferenceTrap {
    public static void main(String[] args) {
        String[] staffLastYear = { "Jan", "Feb", "Mar", "Apr", "May" };

        String[] staffThisYear = staffLastYear;

        String[] staffLastYear1 = { "Jan", "Feb", "Mar", "Apr", "May" };

        String[] staffThisYear1 = new String[staffLastYear1.length];

        String[] staffLastYear2 = { "Jan", "Feb", "Mar", "Apr", "May" };

        String[] staffThisYear2 = Arrays.copyOf(staffLastYear2, staffLastYear2.length);

        for (int i = 0; i < staffThisYear1.length; i++) {
            staffThisYear1[i] = staffLastYear1[i];
        }

        staffThisYear[0] = "isudgkamngrbj";
        staffThisYear1[2] = "askdbjsf";
        staffThisYear2[1] = "kjsdbj";
        System.out.println(Arrays.toString(staffLastYear)); // this is referance trap
        System.out.println(Arrays.toString(staffThisYear));

        // Using for loop the referance trap is removed forom the array
        System.out.println(Arrays.toString(staffLastYear1));
        System.out.println(Arrays.toString(staffThisYear1));

        System.out.println(Arrays.toString(staffLastYear2));
        System.out.println(Arrays.toString(staffThisYear2));

    }

}
