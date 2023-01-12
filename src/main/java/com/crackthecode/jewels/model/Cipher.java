package com.crackthecode.jewels.model;

import com.crackthecode.jewels.model.exceptions.InvalidLevelException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * <strong>Cipher</strong> class (or the code) stores and generates ciphers.
 */

public class Cipher {

  public static final String LEVEL_ONE_POOL = "0123456789";
  public static final String LEVEL_TWO_POOL = "abcdefghijklmnopqrstuvwxyz";
  public static final String LEVEL_THREE_POOL = "0123456789abcdefghijklmnopqrstuvwxyz";
  private final Set<String> sessionCipherSet = new HashSet<>();

  private final int difficultyLevel;
  private String currentCipher;
  private String pool;


  public Cipher(int difficultyLevel) throws InvalidLevelException {
    if (difficultyLevel != 1 && difficultyLevel != 2 && difficultyLevel != 3) {
      throw new InvalidLevelException("Must choose Levels 1, 2, or 3.");
    }
    this.difficultyLevel = difficultyLevel;
    assignPool();
  }


  private void assignPool() {
    if (difficultyLevel == 1) {
      pool = LEVEL_ONE_POOL;
    } else if (difficultyLevel == 2) {
      pool = LEVEL_TWO_POOL.toLowerCase();
    } else {
      pool = LEVEL_THREE_POOL.toLowerCase();
    }
  }

  /**
   * <p>This method generates the cipher at random from a pool of characters (numbers and English
   * letters).</p>
   */
  public void generateCipher() {

    do {
      this.currentCipher = getRandomCharacter() + getRandomCharacter() +
          getRandomCharacter() + getRandomCharacter() + getRandomCharacter();
    }
    while (!sessionCipherSet.add(currentCipher));
  }


  /**
   * <p> The method gets the current cipher. This is what the code is for the current game that
   * needs to be guessed by user.</p>
   *
   * @return currentCipher
   */
  public String getCurrentCipher() {
    return currentCipher;
  }


  /**
   * <p> The method gets the pool of characters used to generate the current game cipher.</p>
   *
   * @return pool
   */
  public String getPool() {
    return pool;
  }

  private String getRandomCharacter() {
    Random rnd = new Random();
    return Character.toString(pool.toCharArray()[rnd.nextInt(pool.length())]);
  }
}
