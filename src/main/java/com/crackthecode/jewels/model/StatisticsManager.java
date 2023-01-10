package com.crackthecode.jewels.model;

//Fields (keeps track of number of wins, losses, success rate, and average number of guess)
public class StatisticsManager {

  private int loss;
  private int win;
  private double successRate;
  private double guessAvg;

  private double guessSum;

  //constructor (makes everything start at zero like fresh start)
  public StatisticsManager() {
//    loss = 0; // might need this section if default value up top is already 0??
//    win = 0;
//    successRate = 0;
//    guessAvg = 0;
//    guessSum = 0;
  }

  //method (this updates the board on the outcome of each game and number of guess)
  public void playGame(boolean won, int numOfGuess) {
    if (won) { //if the game is won, increment the number of wins
      guessSum = guessSum + numOfGuess;
      win = win + 1;
    } else { //if not, increase the number of losses
      loss = loss + 1;
    }
    successRate = (double) win / (win + loss); // this is the math for the number of games won
    guessAvg = guessSum / win; // this keeps score of the number of guess on average

  }
    @Override // not sure if override here would make sense?
    public String toString() {
      // array of strings representing statistics
      String[] stats = {"Wins: " + win, "Losses: " + loss,
          "Success Rate: " + String.format("%.2f", successRate * 100) + "%",
          "Guess Average: " + String.format("%.2f", guessAvg)};
      // join the strings with newline separator
      return "Game Statistics:\n" + String.join("\n", stats);
    }

  }

