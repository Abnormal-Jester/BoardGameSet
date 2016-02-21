package game;

import exception.PlacementFailedException;

/**
 * This class creates a 2d array of squares and keeps track of the piece
 * locations, win conditions, and text display.
 * 
 * @author Jarett Lee
 */
public abstract class CharBoard extends Board {
	/**
	 * board a 2d array of characters that keep track of the pieces on the board
	 */
	private char[][] board;

	/**
	 * This constructor takes the size of the board and Initializes the board.
	 * 
	 * @param size
	 */
	public CharBoard(int size) {
		board = new char[size][size];

		initializeBoard();
	}

	/**
	 * This method sets the board the the position that it should initially be
	 * for the game that is running. The default version is turning every square
	 * into an empty square.
	 */
	public void initializeBoard() {
		for (int j = 0; j < board.length; j++)
			for (int i = 0; i < board[j].length; i++)
				board[j][i] = ' ';
	}

	/**
	 * This method attempts to place the piece on the coordinate. If the
	 * placement fails, then it throws an exception.
	 * 
	 * @return if the piece was successfully placed or not
	 */
	public void attemptPlace(PieceType piece, Coordinate c) {
		if (canPlace(piece, c)) {
			place(piece, c);
		} else {
			throw new PlacementFailedException(
					"The piece cannot be placed on square (" + c.getX() + " , " + c.getY() + ")");
		}
	}

	/**
	 * This method places a piece on the square the coordinates corresponds to
	 * without verification.
	 * 
	 * @param piece
	 *            the piece that will be placed
	 * @param c
	 *            the coordinates of the square the piece will be placed on
	 */
	public void place(PieceType piece, Coordinate c) {
		board[c.getX()][c.getY()] = piece.getChar();
	}

	/**
	 * This method verifies that a piece can be placed on this square.
	 * 
	 * @param piece
	 *            the piece that will be checked
	 * @param c
	 *            the coordinates the piece will be checked on
	 * @return if the piece can be placed on the coordinate
	 */
	public abstract boolean canPlace(PieceType piece, Coordinate c);

	/**
	 * This method tells the user if the square has no piece on it.
	 * 
	 * @param c
	 *            the coordinate
	 * @return if the coordinate is empty
	 */
	public boolean isEmpty(Coordinate c) {
		return board[c.getX()][c.getY()] == ' ';
	}

	/**
	 * For every column, put a number. For every row, put a letter. Place the
	 * single letter representation of the piece inside the grid.
	 * 
	 * @return a text created board with all the square names and pieces on the
	 *         squares shown.
	 */
	public String toString() {
		String out = " ";
		for (int i = 0; i < board.length; i++)
			out += " " + (i + 1);
		out += "\n";

		char letter = 'A';
		for (int j = 0; j < board[0].length; j++) {
			out += "" + letter++ + " " + board[0][j];
			for (int i = 1; i < board.length; i++) {
				out += "|" + board[i][j];
			}
			if (j < board[0].length - 1) {
				out += "\n  -";
				for (int i = 1; i < board.length; i++)
					out += "--";
				out += "\n";
			}
		}
		out += "\n";

		return out;
	}

	public abstract char gameEnd();

	public boolean squareExists(Coordinate out) {
		return out.getX() >= 0 && out.getX() < board.length && out.getY() >= 0 && out.getY() < board[0].length;
	}

	/**
	 * This method returns the board that the game is placed on.
	 * 
	 * @return the board
	 */
	public char[][] getBoard() {
		return board;
	}
}