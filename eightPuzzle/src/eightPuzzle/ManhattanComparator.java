package eightPuzzle;

import java.util.Comparator;

public class ManhattanComparator implements Comparator<Node> {
	public int compare(Node n, Node m) {
		if (n.manhattan>m.manhattan) 
			return 1;
		if (n.manhattan<m.manhattan) 
			return -1;
		return 0;
	}

}
