/**
 * This class runs LittleJackpot and finds averages for multiple trials.
 * The trials are use definened in how many trials are run.
 *
 *
 * @author Aditya Kalari
 * @version 8.21.2015
 */
 
import java.util.Scanner;

public class Casino {

    private int numSides;
    private int numTrials;

    private int diceSet[];

    public Casino () {

        numSides = 4;
        diceSet = [4];

    }

    public static void main (String[] args) {

        Casino setup = new Casino();

    }

    private int runTrials (int sides) {

        for (Dice rollers : diceSet) {
            rollers = new Dice (sides);
        }
        spinWheelBlank();
        return diceSet[0].rollCount();

    }

    private void spinWheelBlank() {

        boolean finished = false;

        do {

            for (Dice rollers : diceSet)
                rollers.roll();

            if (diceSet[0].getValue() == diceSet[0].getValue() == diceSet[0].getValue() == diceSet[0].getValue() == )
                finished = true;

        } while (!finished)
    }

}