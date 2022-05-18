package basic;

import java.util.Arrays;

public class FFF_weight_on_vertex {

	public static int[][] Floyd_Warshall_weight_on_vertex(boolean[][] boolMat, int[] A) {

		int n = boolMat.length;
		int[][] FW_Mat = new int[n][n]; // we create an int matrix for the regular FW function as input

		// Conversion of the input from VERTEX weight TO EDGE weight:
		// each "cell" (edge) in the coverted matrix is the sum of its both connection vertices:
		
		//        weight            weight
		// from:   (3)Vi<--------->Vj(4)   
		// to:     Vi(_)<--(3+4)-->(_)Vj
		for (int i = 0; i < n; i++) { 
			for (int j = 0; j < n; j++) {
				// we know that the weight (cost) from a vertex to itself is 0
				if(i==j) {
					FW_Mat[i][j] = 0;
				}
				// in the bool adjacency Matrix:
				// Vertices i,j are NOT connected (F) then there is no edge between them so the weight is INF
				// if Vertices i,j are connected (T) then the edge is the sum of Vertices i,j
				else if(boolMat[i][j] == false) {
					FW_Mat[i][j] = Integer.MAX_VALUE;
				}
				else {
					// (3)<----->(4)    ==>     (V1)<--(3+4)-->(V2)
					FW_Mat[i][j] = A[i] + A[j];
				}
			}
		}

		// ===========================(testing)====================================== //
		System.out.println("After the conversion FROM VERTEX weight TO EDGE weight:");
		printMatrix(FW_Mat);
		// ========================================================================== //
		
		// send to original floyd_warshall function
		FW_Mat = FFF_weight_on_edge_classic.floyd_warshall(FW_Mat);
		
		// ===========================(testing)====================================== //
		System.out.println("After sending to original floyd_warshall function:");
		printMatrix(FW_Mat);
		// ========================================================================== //


		// NOW THE FIX
		//     A[i]  +     FW_Mat[i][j]     + A[j]
		//   ( { i } + {i +k1+k2+...+kn+ j} + { j } ) / 2                             
		for (int i = 0; i < FW_Mat.length; i++) {
			for (int j = 0; j < FW_Mat.length; j++) {

				// apply the fix:
				// fixed path cell = (2i + k1 + k2 + ... + kn + 2j)/2
				if(FW_Mat[i][j] != Integer.MAX_VALUE) {
					FW_Mat[i][j] = (A[i] + FW_Mat[i][j] + A[j]) / 2;
				}
			}
		}

		// ===========================(testing)====================================== //
		System.out.println("AFTER THE FIX:");
		printMatrix(FW_Mat);
		// ========================================================================== //

		return FW_Mat;
	}

	
	
	
	// print function
	public static void printMatrix(int[][] mat) {
		int INF = Integer.MAX_VALUE;
	
		for (int i = 0; i < mat.length; i++) {
			System.out.print("[");
			for (int j = 0; j < mat.length; j++) {
				
				if (j < mat.length-1) {
					if(mat[i][j] >= INF) System.out.print( "INF, ");
					else { System.out.print(mat[i][j] + ", "); }
				}
				else {
					if(mat[i][j] >= INF) System.out.print( "INF");
					else { System.out.print(mat[i][j]); }
				}
			}
			System.out.println("]");
		}
		System.out.println();
	}


	// MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN 
	public static void main(String[] args) {

		int INF = Integer.MAX_VALUE;
		boolean T = true;
		boolean F = false;		

//		int[] vertexWeight = {1,2,3,4};
//		boolean[][] adjacencyMat =  
//			{
//					{F, T, F, F}, 
//					{T, F, T, F}, 
//					{F, T, F, T}, 
//					{F, F, T, F}, 
//			};
		
		int[] vertexWeight = {1,6,7,0,11,4,7};
		boolean[][] adjacencyMat =  
			{     // 0  1, 2, 3, 4, 5, 6
					{F, F, F, T, F, T, F}, // 0
					{F, F, F, F, T, F, T}, // 1
					{F, F, F, T, T, F, F}, // 2
					{T, F, T, F, F, F, T}, // 3
					{F, T, T, F, F, T, F}, // 4
					{T, F, F, F, T, F, F}, // 5
					{F, T, F, T, F, F, F}, // 6
			};
		
		int[][] ansMat = Floyd_Warshall_weight_on_vertex(adjacencyMat, vertexWeight);  // ANSWER: Shortest Paths
		System.out.println("Answer - Shortest paths costs:");
		printMatrix(ansMat);

	} // main
} // class
