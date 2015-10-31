import java.util.Scanner;
import java.io.PrintWriter;

/**
 *  Recieves a source code file and indents according to Allman Style
 *
 *  @author Aditya Kalari
 *  @version 20 October 2015
 */

public class PrettyPrint
{

	private int numTabs = 0;

	public static void main(String[] args)
	{
		PrettyPrint indent = new PrettyPrint();
		String filename = args[0];
		String outfile = args[0];
		PrintWriter printer = OpenFile.openToWrite("Test.java");
		String small = indent.stripContents(filename);
		String formattedCode = indent.parseContents(small);
		System.out.print(formattedCode);
		printer.print(formattedCode);
		printer.close();
	}
	/**
    *  Read in file and store contents into a String.
    *  Go through the String and remove all tabs and newlines.
		*
    *  @param filename the name of the file that the user inputs as a command line argument
    *  @return The large one line String with all the code, not formatted
    */
	public String stripContents(String filename)
	{
		Scanner scan = OpenFile.openToRead(filename);
		scan.useDelimiter("[ \t\r\n]+");
		String code = "";
		while (scan.hasNext())
		{
			String s = scan.next();
			if (s.length() > 0)
			{
				code += s;
				code += " ";
			}
		}
		return code;
	}
	/**
    *  Goes through the String and marks the comments
		*
    *   @return returns the given comment with no format
    *   @param fileOneLine The whole file in one String
		*		@param start Where to start in the file
    */
	public String parseBlockComment(String fileOneLine, int start)
	{
		String comment  = "";
		for (int i = start; i < fileOneLine.length(); i++)
		{
			comment += fileOneLine.charAt(i);
			if ((i < fileOneLine.length() - 2)
				&& (fileOneLine.charAt(i + 1) == '*')
				&& (fileOneLine.charAt(i + 2) == '/'))
			{
				comment += "*/";
				return comment;
			}
		}
		return comment;
	}
	/**
    *  Formats the Comments with the given marks by parseBlockComment
		*
    *  @return returns NewComment with the given format
    *  @param comment Unformatted Comment
    */
	public String parseComment(String comment)
	{
		String newComment = "/*";
		for (int i = 2; i < comment.length(); i++)
		{
			if (comment.charAt(i) != '*')
			{
				newComment += comment.charAt(i);
			}
			else
			{
				if ((i < comment.length() - 1) && (comment.charAt(i + 1) == '/'))
				{
					newComment += "\n" + " " + tabs();
					newComment += comment.charAt(i);
					newComment += comment.charAt(i + 1);
					i++;
					i+= lookAhead(comment, i+1);
					newComment += '\n' + tabs();
				}
				else
				{
					newComment += "\n" + " " + tabs();
					newComment += comment.charAt(i);
				}
			}
		}
		return newComment;
	}
	/**
    *  Finds all the for loops
		*
    *  @return  If there is a for loop
    *  @param fileOneLine The whole file in one String
		*	 @param start Where to start
    */
	public boolean parseForLoop(String fileOneLine, int start)
	{
		if (start < fileOneLine.length() - 5)
		{
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
		 *
     *  @return Returns the string with the formated forloops
     *  @param fileOneLine The whole file in one String
		 *  @param start Where to start reformatting
     */
	public String fixParsedLoop(String fileOneLine, int start)
	{
		String formatted = "";
		int count = 0, i=start;
		boolean done = false;
		while (!done)
		{
			formatted += fileOneLine.charAt(i);
			if (fileOneLine.charAt(i) == ')')
			{
				count--;
				if (count == 0)
					return formatted;
			}
			else if (fileOneLine.charAt(i) == '(')
				count ++;
			i++;
		}
		return formatted;
	}
	/**
    *  Skips all characters that should be ommitted from final formatting
		*
    *  @return 0
    *  @param fileOneLine The whole file in one String
		*  @param start Where to start looking ahead in the file
    */
	public int lookAhead(String fileOneLine, int start)
	{
		if ((start < fileOneLine.length() - 1)
				&& (fileOneLine.charAt(start + 1) == ' '))
				{
			return 1;
		}
		return 0;
	}
	/**
    *  Whether there should be a tab before the brace in a for loop
		*
    *  @return Whether there should be a tab
    *  @param formatted Formatted for loop
    */
	public boolean parseForBrace(String formatted)
	{
		if (formatted.length() < 4)
			return false;
		for (int j = formatted.length() - 4; j < formatted.length(); j++)
		{
			if (formatted.charAt(j) != ' ')
				return false;
		}
		return true;
	}
	/**
    *  Formats all the code with the methods above.
		*  Runs a case based on which key the sentence starts with.
		*
    *  @return Formatted String of Code
    *  @param fileOneLine The whole file in a single String
    */
	public String parseContents(String fileOneLine) {
		String formatted = "";
		for (int i = 0; i < fileOneLine.length(); i++)
		{
			char c = fileOneLine.charAt(i);
			if (i < (fileOneLine.length() - 1) && fileOneLine.charAt(i) == '/' && fileOneLine.charAt(i+1) == '*' )
			{
				String comment = parseBlockComment(fileOneLine, i);
				formatted += parseComment(comment);
				i += comment.length()-1;
				i += lookAhead(fileOneLine, i);
			}
			else if (fileOneLine.charAt(i) == 'f' && parseForLoop(fileOneLine, i))
			{
				String forBlock = fixParsedLoop(fileOneLine, i);
				i += forBlock.length()-1;
				formatted += forBlock;
			}
			else if (fileOneLine.charAt(i) == ';')
			{
				formatted += ";" + "\n" + tabs();
				i += lookAhead(fileOneLine, i);
			}
			else if (fileOneLine.charAt(i) == '{')
			{
				i += lookAhead(fileOneLine, i);
				formatted += "\n" + tabs() + "{" + "\n";
				numTabs++;
				formatted += tabs();
			}
			else if (fileOneLine.charAt(i) == '}')
			{
				if (parseForBrace(formatted))
					formatted = formatted.substring(0, formatted.length() - 4);
				numTabs--;
				i += lookAhead(fileOneLine, i);
				formatted += "}" + "\n" + tabs();
			}
			else
			{
				formatted += fileOneLine.charAt(i);
			}
		}
		formatted = formatted.substring(0, formatted.length()-7) + "   }\n}";
		return formatted;
	}
	/**
    *  Inserts Tabs inside the Program
		*
    *  @return The formatted Line with the extra tabs put in
    */
	public String tabs()
	{
		String str = "";
		for (int i = 0; i < this.numTabs; i++)
			str += "    ";
		return str;
	}
}
