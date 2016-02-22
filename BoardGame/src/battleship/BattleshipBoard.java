package battleship;

import game.CharBoard;
import game.Coordinate;
import game.PieceType;

public class BattleshipBoard extends CharBoard {

	public BattleshipBoard() {
		super(10);
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
