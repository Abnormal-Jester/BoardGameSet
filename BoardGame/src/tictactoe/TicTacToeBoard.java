package tictactoe;

import game.CharBoard;
import game.Coordinate;
import tictactoe.PieceType;

/**
 * This class creates a 2d array of squares and keeps track of the piece
 * locations, win conditions, and text display.
 * 
 * @author Jarett Lee
 */
public class TicTacToeBoard extends CharBoard{

	/**
	 * This constructor takes the size of the board and Initializes the board.
	 * 
	 * @param size
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

	/*
	 * 2016-02-18 22:30 Added tie condition.
	 */
	@Override
	public char gameEnd() {
		char[][] board = getBoard();

		// check rows
		for (int i = 0; i < board.length; i++)
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2])
				return board[i][0];

		// check columns
		for (int j = 0; j < board[0].length; j++)
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j])
				return board[0][j];

		// check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
			return board[1][1];
		if (board[2][0] == board[1][1] && board[1][1] == board[0][2])
			return board[1][1];

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == ' ')
					return ' ';

		return '_';
	}

}