import java.util.*;

/**
 * File Best.java
 * 
 * Represents Best-First-Search Algorithms class.
 * 
 * @author Isaac Yong
 * @version 17/10/2013
 */

public class Best extends DFS {

	public Best() {
		super();
	}
	
	/**
	 * To call the parent constructor
	 * 
	 * @param fileName
	 */ 
	public Best(String fileName) {
		super(fileName);
	}
	
	/**
         * To act as primary algorithm computation function
         */
	public void compute() {
		this.view.printMaze(this.currentNode, this.closedNodes); // Print the current maze
		this.checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);
		this.stageOne();
		if(!this.checkerObject.isGoal(this.currentNode)) {
			compute();
		}
		else {
			this.view.printMaze(this.currentNode, this.closedNodes); // Print the current maze
			this.view.printSolution(this.closedNodes, this.deadEndList);
			this.view.printSuccess();
			return;
		}
	}
	
	/**
	 * Stage 1 Computation of algorithm
	 */
	public boolean stageOne() {
		int smallest = 0, index = -1;
		ArrayList<Node> tempNodeList = new ArrayList<Node>(4);
		ArrayList<Integer> distance = new ArrayList<Integer>(4);
		// Initialise 4 slots in distance ArrayList to -1
		for (int i=0; i<4; i++) {
			distance.add(-1);
			tempNodeList.add(new Node(-1, -1));
		}

		this.closedNodes.add(this.currentNode);

		if(this.checkerObject.getUp()==1) {
			// Able to move to up
			tempNodeList.set(0, new Node((this.currentNode.getX()-1), (this.currentNode.getY())));
			distance.set(0, calculateDistance(tempNodeList.get(0)));
		}
		if (this.checkerObject.getLeft()==1) {
			// Able to move to left
			tempNodeList.set(1, new Node((this.currentNode.getX()), (this.currentNode.getY()-1)));
			distance.set(1, calculateDistance(tempNodeList.get(1)));
		}
		if (this.checkerObject.getDown()==1) {
			// Able to move to down
			tempNodeList.set(2, new Node((this.currentNode.getX()+1), (this.currentNode.getY())));
			distance.set(2, calculateDistance(tempNodeList.get(2)));
		}
		if (this.checkerObject.getRight()==1) {
			// Able to move to right
			tempNodeList.set(3, new Node((this.currentNode.getX()), (this.currentNode.getY()+1)));
			distance.set(3, calculateDistance(tempNodeList.get(3)));
		}

		// Make sure smallest variable starts out as biggest value
		smallest = distance.get(0) + distance.get(1) + distance.get(2) + distance.get(3) + 999;

		//TODO: resolve similar distances
		for(int i = 0; i < 4; i++) {
			if((distance.get(i) >= 0) && (distance.get(i) < smallest)) {
				smallest = distance.get(i);
				index = i;
			}
		}

		// Hit dead end
		if(index==-1) {
			super.verifyDeadEnd();
		} else {
			this.currentNode = tempNodeList.get(index);
		}

		return true;
	}
	
	/**
	 * To calculate node distance to goal
	 * 
	 * @param node
	 * 
	 * @return integer of distance to goal
	 */
	public int calculateDistance(Node node) {
		return (Math.abs(this.checkerObject.getGoal().getX() - node.getX()) + Math.abs(this.checkerObject.getGoal().getY() - node.getY()));
	}
}
