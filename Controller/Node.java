/**
 * A Node that stores a coordinate.
 * 
 * @author Isaac Yong
 * @version 15/10/2013
 */

public class Node implements Comparable<Node> {
	private int x;
	private int y;

	public Node() {}

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int compareTo(Node nodeB) {
		if((this.x == nodeB.getX())&&(this.y == nodeB.getY()))
			return 1;
		return 0;
	}
}
