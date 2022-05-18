package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSdiameter2 {



	/**
	 * Finds the diameter (2 vertices) of a Tree graph only (uses BFS technique 2 time).
	 * WILL NOT WORK ON:
	 * 1) UNCONNECTED GRAPH
	 * 2) GRAPH WITH CYCLES
	 * @return - 2 vertices which represents the diameter edges
	 * [Vertex 1][Vertex 2][diameter size]
	 * [   0    ][   1    ][      2      ]
	 */
	// [diameter][vi][vj]
	public static int[] BFS_diameter(ArrayList<Integer>[] tree) {

		int[] bfs1 = new int[2];
		int[] bfs2 = new int[2];

		bfs1 = BFS_mostDistant(tree, 0);

		int vertex1 = bfs1[0]; // first vertex of diameter

		bfs2 = BFS_mostDistant(tree, vertex1);

		int vertex2 = bfs2[0];

		int diameter = bfs2[1]; // only after the second bfs return the correct diameter

		int[] result = new int[3];

		result[0] = diameter;
		result[1] = vertex1;
		result[2] = vertex2;

		System.out.println("diameter: " + result[0]);
		System.out.println("vertex1: " + result[1]);
		System.out.println("vertex2: " + result[2]);

		return result;
	}



	/* returns [vertex][distance] */
	public static int[] BFS_mostDistant(ArrayList<Integer>[] tree, int start) {

		Queue<Integer> queue = new LinkedList<Integer>();

		int[] dist = new int[tree.length];
		Arrays.fill(dist, -1);

		boolean[] visited = new boolean[tree.length];
		Arrays.fill(visited, false);

		int curr = start;

		queue.add(curr);
		dist[curr] = 0;
		visited[curr] = true;

		while(!queue.isEmpty()) {

			curr = queue.poll();

			for (int i = 0; i < tree[curr].size(); i++) {

				int tempNeighbor = tree[curr].get(i);

				if(visited[tempNeighbor] == false) {

					visited[tempNeighbor] = true;
					queue.add(tempNeighbor);
					dist[tempNeighbor] = dist[curr]+1;
				}
			}
		}

		// now find the most distant by the dist array
		int maxDist = -1;
		int vertexIndex = -1;

		for (int i = 0; i < dist.length; i++) {
			if(dist[i] > maxDist) {
				maxDist = dist[i];
				vertexIndex = i;
			}
		}
		int[] result = new int[2]; // [most distant vertex][dist from start to most distant vertex]
		result[0] = vertexIndex;
		result[1] = maxDist;
		return result;
	}





	// MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN 
	public static void main(String[] args) {

		int n = 8; // UPDATE <=======(!) <=======(!) <=======(!) <=======(!) <=======(!)

		ArrayList<Integer>[] tree = new ArrayList[n]; 
		for (int i = 0; i < tree.length; i++) {
			tree[i] = new ArrayList<Integer>();
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

		BFS_diameter(tree);

	} // main


}
