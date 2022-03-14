package eightPuzzle;

import java.util.Comparator;

public class CostComparator implements Comparator<Node> {
	public int compare(Node n, Node m) {
		if (n.cost>m.cost) 
			return 1;
		if (n.cost<m.cost) 
			return -1;
		return 0;
	}
}
