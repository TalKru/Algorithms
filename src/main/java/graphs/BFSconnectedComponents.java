package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSconnectedComponents {
	
	/**
	 * Finds the number of connected components in graph:
	 * param graph - G(E,V).
	 * return amount of connected components.
	 */
	public static int BFScomponentsNum(ArrayList<Integer>[] graph) { // O(n)
		
		int compCounter = 0;
		boolean[] scanned = new boolean[graph.length];
		Arrays.fill(scanned, false);
		
		for (int i = 0; i < scanned.length; i++) { // try to run BFS from each possible vertex
			
			if(scanned[i] == false) { // when an unvisited vertex is found its time to scan the conponent its belongs to
				compCounter++;
				scanned[i] = true; // the inner function might also be doing this but never mind
				BFS_components_scan(graph, scanned, i); // i - is the start point for the next scan
			}
		}
		System.out.println("The graph has (" + compCounter + ") components.");
		return compCounter;
	}
	// ====================================================================================================================
	
	public static void BFS_components_scan(ArrayList<Integer>[] graph, boolean[] scanned, int startPoint) {
		
		Queue<Integer> queue = new LinkedList<>();
		
		int curr = startPoint;
		// scanned[] is also here, just in the input
		
		queue.add(curr);      // force init
		scanned[curr] = true; // force init
		
		while(!queue.isEmpty()) {
			
			curr = queue.poll();
			
			for (int i = 0; i < graph[curr].size(); i++) {
				int tempNeighbor = graph[curr].get(i);
				
				if(scanned[tempNeighbor] == false) {
					scanned[tempNeighbor] = true;
					queue.add(tempNeighbor);
				}
			}
		}
	}
	// ====================================================================================================================

	public static void main(String[] args) {

		int n = 6; // UPDATE <=======(!)
		ArrayList<Integer>[] graph = new ArrayList[n];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		// =====================(start tree input)
		graph[0].add(1);
		graph[1].add(0);
		
		graph[2].add(3);
		graph[3].add(2);
		
		graph[4].add(5);
		graph[5].add(4);
		
//		graph[0].add(1);
//		graph[0].add(4);
//		graph[0].add(6);
//		graph[0].add(2);
//
//		graph[1].add(0);
//		graph[1].add(5);
//
//		graph[2].add(3);
//		graph[2].add(0);
//
//		graph[3].add(2);
//
//		graph[4].add(0);
//		graph[4].add(8);
//
//		graph[5].add(1);
//
//		graph[6].add(0);
//
//		graph[7].add(8);
//
//		graph[8].add(4);
//		graph[8].add(7);
		// =====================(end tree)
		BFScomponentsNum(graph);
	}
}
