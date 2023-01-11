package com.crackthecode.jewels.model;

import static org.junit.jupiter.api.Assertions.*;

import com.crackthecode.jewels.model.exceptions.InvalidLevelException;
import org.junit.jupiter.api.Test;

class CipherTest {



  //test case for if invalid input with difficultyLevel
  @Test
  public void testInvalidLevel() {
    assertThrows(InvalidLevelException.class, () -> new Cipher(-12));

  }


 // testing for pool level
  @Test
  public void testAssignPool_LevelOne_poolIsLevelOnePool() {
    Cipher cipher = new Cipher(1);

    assertEquals(Cipher.LEVEL_ONE_POOL, cipher.getPool());
  }

  @Test
  public void testAssignPool_levelTwo_poolIsLevelTwoPool()  {
  Cipher cipher = new Cipher(2);

    assertEquals(Cipher.LEVEL_TWO_POOL, cipher.getPool());
  }

  @Test
  public void testAssignPool_levelThree_poolIsLevelThreePool()  {
    Cipher cipher = new Cipher(3);

    assertEquals(Cipher.LEVEL_THREE_POOL, cipher.getPool());
  }
}