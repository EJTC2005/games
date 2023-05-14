package soup;

public class Segment {
	//snake body parts
	int x;
	int y;
	int width;
	int length;
	boolean snakeOrNot;
	public Segment(int x, int y, int width, int length, boolean snakeOrNot) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.length=length;
		this.snakeOrNot=snakeOrNot;
	}
	public void setSnake(boolean snakeOrNO) {
		this.snakeOrNot= snakeOrNO;
	}
	public void setX(int xtra) {
		this.x = xtra;
	}
	public void setY(int ytra) {
		this.y = ytra;
	}
}
