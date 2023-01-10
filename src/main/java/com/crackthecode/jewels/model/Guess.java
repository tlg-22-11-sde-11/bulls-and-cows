package com.crackthecode.jewels.model;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Guess {

  public static final int GUESS_LENGTH = 5;
  public static final String INVALID_INPUT_LENGTH_MESSAGE_FORMAT = "Invalid guess: %s. Guesses must be 5 characters long. %n";
  public static final String INVALID_CHARACTERS_MESSAGE_FORMAT = "Invalid guess:%s. Guesses for this difficulty setting may only contain the following characters: %n%s%n";
  public static final String DUPLICATE_GUESS_MESSAGE_FORMAT = "Invalid guess:%s. This guess has already been used in this game. %n";

  private String currentGuess;
  private final Set<String> guessSet = new LinkedHashSet<>();
  private final String pool;

  public Guess(Cipher cipher) {
    this.pool = cipher.getPool();
  }

  public String getCurrentGuess() {
    return currentGuess;
  }

  public void setCurrentGuess(String currentGuess) throws IllegalArgumentException {
    if(currentGuess == null || currentGuess.length() != GUESS_LENGTH) {
      throw new IllegalArgumentException(String.format(INVALID_INPUT_LENGTH_MESSAGE_FORMAT,currentGuess));
    }
    if(!currentGuess.matches(pool)) {
      throw new IllegalArgumentException(String.format(INVALID_CHARACTERS_MESSAGE_FORMAT, currentGuess, pool));
    }
    if(!guessSet.add(currentGuess)) {
      throw new IllegalArgumentException(String.format(DUPLICATE_GUESS_MESSAGE_FORMAT, currentGuess));
    }
    this.currentGuess = currentGuess;
  }

  public Set<String> getGuessSet() {
    return guessSet;
  }
}
