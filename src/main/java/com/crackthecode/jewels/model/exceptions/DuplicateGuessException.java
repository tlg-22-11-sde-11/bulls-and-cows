package com.crackthecode.jewels.model.exceptions;

public class DuplicateGuessException extends IllegalArgumentException{

  //constructor
  public DuplicateGuessException(String message){
    super(message);
  }
}
