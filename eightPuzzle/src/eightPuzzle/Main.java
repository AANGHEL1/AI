package eightPuzzle;

import java.io.*;

public class Main {

	public static void main(String[] args) {
		
		BufferedReader reader =  
                new BufferedReader(new InputStreamReader(System.in)); 
      
		
		//Start
		System.out.println("Welcome to 8-puzzle! Which level of difficulty do you choose? (Easy, Medium, Hard) ");
		String difficulty = "";
		try {
			difficulty = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		// Choose difficulty
		Node x = new Node(null , null , null, 0,0,0,0,0);;
		if (difficulty.equals("Easy")) {
			int[] easy = {1,3,4,8,6,2,7,0,5};
			x = new Node(easy , null , null, 0,0,0,0,0);
		}
		else if(difficulty.equals("Medium")) {
			int[] medium = {2,8,1,0,4,3,7,6,5};
			x = new Node(medium , null , null, 0,0,0,0,0);
		}
		else if(difficulty.equals("Hard")) {
			int[] hard = {5,6,7,4,0,8,3,2,1};
			x = new Node(hard , null , null, 0,0,0,0,0);
		}
		else {
			System.out.println("You didn't type correctly! Restart program please.");
		}
		
		String algo ="";
		System.out.println("Perfect! Now, which algorithm do you choose? (BFS, DFS, UCS, GBF, A1, A2) ");
		try {
			algo = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		//Choose algorithm
		if (algo.equals("BFS")) {
			System.out.println("##############BFS##############\n");
			Algorithms.BFS(x);
		}
		else if(algo.equals("DFS")) {
			System.out.println("##############DFS##############\n");
			Algorithms.DFS(x);
		}
		else if(algo.equals("UCS")) {
			System.out.println("##############Uniform-Cost Search##############\n");
			Algorithms.uniformCost(x);
		}
		else if(algo.equals("GBF")) {
			System.out.println("############## Greedy Best-First Search ##############\n");
			Algorithms.GreedyBestFS(x);
		}
		else if(algo.equals("A1")) {
			System.out.println("############## A* using heuristc = number of misplaced tiles ##############\n");
			Algorithms.A1(x);
		}
		else if(algo.equals("A2")) {
			System.out.println("############## A* using heuristic = Manhattan Distances ##############\n");
			Algorithms.A2(x);
		}
		else {
			System.out.println("You didn't type correctly! Restart program please.");
		}
	}
}

