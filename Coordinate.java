package battleship;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate() {
		x = 1;
		y = 1;
	}

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}
