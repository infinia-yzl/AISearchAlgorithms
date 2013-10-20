/**
 * File Core.java
 * 
 * Represents core class that combines the entire project and runs the program.
 * 
 * @author Isaac Yong
 * @version 14/10/2013
 */

public class Core {
	public static void main(String[] args) {
// DFS dfs = new DFS("maze.txt");		
// dfs.compute();

		// BFS bfs = new BFS("maze.txt");		
		// bfs.compute();

		// Best best = new Best("maze.txt");
		// best.compute();
		
		//HillClimbing hill = new HillClimbing("maze.txt");		
		//hill.compute();
		
		AStar a = new AStar("maze.txt");		
		a.compute();
	}
}
