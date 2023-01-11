package com.crackthecode.jewels.model;

import static org.junit.jupiter.api.Assertions.*;

import com.crackthecode.jewels.model.exceptions.InvalidLevelException;
import org.junit.jupiter.api.Test;

class CipherTest {

//  @Test
//  void generateCipher() {
//
//  }

//  @Test
//  void getCurrentCipher() {
//  }

  @Test
  public void testAssignPool_LevelOne_poolIsLevelTwoPool() throws InvalidLevelException(){
    Cipher cipher = new Cipher(1);
    cipher.assignPool(); //made assignPool package private from intellij suggestion to access

    assertTrue(cipher.getPool().equals(Cipher.LEVEL_ONE_POOL));
  }

  @Test
  public void testAssignPool_levelTwo_poolIsLevelTwoPool() throws InvalidLevelException {
  Cipher cipher = new Cipher(2);
  cipher.assignPool();//made assignPool package private from intellij suggestion to access

  assertTrue(cipher.getPool().equals(Cipher.LEVEL_TWO_POOL.toLowerCase()));
  }

  @Test
  public void testAssignPool_levelThree_poolisLevelThreePool() throws InvalidLevelException {
    Cipher cipher = new Cipher(3);
    cipher.assignPool();

    assertTrue(cipher.getPool().equals(Cipher.LEVEL_THREE_POOL().toLowerCase));
  }
}