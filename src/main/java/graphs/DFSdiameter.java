package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFSdiameter {

	/**
	 * Finds the diameter of a tree graph
	 * @param tree - input graph must be a tree
	 * @return array - two vertices which represents the limits of the diameter
	 */
	public static int[] DFSdiamet(ArrayList<Integer>[] tree){

		int[] result = new int[2];
		result[0] = DFSmostDistantLeaf(tree, 0);
		result[1] = DFSmostDistantLeaf(tree, result[0]);

		System.out.println("Diameter limits: (" + result[0] + ")----~[...]~----(" + result[1] + ")");
		return result;
	}

	/**
	 * Finds the most distant vertex from the 
	 * given starting point using DFS search. Natively in a tree, this vertex would be one of the diameter limits
	 * @param tree - input graph must be a tree
	 * @param start - vertex to begin DFS from
	 * @return most distant vertex from the given starting point
	 */
	public static int DFSmostDistantLeaf(ArrayList<Integer>[] tree, int start){

		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[tree.length];
		int[] dist = new int[tree.length];

		for (int i = 0; i < dist.length; i++) { 
			dist[i] = -1;                         // mark all as unreachable (-1)
		} 

		// force start
		int current = start;
		visited[current] = true;
		dist[current] = 0;
		stack.push(current);
		
		System.out.print("Start-> " + current);

		while(!stack.isEmpty()) {

			for (int i = 0; i < tree[current].size(); i++) {

				int tempNeighbor = tree[current].get(i);

				if(visited[tempNeighbor] == false) {

					//System.out.println("found unvisited node: " + tempNeighbor);
					System.out.print(" -> " + tempNeighbor);

					dist[tempNeighbor] = dist[current] +1;
					visited[tempNeighbor] = true; // mark as visited
					stack.push(tempNeighbor);
					current = tempNeighbor;
					i = -1; // reset loop, -1++ = 0, and we start from the beginning again, only with other vertex
				}
			}
			stack.pop();

			if(stack.isEmpty() == false) { // after the last vertex will pop the stack will be empty and peek() will throw exception
				current = stack.peek();
			}
		}

		// find the most distant vertex
		int Index = 0;
		int maxVal = Integer.MIN_VALUE;
		for (int i = 0; i < dist.length; i++) {
			if(dist[i] > maxVal) {
				maxVal = dist[i];
				Index = i;
			}
		}
		
		System.out.println(Arrays.toString(dist));

		
		return Index;
	}




	// MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN 
	public static void main(String[] args) {
		int n = 16; // UPDATE <=======(!) <=======(!) <=======(!) <=======(!) <=======(!)

		ArrayList<Integer>[] graph = new ArrayList[n]; 
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		// =====================(start tree input)
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
		// =====================(end tree)

		DFSdiamet(graph);

	} // main

}
