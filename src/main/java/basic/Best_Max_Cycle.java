package basic;

/**
 * Finding the best largest sub-interval in a cycle (cyclic array).
 * 
 * param cyclicArr - cyclic array of Real numbers
 * 
 * return array of 3 values:
 * Result in array form: [start ][ end  ][maxSum]
 *                       [  0   ][  1   ][  2   ]                      
 * LECTURE:                   
 * Best Sub Cycle = Max{ (Array Sum) - Best_Min(Array) , Best_Max(Array) }
 *  
 * Best_Min(Array) = (-1) * Best_Max(-Array)
 *  
 * Best Sub Cycle = Max{ (Array Sum) + Best_Max(-Array) , Best_Max(Array) }
 */
public class Best_Max_Cycle {

	public static int[] bestSubcycle(int[] cyclicArr) {

		int[] ans = new int[3];
		// ------------------------(special case: when all array is negative)--------------------------
		if(isArrNegative(cyclicArr)) { // then the formula will not work, fix is simple,
			return Best_MaxMin_Array.Best_Max_Array(cyclicArr);
		}
		// ============================================================================================

		// --------------------------------------------------------------------------------------------
		// ( Best_Max(Array) )
		// If the Max sub-interval is not cyclic then Best_Max() will find the best interval
		int[] regSum = Best_MaxMin_Array.Best_Max_Array(cyclicArr);
		int regSumVal = regSum[2]; // answer candidate number 1
		// ============================================================================================

		// --------------------------------------------------------------------------------------------
		// (-Array)
		int[] negativeArr = new int[cyclicArr.length]; 

		for (int i = 0; i < negativeArr.length; i++) {
			negativeArr[i] = (-1)*cyclicArr[i];
		}
		// Best_Max(-Array)
		int[] cyclicSum = Best_MaxMin_Array.Best_Max_Array(negativeArr);

		// (Array Sum)
		int arraySum = 0;

		for (int i = 0; i < cyclicArr.length; i++) {
			arraySum += cyclicArr[i];
		}
		// (Array Sum) + Best_Max(-Array)
		int cycSum = arraySum + cyclicSum[2]; // answer candidate number 2
		// ============================================================================================

		//            Max(part 1,    part 2)
		ans[2] = Math.max(regSumVal, cycSum);

		// now we need to find which indexes to take from: regSum OR cycSum?
		if(cycSum > regSumVal) { 
			ans[0] = cyclicSum[1]+1; // index repair: since we call Best_Max(-A) which returns the indexes of the opposite side of the circle.   
			ans[1] = cyclicSum[0]-1; // index repair: we want [Yes1][Yes2][Yes3][No1][No2][Yes4] - we want 5->2 but we get 3->4
		}             //                                      [0   ][1   ][2   ][3  ][4  ][5   ]
		else { // regSum >= cycSum 
			ans[0] = regSum[0];
			ans[1] = regSum[1];
		}
		return ans;
	}
	// ================================================================================================(end function)
	
	/**
	 * checking if an array is negative
	 * param arr - int arrays
	 * return:
	 * true - if all numbers are negative
	 * false - if there is at least one non-negative number
	 */
	public static boolean isArrNegative(int[] arr) {

		for (int num : arr){
			if (num >= 0){
				return false;
			}
		}
		return true;
	}

	
	/**
	 * creating random array
	 * @param len - length of the wanted random array
	 * @return random array 
	 */
	public static int[] randomArray(int len) {
		int[] randArr = new int[len];

		for (int i = 0; i < randArr.length; i++) {
			randArr[i] = (int)(Math.random()*13+1);

			int coin = (int)(Math.random()*10+1);

			if( coin >= 7) { // chance to convert number to negative = 30%
				randArr[i] = -randArr[i];
			}
		}
		return randArr;
	}
	// ================================================================================================(end function)


	public static void main(String[] args) {

		//int[] arr = {-6,3,-10,100,-18,20,-2};  // cyclic example
		//int[] arr = {6,-3, 10,-100,18,-20,2};  // non-cyclic (array) example
		//int[] arr = {-6,-3,-10,-100,-1,-18,-20,-2};  // all negative - special case
		//int[] arr = {5,13,-1,11}

		int[] randArr = randomArray(4); // create random array for tests
		int[] ans = bestSubcycle(randArr);
		Best_MaxMin_Array.print(randArr, ans); // (input-array, result-array[3])
		System.out.println("=================================================");
		
		// tests
//		for (int i = 0; i < 100; i++) {
//			randArr = randomArray( (i%5)+1 );
//			ans = bestSubcycle(randArr);
//			Best_Max_Array.print(randArr, ans);
//			System.out.println("=================================================");
//		}
	}
}
