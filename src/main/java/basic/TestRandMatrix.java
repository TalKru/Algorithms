package basic;

import java.util.Arrays;

public class TestRandMatrix {


	public static void printMat(int[][] mat) {

		for (int i = 0; i < mat.length; i++) {
			
			System.out.print("[");
			
			for (int j = 0; j < mat[0].length; j++) {
				System.out.printf("%4d", mat[i][j] );
				
//				if(j < mat[0].length-1 ) {
//					System.out.print(",");
//				}
				
			}
			System.out.print(" ]");
			System.out.println();
		}
	}



	public static void main(String[] args) {

		int rows = 4;
		int cols = 5;

		int[][] mat = new int[rows][cols];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {

				mat[i][j] = (int)(Math.random()*13+1);

				int coin = (int)(Math.random()*10+1);

				if( coin >= 6) { // chance to convert number to negative = 40%
					mat[i][j] = -mat[i][j];
				}

			}
		}

		int[] result_1 = test.bestSubRectangle(mat);
		int[] result_2 = Best_Max_Rectangle.bestSubRectangle(mat);
		printMat(mat);
		System.out.println();


		System.out.println();
		System.out.println("result_1: ");
		System.out.println(Arrays.toString(result_1));
		System.out.println("result_2: ");
		System.out.println(Arrays.toString(result_2));


	}

}
