package Minesweeper;

import game.CharBoard;
import game.Coordinate;
import game.PieceType;

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

	// The previous board tracks the revealed squares and the squares with bombs
	// on them

	public MinesweeperBoard(int size) {
		super(size);
		visibleBoard = new int[size][size];
		this.size = size;
		numOfBombs = (size * size + 4) / 5;
		generateVisibleBoard();
	}

	/**
	 * This method gives an initial value to every integer inside of the array
	 * corresponding to the number surrounding that square.
	 */
	public void generateVisibleBoard() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {

				if (getBoard()[j][i] == BOMB.getChar())
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
		
		int[] yOffset = {1, 1, 1, 0,0,-1,-1,-1};
		int[] xOffset = {-1,0,1, -1,1,-1,0,1};
		Coordinate c;
		for(int i = 0; i <= 8; i++) {
			c = new Coordinate(xOffset[i], yOffset[i]);
			if(squareExists(c)) {
				bombs += hasBomb(y+yOffset[i], x+xOffset[i]);
			}
		}
		
		return bombs;
	}

	/**
	 * This method takes a coordinate and returns 1 if a bomb is on the square
	 * 
	 * @param y
	 *            a y coordinate
	 * @param x
	 *            an x coordinate
	 * @return 1 if there is a bomb and 0 if there is not a bomb
	 */
	private int hasBomb(int y, int x) {
		if (getBoard()[y][x] == BOMB.getChar())
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
		return getBoard()[c.getX()][c.getY()] != OPEN.getChar();
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
		if (countBombs() != numOfBombs)
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

	public int countBombs() {
		// TODO
		return 0;
	}

}
