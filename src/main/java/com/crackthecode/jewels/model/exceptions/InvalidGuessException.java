package com.crackthecode.jewels.model.exceptions;

public class InvalidGuessException extends IllegalArgumentException{

  //constructor
  public InvalidGuessException(String message){
    super(message);
  }
}
