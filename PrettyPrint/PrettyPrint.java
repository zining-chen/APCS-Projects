/**
 *  This programs recieves a text file, which is Java
 *  source code in one long line, and formats it using
 *  Allman Style convention. It outputs it to the terminal window.
 *
 *  @author Aditya Kalari
 *  @version 20 October 2015
 */
import java.util.Scanner;
import java.io.PrintWriter;

public class PrettyPrint {

	private int numTabs = 0;

	public static void main(String[] args){
		PrettyPrint in = new PrettyPrint();
		String filename = args[0];
		String outfile = args[0];
		PrintWriter printer = OpenFile.openToWrite("Formatted.java");
		String small = in.fileToString(filename);
		String formattedCode = in.formatCode(small);
		System.out.print(formattedCode);
		printer.print(formattedCode);
		printer.close();
	}
	/**
     *  Read in file and store contents into a String.
     *  Go through the String and remove all tabs and newlines.
     *  @param String: the name of the file that the user inputs as a command line argument
     *  @return String: The large one line String with all the code, not formatted
     */
	public String fileToString(String filename) {
		Scanner scan = OpenFile.openToRead(filename);
		scan.useDelimiter("[ \t\r\n]+"); //takes away all the tabs and spaces
		String code = "";
		while (scan.hasNext()) {
			String s = scan.next();
			if (s.length() > 0) {
				code += s;
				code += " ";
			}
		}
		return code;
	}
	/**
     *  Goes through the String and finds all the Comments
     *   we have to mark in order to format them
     *   @return returns the given comment with no format
     *   @param string: The whole file in one String, int: where to start
     */
	public String splitCommentInLargeString(String fileOneLine, int start)
	{
		String comment  = "";
		for (int i = start; i < fileOneLine.length(); i++)
		{
			comment += fileOneLine.charAt(i);
			if ((i < fileOneLine.length() - 2)
				&& (fileOneLine.charAt(i + 1) == '*')
				&& (fileOneLine.charAt(i + 2) == '/')) {
				comment += "*/";
				return comment;
			}
		}
		return comment;
	}
	/**
     *  Formats the Comments with the given marks by splitCommentInLargeString
     *  @return returns the newComment with the given format
     *  @param the comment with no format
     */
	public String handleComments(String comment)
	{
		String newComment = "/*";
		for (int i = 2; i < comment.length(); i++) {
			if (comment.charAt(i) != '*') {
				newComment += comment.charAt(i);
			} else {
				if ((i < comment.length() - 1) && (comment.charAt(i + 1) == '/')) {
					newComment += "\n" + " " + tabs();
					newComment += comment.charAt(i);
					newComment += comment.charAt(i + 1);
					i++;
					i+= skipAllSpaces(comment, i+1);
					newComment += '\n' + tabs();
				} else {
					newComment += "\n" + " " + tabs();
					newComment += comment.charAt(i);
				}
			}
		}
		return newComment;
	}
	/**
     *  Finds all the ForBlocks.
     *  @return true : if there is a forBlock, false: No forblock
     *  @param string: The whole file in one String, int: where to start
     */
	public boolean findForBlock(String fileOneLine, int start)
	{
		if (start < fileOneLine.length() - 5) {
			String s = "";
			for (int j = start; j < start + 5; j++)
				s += fileOneLine.charAt(j);
			if (s.compareTo("for (") == 0)
				return true;
		}
		return false;
	}
	/**
     *  Formats all the for loops
     *  @return returns the string with the formated forloops
     *  @param string: The whole file in one String, int: where to start
     */
	public String fixForLoop(String fileOneLine, int start)
	{
		String formatted = "";
		int count = 0, i=start;
		boolean done = false;
		while (!done)
		{
			formatted += fileOneLine.charAt(i);
			if (fileOneLine.charAt(i) == ')') {
				count--;
				if (count == 0)
					return formatted;
			} else if (fileOneLine.charAt(i) == '(')
				count ++;
			i++;
		}
		return formatted;
	}
	/**
     *  Skips all the extra Spaces in the String
     *  @return 0
     *  @param string: The whole file in one String, int: where to start
     */
	public int skipAllSpaces(String fileOneLine, int start)
	{
		if ((start < fileOneLine.length() - 1)
				&& (fileOneLine.charAt(start + 1) == ' ')) {
			return 1;
		}
		return 0;
	}
	/**
     *  Should there be a tab before the brace in a Forloop
     *  @return true : there should be a tab, false: there should not be a tab
     *  @param string: formated for Loop
     */
	public boolean findTabBeforeBrace(String formatted)
	{
		if (formatted.length() < 4)
			return false;
		for (int j = formatted.length() - 4; j < formatted.length(); j++) {
			if (formatted.charAt(j) != ' ')
				return false;
		}
		return true;
	}
	/**
     *  Formats all the code with the methods above.
     *  Maniplulates them in order for the program to work
     *  @return string with all the formated code
     *  @param string: The whole file in one String
     */
	public String formatCode(String fileOneLine) {
		String formatted = "";
		for (int i = 0; i < fileOneLine.length(); i++) {
			// keep track of blocks - comments and for loops
			char c = fileOneLine.charAt(i);
			if (i < (fileOneLine.length() - 1) && fileOneLine.charAt(i) == '/' && fileOneLine.charAt(i+1) == '*' ) { //comment format
				String comment = splitCommentInLargeString(fileOneLine, i);
				formatted += handleComments(comment);
				i += comment.length()-1; // ++i still to follow
				i += skipAllSpaces(fileOneLine, i);
			} else if (fileOneLine.charAt(i) == 'f' && findForBlock(fileOneLine, i)) {
				String forBlock = fixForLoop(fileOneLine, i);
				i += forBlock.length()-1; // ++i still to follow
				formatted += forBlock;
			} else if (fileOneLine.charAt(i) == ';') { // semi colon format
				formatted += ";" + "\n" + tabs();
				i += skipAllSpaces(fileOneLine, i);
			} else if (fileOneLine.charAt(i) == '{') { //bracket format
				i += skipAllSpaces(fileOneLine, i);
				formatted += "\n" + tabs() + "{" + "\n";
				numTabs++;
				formatted += tabs();
			} else if (fileOneLine.charAt(i) == '}') {
				if (findTabBeforeBrace(formatted))
					formatted = formatted.substring(0, formatted.length() - 4);
				numTabs--;
				i += skipAllSpaces(fileOneLine, i);
				formatted += "}" + "\n" + tabs();
			} else {
				formatted += fileOneLine.charAt(i);
			}
		}
		return formatted;
	}
	/**
     *  Inserts Tabs inside the Program
     *  @return the formated code in a String with all the necessary tabs
     */
	public String tabs() {
		String str = "";
		for (int i = 0; i < this.numTabs; i++)
			str += "    ";
		return str;
	}
}
