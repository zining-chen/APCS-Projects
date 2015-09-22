import java.util.Scanner;

/**
 *  This program prompts for a string of 3 to 10 letters, then searches the
 *  wordlist.txt database to see what words it matches.
 *
 *  Requires classes OpenFile and Prompt.
 *
 *  @author Aditya Kalari
 *  @version 14 September 2015
 *
 *	Algorithm
 *
 *	Input string of letters
 *	Check if string is only letters
 *	Open wordlist.txt and read each word
 *		test word with letters
 *		if they match, add word to list
 */

public class EveryWord {
	private Scanner infile;
	private String infileName = "wordlist.txt";
	private String letters;
	private String [][] words;
	private int [] numWords;

	public EveryWord() {
		letters = new String("");
		// Row is Num of letters
		words = new String [15][200];
		numWords = new int [15];
		clearArrays();
	}

	public static void main(String[] args) {
		EveryWord setup = new EveryWord();
		setup.run();
	}

	public void run() {
		inputLetters();
		while (letters.length() > 0) {
			findWords(letters);
			printWords();
			clearArrays();
			inputLetters();
		}
	}

	/**
	 *  Input 3 to 10 letters for searching database.
	 */
	public void inputLetters() {
		boolean done;
		do {
			letters = Prompt.getString("List of 3 to 10 letters for search");
			if (letters.length() < 3 || letters.length() > 10) {
				System.err.println("ERROR:  Must have length of 3 to 10 letters");
				System.exit(3);
			}
			// Check each letter
			done = true;
			for (int count = 0; count < letters.length(); count++) {
				char letter = letters.charAt(count);
				if (letter < 'a' || letter > 'z')
					done = false;
			}
		}  while (!done);
	}

	/**
	 *  Find all the words that match a string of letters.
	 *
	 *  @param letters The letters to match the word
	 */
	public void findWords (String letters) {
		String word;

		//Open the Databse file
		infile = OpenFile.openToRead(infileName);
		while (infile.hasNext()) {
			word = infile.next();
			int len = word.length();
			if (wordMatch(word, letters)) {
				words[len][numWords[len]] = word;
				numWords[len]++;
			}
		}
	}

	/**
	 *  Decides if a word matches a group of letters.
	 *
	 *  @param word  The word to test.
	 *  @param letters  A string of letters to compare
	 *  @return  true if the word matches the letters, false otherwise
	 */
	public boolean wordMatch (String word, String letters) {
		for (int i = 0; i < word.length(); i++) {
			int index = letters.indexOf(word.charAt(i));
			if (index == -1)
				return false;
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		return true;
	}

	/**
	 *  Print the list of words that match to the screen.
	 */
	public void printWords() {
		System.out.println("\n");
		for (int i = 0; i < 15;  i++) {
			for (int j = 0; j < numWords[i]; j++) {
				System.out.printf("%s\t", words[i][j]);
				if ((j + 1) % 10 == 0)
					System.out.println();
			}
			if (numWords[i] > 0)
				System.out.println("\n");
		}
	}

	/**
	 *  Set the numWords array to zeros
	 */
	public void clearArrays() {
		for (int i = 0; i < 15; i++) numWords[i] = 0;
	}
}
