package game;

import java.util.Scanner;

public class CustomScanner {
	private final Scanner console;

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

			if (x < low || x > high)
				return 0;

			return x;
		} catch (NumberFormatException e) {
			errorMessage(e.toString());
		}
		return 0;
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
	 * escape to false.
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	public int getInt(int low, int high) {
		return getInt(low, high, false);
	}

	/**
	 * This method is an overloaded method that automatically sets the value of
	 * low and high to the integer limits and escape to false.
	 * 
	 * @return
	 */
	public int getInt() {
		return getInt(Integer.MIN_VALUE, Integer.MIN_VALUE, false);
	}

	/*
	 * 2016-02-18 19:21 Moved to CustomScanner in order to keep the TicTacToe
	 * class cleaner. Found the String trim() method
	 * 
	 * 2016-02-18 17:25 I initially only used split() and did not use
	 * replaceAll(). I changed to the using replaceAll() because it allows to
	 * disregard any blank space that the user might input and only focus on the
	 * length of the user's input (should be 2, [character, number]) and the
	 * values of the input, although that is not validated here. I no longer
	 * check the length of each split input and the length of the input array. I
	 * found this optimization when I put a space in front of input before my a2
	 * (" a2") and it returned an unexpected error.
	 */
	/**
	 * This method gives a game a valid coordinate that corresponds to a square
	 * on the board. The user inputs an alphanumeric character string and this
	 * method turns it into a coordinate.
	 * 
	 * @param game
	 * @return
	 */
	public Coordinate getSquareCoordinates(AbstractGame game) {
		String temp;
		// val[0] is the first input (the character / y value)
		// val[1] is the second input (the number / x value)
		int[] val = new int[2];
		Coordinate out;
		
		try {
			temp = console.nextLine();
			checkEscape(temp, game);

			temp = temp.replaceAll("\\s", "");

			// accept "a2" and "b 3" form
			if (temp.length() == 2) {
				val[0] = temp.toUpperCase().charAt(0) - 65;
				val[1] = Character.getNumericValue(temp.charAt(1)) - 1;
				
				out = new Coordinate(val[0], val[1]);
				
				// reject all numbers out of bounds
				if (game.getBoard().squareExists(out)) {
					errorMessage("ValuesOutOfBoardBounds");
					return null;
				}

				return out;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Unexpected Error");
			e.printStackTrace(System.out);
		}

		return null;
	}

	public void pressToContinue() {
		System.out.println("Press enter to continue");
		checkEscape(console.nextLine());
	}

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
	 * 
	 * @param temp
	 *            a String that will be checked for a key phrase.
	 * @param game
	 */
	public static void checkEscape(String temp, AbstractGame game) {
		// looks for "EXIT" and "RESET" before the squares
		for (String str : temp.split("\\s")) {
			str = str.toUpperCase();

			if (str.equals("EXIT")) {
				System.out.println("Thank you for playing");
				System.exit(0);
			}
			if (str.equals("RESTART") || str.equals("RESET")) {
				System.out.println("Messy restart, please wait");
				game.endGame();
				return;
			}
		}
	}

	private void errorMessage(String error) {
		System.out.println("Invalid: " + error);
	}

}