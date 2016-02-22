package tictactoe;

import java.util.ArrayList;

import exception.InputInvalidException;
import exception.PlacementFailedException;
import game.AbstractGame;
import game.CharBoard;
import game.Coordinate;
import game.PieceType;

/**
 * This class runs a Tic-Tac-Toe game.
 */
public class TicTacToeSinglePlayer extends AbstractGame {

	/**
	 * This variable is the piece the player is placing on the board. Index 0 is
	 * the player, index 1 is the ai.
	 */
	private final PieceType[] pieces;

	/** This integer keeps track of the current team's turn. */
	private int currentIndex;

	/** The human's index */
	private final static int HU = 0;
	/** The ai's index */
	private final static int AI = 1;

	/**
	 * This constructor passes the Board of this game to the super class
	 * constructor and defines the instance variables of this object.
	 */
	public TicTacToeSinglePlayer(PieceType playerPiece) {
		super(new TicTacToeBoard());

		pieces = new PieceType[2];
		pieces[0] = playerPiece;
		pieces[1] = playerPiece == PieceType.X ? PieceType.O : PieceType.X;

		currentIndex = playerPiece == PieceType.X ? 0 : 1;
	}

	/**
	 * This method represents a single placement of a piece by one of the
	 * players in the game. Then, the current team swaps. If the user input
	 * fails, then the current team is not changed.
	 */
	@Override
	public void next() {
		Coordinate input;

		if (currentIndex == HU) {
			try {
				input = getConsole().getSquareCoordinates(this);
			} catch (InputInvalidException e) {
				errorMessage(e.toString());
				return;
			}
			System.out.println();
		} else {
			input = getAIMove();
		}

		try {
			getBoard().attemptPlace(pieces[currentIndex], input);
		} catch (PlacementFailedException e) {
			errorMessage(e.toString());
			return;
		}

		printBoard();
		currentIndex = currentIndex == HU ? AI : HU;

	}

	/**
	 * This method has the AI determine its move and return the corresponding
	 * square the move is located on.
	 * 
	 * @return the ai will place a piece on this square
	 */
	private Coordinate getAIMove() {
		// find winning lines
		// count number of pieces in each line
		// end one turn victories
		// randomly place the piece
		// lines 1-3 are columns, lines 4-6 are rows, lines 7 and 8 are
		// diagonals
		CharBoard tempBoard = getBoard();
		int size = tempBoard.getBoard().length;
		ArrayList<Coordinate> list = new ArrayList<Coordinate>(9);
		Coordinate c;
		
		for (int i = 0; i < size * size; i++) {
			c = new Coordinate(i / size, i % size);
			if(getBoard().squareExists(c)) {
				list.add(c);
			}
		}
		System.out.println(list);

		return list.get((int) (Math.random() * list.size()));
	}

	/**
	 * This method checks if the game should end. This method asks the board if
	 * the game should end and gives a message if the game has ended.
	 */
	@Override
	public void check() {
		char team = getBoard().gameEnd();

		if (team != ' ') {
			if (team == '_') {
				System.out.println("It's a tie!");
			} else {
				System.out.println(team + " won!");
			}

			endGame();
		}
	}
}