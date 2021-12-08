package cs146F21.Abdulkhamidov.project2;

import java.util.Random;

public class BruteForce {

    public static int maxSub(int[] arr) {

        int max=-9999999;
        long start = System.currentTimeMillis();

        for(int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > max)
                    max = sum;
            }
        }


        long end = System.currentTimeMillis();
        System.out.println("Array with size " +arr.length+ " takes "+
                (end - start) + "ms");
        return max;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Random rd = new Random(); // creating Random object

        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(199) - 99; // storing random integers in an array
        }

        System.out.println("Max Sub Array sum is "+maxSub(arr));

        int[] arr1 = new int[10000];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = rd.nextInt(199) - 99; // storing random integers in an array
        }

        System.out.println("Max Sub Array sum is "+maxSub(arr1));

        int[] arr2 = new int[100000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = rd.nextInt(199) - 99; // storing random integers in an array
        }

        System.out.println("Max Sub Array sum is "+maxSub(arr2));

        int[] arr3 = new int[1000000];

        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = rd.nextInt(199) - 99;// storing random integers in an array
        }
        System.out.println("Max Sub Array sum is "+maxSub(arr3));
    }
}
