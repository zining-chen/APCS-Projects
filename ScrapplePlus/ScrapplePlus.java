import java.util.Scanner;

/**
 *  This is a small version of the game of Scrapple
 *	Utilizes Word Utilities
 *
 *
 *  @author Aditya Kalari
 *  @version 28 September 2015
 *
 */

public class ScrapplePlus {

	// Setup Variables
	public int [] scores = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
	private String tilesRemaining = "AAAAAAAAAABBCCDDDDEEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ";
  	private char [] tiles = new char[100]; 
  	private boolean []tile = new boolean[100]; 
  	private int NUMTILES = 8;

  	// Hands
	private char []player = new char[8]; 
  	private char[] comp = new char[8]; 

  	// Score
  	private int []score = new int[2]; 
	private boolean testLeft = false;
	private WordUtilitiesPlus wordPlus = new WordUtilitiesPlus();

	// Opponent and player junk String
	private String opp = "";
	private String play = "";

	// Sets which turn and endgame variables
	private boolean userTurn = true;
	private boolean endOfGame = false;

	public static void main(String [] args) {
		ScrapplePlus setupGame = new ScrapplePlus();
		setupGame.playGame();
	}

	/**
	 *  Initialization of Variables Using Constructor
	 */
	public ScrapplePlus(){
      for (int i = 0; i<100;i++){
          tiles[i] = tilesRemaining.charAt(i);
          tile[i] = true;

          if(i<8){
              player[i] ='1';
              comp[i] ='1';
          }
          if(i<2){
              score[i] = 0;
          }
      }
  	}

	/**
	 * The method that controls all the other methods to play the game.
	 * Runs and validates sequences of words being entered
	 */
	public void playGame() {

		// Print Intro
		printIntroduction();

		// Fill Tiles into hands
		chooseRandomTile();

		// While no DISASTERS have occurred ...
		while(!endOfGame){
			// Print the pool
			printTilesPool();
			// Print various status such as hand, scores, etc.
			printStatus();
			// If user is active
			if (userTurn) { 
				userTurnPlay();
				userTurn = false;
			}
			// Or inactive
			else { 
				computerTurnPlay();
				userTurn = true;
			}
		}

		// EndGame sequence
		showEndStatus();

	}

	/**
	 *  Chooses random Tiles for the user and computer player
	 */
	public void chooseRandomTile(){
		// Loops through the hands
		for(int i = 0; i < 2;i++){
			// Stores random index
			int random = 0;
			// Stay under hand length
			while(random < NUMTILES) {
				// Get Math.random Value for the variable
                int random1 = 0 + (int)(Math.random()*99);
                // If the value at boolean exists is true
                if(tile[random1] == true){
                	// Add to Player ...
					if (i == 0) {
						player[random] = tiles[random1];}
					// ... Or Computer
					else { 
						comp[random] = tiles[random1];
					}
					// Reset tileArray
                    tile[random1] = false;
                    // Increment Random
					random ++;
				}
			}
		}
	}

	/**
	 *  Prints the tiles in the pool
	 */
	public void printTilesPool(){
		// Initial Formatting
		System.out.println();
		System.out.println("Here are the tiles remaining in the pool of letters:\n");
		// Set Counter for new Line
		int counter = 0;
		// Loop through many times to catch - all
		for (int i = 0; i < 100;i++){
			// If the index is available
			if(tile[i] == true){
				// Print the tiles, increment counter
				System.out.print(tiles[i] + " ");
				counter++;
				// New line every 20
				if(counter%20 == 0 && counter != 0){
					System.out.println("");
				}
			}
		}
		if (counter < 8) {
			showEndStatus();
			System.exit(1);
		}
	}

	/**
	 *  Prints the status' of the players including tiles in the hand
	 */
	public void printStatus () {
			// Formatting
			System.out.println();
			System.out.println();
			// Print Scores - Move to separate function
			System.out.println("Players score:   " + score[0]);
			System.out.println("Computers score: " + score[1]);
			System.out.println();
			// Print tiles - Move to Separate Function
			System.out.printf("THE TILES IN YOUR HAND ARE:             ");
			// Add Spaces
			for(int i = 0; i < 8; i++){
				play+=player[i];
				System.out.print(player[i] + "  ");
			}
			System.out.println();
			System.out.println();
			System.out.printf("THE TILES IN THE COMPUTER HAND ARE:     ");
			// Add Spaces
			for(int i = 0; i < 8; i++){
				opp += comp[i];
				System.out.print(comp[i] + "  ");
			}
			// End Formatting
			System.out.println();
			System.out.println();
			System.out.println();
	}

