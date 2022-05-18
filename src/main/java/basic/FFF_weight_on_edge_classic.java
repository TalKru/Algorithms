package basic;

import java.util.Arrays;

public class FFF_weight_on_edge_classic {

	/**
	 * FFF with weights on edges only - basic problem O(n^3)
	 * @param adjacencyMat
	 * @return pathMat - matrix of the shortest paths between any pair of vertices
	 */
	public static int[][] floyd_warshall(int[][] adjacencyMat ) {
		
		// check if the input mat is NxN
//		if(adjacencyMat.length != adjacencyMat[0].length) {
//			System.err.println("Error input, Matrix is not square.");
//		}
		
		int n = adjacencyMat.length;

		int[][] pathMat = new int[n][n]; // return result
		
		// copy the adjacency matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				pathMat[i][j] = adjacencyMat[i][j];
			}
		}
		
		///FFF
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {    // Pick all vertices as source one by one 
				for (int j = 0; j < n; j++) {  // Pick all vertices as destination for the above picked source 
					
					// option 1 - most simple  
					if(pathMat[i][k] != Integer.MAX_VALUE && pathMat[k][j] != Integer.MAX_VALUE ) {
						pathMat[i][j] = Math.min(pathMat[i][j], pathMat[i][k] + pathMat[k][j]);
					}
					
					// option 2 - check if i>--->k>--->j path is better than i>--->j path
//					if(pathMat[i][k] != Integer.MAX_VALUE && pathMat[k][j] != Integer.MAX_VALUE ) {
//						if( pathMat[i][k] + pathMat[k][j] < pathMat[i][j] ) {
//							pathMat[i][j] = pathMat[i][k] + pathMat[k][j];
//						}
//					}
				}
			}
		}
		return pathMat;
	} /// FFF
	

	public static void main(String[] args) {
		
		// int.MAX_VALUE == 2147483647;
		//            INF = 1000000000;
		int INF = Integer.MAX_VALUE;
		
		int[][] adjacencyMat =  
		{
			{  0,   1,  INF,  3  }, 
			{  1,   0,   11,  INF}, 
			{INF,  11,    0,  5  }, 
			{  3, INF,    5,  0  }, 
		};
		
		int[][] pathMat = floyd_warshall(adjacencyMat); // get the answer from Floyd-Warshall algorithm
		
		System.out.println("Adjacency Matrix:");
//		for (int i = 0; i < adjacencyMat.length; i++) {
//			System.out.println(Arrays.toString(adjacencyMat[i]));
//		}
		for (int i = 0; i < adjacencyMat.length; i++) {
			System.out.print("[");
			for (int j = 0; j < adjacencyMat.length; j++) {
				
				if (j < adjacencyMat.length-1) {
					if(adjacencyMat[i][j] >= INF) System.out.print( "INF, ");
					else { System.out.print(adjacencyMat[i][j] + ", "); }
				}
				else {
					if(adjacencyMat[i][j] >= INF) System.out.print( "INF");
					else { System.out.print(adjacencyMat[i][j]);  }
				}
			}
			System.out.println("]");
		} // print
		
		System.out.println("\nShortest paths Matrix:");
		for (int i = 0; i < pathMat.length; i++) {
			System.out.println(Arrays.toString(pathMat[i]));
		}
		
//		for (int i = 0; i < pathMat.length; i++) {
//			System.out.print("[");
//			for (int j = 0; j < pathMat.length; j++) {
//				
//				if (j < pathMat.length-1) {
//					if(pathMat[i][j] >= INF) System.out.print( "INF, ");
//					else { System.out.print(pathMat[i][j] + ", "); }
//				}
//				else {
//					if(pathMat[i][j] >= INF) System.out.print( "INF");
//					else { System.out.print(pathMat[i][j]);  }
//				}
//			}
//			System.out.println("]");
//		} // print
		
	}
}











