package tictactoe;

import exception.PlacementFailedException;
import game.AbstractGame;
import game.Coordinate;
import game.CustomScanner;

public class TicTacToe extends AbstractGame {
	private final static CustomScanner console = new CustomScanner();

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
			input = console.getSquareCoordinates(this);
			System.out.println();

			getBoard().attemptPlace(team[currentIndex].getPieceType(), input);

			printBoard();
			currentIndex = currentIndex == 0 ? 1 : 0;

		} catch (PlacementFailedException e) {
			errorMessage(e.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			errorMessage(e.toString());
		} catch (NullPointerException e) {
			errorMessage("InputFailed");
		}

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