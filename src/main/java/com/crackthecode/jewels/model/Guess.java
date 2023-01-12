package com.crackthecode.jewels.model;

import com.crackthecode.jewels.model.exceptions.DuplicateGuessException;
import com.crackthecode.jewels.model.exceptions.InvalidGuessException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <strong>Guess</strong> class 'gets' and 'sets' the current guess and then adds it to the set.
 */

public class Guess {

  public static final int GUESS_LENGTH = 5;
  public static final String INVALID_INPUT_LENGTH_MESSAGE_FORMAT = "Invalid guess: %s. Guesses must be 5 characters long. %n";
  public static final String INVALID_CHARACTERS_MESSAGE_FORMAT = "Invalid guess:%s. Guesses for this difficulty setting may only contain the following characters: %n%s%n";
  public static final String DUPLICATE_GUESS_MESSAGE_FORMAT = "Invalid guess:%s. This guess has already been used in this game. %n";

  private String currentGuess;
  private final Set<String> guessSet = new LinkedHashSet<>();
  private final String pool;



  public Guess(Cipher cipher) {
    this.pool = '[' + cipher.getPool() + "]+";
  }

  /**
   * <p> This is a string to get the current guess..</p>
   * @return currentGuess
   */
  public String getCurrentGuess() {
    return currentGuess;
  }

  /**
   * <p> The method takes what the uses inputs and checks for validation of pool, guess, and length
   * of guess. If the guess has not already been guessed it adds it to the guess set.</p>
   * @param currentGuess the guess input by the user
   */
  public void setCurrentGuess(String currentGuess) throws InvalidGuessException, DuplicateGuessException {
    if(currentGuess == null || currentGuess.length() != GUESS_LENGTH) {
      throw new InvalidGuessException(String.format(INVALID_INPUT_LENGTH_MESSAGE_FORMAT,currentGuess));
    }
    String guess = currentGuess.toLowerCase();
    if(!guess.matches(pool)) {
      throw new InvalidGuessException(String.format(INVALID_CHARACTERS_MESSAGE_FORMAT, currentGuess, pool));
    }
    if(!guessSet.add(guess)) {
      throw new DuplicateGuessException(String.format(DUPLICATE_GUESS_MESSAGE_FORMAT, currentGuess));
    }
    this.currentGuess = guess;
  }

  /**
   * <p> The method takes the guesses and puts it in a set so that it can be return after each try from the user..</p>
   * @return guessSet
   */
  public Set<String> getGuessSet() {
    return guessSet;
  }
}
