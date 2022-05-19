package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class questionTree {

	// [diameter][vi][vj]
	public static boolean isGivenVertexIsOnDiameter(ArrayList<Integer>[] tree, int isVertexOnDiameter) {

		int[] parent = new int[tree.length]; // on the second BFS it will return the correct parrent array

		int bfs1 = BFS_mostDistant(tree, 0, parent);
		int bfs2 = BFS_mostDistant(tree, bfs1, parent);

		int tempTravelVertex = bfs2;

		while(tempTravelVertex != -1) {

			if(tempTravelVertex == isVertexOnDiameter) {
				System.out.println("Vertex: " + isVertexOnDiameter + "is a part of the tree diameter vertices. [v]");
				return true;
			}
			tempTravelVertex = parent[tempTravelVertex];
		}
		
		System.out.println("Vertex: " + isVertexOnDiameter + "is not part of the tree diameter. [x]");
		return false;
	}


	// return the most distant vertex from given start pint
	public static int BFS_mostDistant(ArrayList<Integer>[] tree, int start, int[] parent) {

		Arrays.fill(parent, -1);

		Queue<Integer> queue = new LinkedList<>();
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

					parent[tempNeighbor] = curr;
					
					visited[tempNeighbor] = true;
					queue.add(tempNeighbor);
					dist[tempNeighbor] = dist[curr] + 1;
				}
			}
		}
		// now find the most distant vertex by the dist array
		int maxDist = -1;
		int vertexIndex = -1;

		for (int i = 0; i < dist.length; i++) {
			if(dist[i] > maxDist) {
				maxDist = dist[i];
				vertexIndex = i;
			}
		}
		return vertexIndex;
	}


	public static void main(String[] args) {

		int n = 9; // UPDATE <=======(!)
		ArrayList<Integer>[] tree = new ArrayList[n];

		for (int i = 0; i < tree.length; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		// =====================(start tree input)==================== tree[].add();
		tree[0].add(1);
		tree[1].add(0);
		
		tree[1].add(2);
		tree[2].add(1);
		
		tree[2].add(3);
		tree[3].add(2);
		
		tree[4].add(3);
		tree[3].add(4);
		
		
		tree[5].add(4);
		tree[4].add(5);
		
		tree[6].add(8);
		tree[8].add(6);
		
		tree[4].add(8);
		tree[8].add(4);
		
		tree[1].add(7);
		tree[7].add(1);
		
		tree[5].add(6);
		tree[6].add(5);

		// =====================(end tree)============================
		int isVertexOnDiameter = 2;
		System.out.println( isGivenVertexIsOnDiameter(tree, isVertexOnDiameter) );
	}
}
