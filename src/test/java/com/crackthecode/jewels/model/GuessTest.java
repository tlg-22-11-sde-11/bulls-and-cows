package com.crackthecode.jewels.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.crackthecode.jewels.model.exceptions.DuplicateGuessException;
import com.crackthecode.jewels.model.exceptions.InvalidGuessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GuessTest {

  private Guess guess1;
  private Guess guess2;
  private Guess guess3;

  @BeforeEach
  public void setUp() {
    guess1= new Guess(new Cipher(1));
    guess2= new Guess(new Cipher(2));
    guess3= new Guess(new Cipher(3));
  }

  @Test
  void guess_level1_validInput() {
    guess1.setCurrentGuess("01235");
    assertEquals("01235", guess1.getCurrentGuess());
  }

  @Test
  void guess_level2_validInput() {
    guess2.setCurrentGuess("qwert");
    assertEquals("qwert", guess2.getCurrentGuess());
  }

  @Test
  void guess_level3_validInput() {
    guess3.setCurrentGuess("01f3q");
    assertEquals("01f3q", guess3.getCurrentGuess());
  }

  @Test
  void guess_level1_invalidCharacter() {
    assertThrows(InvalidGuessException.class,() -> guess1.setCurrentGuess("0123q"));
  }

  @Test
  void guess_level2_invalidCharacter() {
    assertThrows(InvalidGuessException.class,() -> guess2.setCurrentGuess("0123q"));
  }

  @Test
  void guess_level3_invalidCharacter() {
    assertThrows(InvalidGuessException.class,() -> guess3.setCurrentGuess("01$3q"));
  }

  @Test
  void guess_level1_wrongGuessLength_short() {
    assertThrows(InvalidGuessException.class,() -> guess1.setCurrentGuess("0123"));
  }

  @Test
  void guess_level1_wrongGuessLength_long() {
    assertThrows(InvalidGuessException.class,() -> guess1.setCurrentGuess("012345"));
  }

  @Test
  void guess_level2_wrongGuessLength_short() {
    assertThrows(InvalidGuessException.class,() -> guess2.setCurrentGuess("qwer"));
  }

  @Test
  void guess_level2_wrongGuessLength_long() {
    assertThrows(InvalidGuessException.class,() -> guess2.setCurrentGuess("qwerty"));
  }

  @Test
  void guess_level3_wrongGuessLength_short() {
    assertThrows(InvalidGuessException.class,() -> guess3.setCurrentGuess("01wu"));
  }

  @Test
  void guess_level3_wrongGuessLength_long() {
    assertThrows(InvalidGuessException.class,() -> guess3.setCurrentGuess("01pl61"));
  }

  @Test
  void guess_level1_duplicateGuess() {
    guess1.setCurrentGuess("01234");
    assertThrows(DuplicateGuessException.class,() -> guess1.setCurrentGuess("01234"));
  }

  @Test
  void guess_level2_duplicateGuess() {
    guess2.setCurrentGuess("qwert");
    assertThrows(DuplicateGuessException.class,() -> guess2.setCurrentGuess("qwert"));
  }

  @Test
  void guess_level3_duplicateGuess() {
    guess3.setCurrentGuess("01m3q");
    assertThrows(DuplicateGuessException.class,() -> guess3.setCurrentGuess("01m3q"));
  }


}
