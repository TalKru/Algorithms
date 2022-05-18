package sorts;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {

	public static void main(String[] args) {

		Random rand = new Random();
		int[] nums = new int[1000];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = rand.nextInt(1000);
		}
		System.out.println("Before: " + Arrays.toString(nums));
		
		long start = System.currentTimeMillis();
		
		mergeSort(nums);
		
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		
		System.out.println("Sort runtime: (" + timeElapsed + ") mili seconds");
		System.out.println("After: " + Arrays.toString(nums));
	}


	public static void mergeSort(int[] arr) {

		if(arr.length < 2) {
			return;
		}
		int midIndex = arr.length / 2;
		int[] leftHalf = new int[midIndex];
		int[] rightHalf = new int[arr.length - midIndex]; // for odd arrays divided by 2

		for (int i = 0; i < leftHalf.length; i++) {
			leftHalf[i] = arr[i];
		}
		for (int i = 0; i < rightHalf.length; i++) {
			rightHalf[i] = arr[midIndex + i];
		}
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		// merge
		sortedArraysMerge(arr, leftHalf, rightHalf);
	}


	// merge two sorted arrays in one
	public static void sortedArraysMerge(int[] res, int[] a, int[] b) {
		//  [ a ]  [ b ]  [res]
		int i = 0, j = 0, k = 0;

		while(i < a.length  &&  j < b.length) {

			if(a[i] <= b[j]) {
				res[k++] = a[i++];
			}
			else { //(a[i] > b[j]) 
				res[k++] = b[j++];
			}
		}
		// case when one of the arrays a or b was fully used
		while(j < b.length) {
			res[k++] = b[j++];
		}
		while( i < a.length) {
			res[k++] = a[i++];
		}
	}
}
