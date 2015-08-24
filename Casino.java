/**
 * This class runs LittleJackpot and finds averages for multiple trials.
 * The trials are use definened in how many trials are run.
 *
 * @author Aditya Kalari
 * @version 8.21.2015
 */
 
import java.util.Scanner;

public class Casino {
	
	private Dice[] dieSet;
	
	private int numTrials;
	
	public Casino () {
		dieSet = [4]
		dieSet[0] = new Dice();
		dieSet[1] = new Dice();
		dieSet[2] = new Dice();
		dieSet[3] = new Dice()
	}
	
	public static void main (String [] args) {
		
	}
	
	private void getTrials () {
		System.out.println();
		numTrials = Prompt.getInt("The number of Trials", 10, 100000);
		System.out.println();
	}
	
	private int[] spinDice () {
		int[] averages = [18];
		int[] spins = [numTrials];
		
		for (int sides = 4; i < 21; i++) {
			for (Dice roller : dieSet)
				roller = new Dice(sides);
			for (int trials = 0; trials < numTrials; trials ++) {
				spins[trials] = spinWheelBlank();
				averages[sides - 4] = getAverageSpins(spins);
			}
		}
	}
	
	public int spinWheelBlank () {
		boolean done = false;
			dieSet[3].roll();
			if (die1.getValue() == die2.getValue()) {
				if (die2.getValue() == die3.getValue()) {
					if (die3.getValue() == die4.getValue()) {
						done = true;
					}
				}
			}
		} while(!done);
		return dieSet[0].getRollCount();
	}

	private int getAverageSpins (int[] 