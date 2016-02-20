package tictactoe;

public class Team {

	public enum TeamType {
		X(PieceType.X), O(PieceType.O);

		private final PieceType piece;

		private TeamType(PieceType type) {
			piece = type;
		}

		public PieceType getPiece() {
			return piece;
		}
	}

	private TeamType type;

	public Team(TeamType type) {
		this.type = type;
	}

	public PieceType getPieceType() {
		return type.getPiece();
	}

	public char getPieceChar() {
		return type.getPiece().getChar();
	}
}