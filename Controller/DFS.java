/**
 * Depth-First-Search Algorithms class.
 * 
 * @author Isaac Yong
 * @version 10/10/2013
 */

public class DFS extends Algorithms {
	public DFS() {
		super();
	}

	public DFS(String fileName) {
		super(fileName);
	}

	public void compute() {
		this.checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);
		if(!stageOne()) {
			if(!stageTwo()) {
				System.out.println("Something went wrong!");
			}
		}

		if(this.checkerObject.isGoal(this.currentNode)) {
			// End of algorithm
		} /*else if () {
			// Check for NO SOLUTION
			// End with failure msg
		}*/
		// Repeat computation if not exhausted
		compute();
	}

	// Stage One Movemment - Move to any unexplored node
	public boolean stageOne() {
		if(this.checkerObject.getUp()==1) {
			// Able to move to up
			this.closedNodes.add(currentNode);
			this.currentNode.setX(this.currentNode.getX()-1);
			return true;
		} else if (this.checkerObject.getLeft()==1) {
			// Able to move to left
			this.closedNodes.add(currentNode);
			this.currentNode.setX(this.currentNode.getY()-1);
			return true;
		} else if (this.checkerObject.getDown()==1) {
			// Able to move to down
			this.closedNodes.add(currentNode);
			this.currentNode.setX(this.currentNode.getX()+1);
			return true;
		} else if (this.checkerObject.getRight()==1) {
			// Able to move to right
			this.closedNodes.add(currentNode);
			this.currentNode.setX(this.currentNode.getY()+1);
			return true;
		}
		return false;
	}

	// Stage Two Movement - Move back to explored node (no available unexplored nodes)
	public boolean stageTwo() {
		if(this.checkerObject.getUp()==2) {
			// Able to move to up
			this.currentNode.setX(this.currentNode.getX()-1);
			return true;
		} else if (this.checkerObject.getLeft()==2) {
			// Able to move to left
			this.currentNode.setX(this.currentNode.getY()-1);
			return true;
		} else if (this.checkerObject.getDown()==2) {
			// Able to move to down
			this.currentNode.setX(this.currentNode.getX()+1);
			return true;
		} else if (this.checkerObject.getRight()==2) {
			// Able to move to right
			this.currentNode.setX(this.currentNode.getY()+1);
			return true;
		}
		return false;
	}
}
