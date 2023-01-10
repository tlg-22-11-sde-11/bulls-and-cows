package com.crackthecode.jewels.model;

//Fields (keeps track of number of wins, losses, success rate, and average number of guess)
public class StatisticsManager {
  private int loss;
  private int win;
  private double sucessRate;
  private double guessAvg;

  //constructor (makes everything start at zero like fresh start)
  public StatisticsManager(){
    loss = 0;
    win = 0;
    sucessRate = 0;
    guessAvg = 0;
  }

  //method (this updates the board on the outcome of each game and number of guess)
  public String playGame(boolean won, int numOfGuess) {
    if (won) { //if the game is won, increment the number of wins
      win = win + 1;
    } else { //if not, increase the number of losses
      loss = loss - 1;
    }
    sucessRate = (double) win / (win +loss); // this is the math for the number of games won
    guessAvg = (guessAvg * (win + loss) + numOfGuess) / (win + loss + 1); // this keeps score of the number of guess on average

@Override
    public String toString( ); { //this method shows the stats for the player to see
      return "Wins:" + win + "Losses: " + loss + "Success Rate: " + sucessRate + "Guess Avg: "
          + guessAvg;
    }
    }

}
