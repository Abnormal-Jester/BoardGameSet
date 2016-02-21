package tictactoe;

import exception.InputInvalidException;
import exception.PlacementFailedException;
import game.AbstractGame;
import game.Coordinate;

public class TicTacToe extends AbstractGame {

	private final Team[] team;
	private int currentIndex;

	public TicTacToe() {
		super(new TicTacToeBoard());

		team = new Team[2];
		team[0] = new Team(Team.TeamType.X);
		team[1] = new Team(Team.TeamType.O);

		currentIndex = 0;

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
			getBoard().attemptPlace(team[currentIndex].getPieceType(), input);
		} catch (PlacementFailedException e) {
			errorMessage(e.toString());
			return;
		}

		printBoard();
		currentIndex = currentIndex == 0 ? 1 : 0;

	}

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