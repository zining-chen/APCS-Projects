import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * This program uses File IO to read from a text file.
 * Generates the popularity of a name in a chart at the end.
 * 
 * @author Aditya Kalari
 * @version 30 August 2015
 */

public class Names {
	
	// File that stores names.txt
	private File nameFile;
	private boolean foundName;

	// Initializes fields
	public Names() {
		foundName = true;
		nameFile = new File("names.txt");
	}

	// Calls getNameRequest(), getSingleRow(), drawChart() to generate chart
	public static void main (String[] args) {
		Names setup = new Names();
		String requestedName = setup. getNameRequest();
		int[] rank = setup.getSingleRow(requestedName);
		// Make sure to only run the chart if the name is found in database
		if (setup.foundName)
			setup.drawChart(rank);
	}

	/**
	 * This method uses the Prompt class in order to get the String that the user requests
	 * 
	 * @return Returns the String that the user searches for
	 */
	private String getNameRequest () {
		// Initial formatting
		System.out.println();
		return Prompt.getString("Please enter the name you are searching for");
	}

	/**
	 * This method generates the single line of values for the name the user enters
	 *
	 * @param nameRequest: Takes in the string of the requested name
	 * @return Returns the array  that stores the popularity over various years
	 */
	private int[] getSingleRow (String nameRequest) {
		// Initialize method variables
		Scanner readNames = null;
		int[] namePopularity = new int[12];
		foundName = false;

		// Use OpenFile to generate file
		readNames = OpenFile.openToRead("names.txt");

		System.out.print(nameRequest);

		// Go Through each word
		while (readNames.hasNext()) {
			String tempName = readNames.next();
			// Check if it is the corrected name
			if (nameRequest.equalsIgnoreCase(tempName)) {
				// Print the integers connected to that name
				for (int years = 0; years < namePopularity.length; years++) {
					namePopularity[years] = readNames.nextInt();
					System.out.printf("%5d", namePopularity[years]);
				}
				// Make sure the program knows that the name was found
				foundName = true;
			}
		}
		
		// Case of not foudn name
		if (!foundName)
			 System.out.print(" not found in database.");
		System.out.println("\n");
		
		// Return array of popularity
		return namePopularity;

	}

	/**
	 * This method takes in the popularity of names over years and displays a chart with the results
	 *
	 * @param popularity: The array of popularity over the years for the given name
	 */	
	private void drawChart (int[] popularity) {
		
		// Loop through and draw the first heading line of the chart
		System.out.printf("%6s", " ");
		for (int decade = 1900; decade < 2020; decade += 10)
			System.out.printf("%6s", Integer.toString(decade));		
		System.out.println();
		// Go through each row
		for (int place = 20; place < 1001; place += 20) {
			// Print row header
			System.out.printf("%6s", Integer.toString(place));
			// Go though array and check if the value is applicable to that row
			for (int index = 0; index < popularity.length; index++) {
				if (popularity[index] <= place && popularity[index] >= (place-20)  && popularity[index] != 0) 
					System.out.printf("%6s", Integer.toString(popularity[index]));
				// Print empty cell for 0
				else
					System.out.printf("%6s", "---");
			}
			System.out.println();
		}
		System.out.println();
	}

}