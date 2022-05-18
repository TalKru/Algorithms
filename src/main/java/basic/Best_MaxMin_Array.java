package basic;

public class Best_MaxMin_Array {

	/**
	 * Finds the largest Sum of contiguous Sub-Array
	 *
	 * @param arr - array of Real numbers
	 * @return array of 3 values                     [start, end, maxSum]:
	 * i,j - beginning + ending of the best section
	 * max - the sum of the best section
	 */
	public static int[] Best_Max_Array(int[] arr) {
		
		int[] ans = new int[3]; 
		
		int currSum = 0;
		int maxSum  = Integer.MIN_VALUE; // -INF
		int start = 0; // start index of the interval
		int end = 0; 
		int tempStart = 0;
		
        for (int i = 0; i < arr.length; i++) { 
        	
            currSum += arr[i]; 
            /*
             * NOTE: if we will ask the if condition: (currSum < 0) 
             * BEFORE condition: (currSum > topResult)
             * since if all the array is negative it will not work otherwise
             * 
             * */
            
            if (currSum > maxSum) { // hit on better result
            	maxSum = currSum; // update the best result found
            	start = tempStart;
            	end = i;
            }
            if (currSum < 0) { // if we change to <=0 then we will get also a correct result which does NOT include parts such 8,-8,5,-5,-12,12
            	currSum = 0; 
            	tempStart = i+1; // if reached negative value then next possible start can only begin at the next index
            }
                
        } 
        ans[0] = start;
        ans[1] = end;
        ans[2] = maxSum;
        //print(arr, ans);
		return ans;
	}
	// ============================================================================(end)
	
	
	
	/**
	 * Best_Min(Array) = (-1)*Best_Max([-1]*Array)
	 */
	public static int Best_Min_Array(int[] arr) {

		int[] negativeArr = new int[arr.length];
		
		
		// F(A) = -A
		for (int i = 0; i < negativeArr.length; i++) { // [1,2,3,-7,0]  ==>  [-1,-2,-3,7,0]
			negativeArr[i] = (-1)*arr[i];
		}

		// now we find a Max sub interval in the negative array
		// [start,end,maxSum]
		int[] bestMaxArr = Best_Max_Array(negativeArr);

		return bestMaxArr[2]*(-1);
	}
	// ============================================================================(end)
	
	
	
	// print sub array with indexes
	public static void print(int[] intputArr, int[] ansArr) {
		// print result
        System.out.println("Input Array:");
		//System.out.println(Arrays.toString(intputArr));
        for (int i = 0; i < intputArr.length; i++) {
			System.out.print( "[" + (int)intputArr[i] + "] ");
		}
        System.out.println();
		System.out.println("Largest contiguous sum  = (" + (int)ansArr[2] + ")");
		System.out.println("Start+End index: [" + (int)ansArr[0] + "]->[" + (int)ansArr[1] + "]");
	}
	
	
	
	
	// MAIN MAIN MAIN MAIN MAIN
	public static void main(String[] args) {
		
		int[] arr = {-1,-5,8,-8,1,2,-1,5,4,-8};
		int[] ans = Best_Max_Array(arr);
		print(arr, ans);
		
	}
} 









