package com.crackthecode.jewels.model;


/**
 * <strong>Statistics Manager</strong> class keeps track of number of wins, losses, success rate,
 * and average number of guess. At the end of game this updates the board with the outcome of each.
 */


public class StatisticsManager {

  private int loss;
  private int win;
  private double successRate;
  private double guessAvg;

  private double guessSum;


  /**
   * <p>This method keeps score of the wins and losses using a counter and the overall success
   * rate.
   * @param numOfGuess  number of guesses from user
   * @param won returns if the user won or lost
   * </p>
   */
  public void playGame(boolean won, int numOfGuess) {
    if (won) {
      guessSum = guessSum + numOfGuess;
      win = win + 1;
    } else {
      loss = loss + 1;
    }
    successRate = (double) win / (win + loss);
    guessAvg = guessSum / win;

  }


  @Override
  public String toString() {
    String[] stats = {"Wins: " + win, "Losses: " + loss,
        "Success Rate: " + String.format("%.2f", successRate * 100) + "%",
        "Win Guess Average: " + (Double.isNaN(guessAvg) ? "N/A" : String.format("%.2f", guessAvg))};
    return "\nAll Time Statistics:\n" + String.join("\n", stats) + "\n";
  }

}

