import java.util.Scanner;

/**
 *  A simple version of the Scrabble game where the user plays against the computer.
 *
 * @author Aditya Kalari
 * @version 25 September 2015
 */

public class ScrapplePlus {

	private final int NUMTILES = 8;
	public int [] scores = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
	private char[] tileSet;
	private String tilesRemaining = "AAAAAAAAAABBCCDDDDEEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ";
	
	// Constructor
	public ScrapplePlus() {
		tileSet = tilesRemaining.toCharArray();
	}
	
	public static void main(String [] args) {
		ScrapplePlus runGame = new ScrapplePlus();
		runGame.playGame();
	}
	
	public void playGame() {

		// Instructions
		printIntroduction();

		// These hold the scores of the user and comp
		int usrScore = 0;
		int compScore = 0;

		// These hold the current user words
		char[] usrWord = "00000000".toCharArray();
		char[] compWord = "00000000".toCharArray();

		// These hold the values of the hand of the user and the computer
		char[] usrHand = "00000000".toCharArray();
		char[] compHand = "00000000".toCharArray();

		// This holds the previous word of the computer and the user
		char[] usrPrevious = "00000000".toCharArray();
		char[] compPrevious = "00000000".toCharArray();

		// Hold the double bonus
		boolean usrPrevBonus = false;
		boolean usrDoubleBonus = false;
		boolean cmpPrevBonus = false;
		boolean cmpDoubleBonus = false;
		
		// Play the game
		boolean endOfGame = false;
		
		do {

			//Prints the pool of letters
			printPool();
			// Prints the score of both players
			printScores(usrScore, compScore);
			// Refreshes the hand of both players
			usrHand = getLetters(usrHand);
			compHand = getLetters(compHand);
			//  Print hands
			printHands(usrHand, compHand);
			// User's turn
			usrWord = getUserWord();
			validate(usrWord, usrHand);
			usrPrevBonus = checkPrevBonus(usrWord, usrPrevious);
			usrDoubleBonus = checkDoubleBonus(usrWord);
			usrScore += getScore(usrWord, usrDoubleBonus, usrPrevBonus);
			// Computer's turn
			compWord = WordUtilities.bestWord(WordUtilities.findAllWords(String.copyValueOf(compHand)), scores).toCharArray();
			cmpPrevBonus = checkPrevBonus(compWord, compPrevious);
			cmpDoubleBonus = checkDoubleBonus(compWord);
			compScore += getScore(compWord, cmpDoubleBonus, cmpPrevBonus);
			// Check if the pool is still enough for the game
			endOfGame = checkPool(usrHand, compHand);
			
		} while (!endOfGame);

		printScores(usrScore, compScore);
		System.out.println("GAME OVER!");
		
	}
	

	/* ==================== BEGIN INSTRUCTIONS AND INTRO SETUP ==================== */

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
		System.out.print("This game is a modified version of Scrabble. ");
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
		System.out.print("sure it is made up of letters from the letters in the ");
		System.out.println("current hand, and then it is checked against");
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
		
		System.out.print("The score is doubled if the word has consecutive double ");
		System.out.println("letters (e.g. ball). The score can also");
		System.out.print("double if the first character of the word follows the ");
		System.out.println("first character of the last word entered");
		System.out.print("in alphabetical order (e.g. \"catnip\" gets ");
		System.out.println("regular score, followed by \"dogma\" which earns double");
		System.out.print("points). If the word contains both, then quadruple the ");
		System.out.println("points.\n");
		
		System.out.print("Once the player's score has been updated, more tiles are ");
		System.out.println("chosen at random from the remaining pool");
		System.out.printf("of letters, to fill the player's hand to %d letters. ", NUMTILES);
		System.out.println("The player again creates a word, and the");
		System.out.print("process continues. The game ends when the player enters an ");
		System.out.println("invalid word, or the letters in the");
		System.out.println("pool and player's hand run out. Ready? Let's play!\n");
		
