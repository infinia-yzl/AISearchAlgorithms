import java.util.*;

/**
 * File Hill.java
 * 
 * Represents Hill Climbing Algorithms class.
 * 
 * @author Isaac Yong
 * @version 10/10/2013
 */

public class Hill extends Algorithms{
	private Node goal = null;
	private ArrayList<Node> tempNodeList = new ArrayList<Node>(); // store the shortest SLD of each node and become one path

	public Hill(){
		super();
		goal = this.checkerObject.getGoal();

	}
	
	/**
         * To call the parent constructor
         * 
         * @param fileName 
         */
	public HillClimbing(String filename){
		super(filename);
		goal = this.checkerObject.getGoal();
	}

	 /**
         * To act as primary algorithm computation function
         * It won't compute again cause it ONLY find the shortest SLD and NOT backtracking
         */
	public void compute(){
		this.checkerObject.nodeCheckerAll(this.currentNode, this.closedNodes); // arr store one path only even it won't go to goal 
		if(this.currentNode != -1)
			this.checkerObject.isGoal(this.currentNode = stageOne())
			
		else
			System.out.println("No solution");
			return;
	}
	
	/**
         * Stage 1 Computation of algorithm
         */
	public Node stageOne(){
		private smallest = 0, index = -1;
		
		private ArrayList<integer> distance = new ArrayList<Integer> (4, -1);
		private int[] tmpDistance = new int[4]{-1, -1, -1, -1};
		
		this.closedNodes.add(this.currentNode);
		this.expandCounter++;

		if (this.checkerObject.getUp() == 1){
			
			distance.set(0, calculateDistance(tempNodeList.get(0))); // distance[0] = 10- 0 + 12 - 0
		} else if (this.checkerObject.getLeft()==1) {
			// Able to move to left
			
			distance.set(1, calculateDistance(tempNodeList.get(1)));
		} else if (this.checkerObject.getDown()==1) {
			// Able to move to down
			
			distance.set(2, calculateDistance(tempNodeList.get(2)));
		} else if (this.checkerObject.getRight()==1) {
			// Able to move to right
			
			distance.set(3, calculateDistance(tempNodeList.get(3)));
		}

		// similar distance
		for (int i = 0 ; i < distance.size(); i++){
			if ( distance.get(i) < smallest ){
					smallest = distance.get(i);
					switch (i)
					{
						case 0:
							tempNodeList.add(new Node((this.currentNode.getX()-1), (this.currentNode.getY()))); 
							return new Node((this.currentNode.getX()-1), (this.currentNode.getY()));
							break;
						case 1:
							tempNodeList.add(new Node((this.currentNode.getX()), (this.currentNode.getY()-1))));
							return new Node((this.currentNode.getX()), (this.currentNode.getY()-1));
							break;
						case 2:
							tempNodeList.add(new Node((this.currentNode.getX()+1), (this.currentNode.getY())));
							return new Node((this.currentNode.getX()+1), (this.currentNode.getY()));
							break;
						case 3:
							tempNodeList.add(new Node((this.currentNode.getX()), (this.currentNode.getY()+1))));
							return new Node((this.currentNode.getX()), (this.currentNode.getY()+1)));
							break;
					}
			}
			else{ 
				if (distance.get(i) == smallest){
					tmpDistance[i] = distance.get(i);

					for (int j =0 ; j < 4 ; j++){
						if (tmpDistance[j] > 0)
						{
							if (j == 0){
								smallest = tmpDistance{j};
								return new Node((this.currentNode.getX()-1), (this.currentNode.getY())));
								break;
							}
							else{
								if(j == 1){
									smallest = tmpDistance{j};
									return new Node((this.currentNode.getX()), (this.currentNode.getY()-1)));
									break;
								}
								else{
									if (j == 2){
										smallest = tmpDistance{j};
										return new Node((this.currentNode.getX()+1), (this.currentNode.getY())));
										break;
									}
									else{
										smallest = tmpDistance{j};
										return new Node((this.currentNode.getX()), (this.currentNode.getY()+1)));
										break;
										}
									}
								}	
						}
					}
					
				}
			}
		}
		return this.currentNode = -1;
	}

	/**
         * To calculate node distance to goal
         * 
         * @param node
         * 
         * @return integer of distance to goal
         */
	public int calculateDistance(Node node) {
		return (Math.abs(this.goal.getX() - node.getX()) + Math.abs(this.goal.getY() - node.getY()));
	}


} 
