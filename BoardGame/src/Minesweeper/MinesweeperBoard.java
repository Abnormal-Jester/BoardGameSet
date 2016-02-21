package Minesweeper;

import game.CharBoard;
import game.Coordinate;
import game.PieceType;

public class MinesweeperBoard extends CharBoard {

	public MinesweeperBoard() {
		super(5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canPlace(PieceType piece, Coordinate c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public char gameEnd() {
		// TODO Auto-generated method stub
		return 0;
	}

}
