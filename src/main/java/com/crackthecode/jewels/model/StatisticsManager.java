package com.crackthecode.jewels.model;

public class StatisticsManager {
  private int loss;
  private int win;
  private double sucessRate;
  private double guessAvg;

  public StatisticsManager(){
    loss = 0;
    win = 0;
    sucessRate = 0;
    guessAvg = 0;
  }

  public void playGame(boolean won, int numOfGuess) {
    if (won) {
      win = win + 1;
    } else {
      loss = loss - 1;
    }
    sucessRate = (double) win / (win +loss);
    guessAvg = guessAvg * (win + loss) + numOfGuess) / (win + loss + 1);

@Override
    public String toString(); {
      return "Wins:" + win + "Losses: " + loss + "Success Rate: " + sucessRate + "Guess Avg: "
          + guessAvg;
    }
    }

}
