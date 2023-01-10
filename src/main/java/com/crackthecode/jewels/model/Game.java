package com.crackthecode.jewels.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

  public static final int MAX_NUMBER_OF_TRIES = 15;
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
      if (cipher.equals(guess)) { //TODO Make sure this works with how Cipher class is written
        completed = true;
        won = true;
        rubyTracker = 5;
      } else {
        char[] cipherCharArray = cipher.getCurrentCipher().toCharArray(); //TODO Make sure this works with how Cipher class is written
        char[] guessCharArray = guess.getCurrentGuess().toCharArray();

        //checks for exact matches:
        for(int i = 0; i < Guess.GUESS_LENGTH; i++) {
          if(cipherCharArray[i] == guessCharArray[i]) {
            rubyTracker++;
            cipherCharArray[i] = '*';
          }
        }

        //checks for matched characters in wrong location:
        for(char guessChar : guessCharArray) {
          for(int i = 0; i < Guess.GUESS_LENGTH; i++) {
            if(guessChar == cipherCharArray[i]) {
              pearlTracker++;
              cipherCharArray[i] = '*';
            }
          }
        }
      }
      ruby.add(rubyTracker);
      pearl.add(pearlTracker);
    } else {
      completed = true;
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
