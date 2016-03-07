package game;

import battleship.Battleship;

public class GameFactory {

	// use getShape method to get object of type shape
	public AbstractGame getShape(GameType gameType) throws Exception {
		switch (gameType.type()) {
		case BATTLESHIP:
			return new Battleship();
		default:
			return null;
		}
	}

}
