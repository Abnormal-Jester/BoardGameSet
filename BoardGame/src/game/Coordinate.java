package game;

public class Coordinate {
	public Coordinate(int col, int row) {
		setCoordinate(col, row);
	}

	public void setCoordinate(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	@Override
	public String toString() {
		return "( " + col + " , " + row + " )";
	}

	public String toAlphaNumericalString() {
		return "" + (char) ('A' + row) + (col + 1);
	}

	private int col;
	private int row;
}