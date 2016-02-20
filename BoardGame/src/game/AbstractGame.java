package game;

import game.CustomScanner;

/**
 * This class contains the board of the game and the game loop method.
 *
 */
public abstract class AbstractGame {
	/**
	 * console is a CustomScanner that is accessed by every game object that
	 * extends AbstractGame
	 */
	private final static CustomScanner console = new CustomScanner();
	/** board is the Board that the pieces in the game will be placed on */
	private final Board board;
	/** play is the variable that determines if the game continues or ends */
	private boolean play;

	/**
	 * The constructor for any game, only visible to its subclasses.
	 * 
	 * @param board
	 *            the board the game will be played on
	 */
	protected AbstractGame(Board board) {
		this.board = board;
		play = true;

		printBoard();
	}

	/**
	 * This method repeats another turn in the game until the condition to end
	 * the game is satisfied.
	 */
	public void startGame() {
		while (play) {
			next();
			check();
		}
	}

	/**
	 * This method ends the game loop, ending the game.
	 */
	public void endGame() {
		play = false;
	}

	/**
	 * This method represents a single turn of one of the players in the game.
	 */
	public abstract void next();

	/**
	 * This method checks if the game has ended and the program should close.
	 */
	public abstract void check();

	/**
	 * This method prints a text representation of the board to the system.
	 */
	public void printBoard() {
		System.out.println(board);
	}

	/**
	 * This method is for debugging purposes and eventually telling the user
	 * what they did incorrectly.
	 * 
	 * @param error
	 *            the exception name or a custom message
	 */
	public void errorMessage(String error) {
		System.out.println("Invalid: " + error);
	}

	/**
	 * This method returns the board.
	 * 
	 * @return the board the game is being played on
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * This method returns the custom input interpreter, CustomScanner, for all
	 * games.
	 * 
	 * @return the custom scanner for games
	 */
	public static CustomScanner getConsole() {
		return console;
	}
}