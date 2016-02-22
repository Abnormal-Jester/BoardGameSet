package tictactoe;

import game.CharBoard;
import game.Coordinate;
import game.PieceType;

/**
 * This class creates a 2d array of squares and keeps track of the piece
 * locations, win conditions, and text display.
 */
public class TicTacToeBoard extends CharBoard {

	/**
	 * This constructor initializes the board of a Tic-Tac-Toe game.
	 */
	public TicTacToeBoard() {
		super(3);
	}

	/**
	 * This method verifies that a piece can be placed on this square. For
	 * TicTacToe, this only involves checking if the coordinate is empty.
	 * 
	 * @param piece
	 *            the piece that will be checked
	 * @param c
	 *            the coordinates the piece will be checked on
	 * @return if the piece can be placed on the coordinate
	 */
	@Override
	public boolean canPlace(PieceType piece, Coordinate c) {
		return isEmpty(c);
	}

	/**
	 * This method checks if there are 3 pieces in a row and returns an exit
	 * character if there are 3 pieces in a row. Otherwise, it checks if there
	 * is an empty square. If there is an empty square, continue the game.
	 * Otherwise, end the game as a tie.
	 */
	@Override
	public char gameEnd() {
		char[][] tempBoard = getBoard();

		// check rows
		for (int i = 0; i < tempBoard.length; i++)
			if (tempBoard[i][0] == tempBoard[i][1] && tempBoard[i][1] == tempBoard[i][2])
				return tempBoard[i][0];

		// check columns
		for (int j = 0; j < tempBoard[0].length; j++)
			if (tempBoard[0][j] == tempBoard[1][j] && tempBoard[1][j] == tempBoard[2][j])
				return tempBoard[0][j];

		// check diagonals
		if (tempBoard[0][0] == tempBoard[1][1] && tempBoard[1][1] == tempBoard[2][2])
			return tempBoard[1][1];
		if (tempBoard[2][0] == tempBoard[1][1] && tempBoard[1][1] == tempBoard[0][2])
			return tempBoard[1][1];

		for (int i = 0; i < tempBoard.length; i++)
			for (int j = 0; j < tempBoard[i].length; j++)
				if (tempBoard[i][j] == ' ')
					return ' ';

		return '_';
	}

}