	/**
	 *  Prompt's user for word, and does many checks to check if it is a word
	 *  Then it takes away the tiles from the tile pool.
	 */
	public void userTurnPlay(){

			String input = new String();
			boolean test = false;
			boolean test2 = false;
			// Check if word is valid
			do {
				input = Prompt.getString("Please enter a word created from your current set of tiles");
				input = input.toLowerCase();
				test = wordPlus.wordpossible(input);
				test2 = wordPlus.matchletters (input,play);
			}
			// While conditions not valid of infile and in hand
			while(input.length() == 0 || test == false || test2 == false);

			input = input.toUpperCase();
			// Get Score of Word - Add Double Bonuses
			score[0] += wordPlus.getScore(input, scores);
			boolean [] tested = new boolean[8];
			// Make new hand for validation of strings
			char [] word_array = new char[input.length()];
			for(int i = 0; i < 8; i++){
				tested[i]=false;
			}
			// Validate with possible words
 			for (int i = 0; i < input.length(); i++){
				word_array[i] = input.charAt(i);
				for(int j = 0; j < 8; j++){
					if(word_array[i] == player[j] && tested[j] == false){
						tested[j] = true;
						j = 8;
					}

				}
			}

			// Choose random letters and refresh the missing letters
			for(int j = 0; j<8;j++) { 
				if(tested[j] == true) {
					// Count missing letters 
					int counter_r = 0;
					for(int i = 0; i < 100; i++) {
						if(tile[i] == false) {
							counter_r++;
						}
					}
					if(counter_r == 99) {
						player[j] = ' ';
					} else {
					// Or replace if not present with randomized index from pool
					int random1;
					if(testLeft == false) {
						do {
							random1 = 0 + (int)(Math.random()*100);
							player[j] = tiles[random1];
						}
						while(tile[random1] == false);
						tile[random1] = false;}

					}
				}
			}

			// Refresh total hand
			int ct = 0;
			for(int i = 0; i<99;i++){
				if(tile[i] == false){
					ct++;
				}
			}

			// Makes sure it tiles are valid inside the tile pool
			if(ct == 98){
				for(int i = 0; i < 99; i++){
					tile[i] = false;
				}
			}
		}

		/**
		 *  Prompts user to press enter when it is comp's turn
		 *  Plays for the computer so it chooses the best word from Word Utilities
		 */
		public void computerTurnPlay(){
				// Start Computer Turn
				Scanner gg = new Scanner(System.in);
				System.out.print("Hit ENTER for Computer turn:");
				String ggg = gg.nextLine();
				System.out.println();
				// Hold hand
				String letters = "";
				// Fills the hand
				for(int i = 0; i<8;i++){
					letters += comp[i];
				}
				// Take input from WordUtilities
				String input = wordPlus.bestWord(letters,scores);
				// Displays computer word
				System.out.println("The computer chose the word: " + input); 
                score[1] += wordPlus.getScore(input, scores);
                // Change to upperCase for printing
				input = input.toUpperCase();
				boolean [] tested = new boolean[8];
				char [] word_array = new char[input.length()];
				// Fill tested array
				for(int i = 0; i < 8; i++) {
					tested[i]=false;
				}
				// Fill input Lenght with check tested values if letters and word is valid
 				for (int i = 0; i < input.length(); i++) {
					word_array[i] = input.charAt(i);
					for(int j = 0; j<8;j++){
						if(word_array[i] == comp[j]){
							tested[j]=true;
							j = 8;
						}

					}
				}

				// Chooses random tiles once turn is done
				for(int j = 0; j < 8; j++){
					if(tested[j] == true){
						int counter_r = 0;
						for(int i = 0;i<100;i++){
							if(tile[i] == false){
								counter_r++;
							}
						}
						// Runs through
						if(counter_r == 99){
							comp[j] = ' ';
						}else{
							int random1;
							if(testLeft == false){
							do{
								random1 = 0 + (int)(Math.random()*100);
								comp[j] = tiles[random1];
							}
							while(tile[random1] == false);
								tile[random1] = false;}

						}
					}
				}
				// Count runs through falseities
				int ct = 0;
				for(int i = 0; i < 99; i++){
					if(tile[i] == false){
						ct++;
					}
				}
				// Makes sure it tiles are valid inside the tile pool
				if(ct >= 98 && testLeft == false){ 
					for(int i = 0; i < 99; i++){
						tile[i] = false;
						testLeft = true;
					}
				}
		}

