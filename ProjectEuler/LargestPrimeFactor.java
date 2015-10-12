import java.util.Scanner;

/**
 * This program finds the Largest Prime factor of the user inputted number
 *
 * @author Aditya Kalari
 * @version 11 October 2015
 */

public class LargestPrimeFactor {

    public static void main (String [] args) {
        LargestPrimeFactor lpf = new LargestPrimeFactor();
        long input = lpf.getUserInput();
        long factor = lpf.findGreatestFactor(input);
        System.out.println(factor);
    }

    /**
     * Takes the User input
     *
     * @return The inputter number
     */
    private long getUserInput () {
    	Scanner read = new Scanner(System.in);
      	return read.nextLong();
    }

    /**
     * Finds the greatest prime factor
     *
     * @param multiple The number of which to find a factor
     * @return The Greatest Prime Factor
     */
    private long findGreatestFactor (long multiple) {
        int factor = 1;
	for (factor = 1; factor<multiple; factor++) {
        	while (multiple%factor == 0) {
                	multiple /= factor;
		 	System.out.println(multiple); 	
                }
	}
	return multiple;
    }


}
