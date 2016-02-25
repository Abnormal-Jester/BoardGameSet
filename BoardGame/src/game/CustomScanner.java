package game;

import java.util.Scanner;

import exception.InputInvalidException;

/**
 * This class reads the user's input in the context of a game.
 */
public class CustomScanner {
	/** This is the scanner that reads the user's input */
	private final Scanner console;

	/**
	 * This constructs the scanner that reads the user's input.
	 */
	public CustomScanner() {
		console = new Scanner(System.in);
	}

	/**
	 * This method takes the minimum and maximum integer values in addition to
	 * allowing the user to use an escape phrase to exit the program.
	 * 
	 * @param low
	 *            the lowest value the user wants
	 * @param high
	 *            the highest value the user wants. This value must be greater
	 *            than the lowest value.
	 * @param escape
	 *            determines if method checks for an escape phrase or not
	 * @return an integer greater than or equal to the low parameter and less
	 *         than or equal to the high parameter. Otherwise, if the escape is
	 *         true and the program finds an escape phrase, the program exits.
	 */
	public int getInt(int low, int high, boolean escape) {
		String temp;
		int x;
		try {
			temp = console.nextLine().trim();
			if (escape == true)
				checkEscape(temp);
			x = Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			errorMessage(e.toString());
			throw new InputInvalidException();
		}
		
		if (x < low || x > high)
			throw new InputInvalidException();

		return x;
	}

	/**
	 * This method is an overloaded method that automatically sets the value of
	 * low and high to the integer limits.
	 * 
	 * @param escape
	 * @return
	 */
	public int getInt(boolean escape) {
		return getInt(Integer.MIN_VALUE, Integer.MAX_VALUE, escape);
	}

	/**
	 * This method is an overloaded method that automatically sets the value of
	 * escape to true.
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	public int getInt(int low, int high) {
		return getInt(low, high, true);
	}

	/**
	 * This method is an overloaded method that automatically sets the value of
	 * low and high to the integer limits and escape to true.
	 * 
	 * @return
	 */
	public int getInt() {
		return getInt(Integer.MIN_VALUE, Integer.MIN_VALUE, true);
	}

	/**
	 * This method gives a game a valid coordinate that corresponds to a square
	 * on the board. The user inputs an alphanumeric character string and this
	 * method turns it into a coordinate.
	 * 
	 * @param game
	 * @return the coordinate that corresponds to the user's input
	 */
	public Coordinate getSquareCoordinates(AbstractGame game) {
		String temp;
		// val[0] is the first input (the character / y value)
		// val[1] is the second input (the number / x value)
		int[] val = new int[2];

		try {
			temp = console.nextLine();
			checkEscape(temp, game);

			// remove all blank spaces
			temp = temp.replaceAll("\\s", "");

		} catch (Exception e) {
			System.out.println("Unexpected Error");
			e.printStackTrace(System.out);
			throw new InputInvalidException();
		}

		// accept letter number form: a2
		if (temp.length() == 2) {
			val[0] = temp.toUpperCase().charAt(0) - 65;
			val[1] = Character.getNumericValue(temp.charAt(1)) - 1;
		} else {
			throw new InputInvalidException("Number of valid characters should be 2. Actual: " + temp.length());
		}

		Coordinate out = new Coordinate(val[0], val[1]);

		// reject all numbers out of bounds
		if (!game.getBoard().squareExists(out)) {
			throw new InputInvalidException("No square on those coordinates exist: " + temp);
		}

		return out;
	}

	/**
	 * This method waits for the user to press enter. It also looks for an
	 * escape phrase.
	 */
	public void pressToContinue() {
		System.out.println("Press enter to continue");
		checkEscape(console.nextLine());
	}

	/**
	 * This method only checks for the escape phrase "EXIT" and exits from the
	 * program if it is true.
	 * 
	 * @param temp
	 *            the input string
	 */
	public static void checkEscape(String temp) {
		// looks for "EXIT" and "RESET" before the squares
		for (String str : temp.split("\\s")) {
			str = str.toUpperCase();

			if (str.equals("EXIT")) {
				System.out.println("Thank you for playing");
				System.exit(0);
			}
		}
	}

	/**
	 * This method looks for escape phrases that exit the program and exit the
	 * current game.
	 * 
	 * @param temp
	 *            a String that will be checked for a key phrase.
	 * @param game
	 *            a game that will stop running if a key phrase is given.
	 */
	public static void checkEscape(String temp, AbstractGame game) {
		// looks for "EXIT" and "RESET" before the squares
		for (String str : temp.split("\\s")) {
			str = str.toUpperCase();

			if (str.equals("EXIT")) {
				System.out.println("Thank you for playing");
				System.exit(0);
			}
			if (str.equals("BACK") || str.equals("MENU")) {
				System.out.println("Thank you for playing");
				game.endGame();
				return;
			}
		}
	}

	// TODO make it an error message to the player
	/**
	 * This method is for debugging purposes.
	 * 
	 * @param error
	 *            the exception name or a custom message
	 */
	private void errorMessage(String error) {
		System.out.println("Invalid: " + error);
	}

}