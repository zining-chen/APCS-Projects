/**
 * This class defines a Peg in the game PegMaster
 *
 * @author Aditya Kalari
 * @version 15 October 2015
 */

public class Peg{

    // Values 'A' through 'F' and 'X'
    private char letter;

    public Peg() {
        letter = 'X';
    }

    /**
     * Initializes new Peg with specific letter
     *
     * @param l The letter to initialize with
     */
    public Peg (char l) {
        letter = l;
    }

    /**
     * Getter for the letter of this peg
     *
     * @return The letter of this peg
     */
    public char getLetter () {
        return letter;
    }

    /**
     * Setter for the character of the Peg
     *
     * @param l The character to set this Peg to
     */
    public void setLetter (char l) {
        letter = l;
    }

}
