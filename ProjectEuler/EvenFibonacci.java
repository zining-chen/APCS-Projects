/**
 * This program adds up the even Fibonacci numbers below 4 Million
 *
 * @author Aditya Kalari
 * @version 11 October 2015
 */

public class EvenFibonacci {

    private static final int MAXFIB = 4000000;

    public static void main (String [] args) {

        EvenFibonacci evenFib = new EvenFibonacci();
        int runningSum = 0;
        int num = 1;

        while (evenFib.findFib(num) <= MAXFIB) {
            if (evenFib.findFib(num)%2 == 0)
                runningSum += evenFib.findFib(num);
	    num++;
        }

	System.out.println(runningSum);

    }

    /**
     * Returns Fibonacci number at the given index using recursion
     *
     * @param index The index of the Fibonacci number
     * @return The Fibonacci number at that index
     */
    private int findFib (int index) {
        if (index == 1 || index == 2)
            return 1;
        else
            return findFib(index-1) + findFib(index-2);
    }

}
