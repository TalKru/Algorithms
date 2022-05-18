package recursion;

import java.util.Arrays;

public class SortArrayRecursive {

    public static void main(String[] args) {

        int[] arr = {7, -2, 13, 1, 8, 5, 5};
        System.err.println(Arrays.toString(arr));
        sort(arr, arr.length);
        System.err.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int len){

        if(len < 2){
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {

            if(arr[i] > arr[i+1]){
                swap(arr, i, i+1);
            }
        }
        sort(arr, len - 1);
    }

    private static void swap(int[] arr, int a, int b){

        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
