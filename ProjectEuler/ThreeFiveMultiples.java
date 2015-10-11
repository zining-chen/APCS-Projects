/**
 * This program calculates the sum of all multiples of three and five
 * From zero to 1000.
 *
 * @author Aditya Kalari
 * @version 11 October 2015
 */

public class ThreeFiveMultiples {

    public static void main (String[] args) {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            if (i%5 == 0)
                sum += i;
            else if (i%3 == 0)
                sum += i;
        }
        System.out.println(sum);
    }

}
