import java.util.*;

/**
 * Greedy Best-First-Search Algorithms class.
 * 
 * @author Isaac Yong
 * @version 17/10/2013
 */

public class GreedyBFS extends Algorithms {
	private Node goal = null;

	public GreedyBFS() {
		super();
		goal = this.checkerObject.getGoal();
	}

	public GreedyBFS(String fileName) {
		super(fileName);
		goal = this.checkerObject.getGoal();
	}

	public void compute() {
		this.checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);
		// stageOne will return greediest node
		if(!this.checkerObject.isGoal(this.currentNode = stageOne())) {
			compute();
		}
		else
			return;
	}

	public Node stageOne() {
		private int smallest = 0, index = -1;
		private ArrayList<Node> tempNodeList = new ArrayList<Node>();
		private ArrayList<Integer> distance = new ArrayList<Integer>(4, -1); // Initialise 4 slots with -1 as value
		this.closedNodes.add(this.currentNode);

		if(this.checkerObject.getUp()==1) {
			// Able to move to up
			tempNodeList.add(new Node((this.currentNode.getX()-1), (this.currentNode.getY())));
			distance.set(0, calculateDistance(tempNodeList.get(0)));
		} else if (this.checkerObject.getLeft()==1) {
			// Able to move to left
			tempNodeList.add(new Node((this.currentNode.getX()), (this.currentNode.getY()-1)));
			distance.set(1, calculateDistance(tempNodeList.get(1)));
		} else if (this.checkerObject.getDown()==1) {
			// Able to move to down
			tempNodeList.add(new Node((this.currentNode.getX()+1), (this.currentNode.getY())));
			distance.set(2, calculateDistance(tempNodeList.get(2)));
		} else if (this.checkerObject.getRight()==1) {
			// Able to move to right
			tempNodeList.add(new Node((this.currentNode.getX()), (this.currentNode.getY()+1)));
			distance.set(3, calculateDistance(tempNodeList.get(3)));
		}

		//TODO: resolve similar distances
		for(int i = 0; i <= 4; i++) {
			if((distance.get(i)>=0) && (distance.get(i) < smallest)) {
				smallest = distance.get(i);
				index = i;
			}
		}

		// Hit dead end
		if(index==-1) {
			index = stageTwo(); // Returns index of previous node
		}

		return tempNodeList.get(index);
	}

	public int stageTwo() {
		
	}

	public int calculateDistance(Node node) {
		return (Math.abs(this.goal.getX() - node.getX()) + Math.abs(this.goal.getY() - node.getY()));
	}
}
