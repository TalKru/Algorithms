package basic;

import java.util.Arrays;

public class FFF_weight_on_vertex_and_edge {


	public static int[][] FW_weight_on_vertex_and_edge(int[][] adjcnMat, int[] A){
		
		// check input
		if(adjcnMat.length != adjcnMat[0].length || adjcnMat.length != A.length) {
			System.err.println("Error! Wrong input.");
			System.exit(0);
		}

		// first we need to convert the given input (adjacency matrix + vertices array) 
		// to a known input for the classic FW algorithm (adjacency matrix).
		// we transform a graph with weights on both vertices and edges to a graph with weights only on the edges.
		// by using a weight of and an edge W*(i,j)  
		// (when W(i,j) was a weight on old graph, and Vi,Vj are the vertices which connected by edge W) such that:
		//    W*(i,j) = ( f[Vi] + f[Vj] + 2*W(i,j) )/2
		// we multiply by 2 the old edge so our previous FIX (in Floyd_Warshall_weight_on_vertex function) will remain the same

		int n = adjcnMat.length;
		int[][] pathMat = new int[n][n]; // answer

		for (int i = 0; i < pathMat.length; i++) {
			for (int j = 0; j < pathMat.length; j++) {
				// weight from a 
				if(i!=j) {
					if( adjcnMat[i][j] != Integer.MAX_VALUE ) { 
						pathMat[i][j] = 2*adjcnMat[i][j] + A[i] + A[j]; // PUNCH LINE IS HERE ;)
					}
					else { // if there is NO edge between the vertices =>   adjcnMat[i][j] == Integer.MAX_VALUE
						pathMat[i][j] = Integer.MAX_VALUE;
					}
				}
				// if i==j the diagonal must be always zeros[0][0]...[0]
				// since we DO NOT allow anymore weights of vertices
				else {
					pathMat[i][i] = adjcnMat[i][i];
				}
			}
		}

		// ===================================================================================>
		System.out.println("After the conversion - phase (1):");
		printMatrix(pathMat);
		// ===================================================================================>

		// now calling the original Floyd Warshall function
		pathMat = FFF_weight_on_edge_classic.floyd_warshall(pathMat);

		// ===================================================================================>
		System.out.println("After calling the original Floyd Warshall function - phase (2):");
		printMatrix(pathMat);
		// ===================================================================================>

		// NOW THE FIX
		for (int i = 0; i < pathMat.length; i++) {
			for (int j = 0; j < pathMat.length; j++) {
				// NOTE that it "may" be more efficient to copy the diagonal from the vertex weight array
				pathMat[i][j] = ( A[i] + pathMat[i][j] + A[j] )/2;
				
			}
		}

		// ===================================================================================>
		System.out.println("After applying the fix - phase (3):");
		printMatrix(pathMat);
		// ===================================================================================>

		return pathMat;
	} // FFF



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
		int[] verArr = {3,5,2,8}; // vertices weights
		int[][] adjMat =  
			{
					{0, 1, INF, 10}, 
					{1, 0, 3, INF}, 
					{INF, 3, 0, 5}, 
					{10, INF, 5, 0}, 
			};

		System.out.println("Input: Vertecies weights:");
		System.out.println(Arrays.toString(verArr));
		System.out.println("Input: Egdes weights:");
		printMatrix(adjMat);

		int[][] ansMat = FW_weight_on_vertex_and_edge(adjMat, verArr);

		System.out.println("Answer: Shortest paths costs:");
		printMatrix(ansMat);







	} // main
} // class
