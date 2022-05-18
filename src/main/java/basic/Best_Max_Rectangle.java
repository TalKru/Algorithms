package basic;

import java.util.Arrays;

public class Best_Max_Rectangle {

	/**
	 * return array layout: [left, right, up, down, maxSum]
	 * result[0] = left
	 * result[1] = right
	 * result[2] = up
	 * result[3] = down
	 * result[4] = maxSum
	 */
	public static int[] bestSubRectangle(int[][] mat) {

		int rows = mat.length;
		int cols = mat[0].length;
		
		int[] result = new int[5]; // the return result
		int[] resultMaxBestArr = new int[3]; // result from Best_Max_Array - helper func

		int up    = 0; //	  (up) [----------------]
		int down  = 0; //		   [----------------]
		int left  = 0; //		   [----------------]
		int right = 0; //	 (down)[----------------]
		/////////////////        (left)          (right)
		
		int maxSum = Integer.MIN_VALUE;
		// current sum -> will be stored in => resultMaxBestArr[2]

		for (int L = 0; L < cols; L++) {

			// accumulator array {[0][0][0]...[0][0]}
			// fun part: no need to manually reset the array since it auto allocats to clean array
			// each time when L++ happens
			int[] tempSumArr = new int[rows]; 
			
			for (int R = L; R < cols; R++) { // R goes back to L - not to 0!
				
				for (int k = 0; k < rows; k++) {
					tempSumArr[k] += mat[k][R]; // accumulate the "R" coll's into the temp array
				}
				
				resultMaxBestArr = Best_MaxMin_Array.Best_Max_Array(tempSumArr); // calling BEST on the array

				if( resultMaxBestArr[2] > maxSum ) {
					
					maxSum = resultMaxBestArr[2];
					left   = L;
					right  = R;
					up     = resultMaxBestArr[0];
					down   = resultMaxBestArr[1];
				}
			} // for R
		} // for L
		result[0] = left;
		result[1] = right;
		result[2] = up;
		result[3] = down;
		result[4] = maxSum;
		return result;
	}


	public static void main(String[] args) {

		int[][] inputMat = {
				{-2, 5,    1,   -8, -3},
				{4,  -100, 10,  -3,  1},
				{-10, 7,   7,   5,  -2},
				{1,  5,    -20, 3,  -5},
		}; 
		
		
//		int[][] inputMat = {
//				{-2, 5,    1,   -8,  3,  24},
//				{4,  -100, 4,  -3,   1,   3},
//				{-10, -7,  -7,  -5,  2,  99},
//				{1,  5,    -20, 3,  -5, - 1},
//		}; 

		int[] answer = bestSubRectangle(inputMat);
		System.out.println(Arrays.toString(answer));
	}
}
