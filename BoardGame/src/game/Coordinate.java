package game;

/**
 * Two integers that corresponds to squares on a game board.
 */
public class Coordinate {
	private int x;
	private int y;

	/**
	 * This constructor defines the coordinates.
	 * 
	 * @param x
	 * @param y
	 */
	public Coordinate(int x, int y) {
		setCoordinate(x, y);
	}

	/**
	 * This method sets the value of x and y
	 * 
	 * @param x
	 * @param y
	 */
	public void setCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This method return x.
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * This method return y.
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * This method returns the coordinates in (0, 0) form.
	 * 
	 * @return the coordinates in numerical form
	 */
	@Override
	public String toString() {
		return "( " + x + " , " + y + " )";
	}

	/**
	 * This method returns the coordinates in alphanumerical form, the same way
	 * a user would enter the coordinate.
	 * 
	 * @return the coordinates in alphanumerical form
	 */
	public String toAlphaNumerical() {
		return "" + (char) ('A' + y) + (x + 1);
	}
}