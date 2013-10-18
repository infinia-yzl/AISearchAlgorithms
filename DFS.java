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
			this.view.printSuccess();
			return;
		}
		this.checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);
		if(!stageOne()) {
			if(!stageTwo()) {
				this.view.printFailure();
				return;
			}
		}

		// Repeat computation if not exhausted
		compute();
	}

	// Stage One Movement - Move to any unexplored node
	public boolean stageOne() {
		this.closedNodes.add(new Node(this.currentNode.getX(), this.currentNode.getY())); // Create new Node to prevent pointing to currentNode's address
		this.expandCounter++;
		this.view.printCounter(this.expandCounter);
		verifyDeadEnd();
		
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

	// Stage Two Movement - Move back to explored node (no available unexplored nodes)
	public boolean stageTwo() {
		ArrayList<Integer> priorities = new ArrayList<Integer>(4); // Index: 0 is Up, 1 is Left, 2 is Down, 3 is Right
		boolean isDead = false;

		for (int a=0; a<4; a++) {
			priorities.add(-1); // initialise ArrayList size
		}

		if(this.checkerObject.getUp()==2) {
			// Able to move to up
			isDead = false;
			for (Node tmpDead : deadEndList) {
				if(new Node(currentNode.getX()-1, currentNode.getY()).compareTo(tmpDead)==1) {
					isDead = true;
					break;
				}
			}
			// Checks if the node above is a dead end
			if(isDead == false) {
				if(new Node(currentNode.getX()-1, currentNode.getY()).compareTo(this.closedNodes.get(this.closedNodes.size()-2))==1)
					priorities.set(0, 1);
				else
					priorities.set(0, 0);
			}
		} 
		if (this.checkerObject.getLeft()==2) {
			// Able to move to left
			isDead = false;
			for (Node tmpDead : deadEndList) {
				if(new Node(currentNode.getX(), currentNode.getY()-1).compareTo(tmpDead)==1) {
					isDead = true;
					break;
				}
			}
			// Checks if the left node is a dead end
			if(isDead == false) {
				if(new Node(currentNode.getX(), currentNode.getY()-1).compareTo(this.closedNodes.get(this.closedNodes.size()-2))==1) {
					priorities.set(1, 1);
				}
				else {
					priorities.set(1, 0);
				}
			}
		} 
		if (this.checkerObject.getDown()==2) {
			// Able to move to down
			isDead = false;
			for (Node tmpDead : deadEndList) {
				if(new Node(currentNode.getX()+1, currentNode.getY()).compareTo(tmpDead)==1) {
					isDead = true;
					break;
				}
			}
			// Checks if the node below is a dead end
			if(isDead == false) {
				if(new Node(currentNode.getX()+1, currentNode.getY()).compareTo(this.closedNodes.get(this.closedNodes.size()-2))==1)
					priorities.set(2, 1);
				else
					priorities.set(2, 0);
			}
		} 
		if (this.checkerObject.getRight()==2) {
			// Able to move to right
			isDead = false;
			for (Node tmpDead : deadEndList) {
				if(new Node(currentNode.getX(), currentNode.getY()+1).compareTo(tmpDead)==1) {
					isDead = true;
					break;
				}
			}
			// Checks if the right node is a dead end
			if(isDead == false) {
				if(new Node(currentNode.getX(), currentNode.getY()+1).compareTo(this.closedNodes.get(this.closedNodes.size()-2))==1) {
					priorities.set(3, 1);
				}
				else {
					priorities.set(3, 0);
				}
			}
		}

		for (int i=0; i<4; i++) {
			if(priorities.get(i)==0) {
				if(i==0)
					this.currentNode.setX(this.currentNode.getX()-1);
				else if(i==1)
					this.currentNode.setY(this.currentNode.getY()-1);
				else if(i==2)
					this.currentNode.setX(this.currentNode.getX()+1);
				else if(i==3)
					this.currentNode.setY(this.currentNode.getY()+1);
				return true;
			}
		}
		for (int i=0; i<4; i++) {
			if(priorities.get(i)==1) {
				if(i==0)
					this.currentNode.setX(this.currentNode.getX()-1);
				else if(i==1)
					this.currentNode.setY(this.currentNode.getY()-1);
				else if(i==2)
					this.currentNode.setX(this.currentNode.getX()+1);
				else if(i==3)
					this.currentNode.setY(this.currentNode.getY()+1);
				return true;
			}
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
			deadEndList.add(new Node(this.currentNode.getX(), this.currentNode.getY()));
		}
	}
}
