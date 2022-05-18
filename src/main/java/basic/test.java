package basic;

import java.util.Arrays;

public class test {

	/**
	 * [left, right, up, down, maxSum]
	 * result[0] = left
	 * result[1] = right
	 * result[2] = up
	 * result[3] = down
	 * result[4] = maxSum
	 */
	public static int[] bestSubRectangle(int[][] matrix) {
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		int[] result = new int[5];
		int[] resultBestArr = new int[3];
		
		int maxSum = Integer.MIN_VALUE;
		// currSum == resultBestArr[2]
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;
		
		
		for (int L = 0; L < cols; L++) {
			
			int[] tempArr = new int[rows];
			
			for (int R = L; R < cols; R++) {
				
				for (int k = 0; k < rows; k++) {
					tempArr[k] += matrix[k][R];
				}
				
				resultBestArr = Best_MaxMin_Array.Best_Max_Array(tempArr);
				// max from best is stored in: resultBestArr[2]
				
				if(resultBestArr[2] > maxSum) {
					
					maxSum = resultBestArr[2];
					
					up = resultBestArr[0];
					down = resultBestArr[1];
					
					left = L;
					right = R;
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


	// ========================================================== main
	public static void main(String[] args) {
		int[][] inputMat = {
				{-2, 5,    1,   -8, -3},
				{4,  -100, 10,  -3,  1},
				{-10, 7,   7,   5,  -2},
				{1,  5,    -20, 3,  -5},
		}; 

		//	int[][] inputMat = {
		//		{-2, 5,    1,   -8,  3,  24},
		//		{4,  -100, 4,  -3,   1,   3},
		//		{-10, -7,  -7,  -5,  2,  99},
		//		{1,  5,    -20, 3,  -5, - 1},
		//	}; 


		int[] answer = bestSubRectangle(inputMat);

		System.out.println(Arrays.toString(answer));

	}

}
