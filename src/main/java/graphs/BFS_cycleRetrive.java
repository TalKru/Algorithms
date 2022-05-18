package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Searches for cycle in undirected graph.
 * If graph has multiple component then search will take place only at the component containing the starting point input.
 * in case of multiple cycles will return the the closet to given starting point
 * @param graph - G(V, E).
 * @param startPoint - vertex to start the search from
 * @return in case there is cycle:    arrayList which represents the cycle [v1][v2]...[v1]
 *         in case there is no cycle: arrayList contains one element [-1]
 */
public class BFS_cycleRetrive {

	/* find and retrive a first encountered cycle in a graph which has at leat one cycle */
	public static ArrayList<Integer> BFS_cycleRetrive(ArrayList<Integer>[] graph, int start){
		
		int n = graph.length;
		
		ArrayList<Integer> cycle = new ArrayList<Integer>(); // answer
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		int[] parent = new int[n];
		Arrays.fill(parent, -1);
		
		boolean[] visited = new boolean[n];
		Arrays.fill(visited, false); // no vertex is visited by default
		
		int[] dist = new int[n];
		Arrays.fill(dist, -1);
		
		int curr = start; // start point
		
		queue.add(curr); // init
		dist[curr] = 0; // spanning tree root
		visited[curr] = true;
		// parent[curr] remain -1 since the root has no parent
		
		while(!queue.isEmpty()) {
			
			curr = queue.poll();
			
			for (int i = 0; i < graph[curr].size(); i++) {
				
				int tempNeighbor = graph[curr].get(i);
				
				if(visited[tempNeighbor] == false) { // regular case: found new vertex
					
					visited[tempNeighbor] = true;
					queue.add(tempNeighbor);
					dist[tempNeighbor] = dist[curr]+1;
					parent[tempNeighbor] = curr;
				}
				else if(visited[tempNeighbor] == true  && tempNeighbor != parent[curr]) { // found cycle!
					
					System.out.println("Cycle detected at BFS level: " + dist[tempNeighbor]);
					
					// check if brother or son
					if(dist[curr] != dist[tempNeighbor]) { // "son" case: must level them first
						cycle.add(tempNeighbor);
						tempNeighbor = parent[tempNeighbor];
					}
					// else case is brother: when they have same distance from root
					
					while(tempNeighbor != curr) {
						cycle.add(0, tempNeighbor); // tempNeighbor on left side
						cycle.add(curr);     		// curr on the right side
						
						tempNeighbor = parent[tempNeighbor];
						curr = parent[curr];
					}
					cycle.add(0, curr); // only need to add the root
					cycle.add(curr);            // only need to add the root
					
					System.out.println("found cycle:" + cycle.toString());
					return cycle;
				} // if cycle is found
			}
		}
		System.out.println("No cycle was detected in this graph");
		return cycle;
	}
	// ============================================================================================
	
	
	
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
	
	
	
	// MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN
		public static void main(String[] args) {
			
			/*
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
			*/
			
			
			int n = 10; // UPDATE n                             <=======(!!!) rexi

			ArrayList<Integer>[] graph = new ArrayList[n]; 

			for (int i = 0; i < graph.length; i++) {
				graph[i] = new ArrayList<Integer>();
			}

			// =====================(start tree input)========== graph[].add();
			
			graph[9].add(1);
			graph[9].add(5);
			graph[1].add(9);
			graph[5].add(9);
			
			graph[0].add(7);
			graph[0].add(4);
			graph[0].add(3);
			graph[0].add(5);
			
			graph[1].add(6);
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
			graph[5].add(3);
			graph[5].add(0);
			
			graph[6].add(1);
			
			graph[7].add(8);
			graph[7].add(0);
			
			graph[8].add(7);
			
			
			
			
			// test - run algorithm from each vertex and check for similar results
			for (int i = 0; i < graph.length; i++) {
				System.out.println("Start travers at: " + i);
				isCycle(graph, i);
				
				ArrayList<Integer> ans = BFS_cycleRetrive(graph, i);
				System.out.println(ans.toString());
				System.out.println("-----------------------------------------");
			}

			//		ArrayList<Integer> ans = findCycleBFS(graph, 6);
			//		System.out.println(ans.toString());

			//		boolean res = isCycle(graph, 6);
			//		System.out.println(res);
		}
}
