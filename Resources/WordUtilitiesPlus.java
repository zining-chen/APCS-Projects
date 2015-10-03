import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Upgraded WordUtilities for higher compatibility with ScrapplePlus
 *
 * @author Aditya Kalari
 * @version 28 September 2015
 */

public class WordUtilitiesPlus {

    public int [] scores = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

	/**
	 *  Find all words that can be formed by a list of letters.
	 *
	 *  @param letters  String list of letters
	 *  @return   An array of strings with all words found.
	 */
	public static String [] findAllWords (String letters){
		String word;
		String [] words = new String[1000];
		int count = 0; //number of words we have found
		//open the database

		Scanner infile = null;
		letters = letters.toLowerCase();
		infile = OpenFile.openToRead("wordlist.txt");

		while(infile.hasNext()){
			word = infile.next();
			int len = word.length();
			if (matchletters(word, letters)){
				words[count++] = word;
				// After it stores count it then increments
			}
		}

		// Makes an array with an exact memory allocation
		String [] exact = new String[count];
        for(int i = 0; i < count;i++){
			exact[i] = words[i];
		}
		return exact;
	}

	/**
	 * Finds if a word is valid for a certain string of letters
	 * 
	 * @param word The String of letters to be checked
	 * @return Whether the word is possible
	 */
	public static boolean wordpossible (String word) {

		boolean test = false;
		Scanner infile = null;
		infile = OpenFile.openToRead("wordlist.txt");

		while(infile.hasNext()){
			if(word.equals(infile.next())){
				test = true;
			}
		}

		return test;
	}

	/**
	 * Returns true if a word is present in the database
	 *
	 * @param letters The letters to be checked against the database
	 * @return Whether the word is present in the database
	 */
	public static boolean wordThere (String letters) {

		String word;
		int count = 0; 

		Scanner infile = null;
		count=0;
		infile = OpenFile.openToRead("wordlist.txt");

		while(infile.hasNext()){
			word = infile.next();
			int len = word.length();
			if (matchletters(word, letters)){
				count++;
			}
		}

		boolean test = true;
		if(count==0){
			test = false;
		}
		return test;
	}

	/**
	 *  Determines if a word can be formed by a list of letters.
	 *
	 *  @param word The word to be tested.
	 *  @param letters  A string of the list of letter
	 *  @return   True if word can be formed, false otherwise
	 */
	public static boolean matchletters (String word, String letters){

		word = word.toLowerCase();
		letters = letters.toLowerCase();

		for(int i=0;i<word.length();i++) {
			int ind = letters.indexOf(word.charAt(i));
            if(ind == -1)
				return false;
            letters = letters.substring(0,ind) + letters.substring(ind+1);
        }

        return true;
	}

	/**
	 *  Print the words found to the screen.
	 *
	 *  @param word  The string array containing the words.
	 */
	public static void printWords (String [] word){

		for(int a = 0; a < word.length; a++){
			System.out.printf("%15s",word[a]);
			if((a +1)% 5 ==0) System.out.println();
		}

		System.out.println();
	}

	/**
	 *  Finds the highest scoring word according to Scrabble rules.
	 *
	 *  @param word  An array of words to check.
	 *  @param scoretable  An array of 26 integer scores in letter order.
	 *  @return   The word with the highest score.
	 */
    public static String bestWord (String [] word, int [] scoretable) {

        int x = 0;
        int placeKeeper = 0;
        int bestScore = 0;

        for(int i = 0; i < word.length;i++){
            x = getScore(word[i], scoretable);
            if(bestScore < x){
                placeKeeper = i;
                bestScore = x;
            }
        }

        return word[placeKeeper];
    }

    /**
	 *  Finds the highest scoring word according to Scrabble rules using a String of letters
	 *
	 *  @param letters  An array of words to check.
	 *  @param scoretable  An array of 26 integer scores in letter order.
	 *  @return   The word with the highest score.
	 */
	public static String bestWord (String letters, int [] scoretable) {

		String [] word = findAllWords(letters);
        int x = 0;
        int placeKeeper = 0;
        int bestScore = 0;

        for(int i = 0; i < word.length;i++){
            x = getScoreBlanks(word[i], scoretable);
            if(bestScore < x){
                placeKeeper = i;
                bestScore = x;
            }
        }

        return word[placeKeeper];
    }

    /**
	 *  Calculates the score of a word without printing to Scrabble rules.
	 *
	 *  @param word  The word to score
	 *  @param scoretable  The array of 26 scores for alphabet.
	 *  @return   The integer score of the word.
	 */
    public static int getScoreBlanks (String word, int [] scoretable){
		
		word = word.toLowerCase();
        int tmp = 0;
        
        for (int i =0; i < word.length(); i++) {
            tmp += scoretable[((int)word.charAt(i)-97)];
        }
        
        for (int i = 0; i < word.length()-1; i++) {
        	if (word.charAt(i) == word.charAt(i+1)) {
        		tmp *= 2;
        	}
        }
        
        return tmp;
    }

	/**
	 *  Calculates the score of a word according to Scrabble rules.
	 *
	 *  @param word  The word to score
	 *  @param scoretable  The array of 26 scores for alphabet.
	 *  @return   The integer score of the word.
	 */
    public static int getScore (String word, int [] scoretable){
		
		word = word.toLowerCase();
        int tmp = 0;
        
        for (int i =0; i < word.length(); i++) {
            tmp += scoretable[((int)word.charAt(i)-97)];
        }
        
        for (int i = 0; i < word.length()-1; i++) {
        	if (word.charAt(i) == word.charAt(i+1)) {
        		tmp *= 2;
        		System.out.println("DOUBLE WORD SCORE!");
        	}
        }
        
        return tmp;
    }
}
