package game;

public class Square {
	char visibleChar;


	public Square (char visibleChar) {
		setChar(visibleChar);
	}

	public char getChar() {
		return visibleChar;
	}

	public void setChar(char visibleChar) {
		this.visibleChar = visibleChar;
	}

}
