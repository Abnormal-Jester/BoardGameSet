package game;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		setCoordinate(x, y);
	}

	public void setCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}