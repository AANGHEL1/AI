package DotsAndBoxes;

import java.util.ArrayList;
import java.util.Random;



public class Minimax {
	//main MiniMax
	public static Object[] miniMaxSearch (Board board, int ply) {
		
		Random rand = new Random();
		ArrayList<Board> game = board.getChildren();
		int max = Integer.MIN_VALUE;
		Object[] move;
		Object[] returnmove = new Object[3];
		for (int i = 0; i<game.size();i++) {
			move = MaxValue(game.get(i),ply-1);
			if ((int)move[0] > max) {
				max = (int)move[0];
				returnmove[0] = move[0];
				returnmove[1] = move[1];
				returnmove[2] = move[2];
			}
		}
		//if there wasn't any move that would be good for the computer, it randomly picks a move
		if(returnmove[0] == null) {
			for (int i=0;i<board.size();i++) {
				if(max<board.getBox(i).points) {
					max = board.getBox(i).points;
					
					Board toexecute = game.get(rand.nextInt(game.size()));
					returnmove[0] = 0;
					returnmove[1] = toexecute.lastMoveMade;
					returnmove[2] = toexecute.lastBoxModified;
				}
			}
		}
		return returnmove;
	}
	//MaxValue
	public static Object[] MaxValue(Board board, int ply) {
		if(ply == 0 || board.getChildren().size()==0) {
			int value = board.wouldCompleteBox(board.lastMoveMade, board.lastBoxModified);
			return new Object[] {value,board.lastMoveMade,board.lastBoxModified};
		}
		
		Object[] returnobj = new Object[3];
		
		int utilityval = Integer.MIN_VALUE;
		int max;
		for (int i =0; i< board.getChildren().size();i++) {
			Board currentBoard = board.getChildren().get(i);
			max = (int) MinValue(currentBoard,ply-1)[0];
			if (utilityval<max) {
				utilityval = max;
				int value = board.wouldCompleteBox(board.lastMoveMade, board.lastBoxModified);
				returnobj[0] = value;
				returnobj[1] = board.lastMoveMade;
				returnobj[2] = board.lastBoxModified;
				if (utilityval>0) return returnobj;
			}
		}
		return returnobj;
	}
	
	//MinValue 
	public static Object[] MinValue(Board board, int ply) {
		if(ply == 0 || board.getChildren().size()==0) {
			int value = board.wouldCompleteBox(board.lastMoveMade, board.lastBoxModified);
			return new Object[] {value,board.lastMoveMade,board.lastBoxModified};
		}
		Object[] returnobj = new Object[3];
		int utilityval = Integer.MAX_VALUE;
		int max = 0;
		for (int i =0; i< board.getChildren().size();i++) {
			Board currentBoard = board.getChildren().get(i);
			max = (int) MaxValue(currentBoard,ply-1)[0];
			if (utilityval>max) {
				utilityval = max;
				int value = board.wouldCompleteBox(board.lastMoveMade, board.lastBoxModified);
				returnobj[0] = value;
				returnobj[1] = board.lastMoveMade;
				returnobj[2] = board.lastBoxModified;
				if (utilityval>0) return returnobj;
			}
		}
		return returnobj;
	}

}
