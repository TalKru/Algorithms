package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSdistBetween2Ver {
	
	public static int BFSdist(ArrayList<Integer>[] graph, int from, int to) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		int currVer = from; // starting point
		
		boolean[] visitedArr = new boolean[graph.length];
		
		int[] distArr = new int[graph.length];
		
		for (int i = 0; i < distArr.length; i++) {
			distArr[i] = -1;
		}
		
		// force init
		queue.add(currVer);
		visitedArr[currVer] = true;
		distArr[currVer] = 0;
		
		while(!queue.isEmpty()) {
			
			currVer = queue.poll();
			
			int neighborNum = graph[currVer].size(); // amount of neighbors a vertex has
			
			for (int i = 0; i < neighborNum; i++) { // loop over the vertex neighbors
				
				int tempNeighbor = graph[currVer].get(i);
				
				if( visitedArr[tempNeighbor] == false) {
					visitedArr[tempNeighbor] = true;
					distArr[tempNeighbor] = distArr[currVer] + 1;
					queue.add(tempNeighbor);
				}
			}
		}
		System.out.println("Distance between vertices (" + from + ") and (" + to + ") is: (" + distArr[to] + ")");
		return distArr[to];
	}
}
