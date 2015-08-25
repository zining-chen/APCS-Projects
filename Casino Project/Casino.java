/**
 * This class runs LittleJackpot and finds averages for multiple trials.
 * The trials are use definened in how many trials are run.
 *
 *
 * @author Aditya Kalari
 * @version 8.21.2015
 */
 
import java.lang.StringBuilder;
import java.util.Scanner;

public class Casino {

    private int numSides;
    private int numTrials;

    private Dice diceSet[];

    public Casino () {

        numSides = 4;
        diceSet = new Dice [4];

    }

    public static void main (String[] args) {

        Casino setup = new Casino();

        setup.getNumTrials();
        setup.getAvgSpins();

    }

    private void getNumTrials() {

        System.out.println();
        numTrials = Prompt.getInt("The Number of Trials", 10, 100000);
        System.out.println();

        System.out.print(" Number\n");
        System.out.print("  of\t Ave Number\n");
        System.out.print(" Sides\t of Spins\n");

    }

    private void getAvgSpins () {

        int singleAvg[] = new int[100000];
        int totalAvg[] = new int [21];
        int starNum[] = new int [100000];

        for (int tried = 0; tried < totalAvg.length; tried++) {

            for (int trial = 0; trial < numTrials; trial++)
                singleAvg[trial] = runTrials(tried);

            int temp = 0;
            temp = singleAvg[0];

            for (int trial = 0; trial < numTrials; trial++)
                temp += singleAvg[trial];

            int avg = temp/numTrials;
            totalAvg[tried] = avg;

            int diff = (totalAvg[4]*1)+80;
            starNum[tried] = totalAvg[tried]/diff;

            System.out.printf("%2d:\t%6d\t%s\n", tried, avg, drawStars(starNum[tried]));

        }

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

        StringBuilder buildStars = new StringBuilder();

        for (int i = 0; i < avg; i++)
            buildStars.append('*');

        return buildStars.toString();
    }

}