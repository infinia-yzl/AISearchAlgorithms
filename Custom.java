/**
 * File Custom.java
 * 
 * Represents Custom-Search Algorithms class.
 * 
 * @author Isaac Yong
 * @version 18/10/2013
 */

public class Custom extends DFS {
	private int[][] maze = null;

	public Custom() {
		super();
	}
	
	/**
	 * To call the parent constructor
	 * 
	 * @param fileName
	 */ 
	public Custom(String fileName) {
		super(fileName);
		maze = new int[checkerObject.getSize().getX()][checkerObject.getSize().getY()];
		for(int x=0; x < checkerObject.getSize().getX(); x++) {
			for(int y=0; y < checkerObject.getSize().getY(); y++) {
				maze[x][y] = 0;
			}
		}
	}
	
	/**
     * To act as primary algorithm computation function
     */
	public void compute() {
		if(this.expandCounter > 350) {
			this.view.printFailure();
			return;
		}

		this.maze[this.currentNode.getX()][this.currentNode.getY()]++;
		this.view.printMaze(this.currentNode, this.closedNodes); // Print the current maze
		if(this.checkerObject.isGoal(this.currentNode)) {
			// End of algorithm
			this.view.printSolution(this.closedNodes, this.deadEndList);
			this.view.printSuccess();
			return;
		}

		if(((this.currentNode.getX()-1)>0 && (this.currentNode.getX()+1)<(this.checkerObject.getSize().getX()-1))&&((this.currentNode.getY()-1)>0 && (this.currentNode.getY()+1)<(this.checkerObject.getSize().getY()-1))) {
			// Checks 2 nodes ahead and positions current node there if possible
			this.checkerObject.nodeCheckAll(new Node(this.currentNode.getX(), this.currentNode.getY()), this.closedNodes, true);
			if(futureStep()) {
				compute();
			}
		}
		
		this.checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);

		if(!stageOne()) {
			super.verifyDeadEnd();
		}

		// Repeat computation if not exhausted
		compute();
	}

	/**
	 * Checks 2 nodes ahead from current node
	 */
	public boolean futureStep() {
		this.closedNodes.add(new Node(this.currentNode.getX(), this.currentNode.getY())); // Create new Node to prevent pointing to currentNode's address
		this.expandCounter++;
		this.view.printCounter(this.expandCounter);
		
		if(this.checkerObject.getUp()==1) {
			// Able to move to up
			this.currentNode.setX(this.currentNode.getX()-2);
			return true;
		} else if (this.checkerObject.getLeft()==1) {
			// Able to move to left
			this.currentNode.setY(this.currentNode.getY()-2);
			return true;
		} else if (this.checkerObject.getDown()==1) {
			// Able to move to down
			this.currentNode.setX(this.currentNode.getX()+2);
			return true;
		} else if (this.checkerObject.getRight()==1) {
			// Able to move to right
			this.currentNode.setY(this.currentNode.getY()+2);
			return true;
		}

		if(this.checkerObject.getUp()==2 && this.maze[this.currentNode.getX()-2][this.currentNode.getY()]<2) {
			// Able to move to up
			this.currentNode.setX(this.currentNode.getX()-2);
			return true;
		} else if (this.checkerObject.getLeft()==2 && this.maze[this.currentNode.getX()][this.currentNode.getY()-2]<2) {
			// Able to move to left
			this.currentNode.setY(this.currentNode.getY()-2);
			return true;
		} else if (this.checkerObject.getDown()==2 && this.maze[this.currentNode.getX()+2][this.currentNode.getY()]<2) {
			// Able to move to down
			this.currentNode.setX(this.currentNode.getX()+2);
			return true;
		} else if (this.checkerObject.getRight()==2 && this.maze[this.currentNode.getX()][this.currentNode.getY()+2]<2) {
			// Able to move to right
			this.currentNode.setY(this.currentNode.getY()+2);
			return true;
		}
		return false;
	}
}