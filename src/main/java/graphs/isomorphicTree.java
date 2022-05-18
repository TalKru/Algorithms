package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class isomorphicTree {


	public static boolean isomorphicTree(ArrayList<Integer>[] tree1, ArrayList<Integer>[] tree2) {

		if(tree1.length != tree2.length) { // 1. check for equal amount of vertices - O(1)
			return false;
		}

		if( isTree(tree1) == false || isTree(tree2) == false ) { // 2. check that both inputs are valid trees - O(2n)
			return false;
		}
		
		// 3. sorted arrays of degrees must be identical 
		int[] degArr1 = new int [tree1.length]; 
		int[] degArr2 = new int [tree2.length];

		for (int i = 0; i < degArr1.length; i++) { // fill the degree array
			degArr1[i] = tree1[i].size();
		}

		for (int i = 0; i < degArr2.length; i++) { // fill the degree array
			degArr1[i] = tree2[i].size();
		}

		Arrays.sort(degArr1);
		Arrays.sort(degArr2);

		for (int i = 0; i < degArr1.length; i++) {
			if(degArr1[i] != degArr2[i]) {
				return false;	
			}
		}
		
		// 4. find the center/s of tree two trees => T1(c1,c2) , T2(c1,c2)
		int[] burnTree1 = burnLeafs.findTreeCenter(tree1); // find center of tree1
		int c1T1 = burnTree1[0]; // center 1, tree 1
		int c2T1 = burnTree1[1];
		
		int[] burnTree2 = burnLeafs.findTreeCenter(tree2); // find center of tree2
		int c1T2 = burnTree2[0];
		int c2T2 = burnTree2[1];
		
		// if one tree has single center while the other tree has two => they are NOT isomorphic.
		if( (c2T1 == -1 && c2T2 != -1) || (c2T2 == -1 && c2T1 != -1) ) {  
			return false;
		}
		
		// now there are 2 cases, for single center and for two centers
		if(c2T1 == -1 && c2T2 == -1) { // case: 1 center
			// 5.  ???????????
			int[] distFromRootByBFStree1 = distanceFromRootBFS(tree1, c1T1);
			int[] distFromRootByBFStree2 = distanceFromRootBFS(tree2, c1T2);
			
			Arrays.sort(distFromRootByBFStree1);
			Arrays.sort(distFromRootByBFStree2);
			
			
			for (int i = 0; i < distFromRootByBFStree1.length; i++) {
				if(distFromRootByBFStree1[i] != distFromRootByBFStree2[i]) {
					return false;
				}
			}
			////////////////////// NOT ALL CASES BEEN COVERED :(
			return true;
			//////////////////////
		}
		else { // case: 2 centers
			//TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			return true;
		}
	}
	// =================================================================================================


	/**
	 * Checks if the input is valid tree
	 * @param tree - must be valid graph
	 * @return T/F
	 */
	public static boolean isTree(ArrayList<Integer>[] tree) {

		// 1. (n = m-1)
		int n = tree.length;
		int m = 0;

		for (int i = 0; i < tree.length; i++) {
			m += tree[i].size();
		}

		if(n != m-1) {
			return false;
		}

		// 2. connected component
		int compNum = BFSconnectedComponents.BFScomponentsNum(tree);

		if(compNum == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	// =================================================================================================

	/**
	 * Given the starting Vertex, Finds the BFS spanning tree which begins in  starting Vertex
	 * @param tree
	 * @return distance (from start) array
	 */
	public static int[] distanceFromRootBFS(ArrayList<Integer>[] tree, int startPoint) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		int[] dist = new int[tree.length];
		
		for (int i = 0; i < dist.length; i++) {
			dist[i] = -1;
		}
		
		boolean[] visited = new boolean[tree.length];

		// force start
		int currVertex = startPoint;
		dist[currVertex] = 0;
		visited[currVertex] = true;
		queue.add(currVertex);
		
		while(!queue.isEmpty()) {
			
			currVertex = queue.poll();
			
			for (int i = 0; i < tree[currVertex].size(); i++) {
				
				int tempNeighbor =  tree[currVertex].get(i); // the i neighbor of current working vertex
				
				if(visited[tempNeighbor] == false) { // if found unvisited neighbor
					visited[tempNeighbor] = true;
					dist[tempNeighbor] = dist[currVertex] + 1;
					queue.add(tempNeighbor);
				}
			} // neighbors loop
		}
		return dist;
	}
	
	// =================================================================================================





	// MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN 
	public static void main(String[] args) {

	}

}
