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
	private int[][] visibleBoard;
	/**
	 * This board keeps track of the size of the board because the value is used
	 * often
	 */
	private int size;

	// The previous board tracks the revealed squares and the squares with bombs
	// on them

	public MinesweeperBoard(int size) {
		super(size);
		visibleBoard = new int[size][size];
		this.size = size;
		generateVisibleBoard();
	}

	public void generateVisibleBoard() {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {

				if (getBoard()[j][i] == BOMB.getChar())
					visibleBoard[j][i] = 0;
				else
					visibleBoard[j][i] = numOfSurroundingBombs(j, i);
			}
		}
	}

	public int numOfSurroundingBombs(int y, int x) {
		// TODO
		return 0;
	}
	// (1, 1)
	// (1, 0)
	// (1, -1)
	//
	// (0, 1)
	// (0, -1)
	//
	// (-1, -1)
	// (-1, 0)
	// (-1, 1)

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

	@Override
	public char gameEnd() {
		// TODO Auto-generated method stub
		return 0;
	}

}
