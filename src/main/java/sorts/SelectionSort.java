package sorts;

import java.util.Arrays;
import java.util.Random;

public class SelectionSort {

    public static void main(String[] args) {

        int[] arr = generateRandArr(10);

        System.out.println("\033[0;36m" + "Input:  " + Arrays.toString(arr));
        selectionSort(arr); // sort func call
        System.out.println("\033[0;32m" + "Sorted: " + Arrays.toString(arr));
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] generateRandArr(int len) {

        int[] arr = new int[len];
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] =  rand.nextInt(21); // [0, 20]
        }
        return arr;
    }

    public static boolean isSorted(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {

                System.out.println("\033[1;31m" + "ERROR!");
                return false;
            }
        }
        return true;
    }



    public static void selectionSort(int[] arr) {

        int localMin = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < arr.length - 1; i++) { // sortedSector
            for (int j = i; j < arr.length; j++) { // current item

                if (arr[j] < localMin) {
                    localMin = arr[j];
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);

            localMin = Integer.MAX_VALUE; // reset
        }
    }

}
