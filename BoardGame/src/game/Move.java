package game;

public class Move {
	private PieceType piece;
	private Coordinate coordinate;
	private Board board;

	public Move(PieceType piece, Coordinate coordinate, Board board) {
		this.setPiece(piece);
		this.setCoordinate(coordinate);
		this.setBoard(board);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public PieceType getPiece() {
		return piece;
	}

	public void setPiece(PieceType piece) {
		this.piece = piece;
	}

}
