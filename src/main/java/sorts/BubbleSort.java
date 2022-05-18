package sorts;

public class BubbleSort {

	public static void bubbleSort(int[] arr) {
		
		int n = arr.length;
		
		for (int i = 0; i < (n-1); i++) {
			for (int j = 0; j < (n-i-1); ++j) {
				if(arr[j] > arr[j+1]) {
					swap(arr, j, j+1);
				}
			}
		}
	}
	
	public static void mathSwap(int[] arr, int i, int j) {
		arr[i] = arr[i] + arr[j];   // [1] = a+b, [2] = b
		arr[j] = arr[i] - arr[j];   // [1] = a+b, [2] = a
		arr[i] = arr[i] - arr[j];   // [1] = b,   [2] = a
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}






