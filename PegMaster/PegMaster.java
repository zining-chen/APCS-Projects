/**
 * Program that operates the game of PegMaster using Peg and PegArray
 *
 * @author Aditya Kalari
 * @version 15 October 2015
 */

public class PegMaster {

    private static boolean reveal;				  	// Whether to reveal the master combination
    private static PegArray[] guessArray;			// The array of guess peg arrays
    private static PegArray master;				 	// The master (key) peg array
    private static int turn;					    // The turn of the game

    public PegMaster () {
        // INITIALIZE SETUPS
        reveal = false;
        guessArray = new PegArray[10];
        for (int i = 0; i < 10; i++) {
            guessArray[i] = new PegArray();
        }
        master = new PegArray();
        turn = 0;
    }

    public static void main (String [] args) {
        // GAME SETUP
        PegMaster play = new PegMaster();
        play.printIntroduction();

        // SETUP VARS and generate master array
        for (int mastind = 0; mastind < 4; mastind++)
            master.setPeg(play.generateMaster(), mastind);

        do {
            play.printBoard();
            if (!reveal)
                play.guessArray[turn] = play.getUserGuess();
            if (guessArray[turn].equals(master))
                reveal = true;
            turn++;
        } while (!reveal && turn != 9);

        if (!reveal) {
            reveal = true;
            play.printBoard();
        }
        
    }

	/**
	 * Prints the introductory information
	 */
    private void printIntroduction() {
        System.out.println("\n");
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("| WELCOME TO MONTA VISTA PEGMASTER!                                                  |");
        System.out.println("|                                                                                    |");
        System.out.println("| The game of PegMaster is played on a four-peg gameboard, and six peg colors can    |");
        System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
        System.out.println("| some of the six colors (black, white, blue, green, yellow, and red).  Repeats are  |");
        System.out.println("| allowed, so there are 6 * 6 * 6 * 6 = 1296 possibe combinations.  This \"master    |");
        System.out.println("| combination\" is then hidden from the player, and the player starts making guesses  |");
        System.out.println("| at the master combination.  The player has 10 turns to guess the combination.      |");
        System.out.println("| Each time the player makes a guess for the 4-peg combination, the number of exact  |");
        System.out.println("| matches and partial matches are then reported back to the user. If the player      |");
        System.out.println("| finds the exact combination, the game ends with a win.  If the player does not     |");
        System.out.println("| find the master combination after 10 turns, the game ends with a loss.             |");
        System.out.println("|                                                                                    |");
        System.out.println("| LET'S PLAY SOME PEGMASTER!                                                         |");
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("\n");
    }

	/**
	 * Prints the status and results after every trial
	 */
    private void printBoard() {
		for (int col = 0; col < 10; col++) {
          guessArray[col].findMatches(master);
          int num = guessArray[col].getExact();
          if (num == 4) {
       reveal = true;
		  }
        }
        System.out.println();
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("| MASTER      1     2     3     4     5     6     7     8     9     10 |");
        System.out.println("+-------+  +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");
        for (int row = 0; row < 4; row++) {
          // Reveal the master pegs
          if (reveal) System.out.printf("|   %c   |  |", master.getPeg(row).getLetter());
          else System.out.print("|  ***  |  |");
          for (int col = 0; col < 10; col++) {
            char c = guessArray[col].getPeg(row).getLetter();
            if (c != 'X') System.out.printf("  %c  |", c);
            else System.out.printf("     |");
          }
          System.out.println();
          System.out.println("+-------+  +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");
        }
        System.out.print("| Exact    ");
        for (int col = 0; col < 10; col++) {
          guessArray[col].findMatches(master);
          int num = guessArray[col].getExact();
          if (num > -1) System.out.printf("   %d  ", num);
          else System.out.printf("      ");
          if (num == 4) {
			  reveal = true;
		  }
        }
        System.out.println("|");
        System.out.print("| Partial  ");
        for (int col = 0; col < 10; col++) {
          int num = guessArray[col].getPartial();
          if (num > -1) System.out.printf("   %d  ", num);
          else System.out.printf("      ");
        }
        System.out.println("|");
        System.out.println("+----------------------------------------------------------------------+");
        turn++;
        System.out.println("\tTurn " + turn + "\n");
        turn--;

    }

    /**
     * Generates a random Peg
     *
     * @return The generated Peg
     */
    private Peg generateMaster () {
        char letter = (char)(96 + (int)(Math.random() * 5 + 1));
        Peg random = new Peg (letter);
        return random;
    }

    /**
     * Takes the user input and returns as a PegArray
     *
     * @return The inputter PegArray
     */
    private PegArray getUserGuess () {
        // Setup the guess array and the char to read from user
        PegArray guess = new PegArray();
        char read = '0';
        // Cycle through the Array
        for (int ind = 0; ind < 4; ind++) {
            // Make sure to take only valid input
            if (read == '0') {
                read = Prompt.getString("\tEnter your guess for Peg number " + ind + " (A, B, C, D, E, F)").charAt(0);
                // Set the peg with the new character
                guess.getPeg(ind).setLetter(read);
            }
            read = '0';
        }
        return guess;
    }

}
