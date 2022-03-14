package eightPuzzle;

import java.util.ArrayList;

public class Node {
	int[] state;
	Node parent;
	String action;
	int cost;
	int depth;
	int heuristic;
	int a1;
	int manhattan;
	
	public Node(int[]state, Node parent, String action, int cost, int depth, int heuristic, int a1, int manhattan) {
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.cost = cost;
		this.depth = depth;
		this.heuristic = heuristic;
		this.a1 = a1;
		this.manhattan = manhattan;
	}
	
	public ArrayList<Node> getChildren (){
		
		//Check for blank in the puzzle
		int blank = 0;
		for (int i = 0;i<this.state.length;i++) {
			if(this.state[i]==0) {
				blank = i;
			}
		}
		
		
		ArrayList<Node> children = new ArrayList<Node>();
		//rightmost column
		if(blank != 0 && blank != 3 && blank != 6) {
			int[] child = state.clone();
			//cost += child[blank-1];
			child[blank] = child[blank-1];
			child[blank-1] = 0;
			Node newChild = new Node(child, this, "Left",child[blank], this.depth+1,0,0,0);
			children.add(newChild);
			
		}
		//leftmost column
		if(blank != 2 && blank != 5 && blank != 8) {
			int[] child = state.clone();
			//cost += child[blank+1];
			child[blank] = child[blank+1];
			child[blank+1] = 0;
			Node newChild = new Node(child, this, "Right",child[blank], this.depth+1,0,0,0);
			children.add(newChild);
			
		}
		//last row
		if(blank <= 5) {
			int[] child = state.clone();
			//cost += child[blank+3];
			child[blank] = child[blank+3];
			child[blank+3] = 0;
			Node newChild = new Node(child, this, "Down",child[blank], this.depth+1,0,0,0);
			children.add(newChild);
			
		}
		//first row
		if(blank >= 3) {
			int[] child = state.clone();
			//cost += child[blank-3];
			child[blank] = child[blank-3];
			child[blank-3] = 0;
			Node newChild = new Node(child, this, "Up",child[blank], this.depth+1,0,0,0);
			children.add(newChild);
			
		}
		
		return children;
	}

}
