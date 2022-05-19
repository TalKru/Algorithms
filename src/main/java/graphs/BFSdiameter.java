package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSdiameter {

	/**
	 * Finds the diameter (2 vertices) of a Tree graph only (uses BFS technique 2 time).
	 * WILL NOT WORK ON:
	 * 1) UNCONNECTED GRAPH
	 * 2) GRAPH WITH CYCLES
	 * return - 2 vertices which represents the diameter edges
	 * [Vertex 1][Vertex 2][diameter size]
	 * [   0    ][   1    ][      2      ]
	 */
	public static int[] BFSdiameterLimits(ArrayList<Integer>[] graph) {

		int[] result = new int[3];
		
		int firstPart = BFSmostDistantVertex(graph, 0)[0]; // ignore the first BFS
		int[] secondPart = BFSmostDistantVertex(graph, firstPart);
		
		result[0] = firstPart;
		result[1] = secondPart[0];
		result[2] = secondPart[1];

		System.out.println("Edges of the graph diameter: (" + result[0] + ")--(...)--(" + result[1] + ")");
		System.out.println("diameter length: " + result[2]);

		return result;
	}
	// ==================================================================================================

	/**
	 * Searches for the most distant point from a given starting point
	 * param graph - G(E,V)
	 * param startPoint - a point to start from the graph traversal
	 * return one of the most distant points from the starting point
	 * or -1 in case of not fully connected graph
	 * [ Vertex ][length to the most distant]
	 * [   0    ][             1            ]
	 */
	public static int[] BFSmostDistantVertex(ArrayList<Integer>[] graph, int startPoint) {

		Queue<Integer> queue = new LinkedList<>();

		boolean[] visitedArr = new boolean[graph.length];  
		Arrays.fill(visitedArr, false); // [F][F][F]...[F]

		int[] distFromStart = new int[graph.length];
		Arrays.fill(distFromStart, -1); // [-1][-1][-1]...[-1]
		
		// force init
		int currVer = startPoint;
		queue.add(currVer);  
		distFromStart[currVer] = 0; 
		visitedArr[currVer] = true;

		while( !queue.isEmpty() ) { // while there is Vertices in the queue

			currVer = queue.poll(); // take the next neighbor

			for (int i = 0; i < graph[currVer].size(); i++) { // looping over the neighbors

				int currNeighbor = graph[currVer].get(i);

				if(visitedArr[currNeighbor] == false) { // if not yet visited

					visitedArr[currNeighbor] = true;  // mark as visited
					queue.add(currNeighbor);
					distFromStart[currNeighbor] = distFromStart[currVer]+1; // update distance from starting point
				}
			}
		}
		//System.out.println("Starting point (Vertex): " + startPoint);
		//System.out.println(Arrays.toString(distFromStart));

		int maxDistant = -1;
		int mostDistantVer = -1;

		for (int i = 0; i < distFromStart.length; i++) {

			if(distFromStart[i] > maxDistant) {
				maxDistant = distFromStart[i];
				mostDistantVer = i;
			}
		}
		//System.out.println("Most distant point from: [" + startPoint + "] is: ["  + mostDistantVer + "].");
		
		int[] result = new int[2];
		
		result[0] = mostDistantVer;
		result[1] = maxDistant;
		
		return result;
	}
	// ==================================================================================================


	public static void main(String[] args) {

		int n = 8; // UPDATE <=======(!)

		ArrayList<Integer>[] tree = new ArrayList[n]; 
		for (int i = 0; i < tree.length; i++) {
			tree[i] = new ArrayList<>();
		}
		// =====================(start tree input)==================== tree[].add();
		
		/** IN CASE OF ERROR - UPDATE: n */
		tree[0].add(2);
		tree[0].add(3);
		tree[0].add(4);
		
		tree[1].add(2);
		
		tree[2].add(1);
		tree[2].add(0);
		tree[2].add(6);

		tree[3].add(0);
		
		tree[4].add(0);
		tree[4].add(5);
		
		tree[5].add(4);
		tree[5].add(7);
		
		tree[6].add(2);
		
		tree[7].add(5);

		// =====================(end tree)============================
		BFSdiameterLimits(tree);
	}
}
