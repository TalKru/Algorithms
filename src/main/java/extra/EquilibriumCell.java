package extra;

import java.util.Arrays;
import java.util.Random;

public class EquilibriumCell {

	public static void main(String[] args) {

		//              [10]        [10]
		//int[] arr = {5,1,3,1,  4,2,1,2,1};

		Random rand = new Random();
		int[] arr = new int[100];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10);
		}
		int result = equilibrium(arr);

		System.out.println(Arrays.toString(arr));
		System.out.println("\nEquilibrium cell index: " + result);
	}

	// O(n)
	public static int equilibrium(int[] arr){

		int aWeight = 0;
		int bWeight = 0;

		for (int i = 0; i < arr.length; i++) {
			bWeight += arr[i];
		}
		//  setup, left holds the first cell value, while the right holds the sum of the rest:
		//  equilibrium:   leftWeight sum   |    rightWeight sum
		//  arr index:     [0]              |    [1][2][3]...[n]
		for (int i = 0; i < arr.length; i++) {
			aWeight += arr[i];
			bWeight -= arr[i];
			
			if(aWeight == bWeight) {
				return i;
			}
		}
		return -1; // when there is no equilibrium point 
	}
}
