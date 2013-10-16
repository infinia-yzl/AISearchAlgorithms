import java.util.ArrayList;
/**
 * Parent Algorithms class.
 * 
 * @author Isaac Yong
 * @version 10/10/2013
 */
public abstract class Algorithms {
	protected Node currentNode = null;
	protected ArrayList<Node> openedNodes = null; // Nodes currently available to expand
	protected ArrayList<Node> closedNodes = null; // Explored Nodes
	protected Checker checkerObject = null;

	public Algorithms() {
		this.currentNode = new Node(1, 1);
		this.openedNodes = new ArrayList<Node>();
		this.openedNodes.add(currentNode);
		checkerObject = new Checker("maze.txt");
	}

	public Algorithms(String fileName) {
		this.currentNode = new Node(1, 1);
		this.openedNodes = new ArrayList<Node>();
		this.openedNodes.add(currentNode);
		checkerObject = new Checker(fileName);
	}

	public abstract void compute();

}
