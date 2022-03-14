package eightPuzzle;

import java.util.Comparator;

public class A1Comparator implements Comparator<Node> {
	public int compare(Node n, Node m) {
		if (n.a1>m.a1) 
			return 1;
		if (n.a1<m.a1) 
			return -1;
		return 0;
	}

}
