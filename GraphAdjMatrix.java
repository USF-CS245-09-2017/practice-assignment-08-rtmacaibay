/**
 * Creates a graph using a 2d array
 * @author - Robert Macaibay
 * @assignment - p08
 */

import java.util.Arrays;

public class GraphAdjMatrix implements Graph{
	//global arrays
	private int[][] edges;
	private int[] finished;
	
	/**
	 * constructs graph object
	 * @param v - number of vertices
	 */
	public GraphAdjMatrix(int v) {
		//initialize 2d array
		edges = new int[v][v];
		//fill it with -1
		for (int[] row: edges)
			Arrays.fill(row, -1);
		//initialize these arrays
		finished = new int[edges.length];
		//fill finished with -1
		Arrays.fill(finished, -1);
	}
	
	/**
	 * add edge to the graph
	 * @param src - the vertex which points to the other
	 * @param tar - the vertex which is being pointed at
	 */
	public void addEdge(int src, int tar) {
		edges[src][tar] = 1;
	}
	
	/**
	 * finds the number of vertices that point at a certain vertex
	 * @param v - the vertex we want to see if there's vertices pointing at it
	 * @return the number of vertices that point at it
	 */
	public int inDegree(int v) {
		int degree = 0;
		//iterate through the vertices and check if they point at v
		for (int i = 0; i < edges.length; i++)
			if (edges[i][v] != -1)
				degree++;
		return degree;
	}
	
	/**
	 * finds the vertex that has no vertices pointing at it
	 * @param degree - the array of the indegrees of all the vertices
	 * @return the index of the vertex with zero vertices pointing at it
	 */
	public int findZero(int[] degree) {
		for (int i = 0; i < degree.length; i++)
			if (degree[i] == 0)
				return i;
		return -1;
	}

	/**
	 * prints out the graph in its topological order
	 */
	public void topologicalSort() {
		int[] inDegree = new int[edges.length];
		
		//gets the indegree values of the vertices
		for (int i = 0; i < edges.length; i++) {
			inDegree[i] = inDegree(i);
		}
		
		//loops until all the vertices have been displayed
		//to know that, finished is initialized to -1, which the vertices must reach -1
		//does not work with cycles :(
		while (!Arrays.equals(inDegree, finished)) {
			//get the vertex with zero indegree
			int v = findZero(inDegree);
		
			//iterate through that vertex's array, check if points at anything
			for (int i = 0; i < edges.length; i++)
				//if it points at something, decrement the indegree of that vertex
				if (edges[v][i] == 1)
					inDegree[i]--;
			//change that vertex's indegree to -1 bc we done with it
			inDegree[v] = -1;
			//check if are fully finished with the indegree array
			//if not, we print with an arrow
			if (Arrays.equals(inDegree, finished))
				System.out.print(v);
			else
				System.out.print(v + " -> ");
		}
		//formatting things
		System.out.println();
	}

	/**
	 * finds the neighbors of a certain vertex (neighbor being vertices adjacent)
	 * @param v - the certain vertex
	 * @return - an array of neighbors
	 */
	public int[] neighbors(int v) {
		//initialize array of neighbors
		int[] neighbors = new int[edges.length];
		int size = 0;
		
		//if we find a neighbor, add it to array
		for (int i = 0; i < edges.length; i++)
			if (edges[v][i] > -1)
				neighbors[size++] = i;
		
		//return a copy of the array with only the size of the occupied spaces
		return Arrays.copyOf(neighbors, size);
	}
}
