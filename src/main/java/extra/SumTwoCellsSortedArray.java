package RustRemoval;

import java.util.Arrays;
import java.util.Random;


/*
 * Given a sorted array, check if adding 2 cells == k
 *   
 */
public class SumTwoCellsSortedArray {

	public static void main(String[] args) {


		int[] arr = {10,15,17,17,17,20,21,22,23};
		int k = 41;
		int i,j;


		TwoIndex result = twoSumSorted(arr, k);

		i = result.getFirstIndex();
		j = result.getSecondIndex();

		System.out.println(Arrays.toString(arr));
		System.out.println("K: " + k);
		System.out.println("i: " + i + " -> " + arr[i]);
		System.out.println("j: " + j + " -> " + arr[j]);

	}


	// if such sum does not exist will return (-1,-1)
	public static TwoIndex twoSumSorted(int[] arr, int k) {

		int i = 0;
		int j = arr.length-1;

		while (i < j) {

			if(arr[i] + arr[j] == k) {
				return new TwoIndex(i, j);
			}
			if(arr[i] + arr[j] < k ) {
				i++;
			}
			else { //if(arr[i] + arr[j] > k) {
				j--;
			}
		}
		return new TwoIndex(-1, -1);
	}

} // class



class TwoIndex {

	private int index1 = 0;
	private int index2 = 0;

	TwoIndex(int f, int s){
		this.index1 = f;
		this.index2 = s;
	}
	public int getFirstIndex() {
		return this.index1;
	}
	public int getSecondIndex() {
		return this.index2;
	}
}





