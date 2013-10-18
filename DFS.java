import java.util.ArrayList;

/**
 * Depth-First-Search Algorithms class.
 * 
 * @author Isaac Yong
 * @version 10/10/2013
 */

public class DFS extends Algorithms {
	private ArrayList<Node> deadEndList = null;

	public DFS() {
		super();
	}

	public DFS(String fileName) {
		super(fileName);
		deadEndList = new ArrayList<Node>();
	}

	public void compute() {
		this.view.printMaze(this.currentNode, this.closedNodes); // Print the current maze
		if(this.checkerObject.isGoal(this.currentNode)) {
			// End of algorithm
			this.view.printSolution(this.closedNodes, this.deadEndList);
			this.view.printSuccess();
			return;
		}
		this.checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);
		if(!stageOne()) {
			verifyDeadEnd();
		}

		// Repeat computation if not exhausted
		compute();
	}

	// Stage One Movement - Move to any unexplored node
	public boolean stageOne() {
		this.closedNodes.add(new Node(this.currentNode.getX(), this.currentNode.getY())); // Create new Node to prevent pointing to currentNode's address
		this.expandCounter++;
		this.view.printCounter(this.expandCounter);
		
		if(this.checkerObject.getUp()==1) {
			// Able to move to up
			this.currentNode.setX(this.currentNode.getX()-1);
			return true;
		} else if (this.checkerObject.getLeft()==1) {
			// Able to move to left
			this.currentNode.setY(this.currentNode.getY()-1);
			return true;
		} else if (this.checkerObject.getDown()==1) {
			// Able to move to down
			this.currentNode.setX(this.currentNode.getX()+1);
			return true;
		} else if (this.checkerObject.getRight()==1) {
			// Able to move to right
			this.currentNode.setY(this.currentNode.getY()+1);
			return true;
		}
		return false;
	}

	public void verifyDeadEnd() {
		int deadCount = 0;
		if(this.checkerObject.getUp()==0) {
			deadCount++;
		}
		if(this.checkerObject.getLeft()==0) {
			deadCount++;
		}
		if(this.checkerObject.getDown()==0) {
			deadCount++;
		}
		if(this.checkerObject.getRight()==0) {
			deadCount++;
		}

		if(deadCount==3) {
			backTrack();
		}
	}

	// Traceback steps out of dead end
	public void backTrack() {
		boolean tmpCounter = false, skipCounter = false;
		for(int i = closedNodes.size()-1; i >= 0; i--) {
			skipCounter = false;
			for (Node tmpDead : deadEndList) {
				if((closedNodes.get(i).compareTo(tmpDead))==1){
					skipCounter = true;
					break;
				}
			}
			if(skipCounter==false) {
				this.currentNode = closedNodes.get(i);
				this.checkerObject.nodeCheckAll(closedNodes.get(i), closedNodes);
				if((checkerObject.getUp()==1)||(checkerObject.getLeft()==1)||(checkerObject.getDown()==1)||(checkerObject.getRight()==1)) {
					return;
				}
				deadEndList.add(this.currentNode);
				if(tmpCounter==true) {
					this.view.printMaze(this.currentNode, this.closedNodes);
					this.expandCounter++;
					this.view.printCounter(this.expandCounter);
				}
				tmpCounter = true; // Toggle counter to start adding a step
			}
		}
	}
}
