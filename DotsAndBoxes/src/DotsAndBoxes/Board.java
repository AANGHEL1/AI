package DotsAndBoxes;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

	int n;
	int m;
	String lastMoveMade;
	int lastBoxModified;
	List<Box> board = new ArrayList<Box>();
	
	
	public Board(int n, int m, String lastMoveMade, int lastBoxModified) {
		Random rand = new Random();
		this.n = n;
		this.m = m;
		this.lastMoveMade = lastMoveMade;
		this.lastBoxModified = lastBoxModified;
		
		for ( int i = 0; i < n*m; i++) {
			int num = 1 + rand.nextInt(5);
			board.add(new Box(false,false,false,false, num, null,false));
		}
	}
	
	
	
	public ArrayList<Board> getChildren () {
		ArrayList<Board> arr = new ArrayList<Board>();
		//initial copy
		List<Box> copyboard = new ArrayList<Box>();
		Board cpy = new Board(n,m,null,-1);
		
		for (int i = 0; i < board.size();i++) {
			Box b = getBox(i);
			copyboard.add(new Box(b.right,b.left,b.up,b.down,b.points,b.winner,b.complete));
		}
		cpy.board = copyboard;
		
		//searching through the copy of the board
		for (int i=0;i<cpy.size();i++) {
		
			//Copy for down move
			List<Box> copyboard2 = new ArrayList<Box>();
			Board cpy2 = new Board(n,m,null,-1);
			
			for (int l = 0; l < board.size();l++) {
				Box b = getBox(l);
				copyboard2.add(new Box(b.right,b.left,b.up,b.down,b.points,b.winner,b.complete));
			}
			cpy2.board = copyboard2;
			
			//Copy for up move
			List<Box> copyboard3 = new ArrayList<Box>();
			Board cpy3 = new Board(n,m,null,-1);
			
			for (int l = 0; l < board.size();l++) {
				Box b = getBox(l);
				copyboard3.add(new Box(b.right,b.left,b.up,b.down,b.points,b.winner,b.complete));
			}
			cpy3.board = copyboard3;
			
			//Copy for left move
			List<Box> copyboard4 = new ArrayList<Box>();
			Board cpy4 = new Board(n,m,null,-1);
			
			for (int l = 0; l < board.size();l++) {
				Box b = getBox(l);
				copyboard4.add(new Box(b.right,b.left,b.up,b.down,b.points,b.winner,b.complete));
			}
			cpy4.board = copyboard4;
			
			
			//Copy for right move
			List<Box> copyboard5 = new ArrayList<Box>();
			Board cpy5 = new Board(n,m,null,-1);
			
			for (int l = 0; l < board.size();l++) {
				Box b = getBox(l);
				copyboard5.add(new Box(b.right,b.left,b.up,b.down,b.points,b.winner,b.complete));
			}
			cpy5.board = copyboard5;
			
			Box b = getBox(i);
			if (!b.isComplete()) {
				//child with the down position
				if(b.down == false) {
					cpy2.addEdge("down", i);
					cpy2.lastMoveMade = "down";
					cpy2.lastBoxModified = i;
					arr.add(cpy2);
				}
				//child with the up position
				if(b.up == false) {
					cpy3.addEdge("up", i);
					cpy3.lastMoveMade = "up";
					cpy3.lastBoxModified = i;
					arr.add(cpy3);
				}
				//child with the left position
				if(b.left == false) {
					cpy4.addEdge("left", i);
					cpy4.lastMoveMade = "left";
					cpy4.lastBoxModified = i;
					arr.add(cpy4);
				}
				//child with the right position
				if(b.right == false) {
					cpy5.addEdge("right", i);
					cpy5.lastMoveMade = "right";
					cpy5.lastBoxModified = i;
					arr.add(cpy5);
				}
				
			}
		}
		
		
		return arr;
	}
	
	
	
	
	public void addEdge (String edge, int boxpos) {
		int isTerminalRow = boxpos/m;
		int isTerminalColumn = boxpos%m;
		
		
			if (edge.equals("left") && isTerminalColumn !=0) {
				//if I want to add a left line to a box and it is not the first column in the board
				getBox(boxpos).left = true;
				getBox(boxpos - 1).right = true;
			}
			else if (edge.equals("left")) {
				getBox(boxpos).left = true;
			}
			if (edge.equals("right") && isTerminalColumn != m-1) {
				//if I want to add a left line to a box and it is not the last column in the board
				getBox(boxpos).right = true;
				getBox(boxpos + 1).left = true;
			}
			else if (edge.equals("right")) {
				getBox(boxpos).right = true;
			}
			if (edge.equals("up") &&  isTerminalRow != 0) {
				//if I want to add a left line to a box and it is not the first row in the board
				getBox(boxpos).up = true;
				getBox(boxpos - m).down = true;
			}
			else if (edge.equals("up")) {
				getBox(boxpos).up = true;
			}
			if (edge.equals("down") && isTerminalRow != n-1 ) {
				//if I want to add a left line to a box and it is not the last row in the board
				getBox(boxpos).down = true;
				getBox(boxpos + m).up = true;
			}
			else if (edge.equals("down")) {
				getBox(boxpos).down = true;
			}
			
				
		
	}
	
	//get the box with index "index" from the board
	public Box getBox (int index) {
		return board.get(index);
	}
	
	//function that predicts if the move that I want to make would complete a box
	public int wouldCompleteBox(String action, int boxpos) {
		List<Box> copyboard = new ArrayList<Box>();
		Board cpy = new Board(n,m,null,-1);
		
		for (int i = 0; i < board.size();i++) {
			Box b = getBox(i);
			copyboard.add(new Box(b.right,b.left,b.up,b.down,b.points,b.winner,b.complete));
		}
		cpy.board = copyboard;
		
		cpy.addEdge(action, boxpos);
		//the points are returned and considered by the minimax only if the box will be completed with the move
		if (cpy.getBox(boxpos).isComplete()) return cpy.getBox(boxpos).points;
		else
			return 0;
	}
	
	
	
	//getting the size of the board
	public int size () {
		return board.size();
	}
	
	//print of the board in a matrix
	public void printBoard() {
		String[][] p = new String[n*3][m*3];
		
		for (int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				int pos = i*m  + j;
				if (getBox(pos).up == true) {
					p[3*i][3*j+1] = "-";
				}
				if (getBox(pos).down == true) {
					p[3*i+2][3*j+1]="-";
					
				}
				if(getBox(pos).left == true) {
					p[3*i+1][3*j] = "|";
				}
				else {
					p[3*i+1][3*j] = " ";
				}
				if(getBox(pos).right == true) {
					p[3*i+1][3*j+2] = "|";
				}
				else
					p[3*i+1][3*j+2] = " ";
				if(getBox(pos).isComplete())
					p[3*i+1][3*j+1] = ""+getBox(pos).winner;
				else
					p[3*i+1][3*j+1] = ""+getBox(pos).points;
				
			}
		}
		
		
		for (int i = 0;i<n*3;i++) {
			for (int j = 0;j< m*3;j++) {
				if(p[i][j] == null) System.out.print(" ");
				else
					System.out.print(p[i][j]+"");
			}
			System.out.println("");
		}
		
	}
	

	
}
