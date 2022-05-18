package graphs;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class DFS_travel_dist {

	// returns the most distant vertex from the given start point
	// input must be a tree
	// tree since of the diameter
	public static void DFS(ArrayList<Integer>[] tree, int start) {

		// TODO - check valid tree??? input...

		Stack<Integer> stack = new Stack<Integer>();
		
		boolean[] visited = new boolean[tree.length];
		Arrays.fill(visited, false);


		int[] dist = new int[tree.length];
		Arrays.fill(dist, -1);

		int current = start;
		stack.push(current);     // force start
		visited[current] = true; // force start
		dist[current] = 0;       // force start
		
		while(!stack.isEmpty()) {

			for (int i = 0; i < tree[current].size(); i++) { // loop over neighbors

				int tempNeighbor = tree[current].get(i);

				if(visited[tempNeighbor] == false) { // if not found yet visited vertex 

					stack.push(tempNeighbor);
					visited[tempNeighbor] = true; 
					dist[tempNeighbor] = dist[current] + 1; // distance of new child = distance of parent + 1
					current = tempNeighbor;

					i = -1; // reset loop to start
				} // if
				
			} 

 
			stack.pop(); 

			if(!stack.isEmpty()) {
				current = stack.peek();
			}
		} // while loop

		
		System.out.println(Arrays.toString(dist));
	}




	// MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN 
	public static void main(String[] args) {
		int n = 16; // UPDATE <=======(!) <=======(!) <=======(!) <=======(!) <=======(!)

		ArrayList<Integer>[] graph = new ArrayList[n];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		// =====================(start graph input)      graph[].add();
		graph[0].add(10);
		graph[0].add(6);
		graph[0].add(7);
		graph[0].add(4); 

		graph[1].add(7);

		graph[2].add(10);
		graph[2].add(15);

		graph[3].add(13);

		graph[4].add(13);
		graph[4].add(12);
		graph[4].add(0);


		graph[5].add(6);

		graph[6].add(9);
		graph[6].add(5);
		graph[6].add(0);

		graph[7].add(0);
		graph[7].add(1);
		graph[7].add(8);

		graph[8].add(7);

		graph[9].add(6);

		graph[10].add(0);
		graph[10].add(2);
		graph[10].add(11);

		graph[11].add(10);

		graph[12].add(14);
		graph[12].add(4);

		graph[13].add(3);
		graph[13].add(4);

		graph[14].add(12);

		graph[15].add(2);
		// =====================(end graph)

		DFS(graph, 0);

	}

}
