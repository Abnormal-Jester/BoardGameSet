package minesweeper;

import exception.InputInvalidException;
import exception.PlacementFailedException;
import game.AbstractGame;
import game.Coordinate;

/**
 * This class runs a minesweeper game.
 */
public class Minesweeper extends AbstractGame {

	/**
	 * This constructor creates a new Minesweeper object with a board with a set
	 * size. The larger the board, the harder the level
	 * 
	 * @param size
	 *            of the board
	 */
	public Minesweeper(int size) {
		super(new MinesweeperBoard(size));
	}

	/**
	 * This method represents revealing a single square on the board.
	 */
	@Override
	public void next() {
		Coordinate input;

		try {
			input = getConsole().getSquareCoordinates(this);
		} catch (InputInvalidException e) {
			errorMessage(e.toString());
			return;
		}
		System.out.println();

		try {
			getBoard().attemptPlace(MinesweeperBoard.OPEN, input);
		} catch (PlacementFailedException e) {
			errorMessage(e.toString());
			return;
		}

		printBoard();

	}

	/**
	 * This method obtains the status from the board and gives a response
	 * depending on the board response.
	 */
	@Override
	public void check() {
		char verify = getBoard().gameEnd();

		if (verify != ' ') {
			if (verify == MinesweeperBoard.BOMB.getChar()) {
				System.out.println("You lost. Good luck next time!");
			} else {
				System.out.println("You won!");
			}

			endGame();
		}

	}

}
