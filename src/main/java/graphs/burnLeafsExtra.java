package graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class burnLeafsExtra {

	
	public static void treeCenterBurn(ArrayList<Integer>[] tree) {
		
		// check input - must be tree
		// 1. m = n-1
		// 2. single connected componenet
		
		int vertAmount = tree.length;
		int burnsCounter = 0;
		boolean[] whoIsAlive = new boolean[vertAmount];
		Arrays.fill(whoIsAlive, true); // all leafs are safe and well... for now :) 
		
		/* attempt of O(n)
		ArrayList<Integer> leafs = new ArrayList<Integer>();
		
		// fine leafs once O(n)
		for (int i = 0; i < tree.length; i++) {
			if(tree[i].size() == 1) {
				leafs.add(i);
			}
		}
		*/
		
		
		while( vertAmount > 2) {
			
			burnsCounter++; 
			
			/* 
			 (mark the nodes which were the neighbors of the leafs 
			 since their degree may be reduced to 1 
			 and they could by mistakenly taken as leafs at the wrong itteration) 
			 Note: that for each itteration a new list is allocated hence there is no need to clean it. 
			 */
			ArrayList<Integer> leafNeighbors = new ArrayList<Integer>(); 
			
			for (int i = 0; i < tree.length; i++) {
				if(tree[i].size()==1  &&  leafNeighbors.contains(i)==false ) {
					
					whoIsAlive[i] = false;
					
					vertAmount--;
					
					int leafNeighbor = tree[i].get(0);
					
					leafNeighbors.add(leafNeighbor); // mark neighbor to avoid false burn
					
					tree[leafNeighbor].remove( (Integer)i ); // disconnect the edge from neighbor side
					tree[i].clear(); // optinal?             // disconnect the edge from leaf     side
				}
			}
		}
		
		int centerCounter = 0;
		for (int i = 0; i < whoIsAlive.length; i++) {
			
			if(whoIsAlive[i] == true) {
				centerCounter++;
				System.out.println("found center vertex: " + i);
			}
		}
		System.out.println("tree has [" + centerCounter + "] centers!");
		
		
		
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
	
	
	
	// MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN  MAIN 
		public static void main(String[] args) {

			int n = 9; // UPDATE <=======(!) <=======(!) <=======(!) <=======(!) <=======(!)
			
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
			treeCenterBurn(tree);
		}

}
