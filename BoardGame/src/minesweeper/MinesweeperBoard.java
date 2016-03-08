package minesweeper;

import game.CharBoard;
import game.Coordinate;
import game.PieceType;

/**
 * This board contains the minesweeper game board. It uses the character board
 * to keep track of the of bombs and revealed squares and uses an int array to
 * keep track of the number of bombs surrounding a square.
 */
public class MinesweeperBoard extends CharBoard {

	/** This character represents a bomb */
	public static final PieceType BOMB = PieceType.X;
	/** This character represents a revealed square */
	public static final PieceType OPEN = PieceType.O;

	/** This 2d array shows the user the number of bombs around each square */
	private final int[][] visibleBoard;
	/**
	 * This board keeps track of the size of the board because the value is used
	 * often
	 */
	private final int size;
	/** This is the number of bombs that should be on the board */
	private final int numOfBombs;

	/**
	 * This constructs a character array for the bombs are revealed squares, an
	 * integer array for the number of surrounding bombs around each square,
	 * 
	 * @param size
	 */
	public MinesweeperBoard(int size) {
		super(size);
		visibleBoard = new int[size][size];
		this.size = size;
		numOfBombs = (size * size + 4) / 5;
		randomlyPlaceBombs();
		generateVisibleBoard();

		// debugging purposes
		System.out.println();
		System.out.println("Debugging:");
		System.out.println(toStringCheat());
	}

	/**
	 * This method randomly places a bomb anywhere on the board.
	 */
	public void randomlyPlaceBombs() {
		Coordinate c;

		for (int i = numOfBombs; i > 0; i--) {
			c = emptyRandomCoordinate();
			place(BOMB, c);
		}
	}
	
	private Coordinate emptyRandomCoordinate() {
		Coordinate c;
		do {
			c = randomCoordinate();
		} while (!isEmpty(c));
		return c;
	}

	private Coordinate randomCoordinate() {
		int x = (int) (Math.random() * size);
		int y = (int) (Math.random() * size);
		return new Coordinate(x, y);
	}

	/**
	 * This method gives an initial value to every integer inside of the array
	 * corresponding to the number surrounding that square.
	 */
	public void generateVisibleBoard() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {

				if (getCharBoard()[j][i] == BOMB.getChar())
					visibleBoard[j][i] = 9; // a value of 9 means that the
											// square has a bomb on it
				else
					visibleBoard[j][i] = numOfSurroundingBombs(j, i);
			}
		}
	}

	/**
	 * This method counts the number of bombs surrounding a square
	 * 
	 * @param y
	 *            a y coordinate
	 * @param x
	 *            an x coordinate
	 * @return the total number of bombs surrounding a square
	 */
	public int numOfSurroundingBombs(int y, int x) {
		int bombs = 0;

		int[] yOffset = { 1, 1, 1, 0, 0, -1, -1, -1 };
		int[] xOffset = { -1, 0, 1, -1, 1, -1, 0, 1 };
		Coordinate c;
		for (int i = 0; i < 8; i++) {
			c = new Coordinate(x + xOffset[i], y + yOffset[i]);
			if (squareExists(c)) {
				bombs += hasBomb(c.getRow(), c.getCol());
			}
		}

		return bombs;
	}

	/**
	 * This method takes a coordinate and returns 1 if a bomb is on the square.
	 * 
	 * @param y
	 *            a y coordinate
	 * @param x
	 *            an x coordinate
	 * @return 1 if there is a bomb and 0 if there is not a bomb
	 */
	private int hasBomb(int y, int x) {
		if (getCharBoard()[y][x] == BOMB.getChar())
			return 1;
		return 0;
	}

	/**
	 * This method verifies that a square can be revealed. For Minesweeper, this
	 * involves checking if the square had been reveled before.
	 * 
	 * @param piece
	 *            the piece that will be checked. Only an OPEN PieceType should
	 *            be given
	 * @param c
	 *            the coordinates that will be checked
	 * @return if the square can be revealed
	 */
	@Override
	public boolean canPlace(PieceType piece, Coordinate c) {
		return getCharBoard()[c.getCol()][c.getRow()] != OPEN.getChar();
	}

	/**
	 * This method detects if the user had ended the game by either revealed a
	 * bomb (a loss) or filling the board (a win).
	 * 
	 * @return a character that corresponds to a win or loss
	 */
	@Override
	public char gameEnd() {
		// The user had removed a bomb by revealing it
		if (countAllBombs() != numOfBombs)
			return BOMB.getChar();
		// The user has not revealed every square
		Coordinate c;
		for (int j = 0; j < size; j++)
			for (int i = 0; i < size; i++) {
				c = new Coordinate(j, i);
				if (isEmpty(c))
					return ' ';
			}
		// The user has revealed every square
		return OPEN.getChar();
	}

	/**
	 * This method counts the current number of bombs on the square. It is used
	 * the check of a bomb was shown.
	 * 
	 * @return the current number of bombs on the board.
	 */
	private int countAllBombs() {
		int count = 0;
		for (int j = 0; j < size; j++)
			for (int i = 0; i < size; i++)
				count += hasBomb(j, i);
		return count;
	}
	
	@Override
	protected String printRow(int row) {
		String out = "" + showOpenSquares(row, 0);
		for (int i = 1; i < getCharBoard().length; i++) {
			out += "|" + showOpenSquares(row, i);
		}
		return out;
	}

	/**
	 * This method shows a board with all the bombs visible
	 * 
	 * @return The board with the bombs visible
	 */
	private String toStringCheat() {
		String out = " ";
		out += labelEachColumn() + "\n";
		out += printAllRowsCheat() + "\n";

		return out;
	}
	
	protected String printAllRowsCheat() {
		String out = "";
		for (int j = 0; j < size; j++) {
			out += printRowLabel(j) + " ";
			out += super.printRow(j) + "\n";
			out += printRowDivider(!isBottomRow(j)) + "\n";
		}
		return out;
	}

	/**
	 * This method shows the int array if the square is revealed.
	 * 
	 * @param y
	 *            the y coordinate
	 * @param x
	 *            the x coordinate
	 * @return the square the user can see
	 */
	private char showOpenSquares(int y, int x) {
		if (getCharBoard()[y][x] == OPEN.getChar()) {
			// if the value is 9, it is a bomb so show a bomb
			if (visibleBoard[y][x] == 9)
				return BOMB.getChar();
			// if the value is not 9, then show the value
			return Integer.toString(visibleBoard[y][x]).charAt(0);
		}
		// otherwise, just show the piece (which should be ' ')
		return ' ';
	}

}
