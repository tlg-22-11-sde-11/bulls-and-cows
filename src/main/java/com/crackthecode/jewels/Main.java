package com.crackthecode.jewels;

import com.crackthecode.jewels.controller.SessionController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) {
    try {
      new SessionController(System.out, new BufferedReader(new InputStreamReader(System.in))).run();
    } catch (IOException e) {
      System.err.println("Unable to read user input; unable to continue");
    }

  }
}
