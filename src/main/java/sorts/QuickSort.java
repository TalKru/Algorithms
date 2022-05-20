package sorts;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {

        int[] arr = generateRandArr(15);

        System.out.println("\033[0;36m" + "Input:  " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("\033[0;32m" + "Sorted: " + Arrays.toString(arr));
    }

    private static void quickSort(int[] array, int lowIndex, int highIndex) {

        if (lowIndex >= highIndex) {
            return;
        }
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quickSort(array, lowIndex, leftPointer - 1);
        quickSort(array, leftPointer + 1, highIndex);
    }

    private static int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer < rightPointer) {

            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }
        if (array[leftPointer] > array[highIndex]) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }
        return leftPointer;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }



    public static int[] generateRandArr(int len) {

        int[] arr = new int[len];
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100); // [0, 20]
        }
        return arr;
    }

//    public static int partition(int array[], int low, int high) {
//
//        // choose the rightmost element as pivot
//        int pivot = array[high];
//
//        // pointer for greater element
//        int i = (low - 1);
//
//        // traverse through all elements
//        // compare each element with pivot
//        for (int j = low; j < high; j++) {
//            if (array[j] <= pivot) {
//
//                // if element smaller than pivot is found
//                // swap it with the greater element pointed by i
//                i++;
//
//                // swapping element at i with element at j
//                int temp = array[i];
//                array[i] = array[j];
//                array[j] = temp;
//            }
//        }
//        // swap the pivot element with the greater element specified by i
//        int temp = array[i + 1];
//        array[i + 1] = array[high];
//        array[high] = temp;
//
//        return (i + 1); // return the position from where partition is done
//    }

//    public static void quickSort(int array[], int low, int high) {
//
//        if (low < high) {
//
//            // find pivot element such that
//            // elements smaller than pivot are on the left
//            // elements greater than pivot are on the right
//            int pi = partition(array, low, high);
//
//            // recursive call on the left of pivot
//            quickSort(array, low, pi - 1);
//
//            // recursive call on the right of pivot
//            quickSort(array, pi + 1, high);
//        }
//    }
}

