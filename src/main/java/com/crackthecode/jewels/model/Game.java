package com.crackthecode.jewels.model;

import java.util.ArrayList;
import java.util.List;

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
  char[] cipherCharArray;
  char[] guessCharArray;

  public Game(Cipher cipher, StatisticsManager stats) {
    this.stats = stats;
    this.cipher = cipher;
  }

  public void checkGuess(Guess guess) {
    if (guessCounter < MAX_NUMBER_OF_TRIES) {
      cipherCharArray = cipher.getCurrentCipher().toCharArray();
      guessCharArray = guess.getCurrentGuess().toCharArray();
      int rubyTracker = 0;
      int pearlTracker = 0;
      guessCounter++;
      if (cipher.getCurrentCipher().equals(guess.getCurrentGuess())) {
        rubyTracker = Guess.GUESS_LENGTH;
        updateGameStats(true, guessCounter);
      } else {
        if(guessCounter == 15) {
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
    for(int i = 0; i < Guess.GUESS_LENGTH; i++) {
      for(int j = 0; j < Guess.GUESS_LENGTH; j++) {
        if(guessCharArray[i] == cipherCharArray[j]) {
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
    for(int i = 0; i < Guess.GUESS_LENGTH; i++) {
      if(cipherCharArray[i] == guessCharArray[i]) {
        rubyTracker++;
        cipherCharArray[i] = CIPHER_PLACEHOLDER_CHARACTER;
        guessCharArray[i] = GUESS_PLACEHOLDER_CHARACTER;
      }
    }
    return rubyTracker;
  }

  public List<Integer> getRuby() {
    return ruby;
  }

  public List<Integer> getPearl() {
    return pearl;
  }

  public boolean isWon() {
    return won;
  }
  public boolean isCompleted() {
    return completed;
  }
}
