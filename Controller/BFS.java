/**
 * Breadth-First-Search Algorithms class.
 * 
 * @author Isaac Yong
 * @version 10/10/2013
 */

public class BFS extends Algorithms {
	public BFS() {
		super();
	}

	public BFS(String fileName) {
		super(fileName);
	}

	public void compute() {
		Checker checkerObject = new Checker(this.fileName);
		checkerObject.nodeCheckAll(this.currentNode, this.closedNodes);
		if(!stageOne()) {
			if(!stageTwo()) {
				System.out.println("Something went wrong!");
			}
		}

		if(checkerObject.isGoal(this.currentNode)) {
			// End of algorithm
		} /*else if () {
			// Check for NO SOLUTION
			// End with failure msg
		}*/
		// Repeat computation if not exhausted
		compute();
	}

}
