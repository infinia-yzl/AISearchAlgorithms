import java.util.ArrayList;

/**
 * Checker class that contains general checking functions.
 * 
 * @author Isaac Yong
 * @version 14/10/2013
 */

public class Checker {
	private int up = 0, left = 0, down = 0, right = 0; // 0: wall, 1: empty, 2: explored node
	private ArrayList<Node> wallList = null;
	private ArrayList<Node> exploredList = null;
	private Node goal = null;

	public Checker(String fileName) {
		Model model = new Model(fileName);
		this.wallList = model.getWalls(); // Assume the function will return an arraylist of walls
		// Ensure the getSize function swaps the X and Y raw data to suit our system's pattern
		this.goal = computeGoal(model.getSize()); // Assume the model function will pass in the size stored in a Node
	}

	// Check all nodes
	public void nodeCheckAll(Node node, ArrayList<Node> exploredList) {
		this.exploredList = exploredList;
		checkUp(node);
		checkLeft(node);
		checkDown(node);
		checkRight(node);
	}

	public void checkUp(Node node) {
		this.up = 0; // reset value to 0
		if(checkWall(new Node( (node.getX()-1), (node.getY()) ))) {
			for(int i = 0; i < this.exploredList.size(); i++) {
				if(node.compareTo(this.exploredList.get(i))==1)
					this.up = 2; // explored node
				else
					this.up = 1; // empty
			}
		}
	}

	public void checkLeft(Node node) {
		this.left = 0; // reset value to 0
		if(checkWall(new Node( (node.getX()), (node.getY()-1) ))) {
			for(int i = 0; i < this.exploredList.size(); i++) {
				if(node.compareTo(this.exploredList.get(i))==1)
					this.left = 2; // explored node
				else
					this.left = 1; // empty
			}
		}
	}

	public void checkDown(Node node) {
		this.down = 0; // reset value to 0
		if(checkWall(new Node( (node.getX()+1), (node.getY()) ))) {
			for(int i = 0; i < this.exploredList.size(); i++) {
				if(node.compareTo(this.exploredList.get(i))==1)
					this.down = 2; // explored node
				else
					this.down = 1; // empty
			}
		}
	}

	public void checkRight(Node node) {
		this.right = 0; // reset value to 0
		if(checkWall(new Node( (node.getX()), (node.getY()+1) ))) {
			for(int i = 0; i < this.exploredList.size(); i++) {
				if(node.compareTo(this.exploredList.get(i))==1)
					this.right = 2; // explored node
				else
					this.right = 1; // empty
			}
		}
	}

	public boolean checkWall(Node node) {
		for(int i = 0; i < this.wallList.size(); i++) {
			if(node.compareTo(this.wallList.get(i))==1)
				return false; // false = wall exists
		}
		return true; // no wall
	}

	public boolean isGoal(Node node) {
		if(this.goal.compareTo(node)==1)
			return true;
		return false;
	}

	// Receives the maze size stored in a Node and computes the Goal Node coordinates.
	public Node computeGoal(Node mazeSize) {
		return new Node((mazeSize.getX()-1), (mazeSize.getY()-1));
	}

	// Accessor functions for up, left, down, right
	public int getUp() {
		return this.up;
	}

	public int getLeft() {
		return this.left;
	}

	public int getDown() {
		return this.down;
	}

	public int getRight() {
		return this.right;
	}

}
