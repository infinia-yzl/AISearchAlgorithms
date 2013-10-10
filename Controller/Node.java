/**
 * A Node Object that stores a coordinate.
 * 
 * @author Isaac Yong
 * @version 1.0
 */

public class Node {
	private int x;
	private int y;

	public Node() {

	}

	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}
}