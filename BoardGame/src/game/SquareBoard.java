package game;

import exception.PlacementFailedException;

public abstract class SquareBoard {

	public SquareBoard(int size) {
		board = new Square[size][size];
		currentMove = null;

		initializeBoard();
	}

	public void initializeBoard() {
		for (int row = 0; row < getSize(); row++)
			for (int col = 0; col < getSize(); col++)
				board[row][col] = new Square(' ');
	}

	public void setMove(Move move) {
		currentMove = move;
	}

	public void attemptMove() {
		if (validMove()) {
			executeMove();
		} else {
			// TODO change failure message
			throw new PlacementFailedException("The piece cannot be placed on square \""
					+ currentMove.getCoordinate().toAlphaNumericalString() + '\"');
		}
	}

	public void executeMove() {
		getTargetSquare().setChar(currentMove.getPiece().getChar());
	}

	public boolean validMove() {
		return squareExists(currentMove.getCoordinate());
	}

	public boolean isEmpty(Coordinate c) {
		return board[c.getCol()][c.getRow()].getChar() == ' ';
	}

	public String toString() {
		String out = " ";
		out += labelEachColumn() + "\n";
		out += printAllRows() + "\n";

		return out;
	}

	protected String labelEachColumn() {
		String out = "";
		for (int col = 1; col <= getSize(); col++)
			out += " " + labelColumn(col);

		return out;
	}

	protected String labelColumn(int col) {
		return "" + (col % 10 == 0 ? col / 10 : col % 10);
	}

	protected String printAllRows() {
		String out = "";
		for (int row = 0; row < getSize(); row++) {
			out += printRowLabel(row) + " ";
			out += printRow(row) + "\n";
			out += printRowDivider(!isBottomRow(row)) + "\n";
		}
		return out;
	}

	protected String printRowLabel(int row) {
		return "" + (char) ('A' + row);
	}

	protected String printRow(int row) {
		String out = "" + board[row][0].getChar();
		for (int col = 1; col < getSize(); col++) {
			out += "|" + board[row][col].getChar();
		}
		return out;
	}

	protected String printRowDivider(boolean shouldPrint) {
		if (shouldPrint) {
			String out = "  -";
			for (int col = 1; col < getSize(); col++)
				out += "--";
			return out;
		} else {
			return "";
		}
	}

	protected boolean isBottomRow(int row) {
		return getSize() - 1 == row;
	}

	public abstract char gameEnd();

	public boolean squareExists(Coordinate out) {
		return out.getCol() >= 0 && out.getCol() < getSize() && out.getRow() >= 0 && out.getRow() < getSize();
	}

	public char[][] getCharBoard() {
		char[][] out = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				out[row][col] = board[row][col].getChar();
		return out;
	}

	private Square getTargetSquare() {
		return board[currentMove.getCol()][currentMove.getRow()];
	}

	public Move getMove() {
		return currentMove;
	}

	public int getSize() {
		return board.length;
	}

	private Square[][] board;
	private Move currentMove;
}