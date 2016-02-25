package othello;

import game.CharBoard;
import game.Coordinate;
import game.PieceType;

public class OthelloBoard extends CharBoard {
	public OthelloBoard() {
		super(8);
	}

	@Override
	public void initializeBoard() {
		for (int j = 0; j < getCharBoard().length; j++)
			for (int i = 0; i < getCharBoard()[j].length; i++)
				getCharBoard()[j][i] = ' ';
		getCharBoard()[4][4] = 'X';
	}

	@Override
	public char gameEnd() {

		int team1 = 0;
		int team2 = 0;

		// check for empty squares
		for (int i = 0; i < getCharBoard().length; i++) {
			for (int j = 0; j < getCharBoard()[i].length; j++) {
				if (getCharBoard()[i][j] == 'X')
					team1++;
				if (getCharBoard()[i][j] == 'O')
					team2++;
				else
					return ' ';
			}
		}

		return (team1 == team2) ? '_' : ((team1 > team2) ? 'X' : 'O');
	}

	@Override
	public boolean canPlace(PieceType piece, Coordinate c) {
		// TODO Auto-generated method stub
		// Must be empty
		// Must flip pieces
		return isEmpty(c);
	}
}