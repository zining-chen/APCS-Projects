import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Open files for reading and writing
 * 
 * @author Aditya Kalari
 * @version August 27, 2015
 */

public class OpenFile {

	public static Scanner openToRead (String fileName) {
		Scanner input = null;
		try {
			input = new Scanner(new File(fileName));
		} catch (FileNotFoundException error) {
			System.err.println("ERROR: Cannot open " + fileName + " for reading");
			System.exit(1);
		}		
		return input;
	}
	
	public static PrintWriter openToWrite (String fileName) {

		PrintWriter output = null;
		try {
			output = new PrintWriter(new File(fileName));
		} catch (IOException error) {
			System.err.println("ERROR: Cannon open " + fileName + " for writing");
			System.exit(2);
		}	

		return output;

	}

}