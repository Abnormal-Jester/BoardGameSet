package Minesweeper;

import exception.InputInvalidException;
import exception.PlacementFailedException;
import game.AbstractGame;
import game.Coordinate;

public class Minesweeper extends AbstractGame{

	public Minesweeper() {
		super(new MinesweeperBoard(5));
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
		// TODO Auto-generated method stub
		
	}

}
