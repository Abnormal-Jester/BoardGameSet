package game;

import exception.PlacementFailedException;

public abstract class Board {
	private Square[][] board;
	private Move currentMove;

	public Board(int size) {
		board = new Square[size][size];

		initializeBoard();
	}

	public void initializeBoard() {
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				board[row][col] = new Square(' ');
	}

	public void setMove(Move move) {
		currentMove = move;
	}

	public Move getMove() {
		return currentMove;
	}


	public void attemptPlace(PieceType piece, Coordinate c) {
		if (canPlace(piece, c)) {
			place();
		} else {
			throw new PlacementFailedException("The piece cannot be placed on square \"" + c.toAlphaNumerical() + '\"');
		}
	}

	public void place() {
		int row = currentMove.getCoordinate().getX();
		int col = currentMove.getCoordinate().getY();
		PieceType piece = currentMove.getPiece();
		board[row][col].setChar(piece.getChar());
	}

	public abstract boolean canPlace(PieceType piece, Coordinate c);

	public boolean isEmpty(Coordinate c) {
		return board[c.getX()][c.getY()].getChar() == ' ';
	}

	public String toString() {
		String out = " ";
		out += labelEachColumn() + "\n";
		out += printAllRows() + "\n";

		return out;
	}

	protected String labelEachColumn() {
		String out = "";
		for (int i = 1; i <= board.length; i++)
			out += " " + labelColumn(i);

		return out;
	}

	protected String labelColumn(int i) {
		return "" + (i % 10 == 0 ? i / 10 : i % 10);
	}

	protected String printAllRows() {
		String out = "";
		for (int j = 0; j < board[0].length; j++) {
			out += printRowLabel(j) + " ";
			out += printRow(j) + "\n";
			out += printRowDivider(!isBottomRow(j)) + "\n";
		}
		return out;
	}

	protected String printRowLabel(int row) {
		return "" + (char) ('A' + row);
	}

	protected String printRow(int j) {
		String out = "" + board[j][0].getChar();
		for (int i = 1; i < board.length; i++) {
			out += "|" + board[j][i].getChar();
		}
		return out;
	}

	protected String printRowDivider(boolean shouldPrint) {
		if (shouldPrint) {
			String out = "  -";
			for (int i = 1; i < board.length; i++)
				out += "--";
			return out;
		} else {
			return "";
		}
	}

	protected boolean isBottomRow(int row) {
		return board.length - 1 == row;
	}

	public abstract char gameEnd();

	public boolean squareExists(Coordinate out) {
		return out.getX() >= 0 && out.getX() < board[0].length && out.getY() >= 0 && out.getY() < board.length;
	}

	public char[][] getCharBoard() {
		char[][] out = new char[board.length][board[0].length];
		for (int j = 0; j < board.length; j++)
			for (int i = 0; i < board[j].length; i++)
				out[j][i] = board[j][i].getChar();
		return out;
	}
}