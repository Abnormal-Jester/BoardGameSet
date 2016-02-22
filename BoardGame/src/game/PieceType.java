package game;

/**
 * This class defines the types of pieces and the character representation of
 * each piece.
 */
public enum PieceType {
	X('X'), O('O');

	private final char piece;

	private PieceType(char ch) {
		piece = ch;
	}

	public char getChar() {
		return piece;
	}
}