package sorts;

import java.util.Arrays;
import java.util.Random;

public class CountingSort {

    public static void main(String[] args) {

        int arrLen = 15;
        int[] arr = new int[arrLen];
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            //arr[i] =  rand.nextInt(21); // [0, 20]
            arr[i] =  1000 + rand.nextInt(11); // [1000, 1010]
        }
        System.out.println("\033[0;36m" + "Input:  " + Arrays.toString(arr));
        //countingSort(arr);
        countingSortExactRange(arr);
        System.out.println("\033[0;32m" + "Sorted: " + Arrays.toString(arr));
    }

    // O(n + range)
    public static void countingSort(int[] arr){

        int max = Integer.MIN_VALUE;

        for (int n : arr) {
            max = Math.max(max, n);
        }
        int[] countArr = new int[max + 1]; // max=10, range[0,10] >> len is 11

        for (int val : arr) {
            countArr[val]++;
        }
        int index = 0; // countArr's index\pointer

        for (int i = 0; i < arr.length; i++) {

            while(countArr[index] == 0){
                index++;
            }
            arr[i] = index;
            countArr[index]--;
        }
    }

    /*
    way more efficient memory wise,
    Example:
    given a known range [1,000,200,  1,000,275]
    the range is only 76 possible numbers
    so there is no need to create a count array
    of 1,000,275 cells when most of them not in use.
    only to shift form 0 cell to 1,000,200 (min value)
     */
    public static void countingSortExactRange(int[] arr){

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int n : arr) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        int[] countArr = new int[max - min + 1]; // min=0, max=10, range[0,10] >> len is 11

        for (int val : arr) {
            countArr[val - min]++;
        }
        int index = 0; // countArr's index\pointer

        for (int i = 0; i < arr.length; i++) {

            while(countArr[index] == 0){
                index++;
            }
            arr[i] = index + min;
            countArr[index]--;
        }
    }

}

