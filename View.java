import java.util.*;

/**
 * Handles output to screen.
 * 
 * @author Isaac Yong
 * @version 17/10/2013
 */

public class View {
	private String[][] maze = null;
	private ArrayList<Node> wallList = null;
	private Node goal = null, size = null;

	public View(ArrayList<Node> wallList, Node goal, Node size) {
		this.wallList = wallList;
		this.goal = goal;
		this.size = size;
		this.maze = new String[size.getX()][size.getY()];
		populateMaze();
	}

	public void populateMaze() {
		// Populate array with the walls
		for (Node wall : wallList) {
			// System.out.println("Get X: " + wall.getX() + " Get Y: " + wall.getY());
			this.maze[wall.getX()][wall.getY()] = "#";
		}

		// Populate array with the empty spaces & goal
		for (int x = 0; x < size.getX(); x++) {
			for (int y = 0; y < size.getY(); y++) {
				if((maze[x][y]==null) || (!maze[x][y].equals("#"))) {
					if((goal.getX()==x)&&(goal.getY()==y)) {
						maze[x][y] = "=";
					} else {
						maze[x][y] = " ";
					}
				}	
			}
		}
	}

	public void printMaze(Node currentNode, ArrayList<Node> closedNodes) {
		// Closed nodes will use "X" symbol
		for (Node closed : closedNodes) {
			// System.out.println("PRINT: Closed.getX(): " + closed.getX() + " Closed.getY(): " + closed.getY());
			this.maze[closed.getX()][closed.getY()] = "X";
		}
		// Current node will use "O" symbol
		this.maze[currentNode.getX()][currentNode.getY()] = "O";

		for (int x = 0; x < size.getX(); x++) {
			for (int y = 0; y < size.getY(); y++) {
				System.out.print(maze[x][y]);	
			}
			System.out.println();
		}
	}

	public void printSolution(ArrayList<Node> closedNodes, ArrayList<Node> deadEndList) {
		// Closed nodes will use "X" symbol
		for (Node closed : closedNodes) {
			this.maze[closed.getX()][closed.getY()] = "X";
		}
		System.out.println();
		System.out.println("--- Solution path ---");
		for (int x = 0; x < size.getX(); x++) {
			for (int y = 0; y < size.getY(); y++) {
				if(!maze[x][y].equals("#")) {
					for (Node n : deadEndList) {
						if((x == n.getX())&&(y == n.getY())){
							maze[x][y] = "X";
							break; 
						}
						else
							maze[x][y] = "O"; // Signifies solution path
					}
				}
				System.out.print(maze[x][y]);	
			}
			System.out.println();
		}
	}

	public void printCounter(int counter) {
		System.out.println();
		System.out.println("Step #" + counter);
	}

	// Reach goal
	public void printSuccess() {
		System.out.println("--- Goal achieved! ---");
	}

	public void printFailure() {
		System.out.println("------ ERROR ------");
		System.out.println("Unable to reach solution!");
	}

}
