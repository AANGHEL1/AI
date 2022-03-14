package DotsAndBoxes;

public class Box {
	
	boolean right;
	boolean left;
	boolean up;
	boolean down;
	int points;
	String winner;
	boolean complete;
	
	public Box (boolean right, boolean left, boolean up, boolean down, int points, String winner,boolean complete) {
		this.right = right;
		this.left = left;
		this.up = up;
		this.down = down;
		this.points = points;
		this.winner = winner;
		this.complete = complete;
	}
	
	//checking if the box is complete
	public boolean isComplete() {
		if(this.left == true && this.right == true && this.up == true && this.down == true) {
			return true;
		}
		else
			return false;
	}
	
}
