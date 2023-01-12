package com.crackthecode.jewels.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>Game</strong> class checks completion of game, checks validity of guesses, updates the
 * stats,
 * and determines user score.
 */

public class Game {

  public static final int MAX_NUMBER_OF_TRIES = 15;
  public static final char GUESS_PLACEHOLDER_CHARACTER = '@';
  public static final char CIPHER_PLACEHOLDER_CHARACTER = '*';

  private final StatisticsManager stats;
  private final Cipher cipher;
  private final List<Integer> ruby = new ArrayList<>();
  private final List<Integer> pearl = new ArrayList<>();
  private boolean completed;
  private boolean won;
  private int guessCounter;
  private char[] cipherCharArray;
  private char[] guessCharArray;


  public Game(Cipher cipher, StatisticsManager stats) {
    this.stats = stats;
    this.cipher = cipher;
  }

  /**
   * <p> The method is checking if the guess input by the user is correct and assigning rubies and
   * pearls via a counter.</p>
   * @param  guess the instance of Guess for this current game
   */
  public void checkGuess(Guess guess) {
    if (guessCounter < MAX_NUMBER_OF_TRIES) {
      cipherCharArray = cipher.getCurrentCipher().toCharArray();
      guessCharArray = guess.getCurrentGuess().toCharArray();
      int rubyTracker;
      int pearlTracker = 0;
      guessCounter++;
      if (cipher.getCurrentCipher().equals(guess.getCurrentGuess())) {
        rubyTracker = Guess.GUESS_LENGTH;
        updateGameStats(true, guessCounter);
      } else {
        if (guessCounter == 15) {
          updateGameStats(false, guessCounter);
        }

        rubyTracker = updateRubyTracker();
        pearlTracker = updatePearlTracker();
      }
      ruby.add(rubyTracker);
      pearl.add(pearlTracker);
    } else {
      updateGameStats(false, guessCounter);
    }
  }

  private void updateGameStats(boolean won, int guessCounter) {
    this.won = won;
    this.completed = true;
    stats.playGame(won, guessCounter);
  }

  private int updatePearlTracker() {
    int pearlTracker = 0;
    for (int i = 0; i < Guess.GUESS_LENGTH; i++) {
      for (int j = 0; j < Guess.GUESS_LENGTH; j++) {
        if (guessCharArray[i] == cipherCharArray[j]) {
          pearlTracker++;
          cipherCharArray[j] = CIPHER_PLACEHOLDER_CHARACTER;
          guessCharArray[i] = GUESS_PLACEHOLDER_CHARACTER;
        }
      }
    }
    return pearlTracker;
  }

  private int updateRubyTracker() {
    int rubyTracker = 0;
    for (int i = 0; i < Guess.GUESS_LENGTH; i++) {
      if (cipherCharArray[i] == guessCharArray[i]) {
        rubyTracker++;
        cipherCharArray[i] = CIPHER_PLACEHOLDER_CHARACTER;
        guessCharArray[i] = GUESS_PLACEHOLDER_CHARACTER;
      }
    }
    return rubyTracker;
  }

  /**
   * <p> The method returns all rubies from current game.</p>
   *
   * @return ruby
   */
  public List<Integer> getRuby() {
    return ruby;
  }

  /**
   * <p> The method returns all pearls from the current game.</p>
   *
   * @return pearl
   */
  public List<Integer> getPearl() {
    return pearl;
  }

  /**
   * <p> The method checks if the game has been won.</p>
   *
   * @return won
   */
  public boolean isWon() {
    return won;
  }

  /**
   * <p> The method checks if the game is completed via a boolean.</p>
   *
   * @return completed
   */
  public boolean isCompleted() {
    return completed;
  }
}
