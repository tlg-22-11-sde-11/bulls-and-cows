package com.crackthecode.jewels.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

  public static final int MAX_NUMBER_OF_TRIES = 15;
  public static final char GUESS_PLACEHOLDER_CHARACTER = '@';
  public static final char CIPHER_PLACEHOLDER_CHARACTER = '*';

  private final StatisticsManager stats;
  private final Cipher cipher;
  private List<Integer> ruby = new ArrayList<>();
  private List<Integer> pearl = new ArrayList<>();
  private boolean completed;
  private boolean won;
  private int guessCounter;


  public Game(Cipher cipher, StatisticsManager stats) {
    this.stats = stats;
    this.cipher = cipher;
  }

  public boolean checkGuess(Guess guess) {
    if (guessCounter < MAX_NUMBER_OF_TRIES) {
      int rubyTracker = 0;
      int pearlTracker = 0;
      guessCounter++;
      if (cipher.getCurrentCipher().equals(guess.getCurrentGuess())) {
        won = true;
        rubyTracker = Guess.GUESS_LENGTH;
        completed = true;
        stats.playGame(won, guessCounter);
      } else {
        char[] cipherCharArray = cipher.getCurrentCipher().toCharArray();
        char[] guessCharArray = guess.getCurrentGuess().toCharArray();

        //checks for exact matches and replaces characters with placeholder if matched:
        for(int i = 0; i < Guess.GUESS_LENGTH; i++) {
          if(cipherCharArray[i] == guessCharArray[i]) {
            rubyTracker++;
            cipherCharArray[i] = CIPHER_PLACEHOLDER_CHARACTER;
            guessCharArray[i] = GUESS_PLACEHOLDER_CHARACTER;
          }
        }

        //checks for matched characters in wrong location and replaces cipher characters with placeholder if matched:
        for(char guessChar : guessCharArray) {
          for(int i = 0; i < Guess.GUESS_LENGTH; i++) {
            if(guessChar == cipherCharArray[i]) {
              pearlTracker++;
              cipherCharArray[i] = CIPHER_PLACEHOLDER_CHARACTER;
            }
          }
        }
      }
      ruby.add(rubyTracker);
      pearl.add(pearlTracker);
    } else {
      completed = true;
      won = false;
      stats.playGame(won, guessCounter);
    }
    return won;
  }

  public List<Integer> getRuby() {
    return ruby;
  }

  public List<Integer> getPearl() {
    return pearl;
  }

  public boolean isCompleted() {
    return completed;
  }
}
