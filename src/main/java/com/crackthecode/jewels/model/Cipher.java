package com.crackthecode.jewels.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Cipher {

  //Constants

  public static final String LEVEL_ONE_POOL = "0123456789";
  String LEVEL_TWO_POOL = "abcdefghijklmnopqrstuvwxyz";
  String LEVEL_THREE_POOL = "0123456789abcdefghijklmnopqrstuvwxyz";
  Set<String> sessionCipherSet = new HashSet<>();

  //fields

  int difficultyLevel;
  String currentCipher;
  private String pool;



   // constructors

  public Cipher(int difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
    }

    // methods

  private void assignPool() {
    if (difficultyLevel == 1) {
      pool = LEVEL_ONE_POOL.toLowerCase();
    } else if (difficultyLevel == 2) {
      pool = LEVEL_TWO_POOL.toLowerCase();
    } else {
      pool = LEVEL_THREE_POOL.toLowerCase();
    }
  }

    public void generateCipher() {
      List<Character> characters  = pool
          .chars()
          .mapToObj(c -> (char) c)
          .collect(Collectors.toList());

      Random rng = new Random();
      Collections.shuffle(characters, rng);

      this.currentCipher = characters
          .stream()
          .limit(5)
          .map(Object::toString)
          .collect(Collectors.joining());

      sessionCipherSet.add(currentCipher);
    }

    public boolean checkEquals(Guess guess) {
    return currentCipher.equals(guess.getCurrentGuess());
    }


  // getters and setters


  public String getCurrentCipher() {
    return currentCipher;
  }

  public String getPool() {
    return pool;
  }
}
