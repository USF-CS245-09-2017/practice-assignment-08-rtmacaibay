
public class Practice8Test {
	
	
	public Graph getGraph(int size) {
		return new GraphAdjMatrix(size);
	}

	
	public boolean createGraphTest() {
		// Just create a graph and expect it not to blow up...
		try {
			Graph g = getGraph(3);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	public boolean addEdgeTest() {
		Graph g = getGraph(3);
		
		g.addEdge(0,  1);
		g.addEdge(0, 2);
		
		// Check the neighbours...
		int[] neighbours = g.neighbors(0);
		boolean contains1 = false;
		boolean contains2 = false;
		int items = 0;
		for (int i = 0; i < neighbours.length; i++) {
			++items;
			if (neighbours[i] == 1)
				contains1 = true;
			if (neighbours[i] == 2)
				contains2 = true;
		}
		
		return contains1 && contains2 && items == 2;
	}
	
	
	public void topologicalSortTest() {
		Graph g = getGraph(6);
		//modified this to test out the "sort"
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 1);
		g.addEdge(1, 3);
		g.addEdge(4, 1);
		g.addEdge(5, 2);
		g.topologicalSort();
	}
	
	
	public void runTest () {
		int grade = 0;
		
		if (createGraphTest()) {
			grade += 10;
			System.out.println("[+10%] Passed create graph test");
		} else {
			System.out.println("[    ] Failed create graph test");
		}
		
		if (addEdgeTest()) {
			grade += 30;
			System.out.println("[+30%] Passed adding edge / check neighbours test");
		} else {
			System.out.println("[    ] Failed adding edge / check neighbours test");
		}
		
		// Topological sort will be checked by hand, because ...
		topologicalSortTest();
		System.out.println("[    ] Topological sort will be checked by the grader");
		
		System.out.println("Grade for this assignment: " + grade + "% - " + (grade+60) + "%");
	}

	public static void main(String[] args) {
		Practice8Test test = new Practice8Test();
		test.runTest();
	}

}
