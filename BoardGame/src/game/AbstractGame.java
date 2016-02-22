package game;

import game.CustomScanner;

/**
 * This class contains the board of the game and the game loop method.
 */
public abstract class AbstractGame {
	/**
	 * console is a CustomScanner that is accessed by every game. It interprets
	 * the user's input into an output that the program can understand.
	 */
	private final static CustomScanner console = new CustomScanner();
	/** board keeps track of where the pieces in the game are placed */
	private final CharBoard board;
	/** play is a boolean value that determines if the game continues or ends */
	private boolean play;

	/**
	 * The constructor for a game, only visible to its subclasses.
	 * 
	 * @param board
	 *            the board the game will be played on
	 */
	protected AbstractGame(CharBoard board) {
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
	 * This method represents a single turn of one of the players in the game.
	 */
	public abstract void next();

	/**
	 * This method checks if the game should end.
	 */
	public abstract void check();
	
	/**
	 * This method ends the game.
	 */
	public void endGame() {
		play = false;
	}

	/**
	 * This method prints a text representation of the board to the system out stream.
	 */
	public void printBoard() {
		System.out.println(board);
	}

	// TODO make it an error message to the player
	/**
	 * This method is for debugging purposes.
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
	public CharBoard getBoard() {
		return board;
	}

	/**
	 * This method returns the custom input interpreter, CustomScanner.
	 * 
	 * @return the custom scanner for games
	 */
	public static CustomScanner getConsole() {
		return console;
	}
}