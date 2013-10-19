/**
 * File Node.java
 * 
 * Represents a Node that stores a coordinate.
 * 
 * @author Isaac Yong
 * @version 15/10/2013
 */

public class Node implements Comparable<Node> {
	private int x;
	private int y;

	public Node() {}

	/**
	 * To assign the coordinates to a node
	 * 
	 * @param integer x for coordinate x  
	 * @param integer y for coordinate y
	 */
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * To get the coordinate of x
	 * 
	 * @return coordinate of x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * To get the coordinate of y
	 * 
	 * @return coordinate of y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * To set the coordinate of x
	 * 
	 * @return coordinate of x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * To set the coordinate of y
	 * 
	 * @return coordinate of y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * To compare equality between two nodes
	 */
	public int compareTo(Node nodeB) {
		if((this.x == nodeB.getX())&&(this.y == nodeB.getY())) {
			return 1;
		}
		return 0;
	}
}
