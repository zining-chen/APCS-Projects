import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
/*
 * 
 * This program reads from a text file, counts the number of each letter in the alphabet, 
 * then prints out a frequency chart. 
 * 
 * @author Mr Greenstein 
 * @version May 4, 2014 
 */
public class LetterCount 
{
    Scanner in;
    PrintWriter out;
    public static void main(String[] args) 
    {
        LetterCount lc = new LetterCount();
        lc.Run();
    }
    public void Run() 
    {
        String inFileName = Prompt.getString("Please enter the name of the file to be" + " processed");
        String outFileName = Prompt.getString("Please enter the name of the file to be" + " created");
        try 
        {
            in = new Scanner(new File(inFileName));
        }
        catch (IOException e) 
        {
            System.err.println("ERROR: Cannot open " + inFileName);
            System.exit(1);
        }
        try 
        {
            out = new PrintWriter(new File(outFileName));
        }
        catch (IOException e) 
        {
            System.err.println("ERROR: Cannot open " + outFileName);
            System.exit(1);
        }
        int [] alpha = new int[26];
        for (int i = 0; i < 26; i++) 
        {
            alpha[i] = 0;
        }
        while (in.hasNext()) 
        {
            String line = in.nextLine();
            for (int i = 0; i < line.length(); i++) 
            {
                int c = (int)line.charAt(i);
                if (c > 64 && c < 91) alpha[c % 65]++;
                if (c > 96 && c < 123) alpha[c % 97]++;
            }
        }
        in.close();
        int maxCnt = 0;
        for (int i = 0; i < 26; i++) 
        {
            if (maxCnt < alpha[i]) maxCnt = alpha[i];
        }
        int delta = maxCnt / 80 + 1;
        char lineChar = 'A';
        System.out.printf("\nFrequency of letters in file %s\n\n", inFileName);
        for (int i = 0; i < 26; i++) 
        {
            System.out.printf("%c: %6d ", lineChar, alpha[i]);
            int col = delta;
            while (alpha[i] > col - delta/2) 
            {
                System.out.printf("=");
                col += delta;
            }
            System.out.println();
            lineChar = (char)((int)lineChar + 1);
        }
        System.out.println();
        lineChar = 'A';
        out.printf("\nFrequency of letters in file %s\n\n", inFileName);
        for (int i = 0; i < 26; i++) 
        {
            out.printf("%c: %6d ", lineChar, alpha[i]);
            int col = delta;
            while (alpha[i] > col - delta/2) 
            {
                out.printf("=");
                col += delta;
            }
            out.println();
            lineChar = (char)((int)lineChar + 1);
        }
        out.println();
        out.close();
    }
}