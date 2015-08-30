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

	private File nameFile;

	public Names() {
		nameFile = new File("names.txt");
	}

	public static void main (String[] args) {
		Names setup = new Names();
		setup.getSingleRow(setup.getNameRequest());
	}

	private String getNameRequest () {
		System.out.println();
		return Prompt.getString("Please enter the name you are searching for");
	}

	private void getSingleRow (String nameRequest) {
		Scanner readNames = null;
		int[] namePopularity = new int[12];
		boolean foundName = false;

		sreadNames = OpenFile.openToRead("names.txt");

		System.out.print(nameRequest);

		while (readNames.hasNext()) {
			String tempName = readNames.next();
			if (nameRequest.equals(tempName)) {
				for (int years = 0; years < namePopularity.length; years++) {
					namePopularity[years] = readNames.nextInt();
					System.out.printf("%5d", namePopularity[years]);
				}
				foundName = true;
			}
		}

		if (!foundName) System.out.println(" not found in database.");

		System.out.println();

	}

}