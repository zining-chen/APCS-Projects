 /**
 * This program takes in a value of money and produces change using quarter, dime, and nickels.
 *
 * @author Aditya Kalari
 * @version 5 September 2015
 */

public class Coins {

	public Coins () {}
	
	public static void main (String[] args) {
		Coins setup = new Coins();
		// Get number of cents from user
		int coins = setup.getCents();
		// Pass coins and get combos
		int numCombos = setup.getCoins(coins);
		// Print total combos
		System.out.println("\nTotal Number of Combos: " + numCombos + "\n");
	}
	
	/**
	 * This method gets the number of coins by using Prompt
	 *
	 * @return The amount of user entered coins
	 */
	private int getCents() {
		// Formatting and return Prompt value with modified minInt method
		System.out.println();
		return Prompt.getInt("Enter the number of cents");
	}

	/**
	 * This method creates all the combos of coins for the amount of change
	 *
	 * @param The amount of cents required to make change
	 */
	private int getCoins(int cents) {
		// Formatting
		System.out.println();
		// Holds number of combinations
		int combos = 0;
		// Loops through the possible quarters
		for (int quarters = 0; quarters <= cents/25; quarters++) {
			// Loops through the possible dimes given a number of quarters
			for (int dimes = 0; dimes <= (cents - (quarters*25)); dimes++) {
				// Loops through the nickels given a number of dimes and quarters
				for (int nickels = 0; nickels <= (cents - (quarters*25) - (dimes*10)); nickels++) {
					// Makes sure that there are no negative pennies
					if ((cents - (quarters*25) - (dimes*10) - (nickels*5)) >= 0) {
						// Send coins to printCoins and increment combos
						printCoins(cents, quarters, dimes, nickels);
						combos++;
					}
				}
			}
		}

		return combos;
		
	}


	/**
	 * This method prints the number of coins with printf
	 * 
	 * @param The number of quarters
	 * @param The number of dimes
	 * @param The number of nickels
	 */
	private void printCoins (int total, int quarters, int dimes, int nickels) {
		// Printf with 8 spaces for words and 6 for the numbers
		System.out.printf("%8s: %6d %8s: %6d %8s %6d %8s %6d \n",
			 "Quarters", quarters,
                         "Dimes", dimes,
                         "Nickels", nickels,
                         "Pennies", (total - (quarters*25) - (dimes*10) - (nickels*5)));
	}

}
