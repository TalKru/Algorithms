package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//import javax.xml.bind.ParseConversionEvent;

public class BFS_cycleDetect {


	/**
	 * Checks if there is a cycle in a given undirected graph (Uses BFS).
	 * Note: if the graph has more than 1 component, 
	 * then only a single component will be scanned, which contains the startPoint vertex.
	 * @param graph - G(V, E).
	 * @param startPoint - vertex to start the search from
	 * @return [true] - cycle detected.
	 *  	   [false] - no cycle in the scanned component.
	 */
	public static boolean isCycle(ArrayList<Integer>[] graph, int startPoint) {

		Queue<Integer> queue = new LinkedList<Integer>();
		
		boolean[] visited = new boolean[graph.length];
		Arrays.fill(visited, false);

		int[] parent = new int[graph.length];
		Arrays.fill(parent, -1);

		// force init
		int current = startPoint;
		queue.add(current);
		visited[current] = true;

		while(!queue.isEmpty()) {

			current = queue.poll();

			for (int i = 0; i < graph[current].size(); i++) {

				int tempNeighbor = graph[current].get(i);

				if(visited[tempNeighbor] == false) {

					visited[tempNeighbor] = true;
					queue.add(tempNeighbor);
					parent[tempNeighbor] = current;
				}
				else if(visited[tempNeighbor] == true && tempNeighbor != parent[current]) { // if found visited neighbor which isn'r your parent
					
					System.out.println("Cycle was detected.");
					//	System.out.println("vertex (" + current + ") detected vertex (" + tempNeighbor + ") as cycle closure.");
					//	System.out.println("Cycle detected at level: " + dist[tempNeighbor]);
					return true;
				}
			}
		}
		//System.out.println(Arrays.toString(parent));
		System.out.println("No cycle in the scanned component.");
		return false;
	}
	// =========================================================================================================

	
	
	/**
	 * Searches for cycle in undirected graph.
	 * If graph has multiple component then search will take place only at the component containing the starting point input.
	 * in case of multiple cycles will return the the closet to given starting point
	 * @param graph - G(V, E).
	 * @param startPoint - vertex to start the search from
	 * @return in case there is cycle:    arrayList which represents the cycle [v1][v2]...[v1]
	 *         in case there is no cycle: arrayList contains one element [-1]
	 */
	public static ArrayList<Integer> findCycleBFS(ArrayList<Integer>[] graph, int startPoint) {

		ArrayList<Integer> cycle = new ArrayList<Integer>(); // result list

		Queue<Integer> queue = new LinkedList<Integer>();

		boolean[] visited = new boolean[graph.length];
		Arrays.fill(visited, false);

		int[] parent = new int[graph.length];
		Arrays.fill(parent, -1);

		int[] level = new int[graph.length]; // dist[]
		Arrays.fill(level, -1);

		int current = startPoint; // Spanning tree root vertex

		// force init
		queue.add(current);
		visited[current] = true;
		level[current] = 0; // root

		while(!queue.isEmpty()) {

			current = queue.poll();

			for(int i = 0; i < graph[current].size(); ++i) {

				int tempNeighbor = graph[current].get(i);

				if(visited[tempNeighbor] == false) {

					visited[tempNeighbor] = true;
					queue.add(tempNeighbor);
					level[tempNeighbor] = level[current] + 1;
					parent[tempNeighbor] = current;
				}
				/* if found visited neighbor which isn't your parent, then cycle is found. */
				else if(visited[tempNeighbor] == true && tempNeighbor != parent[current]) {
					
					System.out.println("Cycle detected at BFS level: " + level[tempNeighbor]);
					
					int L = current;	   //  <--
					int R = tempNeighbor;  //  -->

					/* balance the levels of L, R */
					if(level[L] > level[R]) { // if L is at deeper level of the spanning tree
						cycle.add(0, L); // save the used vertex in the cycle list
						L = parent[L]; // balance level, L vertex becomes his parent
					}
					else if(level[R] > level[L]){ // if R is at deeper level of the spanning tree
						cycle.add(R);
						R = parent[R];
					}
					else { /* (level[R] == level[L]) */ } // do nothing

					while(L != R) { // while did not reached the connecting vertex
						cycle.add(0, L);  //  <--  add to start of the list
						cycle.add(R);     //  -->  add to end of the list
						
						L = parent[L];
						R = parent[R];
					}
					
					// add the connecting vertex for both start & end (L == R)
					cycle.add(0, L);
					cycle.add(R);

					return cycle;
				}
			}
		}
		cycle.add(-1); // no cycle was found
		return cycle;
	}
	// =========================================================================================================



	// MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN
	public static void main(String[] args) {
		
		int n = 11; // UPDATE n                             <=======(!!!) piter

		ArrayList<Integer>[] graph = new ArrayList[n]; 

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		// =====================(start tree input)========== graph[].add();
		
		graph[0].add(9);
		graph[0].add(7);
		graph[0].add(5);
		graph[0].add(2);
		
		graph[1].add(2);
		
		graph[2].add(1);
		graph[2].add(10);
		graph[2].add(0);
		graph[2].add(3);
		
		graph[3].add(2);
		
		graph[4].add(5);
		
		graph[5].add(0);
		graph[5].add(4);
		
		graph[6].add(9);
		
		graph[7].add(0);
		
		graph[8].add(9);
		
		graph[9].add(6);
		graph[9].add(8);
		graph[9].add(0);
		
		graph[10].add(2);
		
		// cycle edge:
		graph[4].add(3);
		graph[3].add(4);
		
		
		/*
		int n = 9; // UPDATE n                             <=======(!!!) rexi

		ArrayList<Integer>[] graph = new ArrayList[n]; 

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		// =====================(start tree input)========== graph[].add();
		
		graph[0].add(7);
		graph[0].add(4);
		graph[0].add(3);
		graph[0].add(5);
		
		graph[1].add(6);
		graph[1].add(5);
		graph[1].add(4);
		graph[1].add(3);
		
		graph[2].add(5);
		
		graph[3].add(0);
		graph[3].add(4);
		graph[3].add(5);
		graph[3].add(1);
		
		graph[4].add(0);
		graph[4].add(3);
		graph[4].add(1);
		
		graph[5].add(2);
		graph[5].add(1);
		graph[5].add(3);
		graph[5].add(0);
		
		graph[6].add(1);
		
		graph[7].add(8);
		graph[7].add(0);
		
		graph[8].add(7);
		
		
		*/

		// test - run algorithm from each vertex and check for similar results
		for (int i = 0; i < graph.length; i++) {
			System.out.println("Start travers at: " + i);
			isCycle(graph, i);
			
			ArrayList<Integer> ans = findCycleBFS(graph, i);
			System.out.println(ans.toString());
			System.out.println("-----------------------------------------");
		}

		//		ArrayList<Integer> ans = findCycleBFS(graph, 6);
		//		System.out.println(ans.toString());

		//		boolean res = isCycle(graph, 6);
		//		System.out.println(res);
	}

}
