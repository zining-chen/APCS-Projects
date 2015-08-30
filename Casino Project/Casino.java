/**
 * This class runs trials based on LittleJackpot and finds averages for multiple trials.
 * The trials are use defined in how many trials are run.
 *
 *
 * @author Aditya Kalari
 * @version 8.21.2015
 */
 
import java.util.Scanner;

public class Casino {

    // NumSides, NumTrials for later use
    private int numSides;
    private int numTrials;

    // Declare array of dice
    private Dice diceSet[];

    public Casino () {

        // Initialize values to defaults
        numSides = 4;
        diceSet = new Dice [4];

    }

    public static void main (String[] args) {

        Casino setup = new Casino();

	// Run printing methods
        setup.getNumTrials();
        setup.getAvgSpins();

    }
	  
    /**
     * Gets the number of Trials by prompting the user for the correct values.
     * It also creates the header of the the data table/histogram.
     */
    private void getNumTrials() {

        // Original Prompt using Prompt Class
        System.out.println();
        numTrials = Prompt.getInt("The Number of Trials", 10, 100000);
        System.out.println();

        // Print Table Headers
        System.out.print(" Number\n");
        System.out.print("  of\t Ave Number\n");
        System.out.print(" Sides\t of Spins\n\n");

    }

    /**
     * Goes through the correct number of trials, calculates the average values.
     * Draws a histogram showing the proportion of each trial
     */
    private void getAvgSpins () {

        int singleAvg[] = new int[100000];  // Holds the current averages from the current set of trials
        int totalAvg[] = new int [21];      // Holds all the averages across all the dice
        int starNum[] = new int [21];       // Holds the number of stars for each row of histogram
        
        int maxSpins = 0; // Hold the maxSpins, for making proportions based on stars

        // Loops through the totalAvg array and stores values from all the trials
        for (int tried = 4; tried < totalAvg.length; tried++) {

            // Stores values from a single Trial from runTrials()
            for (int trial = 0; trial < numTrials; trial++)
                singleAvg[trial] = runTrials(tried);

            // Temp hold the head value of the array
            int temp = 0;
            temp = singleAvg[0];

            // Uses temp to create the total avg
            for (int trial = 0; trial < numTrials; trial++)
                temp += singleAvg[trial];

            // Takes the average of temp
            totalAvg[tried] = temp/numTrials;

        }
        
        // Get the maxNumber of spins for the Histogram
        maxSpins = getArrayMax(totalAvg);
        
        // Loops through starNum and sets the correct number of stars
        for (int setStars = 0; setStars < starNum.length; setStars++)
			starNum[setStars] = getStars(maxSpins, totalAvg[setStars]);
        
        // Prints the output using printf to format
        for (int sides = 4; sides < 21; sides++)
			System.out.printf("%2d:\t%6d\t%s\n", sides, totalAvg[sides], drawStars(starNum[sides]));
		
	}

    /**
     * Calculates the correct number of stars for each row of the histogram based on the maximum stars 60.
     *
     * @param    Indicates the maximum value of the histogram
     * @param    Indicates the value that is being calculated for
     * @return   Returns the number of stars for that row of the histogram
     */
    private int getStars (int maxValue, int currentValue) {
	
        // Store proportion, and calculate to get max number of stars
		double proportion = ((double)(currentValue))/((double)(maxValue));
		int numStars = (int)(60 * proportion);
		
		return numStars;
	}
    
    /**
     * Calculates the maximum value of an Array of ints
     *
     * @param inputArray[]: Array that holds set of values
     * @return runningMax:  Returns the value of maximum value
     */
    private int getArrayMax (int inputArray[]) {
		
		int runningMax = 0;
		
		// Loops through and get the maximum of the array
		for (int checkMax = 0; checkMax < inputArray.length; checkMax++)
			if (inputArray[checkMax] > runningMax) { runningMax = inputArray[checkMax]; }
		
		return runningMax;
		
	}

    /**
     * Runs the trial of the dice and returns the number of rolls to win
     *
     * @param  Number of the sides on each die
     * @return Returns the number of rolls for each dice to match up
     */
    private int runTrials (int sides) {

        // Loops through Dice and initializes Dice
        for (int dieNum = 0; dieNum < 4; dieNum++)
            diceSet[dieNum] = new Dice(sides);

        spinWheelBlank();
        return diceSet[0].getRollCount();

    }

    /**
     * Spins the wheels of the casino until all four dice match
     */
    private void spinWheelBlank() {

        boolean finished = false;

        // Spins the wheel until all four dice are equal to each other
        do {

            for (int dieNum = 0; dieNum < 4; dieNum++)
                diceSet[dieNum].roll();

            if (diceSet[0].getValue() == diceSet[1].getValue()) {
                if (diceSet[0].getValue() == diceSet[2].getValue()) {
                    if (diceSet[0].getValue() == diceSet[3].getValue()) {
                        finished = true;
                    }
                }
            }

        } while (!finished);

    }

    /**
     * Creates a string of '*' based on how many stars should be in the histogram
     *
     * @param This is the number of stars that need to be drawn in the String
     * @return Returns the String of stars used for the histogram
     */
    private String drawStars (int avg) {

        String buildStars = "";

        // Loops through and concatenates correct number of stars
        for (int i = 0; i < avg; i++)
            buildStars += "*";

        return buildStars;
    }

}