// 2D Array Example

import java.util.Arrays;

public class TwoDArray {
    public static void main(String[] args) {
        int[][] array2d = new int[3][4];
        int[][] arr = {
                { 24, 12, 41, 98 },
                { 92, 23, 23, 98 },
                { 92, 23, 23, 89 },
        };

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < array2d.length; i++) {
            for (int j = 0; j < array2d[i].length; j++) {
                array2d[i][j] = i * j;
            }
        }

        for (int i = 0; i < array2d.length; i++) {
            for (int j = 0; j < array2d[i].length; j++) {
                System.out.print(array2d[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Rahul : " + Arrays.toString(array2d[0]));
        System.out.println("Riya : " + Arrays.toString(array2d[1]));
        System.out.println("Soumya : " + Arrays.toString(array2d[2]));

    }

}
