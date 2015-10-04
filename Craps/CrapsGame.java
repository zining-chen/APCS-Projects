/**
 * Implements the game of Craps and its logic
 *
 * @author Aditya Kalari
 * @version 3 October 2015
 */

public class CrapsGame {

  private int point = 0;

  /**
   *  Calculates the result of the next dice roll in the Craps game.
   *  The parameter total is the sum of dots on two dice.
   *  Point is set to the saved total, if the game continues,
   *  Or 0, if the game has ended.
   *  Returns 1 if player won, -1 if player lost, 0 if player continues rolling.
   *
   * @param total The total amount of die rolled
   * @return The result of the turn
   */
  public int processRoll (int total) {

    // On Point
    if (point != 0) {
        // If Dice Roll = Point
        if (total == point) {
            point = 0;
            return 1;
        }
        // Or if roll = 7
        else if (total == 7) {
            point = 0;
            return -1;
        }
        else {
            return 0;
        }
    }

    // Coming Out turn
    else {
        if (total == 2 || total == 3 || total == 12) {
            point = 0;
            return -1;
        }
        else if  (total == 7 || total == 11) {
            point = 0;
            return 1;
        }
        else {
            point = total;
            return 0;
        }
    }

  }

  /**
   *  Returns the saved point
   */
  public int getPoint() {
    return point;
  }

}