		/**
		 *  Gets the Tiles from the user and deletes it from tile pool
		 */
		public void getTiles(){
			// Junk temp String
			String send = "";
			// Take user turn and create send
			if(userTurn == true){
				for(int i = 0; i < 8; i++){
					send += player[i];
			}} else {
			for(int i = 0;i<8;i++){
				send += comp[i];
			}}
			// Replace letters if words are present
			endOfGame =! (wordPlus.wordThere(send));
 			userTurn =! userTurn;
		}

		/**
		 *  Shows the end status of the game when either player or user is done with turn.
		 */
		public void showEndStatus(){
			System.out.println();
			System.out.println("Here are the tiles remaining in the pool of letters:\n");
			int counterrr = 0;
			for (int i = 0; i<100;i++){

				if(tile[i] == true){
					System.out.print(tiles[i]+" ");
					counterrr ++;
					if(counterrr%20 == 0&&counterrr!=0){
						System.out.print(counterrr);
						System.out.println();
					}
				}
			}
            // Print scores
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("No more words can be created");
			System.out.println("Player Score:" + score[0]);
			System.out.println("Computer Score:" +score[1]);
			System.out.println("THANK YOU FOR PLAYING!");
			System.exit(1);
		}

	/**
	 *  Print the introduction screen for Scrapple.
	 */
	public void printIntroduction() {
		System.out.print(" _______     _______     ______     ______    ");
		System.out.println(" ______    ______   __          _______");
		System.out.print("/\\   ___\\   /\\  ____\\   /\\  == \\   /\\  __ \\   ");
		System.out.println("/\\  == \\  /\\  == \\ /\\ \\        /\\  ____\\");
		System.out.print("\\ \\___   \\  \\ \\ \\____   \\ \\  __<   \\ \\  __ \\  ");
		System.out.println("\\ \\  _-/  \\ \\  _-/ \\ \\ \\_____  \\ \\  __\\");
		System.out.print(" \\/\\______\\  \\ \\______\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\ ");
		System.out.println(" \\ \\_\\     \\ \\_\\    \\ \\______\\  \\ \\______\\");
		System.out.print("  \\/______/   \\/______/   \\/_/ /_/   \\/_/\\/_/ ");
		System.out.println("  \\/_/      \\/_/     \\/______/   \\/______/ TM");
		System.out.println();
		System.out.print("This game is a \"scaled down\" version of Scrabble. ");
		System.out.println("The game starts with a pool of letter tiles, with");
		System.out.println("the following group of 100 tiles:\n");

		for (int i = 0; i < tilesRemaining.length(); i ++) {
			System.out.printf("%c ", tilesRemaining.charAt(i));
			if (i == 49) System.out.println();
		}

		System.out.println("\n");
		System.out.printf("The game starts with %d tiles being chosen at ran", NUMTILES);
		System.out.println("dom to fill the player's hand. The player must");
		System.out.printf("then create a valid word, with a length from 4 to %d ", NUMTILES);
		System.out.println("letters, from the tiles in his/her hand. The");
		System.out.print("\"word\" entered by the player is then checked. It is first ");
		System.out.println("checked for length, then checked to make ");
		System.out.print(" sure it is made up of letters from the letters in the current hand, ");
		System.out.println("and then it is checked against");
		System.out.print("the word text file. If any of these tests fail, the game");
		System.out.println(" terminates. If the word is valid, points");
		System.out.print("are added to the player's score according to the following table ");
		System.out.println("(These scores are taken from the");
		System.out.println("game of Scrabble):");

		// Print line of letter scores
		char c = 'A';
		for (int i = 0; i < 26; i++) {
			System.out.printf("%3c", c);
			c = (char)(c + 1);
		}
		System.out.println();
		for (int i = 0; i < scores.length; i++) System.out.printf("%3d", scores[i]);
		System.out.println("\n");
		Scanner gg = new Scanner(System.in);
		System.out.print("Once the player's score has been updated, more tiles are ");
		System.out.println("chosen at random from the remaining pool");
		System.out.printf("of letters, to fill the player's hand to %d letters. ", NUMTILES);
		System.out.println("The player again creates a word, and the process");
		System.out.print("continues. The game ends when the player enters an invalid ");
		System.out.println("word, or the letters in the pool and");
		System.out.println("player's hand run out. Ready? Let's play!\n");
		System.out.print("HIT ENTER on the keyboard to continue:");
		String input= gg.nextLine();
	}
}