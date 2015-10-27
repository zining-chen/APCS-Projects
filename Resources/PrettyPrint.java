import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Indents the inputted java file and indents it using the Allman style of brackets
 *
 * @author Aditya Kalari
 * @version 22 October 2015
 */

public class PrettyPrint {

	private String contents;
	private String token;

	public PrettyPrint () {
		// Setup String variables
		contents = new String();
	}

	public static void main (String [] args) {
		// Initialize variables using the constructor
		PrettyPrint indent = new PrettyPrint();
		// Strip the file of all new Characters
		String javafile = new String (args[0]);
		indent.contents = indent.javaToString(javafile, false);
    indent.contents = indent.stripContents(indent.contents);
	}

	/**
	 *  Converts java file to String
   *
   *  @param javaname The name of the java file
   *  @param formatted Whether the function should return the formatted file or not
   *  @return The file that has been read from the inputted javaname file.
   */
  public String javaToString (String javaname, boolean formatted) {
     Scanner readJava = OpenFile.openToRead(javaname);
     String file =  new String();
     while (readJava.hasNext()) {
         file += readJava.nextLine();
         if (formatted)
             file += "\n";
     }
     return file;
  }


  /**
   *  Strips contents of any newlines or tab characters
   *
   *  @param String The string version of the file
   *  @return The stripped string withouth \n or \t characters
   */
  public String stripContents (String javafile) {
      int nextEscape = -2;
      String strippedFile = new String();
      nextEscape = Math.min(javafile.indexOf("\n"), javafile.indexOf("\t"));
      while (nextEscape != 0 && nextEscape != -1) {
          strippedFile += javafile.substring(0, nextEscape);
          javafile = javafile.substring(nextEscape+1);
          nextEscape = Math.min(javafile.indexOf("\n"), javafile.indexOf("\t"));
      }
      return strippedFile;
  }

	/**
	 *  Parses the contents
	 */
	public void parseContents() {}

	/**
	 *  Parses one line at a time. Calls itself recursively as it indents.
	 *
	 *  @param indent The level of indentation
	 */
	public void parseSentence(int indent) {}

	/**
	 *  Parses one token from variable contents
	 *
	 *  @return The token string
	 */
	public String parseToken() { return token; }

	/**
	 *  Parses one token ahead from variable contents without moving index.
	 *
	 *  @return The next token in contents
	 */
	public String lookAhead() { return ""; }

	/**
	 *  Parses comment blocks
	 *
	 *  @param indent The level of indentation
	 */
	public void parseComment(int indent) {}

}
