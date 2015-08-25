/**
 * This class runs trials based on LittleJacpot and finds averages for multiple trials.
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
	  
    private void getNumTrials() {

        System.out.println();
        numTrials = Prompt.getInt("The Number of Trials", 10, 100000);
        System.out.println();

        System.out.print(" Number\n");
        System.out.print("  of\t Ave Number\n");
        System.out.print(" Sides\t of Spins\n\n");

    }

    private void getAvgSpins () {

        int singleAvg[] = new int[100000];
        int totalAvg[] = new int [21];
        int starNum[] = new int [21];
        
        int maxSpins = 0;

        for (int tried = 4; tried < totalAvg.length; tried++) {

            for (int trial = 0; trial < numTrials; trial++)
                singleAvg[trial] = runTrials(tried);

            int temp = 0;
            temp = singleAvg[0];

            for (int trial = 0; trial < numTrials; trial++)
                temp += singleAvg[trial];

            int avg = temp/numTrials;
            totalAvg[tried] = avg;

        }
        
        maxSpins = getArrayMax(totalAvg);
        
        for (int setStars = 0; setStars < starNum.length; setStars++)
			starNum[setStars] = getStars(maxSpins, totalAvg[setStars]);
        
        for (int sides = 4; sides < 21; sides++)
			System.out.printf("%2d:\t%6d\t%s\n", sides, totalAvg[sides], drawStars(starNum[sides]));
		
	}

    private int getStars (int maxValue, int currentValue) {
	
		double proportion = ((double)(currentValue))/((double)(maxValue));
		int numStars = (int)(60 * proportion);
		
		return numStars;
	}
    
    private int getArrayMax (int inputArray[]) {
		
		int runningMax = 0;
		
		for (int checkMax = 0; checkMax < inputArray.length; checkMax++)
			if (inputArray[checkMax] > runningMax) { runningMax = inputArray[checkMax]; }
		
		return runningMax;
		
	}

    private int runTrials (int sides) {

        for (int dieNum = 0; dieNum < 4; dieNum++)
            diceSet[dieNum] = new Dice(sides);

        spinWheelBlank();
        return diceSet[0].getRollCount();

    }

    private void spinWheelBlank() {

        boolean finished = false;

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

    private String drawStars (int avg) {

        String buildStars = "";

        for (int i = 0; i < avg; i++)
            buildStars += "*";

        return buildStars;
    }

}