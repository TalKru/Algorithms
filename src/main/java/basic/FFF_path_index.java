package basic;

import java.util.Arrays;

public class FFF_path_index {

	public static int[][] FFF_index_paths(int[][] adjacencyMat ){

		int len = adjacencyMat.length;
		//		int checkInput = adjacencyMat[0].length;
		//		if( len != checkInput) {System.exit(-1);}

		int[][] pathMat = new int[len][len];         // the answer
		String[][] indexPath = new String[len][len]; // the answer

		// copy the adjacency matrix
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				pathMat[i][j] = adjacencyMat[i][j];
			}
		}

		// initialize the String mat
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if(i==j) {
					indexPath[i][j] = "Stay";
				}
				else if(adjacencyMat[i][j] < Integer.MAX_VALUE) {
					indexPath[i][j] = " "+i+"->"+j+" ";
				}
				else {
					indexPath[i][j] = "";
				}
			}
		}
		//==================================================================(testing...)
		System.out.println();
		printStringMat(indexPath);
		System.out.println();
		printMatrix(pathMat);
		//==================================================================(----------)


		///FFF
		for (int k = 0; k < len; k++) {
			for (int i = 0; i < len; i++) {    // Pick all vertices as source one by one 
				for (int j = 0; j < len; j++) {  // Pick all vertices as destination for the above picked source 

					// option 2 - check if i>--->k>--->j path is better than i>--->j path
					if(pathMat[i][k] != Integer.MAX_VALUE && pathMat[k][j] != Integer.MAX_VALUE ) {
						if( pathMat[i][k] + pathMat[k][j] < pathMat[i][j] ) {
							pathMat[i][j] = pathMat[i][k] + pathMat[k][j];

							// update the paths indexes
							indexPath[i][j] = indexPath[i][k] + indexPath[k][j];
						}
					}
				}
			}
		}
		//==================================================================(testing...)
		System.out.println();
		printStringMat(indexPath);
		System.out.println();
		printMatrix(pathMat);
		//==================================================================(----------)

		return pathMat;
	} /// FFF






	// print String matrix
	public static void printStringMat(String[][] mat) {

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				
				if(mat[i][j] == "" || mat[i][j] == null) {
					System.out.print("[-X-]");
				}
				else {
					System.out.print("[" +  mat[i][j]  + "] ");
				}
			}
			System.out.println();
		}
	}

	
	// print number matrix
	public static void printMatrix(int[][] mat) {
		int INF = Integer.MAX_VALUE;

		for (int i = 0; i < mat.length; i++) {
			System.out.print("[");
			for (int j = 0; j < mat.length; j++) {

				if (j < mat.length-1) {
					if(mat[i][j] == INF) System.out.print( "INF, ");
					else { System.out.print(mat[i][j] + ", "); }
				}
				else {
					if(mat[i][j] == INF) System.out.print( "INF");
					else { System.out.print(mat[i][j]); }
				}
			}
			System.out.println("]");
		}
		System.out.println();
	}













	public static void main(String[] args) {
		// MAX_VALUE = 2147483647;
		int inf = Integer.MAX_VALUE;

		int[][] adjacencyMat =  
			{
					{ 0, 70, 3, inf, inf, 5  }, 
					{ 70, 0, inf, inf, 1, 7  }, 
					{ 3, inf, 0, 1, inf, inf }, 
					{ inf, inf, 1, 0, 1, inf }, 
					{ inf, 1, inf, 1, 0, inf }, 
					{ 5, 7, inf, inf, inf, 0 }, 
			};

		FFF_index_paths(adjacencyMat);



		/*
				int[][] pathMat = floyd_warshall(adjacencyMat); // get the answer from Floyd-Warshall algorithm

				System.out.println("Adjacency Matrix:");
				for (int i = 0; i < adjacencyMat.length; i++) {
					System.out.print("[");
					for (int j = 0; j < adjacencyMat.length; j++) {

						if (j < adjacencyMat.length-1) {
							if(adjacencyMat[i][j] >= inf) System.out.print( "inf, ");
							else { System.out.print(adjacencyMat[i][j] + ", "); }
						}
						else {
							if(adjacencyMat[i][j] >= inf) System.out.print( "inf");
							else { System.out.print(adjacencyMat[i][j]);  }
						}
					}
					System.out.println("]");
				} // print

				System.out.println("\nShortest paths Matrix:");
				for (int i = 0; i < pathMat.length; i++) {
					System.out.print("[");
					for (int j = 0; j < pathMat.length; j++) {

						if (j < pathMat.length-1) {
							if(pathMat[i][j] >= inf) System.out.print( "inf, ");
							else { System.out.print(pathMat[i][j] + ", "); }
						}
						else {
							if(pathMat[i][j] >= inf) System.out.print( "inf");
							else { System.out.print(pathMat[i][j]);  }
						}
					}
					System.out.println("]");
				} // print
		 */
	} // main
} // class
