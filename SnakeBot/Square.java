package Geometry;

public class Square {
	static int land = 0;
	static int food = 1;
	static int cell = 2;
	int type = land;
	int x;
	int y;
	int orientation;
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
