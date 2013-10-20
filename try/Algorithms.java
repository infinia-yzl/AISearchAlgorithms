import java.util.ArrayList;
/**
 * File Algorithms.java
 * 
 * Represents Parent Algorithms class.
 * 
 * @author Isaac Yong
 * @version 10/10/2013
 */
 
public abstract class Algorithms {
	protected int expandCounter = -1;
	protected Node currentNode = null;
	protected ArrayList<Node> openNodes = null; // Nodes currently available to expand
	protected ArrayList<Node> closedNodes = null; // Explored Nodes
	protected Checker checkerObject = null;
	protected View view = null;
	
	/**
 	* To act as the parent constructor as well
 	*/
	public Algorithms() {
		expandCounter = 0;
		this.currentNode = new Node(1, 0);
		this.openNodes = new ArrayList<Node>();
		this.openNodes.add(currentNode);
		this.closedNodes = new ArrayList<Node>();
		checkerObject = new Checker("maze.txt");
		view = new View(checkerObject.getWallList(), checkerObject.getGoal(), checkerObject.getSize());
	}
	
	/**
 	* To act as the parent constructor as well
 	* 
 	* @param fileName
 	*/
	public Algorithms(String fileName) {
		expandCounter = 0;
		this.currentNode = new Node(1, 0);
		this.openNodes = new ArrayList<Node>();
		this.openNodes.add(currentNode);
		this.closedNodes = new ArrayList<Node>();
		checkerObject = new Checker(fileName);
		view = new View(checkerObject.getWallList(), checkerObject.getGoal(), checkerObject.getSize());
	}

	public abstract void compute();

}
