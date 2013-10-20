import java.util.Scanner;

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
		Scanner scanIn = new Scanner(System.in);

		System.out.println("Please key in number for following algorithms:");
		System.out.println("[1] Depth-First-Search");
		System.out.println("[2] Breadth-First-Search");
		System.out.println("[3] Best-First-Search");
		System.out.println("[4] Hill Climbing Search");
		System.out.println("[5] A* Search");
		System.out.println("[6] Custom Search");
		
		int input = scanIn.nextInt();

		if (input==1) {
			DFS dfs = new DFS("maze.txt");		
			dfs.compute();
		}
		else if (input==2) {
			BFS bfs = new BFS("maze.txt");		
			bfs.compute();
		}
		else if (input==3) {
			Best best = new Best("maze.txt");
			best.compute();
		}
		else if (input==4) {	
			HillClimbing hill = new HillClimbing("maze.txt");		
			hill.compute();
		}
		else if (input==5) {
			AStar a = new AStar("maze.txt");		
			a.compute();
		}
		else if (input==6) {
			Custom aheadTwoSteps = new Custom("maze.txt");
			aheadTwoSteps.compute();
		}
	}
}
