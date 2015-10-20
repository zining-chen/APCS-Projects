/**
 * This program holds an array of pegs to use for the PegMaster game
 *
 * @author Aditya kalari
 * @version 15 October 2015
 */

public class PegArray {

    // FIELDS TO HOLD GUESSES
    private Peg[] pegs;
    private boolean[] exactMatch;
    private int exact;
    private int partial;

    /**
     * Initializes four pegs
     */
    public PegArray () {
        // Setup four new pegs
        pegs = new Peg[4];
        for (int peg = 0; peg < 4; peg++)
            pegs[peg] = new Peg();
        exactMatch = new boolean[4];
    }

    /**
     * Initializes a specific number of pegs
     *
     * @param number The number of Pegs being created
     */
    public PegArray (int number) {
        // Setup specific number of Pegs
        pegs = new Peg[number];
        for (int peg = 0; peg < pegs.length; peg++)
            pegs[peg] = new Peg();
        exactMatch = new boolean[number];
    }

	/**
	 * Returns the specified Peg.
	 *
	 * @param num The index of the specified peg
	 * @return The Peg that was requested
	 */
   public Peg getPeg (int num) {
     return pegs[num];
   }

	/**
	 * Sets a specified Peg.
	 *
	 * @param temp The value of the new peg
	 * @param num The index of the new peg
	 */
    public void setPeg (Peg temp, int num) { pegs[num] = temp; }

    /**
     * Generates the number of matches
     *
     * @param master The master PegArray
     */
    public void findMatches (PegArray master) {
        exact = 0;
        partial = 0;
        findExacts(master);
        findPartials(master);
    }

    /**
     * Finds the number of exact matches
     *
     * @param master The Master Peg Array
     */
    private void findExacts (PegArray master) {
        // Reset exacts and recount for this guess
        for (int ind = 0; ind < pegs.length; ind++) {
            if (master.getPeg(ind).getLetter() == (pegs[ind]).getLetter()) {
                exact++;
                exactMatch[ind] = true;
            }
        }
    }

    /**
     * Finds the number of partial matches
     *
     * @param master The master Peg Array
     */
    private void findPartials (PegArray master) {
        // Reset partials and recount for this guess
        for (int ind = 0; ind < pegs.length; ind++) {
            // Loop through each array making sure not to count duplicates

            for (int indG = 0; indG < pegs.length; indG++) {
                if (master.pegs[ind].getLetter() == pegs[indG].getLetter() && !exactMatch[ind])
                    partial++;
           	}
        }
        // Makes sure Partial doesnt exceed 4
        if (partial > 4) partial = 4;
    }

    /**
     * Returns the exact matches
     *
     * @return The exact matches
     */
    public int getExact () { return exact; }


    /**
     * Returns the partial matches
     *
     * @return The partial matches
     */
    public int getPartial () { return partial; }

}