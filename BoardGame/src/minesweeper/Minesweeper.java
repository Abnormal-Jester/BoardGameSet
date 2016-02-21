package minesweeper;

import exception.InputInvalidException;
import exception.PlacementFailedException;
import game.AbstractGame;
import game.Coordinate;

public class Minesweeper extends AbstractGame {

	public Minesweeper() {
		super(new MinesweeperBoard(2));
	}

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
