package eightPuzzle;

import java.util.*;

public class Algorithms {
	
	//print current node
	private static void printNode( Node current) {
		System.out.print(current.action+", Current state is: ");
		for(int i =0;i<current.state.length;i++)
			System.out.print(current.state[i]+ " ");
		System.out.print("\n\n");
	}
	
	//print total cost, depth and number of iterations
	
	private static void printSolved( Node child, int iterations) {
		System.out.println("Total cost = "+ child.cost + " at depth: "+child.depth);
		System.out.println("Solved after " + iterations + " iterations!\n");
	}
	
	
	//check if reached contains states
	private static int contains(ArrayList<int[]> reached, Node child) {
		int count = 0;
		for(int i=0 ; i<reached.size();i++) {
			if( Arrays.equals(reached.get(i), child.state)) {
				count = 1;
				break;
			}
		}
		return count;
	}
	
	
	///////////////BFS/////////////
	public static Node BFS (Node initial) {
		Deque<Node> frontier = new LinkedList<Node>();
		int[] goal = {1,2,3,8,0,4,7,6,5};
		int iterations = 0;
		int sz = 0;
		ArrayList<int[]> reached = new ArrayList<int[]>();
		
		//Node is goal
		if(initial.state == goal) {
			System.out.println("Solved after 0 iterations!\n");
			return initial;
		}
		
		frontier.addFirst(initial);
		reached.add(initial.state);
		
		while(!frontier.isEmpty()) {
			Node current = frontier.pop();
			
			//Printing current state
			printNode(current);
			
			iterations++;
			
			//Visit every child
			for(Node child : current.getChildren()){
				int[] s = child.state;
				//Child is goal
				if (Arrays.equals(s, goal)) {
					child.cost = current.cost+child.cost;
					printNode(child);
					printSolved(child,iterations);
					System.out.println("Max size of stack is: " + sz);
					return child;
				}
				//Checking if s is in reached
				int count = contains(reached,child);
				
				//insert in frontier, reached and update cost
				if(count != 1) {
					child.cost = current.cost+child.cost;
					reached.add(s);
					frontier.addLast(child);
					//max size of stack
					if(sz< frontier.size())
						sz = frontier.size();
				}
			}
		}
		System.out.println("Fail");
		return null;
	}
	
	
	///////////////DFS/////////////
	
	public static Node DFS(Node initial) {
		
		//LinkedList<Node> frontier = new LinkedList<Node>();
		Stack<Node> frontier = new Stack<Node>();
		int sz = 0;
		int[] goal = {1,2,3,8,0,4,7,6,5};
		int iterations = 0;
		ArrayList<int[]> reached = new ArrayList<int[]>();
		
		
		//Node is goal
		if(initial.state == goal) {
			System.out.println("Solved after 0 iterations!\n");
			return initial;
		}
		frontier.push(initial);
		reached.add(initial.state);
		while(!frontier.isEmpty()) {
			Node current = frontier.pop();
			
			//Printing current state
			printNode(current);
			
			iterations++;
			
			for(Node child : current.getChildren()){
				int[] s = child.state;
				
				if (Arrays.equals(s, goal)) {
					child.cost = current.cost+child.cost;
					printNode(child);
					printSolved(child,iterations);
					System.out.println("Max size of stack is "+ sz);
					
					return child;
				}
				
				int count = contains(reached,child);
				//insert in frontier, reached and update cost
				if(count != 1) {
					reached.add(s);
					child.cost = current.cost+child.cost;
					frontier.add(child);
					//max size of stack
					if(sz< frontier.size())
						sz = frontier.size();
				}
			}
			
		}
		System.out.println("Fail");
		return null;
	
	}
	
	//Uniform-cost Search
	public static Node uniformCost(Node initial) {
		
		int sz = 0;
		int[] goal = {1,2,3,8,0,4,7,6,5};
		int iterations = 0;
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, new CostComparator());
		frontier.add(initial);
		ArrayList<int[]> reached = new ArrayList<int[]>();
		
		//Node is goal
		if(initial.state == goal) {
			System.out.println("Solved after 0 iterations!\n");
			return initial;
		}
		reached.add(initial.state);
		
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			
			//Printing current state
			printNode(current);
			
