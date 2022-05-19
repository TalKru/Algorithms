package graphs;

import java.util.Arrays;

public class Dijkstra {
	
	
	public static int[] dijkstra(int[][] adjMat, int start) {
		
		int[] dist = new int[adjMat.length];

		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;

		boolean[] visited = new boolean[adjMat.length];

		Arrays.fill(visited, false);
		
		int[] parent = new int[adjMat.length];
		Arrays.fill(parent, -1);

		for (int i = 0; i < visited.length; i++) {
			
			int min = Integer.MAX_VALUE;
			int u = 0;
			
			for (int j = 0; j < visited.length; j++) {
				
				if(visited[j] == false && dist[j] < min) {
					min = dist[j];
					u = j;
				}
			}
			visited[u]= true;
			
			for (int v = 0; v < visited.length; v++) {

				if(adjMat[u][v] != Integer.MAX_VALUE && !visited[v] && adjMat[u][v] + dist[u] < dist[v] ) {
					dist[v] = adjMat[u][v] + dist[u];
					parent[v] = u;
				}
			}
		}
		System.out.println(Arrays.toString(dist));
		return dist;
	}


	public static void main(String[] args) {

		int inf = Integer.MAX_VALUE;
		int mat[][] = { {0,3,1,inf, inf,inf},
						{3,0,1,5,inf, inf},
						{1,1,0,inf,15,inf},
						{inf,5,inf,0,1,4},
						{inf,inf , 15,1,0,1},
						{inf,inf,inf,4,1,0}};
		
		dijkstra(mat, 0);
	}
}
