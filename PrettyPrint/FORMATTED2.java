import java.util.Scanner;
import java.util.InputMismatchException;
/*
 * 
 * A series of prompt methods. 
 * 
 * @author Mr Greenstein 
 * @version May 4, 2014 
 */
public class Prompt 
{
    /*
     * 
     * @param ask Prompt line 
     * @return The double value entered 
     */
    public static double getDouble(String ask) 
    {
        double d = 0;
        System.out.printf("\n%s\t\t-> ", ask);
        Scanner kb = new Scanner(System.in);
        try 
        {
            d = kb.nextDouble();
        }
        catch (InputMismatchException e) 
        {
            System.err.println("ERROR: Input mismatch");
            System.exit(1);
        }
        return d;
    }
    /*
     * 
     * @param ask Prompt line 
     * @param min Minimum value 
     * @param max Maximum value 
     * @return The double value entered 
     */
    public static double getDouble(String ask, double min, double max) 
    {
        double d = 0;
        System.out.printf("\n%s\t(%.2f - %.2f)\t-> ", ask, min, max);
        Scanner kb = new Scanner(System.in);
        try 
        {
            d = kb.nextDouble();
        }
        catch (InputMismatchException e) 
        {
            System.err.println("ERROR: Input mismatch");
            System.exit(1);
        }
        return d;
    }
    /*
     * 
     * @param ask Prompt line 
     * @return The string value entered 
     */
    public static String getString(String ask) 
    {
        String str = "";
        System.out.printf("\n%s -> ", ask);
        Scanner kb = new Scanner(System.in);
        try 
        {
            str = kb.nextLine();
        }
        catch (InputMismatchException e) 
        {
            System.err.println("ERROR: Input mismatch");
            System.exit(1);
        }
        return str;
    }
}