			iterations++;
			//reached.add(current.state);
			for (Node child : current.getChildren()) {
				int[] s = child.state;
				if (Arrays.equals(s, goal)) {
					child.cost = current.cost + child.cost;
					printNode(child);
					printSolved(child,iterations);
					System.out.println("Max size of stack is: " + sz);
					
					return child;
				}
				int count = contains(reached,child);
				//insert in frontier, reached and update cost
				if(count != 1) {
					reached.add(s);
					child.cost = current.cost+child.cost;
					frontier.offer(child);
					//max size of stack
					if(sz< frontier.size())
						sz = frontier.size();
				}
			}
		}
		System.out.println("Fail");
		return null;
	}
	

	
	
	//////////Greedy Best First Search///////////
	
	public static Node GreedyBestFS(Node initial) {
		
		int[] goal = {1,2,3,8,0,4,7,6,5};
		int iterations = 0;
		int sz = 0;
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, new HeuristicComparator());
		
		//Node is goal
		if(initial.state == goal) {
			System.out.println("Solved after 0 iterations!\n");
			return initial;
		}
		
		
		initial.heuristic = heuristic(initial.state);
		frontier.add(initial);
		
		ArrayList<int[]> reached = new ArrayList<int[]>();
		reached.add(initial.state);
		
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			
			printNode(current);
			
			current.heuristic = heuristic(current.state);
			iterations++;
			for(Node child : current.getChildren()) {
				child.heuristic = heuristic(child.state);
				if(child.heuristic == 0) {
					child.cost = current.cost + child.cost;
					printNode(child);
					printSolved(child,iterations);
					System.out.println("Max size of stack is: " + sz);
					return child;
				}
				
				int count = contains(reached,child);
				//insert in frontier, reached and update cost
				if(count != 1) {
					reached.add(child.state);
					frontier.add(child);
					child.cost = current.cost+child.cost;
					//max size of stack
					if(sz< frontier.size())
						sz = frontier.size();
					}
				
			}
			
		}
		System.out.println("Fail");
		return null;
	}
	
	/////////// A1 - heuristic = number of misplaced tiles////////////
	
	
	
	private static int heuristic (int[] state) {
		int[] goal = {1,2,3,8,0,4,7,6,5};
		int misplaced = 0;
		for(int i=0;i<state.length;i++) {
			if(state[i]!=goal[i])
				misplaced++;
		}
		return misplaced;
	}
	
	
	
	public static Node A1 (Node initial){
		
		int[] goal = {1,2,3,8,0,4,7,6,5};
		int iterations = 0;
		int sz = 0;
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, new A1Comparator());
		

		//Node is goal
		if(initial.state == goal) {
			System.out.println("Solved after 0 iterations!\n");
			return initial;
		}
		
		
		initial.heuristic = heuristic(initial.state);
		initial.a1 = initial.heuristic+initial.cost;
		frontier.add(initial);
		
		ArrayList<int[]> reached = new ArrayList<int[]>();
		reached.add(initial.state);
		
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			printNode(current);
			current.heuristic = heuristic(current.state);
			current.a1 = current.heuristic+current.cost;
			iterations++;
			for(Node child : current.getChildren()) {
				child.heuristic = heuristic(child.state);
				child.a1 = child.heuristic+child.cost;
				if(child.heuristic == 0) {
					child.cost = current.cost + child.cost;
					printNode(child);
					printSolved(child,iterations);
					System.out.println("Max size of stack is: " + sz);
					
					return child;
				}
				
				int count = contains(reached,child);
				//insert in frontier, reached and update cost
				if(count != 1) {
					reached.add(child.state);
					frontier.add(child);
					child.cost = current.cost+child.cost;
					//max size of stack
					if(sz< frontier.size())
						sz = frontier.size();
				}
			}
			
		}
		System.out.println("Fail");
		return null;
	}
	
	
	
	
	/////////////////////A2 - heuristic = Manhattan distances//////////
	
	
	private static int manhattan (int[] state) {
		int[] goal = {1,2,3,8,0,4,7,6,5};
		int s = -1;
		int g = -1;
		int m = 0;
		
		for(int i = 1; i < 9; i++) {
			s = -1;
			g = -1;
			for(int j = 0; j < state.length; j++) {
				if(state[j]==i) 
					s = j;
				if(goal[j]==i) 
					g = j;
				if (s!=-1 && g!=-1) break;
			}
			m = m+ (Math.abs(s/3 - g/3) + Math.abs(s%3 - g%3));
		}
		
		
		return m;
	}
	
	
	public static Node A2(Node initial) {
		
		int[] goal = {1,2,3,8,0,4,7,6,5};
		int iterations = 0;
		int sz = 0;
	
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, new ManhattanComparator());
		

		//Node is goal
		if(initial.state == goal) {
			System.out.println("Solved after 0 iterations!\n");
			return initial;
		}
		
		
		initial.manhattan = manhattan(initial.state);
		frontier.add(initial);
		
		ArrayList<int[]> reached = new ArrayList<int[]>();
		reached.add(initial.state);
		
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			printNode(current);
			current.manhattan = manhattan(current.state);
			iterations++;
			
			for(Node child : current.getChildren()) {
				child.manhattan = manhattan(child.state);
				
				if (Arrays.equals(child.state, goal)) {
					child.cost = current.cost + child.cost;
					printNode(child);
					printSolved(child,iterations);
					System.out.println("Max size of stack is: " + sz);
					return child;
				}
				
				int count = contains(reached,child);
				//insert in frontier, reached and update cost
				if(count != 1) {
					reached.add(child.state);
					frontier.add(child);
					child.cost = current.cost+child.cost;
					//max size of stack
					if(sz< frontier.size())
						sz = frontier.size();
				}
			}
		}
		
		System.out.println("Fail");
		return null;
	}
	
	
	
}
