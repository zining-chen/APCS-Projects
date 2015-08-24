/**
 *  Simulates a single die.  The die has a number of
 *  sides, it can be rolled, and it keeps its own
 *  roll count.
 *
 *  @author Aditya Kalari
 *  @version  8-18-15
 */

public class Dice {
	
	private int numSides;	// Number of sides 
	private int rollCount;	// Count number of rolls
	private int value;		// Value of a die roll
	
	// Constructor - assumes there are 6 sides to the die and initializes other fields
	public Dice () {
		numSides = 6;
		rollCount = 0;
		value = 0;
	}
	
	// Constructor - parameter specifies the number of sides  
	public Dice (int initSides)  {
		this();
		numSides = initSides;
	}
	
	/**
	 *  Rolls the die to generate a random number
	 *
	 *  @return   A random number from the roll of the die
	 */
	public int roll () {
		rollCount++;
		value = (int)(Math.random() * numSides + 1);
		return value;
	}
	
	/**
	 *  Returns the number of rolls
	 *
	 *  @return   The number of rolls
	 */
	public int getRollCount ( ) { return rollCount; }
	
	/**
	 *  Returns the value of the die roll
	 *
	 *  @return   Value of the die roll
	 */	 
	public int getValue ( ) { return value; }

}