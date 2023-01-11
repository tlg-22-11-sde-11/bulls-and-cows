package com.crackthecode.jewels.model;

import com.crackthecode.jewels.model.exceptions.InvalidLevelException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Cipher {

  //Constants

  public static final String LEVEL_ONE_POOL = "0123456789";
  public static final String LEVEL_TWO_POOL = "abcdefghijklmnopqrstuvwxyz";
  public static final String LEVEL_THREE_POOL = "0123456789abcdefghijklmnopqrstuvwxyz";
  private final Set<String> sessionCipherSet = new HashSet<>();

  //fields

  private final int difficultyLevel;
  private String currentCipher;
  private String pool;



   // constructors

  public Cipher(int difficultyLevel) throws InvalidLevelException {
    if (difficultyLevel != 1 && difficultyLevel != 2 && difficultyLevel != 3) {
      throw new InvalidLevelException("Must choose Levels 1, 2, or 3.");
    }
    this.difficultyLevel = difficultyLevel;
    }

    // methods

  private void assignPool() {
    if (difficultyLevel == 1) {
      pool = LEVEL_ONE_POOL;
    } else if (difficultyLevel == 2) {
      pool = LEVEL_TWO_POOL.toLowerCase();
    } else {
      pool = LEVEL_THREE_POOL.toLowerCase();
    }
  }

    public void generateCipher() {

      do {
        this.currentCipher = getRandomCharacter() + getRandomCharacter() +
            getRandomCharacter() + getRandomCharacter() + getRandomCharacter();
      }
      while (!sessionCipherSet.add(currentCipher));
    }


  // getters and setters


  public String getCurrentCipher() {
    return currentCipher;
  }

  public String getPool() {
    return pool;
  }



  // helper methods

  private String getRandomCharacter() {
    Random rnd = new Random();
    return Character.toString(pool.toCharArray()[rnd.nextInt(pool.length())]);
  }
}
