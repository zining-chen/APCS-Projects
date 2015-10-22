/**
 * This program automatically indents a java file.
 * It follows the Allman style of indentation
 *
 * @author Aditya Kalari
 * @version 22 October 2015
 */

public class PrettyPrint {

    // Contains the whole program in one string
    String contents;
    // Current Token of string
    String token;

    public static void main (String [] args) {}

    /**
     * Strips contents of any newlines or tab characters
     * Use Scanner useDelimiter to accomplish this task
     */
    public void stripContents() {}

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
    public String parseToken() {

        return token;

    }

    /**
     *  Parses one token ahead from variable contents without moving index.
     *
     *  @return The next token in contents
     */
    public String lookAhead() {}

    /**
     *  Parses comment blocks
     *
     *  @param indent The level of indentation
     */
    public void parseComment(int indent) {}

}
