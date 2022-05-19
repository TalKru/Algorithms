package graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class burnLeafs {

	/**
	 * finds the center, radius, diameter of a tree.
	 * In case of wrong input or a non-tree graph will Exit.
	 * (input must be a connecter graph without cycles)
	 * param tree - array of ArraysLists
	 * return result array:
	 *         [center1][center2 / NO second][diameter]
	 *         [0------][1------------------][2-------]
	 *         
	 *         (*) if there is one center only then [1] cell will contain -1
	 */
	public static int[] findTreeCenter(ArrayList<Integer>[] tree) {

		if( isTree(tree) == false ) { /*TODO LATER*/ } // check input
		/*
		 * A degree of a vertex is the number of neighbors he has
		 * a leaf has only 1 neighbor, therefore a leaf will always have degree = 1
		 * leaf burn - find vertices with degree = 1 and remove them 
		 * and update their neighbors since they lost 1 neighbor
		 */
		int[] result = new int[3];
		
		boolean[] liveVertices = new boolean[tree.length]; // will help us find is the center vertices

		for (int i = 0; i < liveVertices.length; i++) { // [T][T][T]...[T]
			liveVertices[i] = true;
		}
		int liveVertNum = tree.length; // remaining vertex counter - will tell us when to stop burning
		int firesCount = 0; // count the fires number to calc radius and diameter

		while(liveVertNum > 2) {
			firesCount++;
			/*
			 * NOTE: when a neighbor of a leaf is updated he might end up with a degree = 1 
			 * and will be burned by mistake as a leaf, to prevent such case 
			 * we must save all the the neighbors for each burn-cycle to remember not to burn them prematurely
			 */
			ArrayList<Integer> tempNrighbors = new ArrayList<>();

			for (int i = 0; i < tree.length; i++) { // burning one layer of leafs

				if( tree[i].size()==1 && tempNrighbors.contains(i) == false ) { //  degree == 1 (leaf is found)

					int neighbor = tree[i].get(0); // save the neighbor to remove current leaf from his list
					tempNrighbors.add(neighbor); // add to "safe from burn" list

					tree[neighbor].remove(new Integer(i)); // remove the deleted leaf from his neighbor list //// tree[neighbor].remove(i); // WILL NOT WORK, java sucks

					tree[i].clear(); // burn the leaf - we may clear the entire vector since a leaf only has 1 neighbor

					liveVertNum--;
					liveVertices[i] = false; // mark as killed
				} // if
			}
		}
		// return the remaining center vertices
		int centerCounter = 0;

		for (int i = 0; i < liveVertices.length; i++) {

			if(liveVertices[i]) { // search for survivals
				
				result[centerCounter] = i;
				centerCounter++;
				System.out.println("Center vertex: " + i);
			}
		}
		if(centerCounter == 2) {
			
			result[2] = 2*firesCount+1;
			
			System.out.println("Diameter: " + (2*firesCount+1) );
			System.out.println("Radius: " + (firesCount+1) );
		}
		else if(centerCounter == 1) {
			
			result[1] = -1; // mark that there is NO second center
			result[2] = 2 * firesCount;
					
			System.out.println("Diameter: " + (2*firesCount) );
			System.out.println("Radius: " + firesCount );
		}
		else { 
			System.out.println("Error");
		}
		//System.out.println(Arrays.toString(result));
		return result;
	}
	// ========================================================================================

	public static boolean isTree(ArrayList<Integer>[] graph) {
		// every tree must satisfy:  ()--()--()
		// |E| = |V|-1               V E V E  V
		// |V| = |E|+1 
		// now we test this cond first:
		int vertices = graph.length;
		int edges = 0;
		for (int i = 0; i < vertices; i++) { 
			edges += graph[i].size();
		}
		// note we count each edge twice (a)---(b) 
		// so we must divide by 2 the result
		edges = (edges / 2);
		if( vertices != (edges + 1)  ) {
			return false;
		}
		// check for single connected component, else throw error
		/**TO DO...**/
		return true;
	}
	// ========================================================================================

	public static void printTree(ArrayList<Integer>[] tree) {

		for (int i = 0; i < tree.length; i++) {
			System.out.print("Vertex ("  + i + ") neighbors - ");
			System.out.println( tree[i].toString() );
		}
		System.out.println();
	}
	// ========================================================================================

	public static void main(String[] args) {

		int n = 9; // UPDATE <=======(!)
		
		ArrayList<Integer>[] tree = new ArrayList[n]; 
		for (int i = 0; i < tree.length; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		// =====================(start tree input)
		tree[0].add(1);
		tree[0].add(4);
		tree[0].add(6);
		tree[0].add(2);

		tree[1].add(0);
		tree[1].add(5);

		tree[2].add(3);
		tree[2].add(0);
		
		tree[3].add(2);

		tree[4].add(0);
		tree[4].add(8);

		tree[5].add(1);

		tree[6].add(0);

		tree[7].add(8);

		tree[8].add(4);
		tree[8].add(7);
		// =====================(end tree)
		printTree(tree);
		findTreeCenter(tree);
	}
}
