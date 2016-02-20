package game;
import othello.Othello;
import tictactoe.TicTacToe;

public class GameDriver {
   
   public static void main(String[] args) {
      int selection;
      CustomScanner console = new CustomScanner();
      
      while(true) {
         System.out.println("1. TicTacToe\n2. Othello\n3. Help\n4. Exit");
         System.out.print("Please enter the number of the game you want " +
            "to play: ");
         
         selection = console.getInt(1, 4);
         
         switch(selection) {
         case 1: new TicTacToe().startGame();
            break;
         case 2: System.out.println("Not yet implemented");
         	new Othello();
            break;
         case 3: displayHelp();
            break;
         case 4: System.exit(0);
         default: System.out.println("Try again");
            break;
         }
         
         console.pressToContinue();
      }
   }
   
   // TODO
   // May eventually be moved inside of the game classes
   /**
    * This method will eventually ask which game the user wants help with and
    * then give the instructions of the game to the user.
    */
   public static void displayHelp() {
      System.out.println("Not yet implemented");
   }
   
}