		Prompt.getString("HIT ENTER on the keyboard to continue:");

	}

	/* ==================== END INSTRUCTIONS AND INTRO SETUP ==================== */

	/* ==================== BEGIN STATUS SETTINGS AND MESSAGES ==================== */

	/**
	 * Prints the hand of both users
	 *
	 * @param user The user hand
	 * @param comp The computer hand
	 */
	private void printHands (char[] user, char[] comp) {

		System.out.printf("%40s", "THE TILES IN YOUR HAND: ");
		// Add spaces to Strings
		// Print hands of user
		for (int i = 0; i < NUMTILES; i++) {
			System.out.print(user[i] + " ");
		}

		System.out.printf("%40s", "THE TILES IN THE COMPUTER HAND: ");
		// Add spaces to Strings
		// Print hands of user
		for (int i = 0; i < NUMTILES; i++) {
			System.out.print(comp[i] + " ");
		}

	}

	/**
	 * Prints the score of both users
	 *
	 * @param user The score of the human player
	 * @param comp The score of the computer
	 */
	private void printScores (int user, int computer) {

		// Prints the score of the user
		// Prints the score of the comp
		System.out.println("\nYour score is: " + user);
		System.out.println("The computer score is: " + computer + "\n");
	
	}

	/**
	 * Waits for the user to start the turn
	 * Prints the pool of letters in an acceptable format
	 */
	private void printPool () {

		String junk = Prompt.getString("Hit ENTER to continue");

		byte printCounter = 0;

		// Print the pool of letters by looping through the String
		System.out.printf("\nThe remaining letters are:\n");
		for (int i = 0; i < tilesRemaining.length(); i++) {
			if (printCounter%20 == 0) {
				System.out.println();
			}
			if (tilesRemaining.charAt(i) != '0') {
				System.out.print(tilesRemaining.charAt(i) + " ");
				printCounter++;
			}
		}

		System.out.println("\n");

	}

	/**
	 * This method returns a string of random letters from the existing pool
	 * The method takes into account how many spots are available in the player hand
	 *
	 * @param hand The hand of the player
	 * @return The set of letters available to the player
	 */
	private char[] getLetters(char[] hand) {

		int junkIndex = 0;

		// Run Math.random() looking for letters that are in the pool
		for (int i = 0; i < NUMTILES; i++) {
			System.out.println("HELLO");
			while (hand [i] == '0') {
				System.out.println("JUNKINDEX" + tileSet[junkIndex] + "");
				junkIndex = (int)(Math.random() * tileSet.length);
				hand[i] = tileSet[junkIndex];
			}
			tileSet[junkIndex] = '0';
		}

		return hand;

	}
	
	/* ==================== END STATUS SETTINGS AND MESSAGES ==================== */

	/* ==================== BEGIN USER GAME AND SCORING ==================== */

	/**
	 * Gets the word input from the user
	*/
	private char[] getUserWord () {

		String tempAnswer = new String("");

		while (tempAnswer.length() < 3)
			tempAnswer =  Prompt.getString("\nPlease enter a word from your set of tiles");

		return tempAnswer.toCharArray();
	}

	/**
	 * Checks if the word is valid with the letters in the hand
	 * Checks if the word is present in wordlist.txt
	 * Terminates the program if it is not
	 *
	 * @param usrWord The word that the user entered
	 * @param usrHand The hand that the user currently has
	 */
	private char[] validate (char[] usrWord, char[] usrHand) {
		
		char[] tempHand = usrHand;
		boolean valid = false;
		boolean timeTest = false;

		for (int wordI = 0; wordI < usrWord.length; wordI++) {
			for (int handI = 0; handI < usrHand.length; handI++) {
				if (usrWord[wordI] == usrHand[handI] && !timeTest) {
					tempHand[handI] = '0';
					timeTest = true;
				}
				else {
					System.out.println("Your word is INVALID: HAND");
					System.exit(100);
				}
			}
			timeTest = false;
		}

		String userWord = new String(usrWord);
		userWord = userWord.toUpperCase();
		Scanner wordRead = OpenFile.openToRead("wordlist.txt");
		while (wordRead.hasNext()) {
			if (wordRead.nextLine().equals(userWord))
				valid = true;
		}
		if (!valid) {
			System.out.println("Your word is INVALID: FILE");
			System.exit(1000);
		}

		return tempHand;

	}

	/**
	 * Checks to see if the first char of this word and the previous are the same
	 * Applies dobule word score if applicable
	 *
	 * @param current The current user word
	 * @param previous The previous user word
	 */
	private boolean checkPrevBonus (char[] current, char[] previous) {
		
		char firstC = current[0];
		char firstP = previous[0];

		if (firstC == firstP) {
			System.out.println("DOUBLE WORD SCORE!");
			return true;
		}

		return false;
	}

	/**
	 * Checks to see if there are characters in a row in the word
	 * Applies double word score if applicable
	 *
	 * @param word The word that the user inputted
	 */
	private boolean checkDoubleBonus(char[] word) {
		
		boolean bonus = false;

		for (int i = 0; i < word.length - 1; i++) {
			if (word[i] == word[i+1]) {
				bonus = true;
				System.out.println("DOUBLE WORD SCORE!");
			}
		}

		return bonus;

	}

	/**
	 * Calculates the score based on the scoretable
	 * Takes into account double word scores
	 *
	 * @param word The word being scored
	 * @param dbonus If double word score is applicable
	 * @param prev If double previous word score bonus is applicable
	 * @return The overall score of the word
	 */
	private int getScore (char[] word, boolean dBonus, boolean prev) {
		int score = 0;
		String tempWord = new String(word);

		score = WordUtilities.getScore(tempWord, scores);
		if (dBonus)
			score *= 2;
		if (prev) 
			score *= 2;

		return score;

	}

	/**
	 * Checks if there are enough words in the pool to play again
	 *
	 * @param usr The user hand
	 * @param comp The computer hand
	 * @return Whether another round can be played
	 */
	private boolean checkPool (char[] usr, char[] comp) {

		int handZero = 0;
		int poolRem = 0;

		for (int i = 0; i < NUMTILES; i++) {
			if (usr[i] == '0') handZero++;
			if (comp[i] == '0') handZero++;
		}

		for (int i = 0; i < tileSet.length; i++) {
			if (tileSet[1] != 0) poolRem++;
		}

		if (poolRem < handZero) 
			return false;

		return true;

	}

	/* ==================== END USER GAME AND SCORING ==================== */

}