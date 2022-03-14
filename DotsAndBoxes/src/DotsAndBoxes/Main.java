package DotsAndBoxes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		
		//intro to game
		System.out.println("Starting game");
		System.out.println("You are player A, the computer will be player B");
		
		System.out.println("Pick a ply level (number between 1 and 7): ");
		String ply = reader.readLine();
		
		System.out.println("Please pick the dimensions of the board!");
		System.out.println("Number of rows: ");
		String row = "";
		try {
			row = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String column = "";
		System.out.println("Number of columns: ");
		try {
			column = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int n = Integer.parseInt(row);
		int m = Integer.parseInt(column);
		
	
		System.out.println("This is the structure of the board: ");
		Board display = new Board(n,m,null,-1);
		for (int i = 0;i<n*m;i++) {
			display.getBox(i).left = true;
			display.getBox(i).right = true;
			display.getBox(i).up = true;
			display.getBox(i).down = true;
			display.getBox(i).winner = ""+ i;
		}
		display.printBoard();
		
		
		Board board = new Board(n,m,null,-1);
		System.out.println("\nInitial board is: ");
		board.printBoard();
		boolean gameEnded = false;
		
		
		//while there are moves left to be made
		while (gameEnded == false) {
			
			
			//the player's turn
			System.out.println("Your turn! Choose an action and a position for your move. Example: if you want to take the right line write 'right'");
			String action = reader.readLine();
			System.out.println("Choose the box you want to modify. Example: if you want to take the right line write of box 1 write '1'");
			String box = reader.readLine();

			board.addEdge(action, Integer.parseInt(box));
			//adjust winner fields in boxes won
			completeWinners(board,n,m,"A");
			//printing the board
			board.printBoard();
			//checking if the game was won
			if(announceWinner(board,n,m,gameEnded)==1) break;
			
			//Computer's turn
			System.out.println("Computer's turn!");
			Object[] computermove = Minimax.miniMaxSearch(board,Integer.parseInt(ply));
			String computeraction = (String) computermove[1];
			int computerbox = (Integer) computermove[2];
			
			
			board.addEdge(computeraction, computerbox);
			//adjust winner fields in boxes won
			completeWinners(board,n,m,"B");
			//printing the board
			board.printBoard();
			//checking if the game was won
			if(announceWinner(board,n,m,gameEnded)==1) break;
			
		}
		
	}
	
	
	//adjust winner fields in boxes won
	public static void completeWinners(Board board, int n, int m, String winner) {
		for(int i =0;i<n*m;i++) {
			if(board.getBox(i).isComplete() && board.getBox(i).complete == false)
			{
				board.getBox(i).winner =winner;
				board.getBox(i).complete = true;
			}
		}
	}
	
	//checking if the game was won
	public static int announceWinner(Board board, int n, int m, boolean gameEnded) {
		int scoreA = 0;
		int scoreB = 0;
		int count = 0;
		
		//if every box in the board is complete
		for (int i = 0;i<n*m;i++) {
			if (!board.getBox(i).isComplete()) {
				count++;
			}
		}
		if (count == 0) {
			gameEnded = true;
			for (int i = 0;i<n*m;i++) {
				if (board.getBox(i).winner == "A") {
					scoreA = scoreA + board.getBox(i).points;
				}
				if (board.getBox(i).winner == "B") {
					scoreB = scoreB + board.getBox(i).points;
				}
			}
			
			//Display scores
			System.out.println("Score: Computer: "+scoreB + ". You: "+ scoreA + " ");
			if(scoreA>scoreB) System.out.println("You win with " + scoreA + " points");
			else if (scoreA<scoreB)
				System.out.println("Computer wins with " + scoreB + " points");
			else
				System.out.println("It s a draw!");
			
			return 1;
		}
		return 0;
	}
	

}
