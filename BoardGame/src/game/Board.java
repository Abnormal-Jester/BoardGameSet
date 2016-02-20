package game;

import tictactoe.PieceType;

public abstract class Board {
	public abstract void initializeBoard();
	public abstract void attemptPlace(PieceType piece, Coordinate c);
	public abstract void place(PieceType piece, Coordinate c);
	public abstract boolean canPlace(PieceType piece, Coordinate c);
	public abstract char gameEnd();
	public abstract boolean squareExists(Coordinate out);
}
