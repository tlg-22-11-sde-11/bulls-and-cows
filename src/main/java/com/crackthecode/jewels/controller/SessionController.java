package com.crackthecode.jewels.controller;

import com.crackthecode.jewels.model.Cipher;
import com.crackthecode.jewels.model.Game;
import com.crackthecode.jewels.model.Guess;
import com.crackthecode.jewels.model.StatisticsManager;
import com.crackthecode.jewels.model.exceptions.InvalidLevelException;
import com.crackthecode.jewels.view.GameView;
import com.crackthecode.jewels.view.SessionView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class SessionController {

  private final PrintStream output;
  private final BufferedReader input;
  private Cipher cipher;

  public SessionController(PrintStream output, BufferedReader input) {
    this.output = output;
    this.input = input;
  }

  public void run() throws IOException {
    StatisticsManager stats = new StatisticsManager();
    SessionView view = new SessionView(stats);
    output.print(SessionView.GAME_STORY);
    this.input.readLine();
    output.printf(SessionView.GAME_RULES, Game.MAX_NUMBER_OF_TRIES, Guess.GUESS_LENGTH,
        Cipher.LEVEL_ONE_POOL, Cipher.LEVEL_TWO_POOL, Cipher.LEVEL_THREE_POOL);
    do {
      chooseDifficulty();
      cipher.generateCipher();
      //DONE: new game
      Game game = new Game(cipher, stats);
      //DONE: new guess
      Guess guess = new Guess(cipher); 
      //DONE: new game view
      GameView gameView= new GameView();
      //DONE:  new game controller
      GameController gameController = new GameController(game, guess, cipher, gameView, output, this.input);
      gameController.playGame();
      output.print(view.getStats());
    }while (continuePlay());

  }
  private boolean continuePlay() throws IOException{
    output.print("\nDo you want to play again? (Y/n):");
    String input = this.input.readLine().strip().toLowerCase();
    boolean playAgain = input.isEmpty() || input.charAt(0) != 'n';
    if (!playAgain) {
      output.print("Your choice to not play again means you have given up your life of crime. Good luck! \n"
          + "If you change your mind, come back and play Crack the Code!");
    }
    return playAgain;
  }

  private void chooseDifficulty() throws IOException{
    output.println("\nChoose Difficulty Level (1, 2, or 3): ");
    try {
      String input = this.input.readLine().strip();
      int difficultyLevel = Integer.parseInt(input);
      cipher = new Cipher(difficultyLevel);
    } catch (InvalidLevelException e) {
      output.printf(" Invalid Input: %s%n  Level must be '1', '2', or '3' \n ", input);
    }
  }
}

