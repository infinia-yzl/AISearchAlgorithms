import java.util.ArrayList;

/**
 * Breadth-First-Search Algorithms class.
 * 
 * @author Isaac Yong
 * @version 10/10/2013
 */

public class BFS extends Algorithms {
	private ArrayList<Node> copyOpen = null;

	public BFS() {
		super();
	}

	public BFS(String fileName) {
		super(fileName);
	}

	public void compute() {
		this.view.printMaze(this.currentNode, this.closedNodes); // Print the current maze
		if(openNodes.isEmpty()) {
			this.view.printFailure();
			return; // Exhausted all nodes; NO SOLUTION
		}
		
		this.copyOpen = (ArrayList<Node>) openNodes.clone(); // Prevent concurrent modification

		// This follows the queueing system
		for(int i = 0; i < copyOpen.size(); i++) {
			if(!checkerObject.isGoal(this.currentNode)) {
				openNodes.remove(0); // Remove current node from openNodes ArrayList
				this.checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);
				stageOne();
			} else {
				this.view.printMaze(this.currentNode, this.closedNodes);
				this.view.printSuccess();
				return; // Current Node is the Goal
			}
		}

		// Repeat computation if not exhausted
		compute();
	}

	// Adds new nodes to the openNodes ArrayList
	public void stageOne() {
		if(this.checkerObject.getUp()==1) {
			// Able to move to up
			this.openNodes.add(new Node((this.currentNode.getX()-1), (this.currentNode.getY())));
		}
		if (this.checkerObject.getLeft()==1) {
			// Able to move to left
			this.openNodes.add(new Node((this.currentNode.getX()), (this.currentNode.getY()-1)));
		}
		if (this.checkerObject.getDown()==1) {
			// Able to move to down
			this.openNodes.add(new Node((this.currentNode.getX()+1), (this.currentNode.getY())));
		}
		if (this.checkerObject.getRight()==1) {
			// Able to move to right
			this.openNodes.add(new Node((this.currentNode.getX()), (this.currentNode.getY()+1)));
		}
		this.closedNodes.add(this.currentNode);
		this.currentNode = this.openNodes.get(0);
		this.expandCounter++;
		this.view.printCounter(this.expandCounter);
	}

}
