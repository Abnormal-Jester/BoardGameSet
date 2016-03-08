package game;

public class Move {
	private PieceType piece;
	private Coordinate coordinate;
	private SquareBoard board;

	public Move(PieceType piece, Coordinate coordinate, SquareBoard board) {
		this.setPiece(piece);
		this.setCoordinate(coordinate);
		this.setBoard(board);
	}

	public SquareBoard getBoard() {
		return board;
	}
	public void setBoard(SquareBoard board) {
		this.board = board;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public int getCol() {
		return coordinate.getCol();
	}
	public int getRow() {
		return coordinate.getRow();
	}

	public PieceType getPiece() {
		return piece;
	}
	public void setPiece(PieceType piece) {
		this.piece = piece;
	}
}
