/**
 * This program holds an array of pegs to use for the PegMaster game
 *
 * @author Aditya kalari
 * @version 15 October 2015
 */

public class PegArray {

    private Peg[] pegs;
    private int exact;
    private int partial;

    /**
     * Initializes four pegs
     */
    public PegArray () {
        pegs = new Peg[4];
        for (int peg = 0; peg < 4; peg++)
            pegs[peg] = new Peg();
    }

    /**
     * Initializes a specific number of pegs
     *
     * @param num The number of Pegs being created
     */
    public PegArray (int number) {
        pegs = new Peg[number];
        for (int peg = 0; peg < pegs.length; peg++) {
            pegs[peg] = new Peg();
        }
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
        exact = 0;
        for (int ind = 0; ind < pegs.length; ind++) {
            if (master.pegs[ind] == pegs[ind])
                exact++;
        }
    }

    /**
     * Finds the number of partial matches
     *
     * @param master The master Peg Array
     */
    private void findPartials (PegArray master) {
        // Reset partials and recount for this guess
        partial = 0;
        for (int ind = 0; ind < pegs.length; ind++) {
            for (int indG = 0; indG < pegs.length; ind++) {
                if (master.pegs[indG] == pegs[ind])
                    partial++;
            }
        }
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
