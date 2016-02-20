package tictactoe;
public enum PieceType {
   X ('X'),
   O ('O');
   
   private final char piece;
      
   private PieceType(char ch) {
      piece = ch;
   }
   
   public char getChar() {
      return piece;
   }
}