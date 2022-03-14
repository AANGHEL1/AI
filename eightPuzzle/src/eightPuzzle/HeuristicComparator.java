package eightPuzzle;

import java.util.Comparator;

public class HeuristicComparator implements Comparator<Node>{
	public int compare(Node n, Node m) {
		if (n.heuristic>m.heuristic) 
			return 1;
		if (n.heuristic<m.heuristic) 
			return -1;
		return 0;
	}
}
