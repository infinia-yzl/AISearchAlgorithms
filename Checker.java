import java.util.ArrayList;

/**
 * File Checker.java
 * Represents hecker class that contains general checking functions.
 * 
 * @author Isaac Yong
 * @version 14/10/2013
 */

public class Checker {
	private int up = 0, left = 0, down = 0, right = 0; // 0: wall, 1: empty, 2: explored node
	private ArrayList<Node> wallList = null;
	private ArrayList<Node> closedNodes = null;
	private Node goal = null, size = null;
	
	
        /**
         * To call the parent constructor
         * 
         * @param fileName 
         */
	public Checker(String fileName) {
		Model model = new Model(fileName);
		this.wallList = model.getWalls(); // Assume the function will return an arraylist of walls
		// Ensure the getSize function swaps the X and Y raw data to suit our system's pattern
		this.size = model.getSize();
		this.goal = computeGoal(this.size); // Assume the model function will pass in the size stored in a Node
	}

	/**
	 * To check all nodes
	 */
	public void nodeCheckAll(Node node, ArrayList<Node> closedNodes) {
		this.closedNodes = closedNodes;
		checkUp(node);
		checkLeft(node);
		checkDown(node);
		checkRight(node);
	}
	
	/**
	 * To check if the node above is a wall, empty or visited
	 * 
	 * @param node
	 */
	public void checkUp(Node node) {
		this.up = -1; // reset value to -1
		// Prevent out of bounds (negative coordinates)
		if((node.getX()-1) >= 0) {
			if(checkWall(new Node( (node.getX()-1), (node.getY()) ))) {
				if(this.closedNodes.size()==0)
					this.up = 1;
				else {
					for(int i = 0; i < this.closedNodes.size(); i++) {
						// Check with closed nodes to differenciate explored or not
						if(new Node(node.getX()-1, node.getY()).compareTo(this.closedNodes.get(i))==1) {
							this.up = 2; // explored node
							break;
						}
						else
							this.up = 1; // empty
					}
				}
			} else {
				this.up = 0; // set 0 to specify as wall
			}
		}
	}

	/**
	 * To check if the node on the left is a wall, empty or visited
	 * 
	 * @param node
	 */
	public void checkLeft(Node node) {
		this.left = -1; // reset value to -1
		// Prevent out of bounds (negative coordinates)
		if((node.getY()-1) >= 0) {
			if(checkWall(new Node( (node.getX()), (node.getY()-1) ))) {
				if(this.closedNodes.size()==0)
					this.left = 1;
				else {
					for(int i = 0; i < this.closedNodes.size(); i++) {
						// Check with closed nodes to differenciate explored or not
						if(new Node(node.getX(), node.getY()-1).compareTo(this.closedNodes.get(i))==1) {
							this.left = 2; // explored node
							break;
						}
						else
							this.left = 1; // empty
					}
				}
			} else {
				this.left = 0; // set 0 to specify as wall
			}
		}
	}

	/**
	 * To check if the node below is a wall, empty or visited
	 * 
	 * @param node
	 */
	public void checkDown(Node node) {
		this.down = -1; // reset value to -1
		// Prevent out of bounds
		if((node.getX()+1) < this.size.getX()) {
			if(checkWall(new Node( (node.getX()+1), (node.getY()) ))) {
				if(this.closedNodes.size()==0)
					this.down = 1;
				else {
					for(int i = 0; i < this.closedNodes.size(); i++) {
						// Check with closed nodes to differenciate explored or not
						if(new Node(node.getX()+1, node.getY()).compareTo(this.closedNodes.get(i))==1) {
							this.down = 2; // explored node
							break;
						}
						else
							this.down = 1; // empty
					}
				}
			} else {
				this.down = 0; // set 0 to specify as wall
			}
		}
	}

	/**
	 * To check if the node on the right is a wall, empty or visited
	 * 
	 * @param node
	 */
	public void checkRight(Node node) {
		this.right = -1; // reset value to -1
		// Prevent out of bounds
		if((node.getY()-1) < this.size.getY()) {
			if(checkWall(new Node( (node.getX()), (node.getY()+1) ))) {
				if(this.closedNodes.size()==0)
					this.right = 1;
				else {
					for(int i = 0; i < this.closedNodes.size(); i++) {
						// Check with closed nodes to differenciate explored or not
						if(new Node(node.getX(), node.getY()+1).compareTo(this.closedNodes.get(i))==1) {
							this.right = 2; // explored node
							break;
						}
						else
							this.right = 1; // empty
					}
				}
			} else {
				this.right = 0; // set 0 to specify as wall
			}
		}
	}

	/**
	 * To check if the node is wall
	 * 
	 * @param node
	 * 
	 * @return true means it is not a wall
	 */
	public boolean checkWall(Node node) {
		for(int i = 0; i < this.wallList.size(); i++) {
			if(node.compareTo(this.wallList.get(i))==1)
				return false; // false = wall exists
		}
		return true; // no wall
	}
	
	/**
	 * To check if the node is goal
	 * 
	 * @param node
	 * 
	 * @return false means it is not a goal
	 */
	public boolean isGoal(Node node) {
		if(this.goal.compareTo(node)==1)
			return true;
		return false;
	}

	/**
	 * To receive the maze size stored in a Node and computes the Goal Node coordinates
	 * 
	 * @return goal node coordinates
	 */
	public Node computeGoal(Node mazeSize) {
		return new Node((mazeSize.getX()-2), (mazeSize.getY()-1));
	}

	/**
	 * Accessor functions for up, left, down, right, goal, wallList
	 */
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

	public Node getGoal() {
		return this.goal;
	}

	public Node getSize() {
		return this.size;
	}

	public ArrayList<Node> getWallList() {
		return this.wallList;
	}

}
