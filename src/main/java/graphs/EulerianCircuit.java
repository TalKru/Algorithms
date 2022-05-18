package graphs;

import java.util.ArrayList;
import java.util.Stack;

public class EulerianCircuit {
	
	/**
	 * finds the Eulerian Circuit in a given graph
	 * @param graph
	 * @return circle - vector which represents the Eulerian Circuit travel
	 */
	public static ArrayList<Integer> printEulerCircuit(ArrayList<Integer>[] graph) {
		
		/////////////////////////////////////////////////////////
		/* first chech if valid input */
		if(isEulerCircuit(graph) == false) { 
			ArrayList<Integer> error = new ArrayList<Integer>();
			error.add(-1);
			return error;
		}
		/////////////////////////////////////////////////////////

		ArrayList<Integer> cycle = new ArrayList<Integer>(); // answer

		int[] degree = new int[graph.length]; // temp degrees count for valid travel

		for (int i = 0; i < degree.length; i++) { // find the degree for each vertex
			degree[i] = graph[i].size();
		}

		Stack<Integer> stack = new Stack<Integer>();

		int curr = 0; // start point - any random vertex

		stack.push(curr); // init loop

		while(!stack.isEmpty()) {

			if(degree[curr] != 0 ) { // if there is valid neighbors to go to

				int neighbor = graph[curr].get(0);

				stack.push(neighbor);

				graph[curr].remove( (Integer)neighbor );  // remove edge: (curr)----(neighbor)
				graph[neighbor].remove( (Integer)curr );  // remove edge: (neighbor)----(curr)

				degree[curr]--;
				degree[neighbor]--;

				curr = neighbor; // advance travel
			}
			else { // if degree[curr] == 0
				
				cycle.add(stack.pop());
				
				if(stack.isEmpty() == false) {
					curr = stack.peek();
				}
			}
		} // while

		return cycle;
	}


	
	// =================================================================================================
	/**
	 * Checks if a graph has Eulerian Circuit
	 * @param graph
	 * @return [true / false]
	 */
	public static boolean isEulerCircuit(ArrayList<Integer>[] graph) {

		// O(m + n)
		if(BFSconnectedComponents.BFScomponentsNum(graph) != 1) { // check if graph is connected
			return false;
		}
		for (int i = 0; i < graph.length; i++) {

			if(graph[i].size() % 2 != 0) { // if found odd degree
				return false;
			}
		}
		return true;
	}
	// =================================================================================================

	

	/**
	 * Checks if a graph has Eulerian Path
	 * @param graph
	 * @return [true / false]
	 */
	public static boolean isEulerPath(ArrayList<Integer>[] graph) {

		if(BFSconnectedComponents.BFScomponentsNum(graph) != 1) { // check if graph is connected
			return false;
		}
		
		int oddDegreesCount = 0;
		
		for (int i = 0; i < graph.length; i++) {

			if(graph[i].size() % 2 != 0) { // if found odd degree
				++oddDegreesCount;
			}
		}
		if(oddDegreesCount == 2) {
			return true;
		}
		else {
			return false;
		}
	}
	// =================================================================================================


	
	
	// MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN 
	public static void main(String[] args) {
		
		int n = 10; // UPDATE n                             <=======(!!!) Lusi

		ArrayList<Integer>[] graph = new ArrayList[n]; 

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		 graph[0].add(9);
		 graph[0].add(1);
		 graph[0].add(4);
		 graph[0].add(7);
		 
		 graph[1].add(0);
		 graph[1].add(8);
		 
		 graph[2].add(3);
		 graph[2].add(6);
		 
		 graph[3].add(5);
		 graph[3].add(2);
		 
		 graph[4].add(5);
		 graph[4].add(0);
		 
		 graph[5].add(4);
		 graph[5].add(3);
		 graph[5].add(7);
		 graph[5].add(6);
		 
		 graph[6].add(5);
		 graph[6].add(2);
		 
		 graph[7].add(5);
		 graph[7].add(0);
		 
		 graph[8].add(9);
		 graph[8].add(1);
		 
		 graph[9].add(8);
		 graph[9].add(0);
		 
		 
		 ArrayList<Integer> resultCicuit = new ArrayList();
		 
		 resultCicuit = printEulerCircuit(graph);
		 
		 System.out.print("Eulerian Circuit: ");
		 System.out.println(resultCicuit.toString());
	} // main

}
