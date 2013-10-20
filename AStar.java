import java.util.*;

/**
 * File AStar.java
 * 
 * Represents A* Search Algorithm class.
 * 
 * @author Isaac Yong
 * @version 10/10/2013
 */

public class AStar extends Algorithms{
	private ArrayList<Node> deadEndList = null;
	private Node goal = this.checkerObject.getGoal();
    
	public AStar()  {
		super();
        
	}

	/**
	* To call the parent constructor
	* 
	* @param fileName 
	*/		
	public AStar(String filename) {
        super(filename);
        deadEndList = new ArrayList<Node>();
    	}

	/**
         * To act as primary algorithm computation function
         */
	public void compute(){
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
	
	/**
         * Stage 1 Computation of algorithm
         */
	public boolean stageOne(){
		ArrayList<Node> tempNodeList = new ArrayList<Node>();   
		int[] disList = new int[4];
		int lowest =100, count =0, i=0, j=-1;
        
		this.closedNodes.add(new Node(this.currentNode.getX(), this.currentNode.getY())); // Create new Node to prevent pointing to currentNode's address
		tempNodeList.add(this.currentNode);
        	this.expandCounter++;
        	this.view.printCounter(this.expandCounter);

        
        	if(this.checkerObject.getUp()==1) {
			disList[0] = calculateDistance(this.currentNode, tempNodeList);
        
		} else if (this.checkerObject.getLeft()==1) {
			disList[1] = calculateDistance(this.currentNode, tempNodeList);
            
		} else if (this.checkerObject.getDown()==1) {
			disList[2] = calculateDistance(this.currentNode, tempNodeList);
            
		} else if (this.checkerObject.getRight()==1) {
			disList[3] = calculateDistance(this.currentNode, tempNodeList);
		}

		while(i < 4 ){         
			if (disList[i] < lowest && disList[i] != 0){
				lowest = disList[i];
				j = i;
			}
			i++;            
		}

	switch (j){
		case 0: {
			this.currentNode.setX(this.currentNode.getX()-1);
			return true;
		}
            	case 1: {
			this.currentNode.setY(this.currentNode.getY()-1);
               		return true;
		}
		case 2:{
			this.currentNode.setX(this.currentNode.getX()+1);
			return true;
		}
		case 3:{
			this.currentNode.setY(this.currentNode.getY()+1);
			return true;
		}
        }
	return false;
	}

    	 /**
         * To calculate sum of the current distance to reach the node and the estimated distance from the node to get to the goal
         * 
         * @param node
         * @param tempNodeList
         * 
         * @return integer of current distance to reach the node plus estimated distance from the node to get to the goal
         */
	public int calculateDistance(Node node, ArrayList<Node> tempNodeList) {
		int n = Math.abs(this.goal.getX() - node.getX()) + Math.abs(this.goal.getY() - node.getY());
		int m =0;
		for ( m= 0 ; m < tempNodeList.size() ; m++)
		{
		if (m == 0)
                	m = 0;
		else
			m = tempNodeList.size();
		}
		return (n + m);
    
	}

	/**
	 * To verify the dead end
	 */
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

	/**
	 * To traceback steps out of dead end
 	 */
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
