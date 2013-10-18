import java.io.*;
import java.util.*;

/**
 * Handles File I/O.
 * 
 * @author Isaac Yong
 * @version 17/10/2013
 */
public class Model {
	private ArrayList<Node> wallList = null;
	private String mazeSize = null ;

	public Model(String fileName) {
		mazeSize = new String();
		wallList = new ArrayList<Node>();
		readFile(fileName);
	}

	public void readFile(String fileName) {
		try {
			String tmpStr = "", x = "", y = "";
			BufferedReader buf = new BufferedReader(new FileReader(fileName));
			this.mazeSize = buf.readLine(); // Reads first line to obtain maze size
			while((tmpStr = buf.readLine()) != null) {
				// Remove whitespace and brackets
				tmpStr = tmpStr.trim();
				tmpStr = tmpStr.replace("(", "");
				tmpStr = tmpStr.replace(")", "");
				StringTokenizer token = new StringTokenizer(tmpStr, ", ");
				if(token.hasMoreTokens()) {
					x = token.nextToken();
				}
				if(token.hasMoreTokens()) {
					y = token.nextToken();
				}
				wallList.add(new Node(Integer.parseInt(x), Integer.parseInt(y)));
			}
		} catch(Exception e) {
			System.out.println("There was an error reading the file.");
            e.printStackTrace();
		}
	}

	// Accessor for wallList
	public ArrayList<Node> getWalls() {
		return this.wallList;
	}

	public Node getSize() {
		String x = "", y ="";
		mazeSize = mazeSize.trim();
		mazeSize = mazeSize.replace("[", "");
		mazeSize = mazeSize.replace("]", "");

		StringTokenizer token = new StringTokenizer(mazeSize, ", ");

		if(token.hasMoreTokens()) {
			x = token.nextToken();
		}
		if(token.hasMoreTokens()) {
			y = token.nextToken();
		}

		return new Node(Integer.parseInt(x), Integer.parseInt(y));
	}
}
