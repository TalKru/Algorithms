


/* O(n^2) - there is more efficient way so this is not for the test*/
public class Graph_Components_Num {

	/* Given an path matrix as input - find the number of connected components in undirected graph */
	public static int graphComponentSum(int[][] adjMat) {
		int sum = 0; // for sorted components
		boolean[] sortedToComponent = new boolean[adjMat.length]; // helper array to know which vertices Submitted to connected component 

		for(int i = 0 ; i < sortedToComponent.length ; ++i) {
			sortedToComponent[i] = false;
		}
		for(int i = 0 ; i < adjMat.length ; ++i) { // for each vertex

			if( false == sortedToComponent[i] ) { // check if the vertex was already visited 
				sortedToComponent[i] = true; // mark vertex in boolean array as visited and done
				++sum; // count a new component

				for(int j = 0 ; j < adjMat[i].length ; ++j ) { // go over the vertex connections and mark each vertex that connected to him
					if(adjMat[i][j] > 0) { // (!!!) MAY BE CHANGED - "> 0" means that the weight/cost between 2 vertices is not infinite 
						sortedToComponent[j] = true;
					}
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		int[][] connectivityMat =
			{
					{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
					{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
					{0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
					{0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
					{0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
					{0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
					{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0}, 
					{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0}, 
					{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0}, 
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, 
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, 
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0}, 
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0}, 
					{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
					{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
			};
		int ans = graphComponentSum(connectivityMat);
		boolean isConnected = true;
		if(ans > 1 ) isConnected = false;
		System.out.println("Number of vertices: " + connectivityMat.length);
		System.out.println("Number of connectivity components: " + ans);
		System.out.println("Is the graph connected: " + isConnected);
	}
}


/*
{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
{0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, 
{0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, 
{0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
{0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
{0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, 
{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
{0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, 
{0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, 
{0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
 */




