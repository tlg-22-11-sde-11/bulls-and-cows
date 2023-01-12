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

/**
 * <strong>SessionController</strong> class controls input and output for the entire session
 * and handles exceptions that occur before the game is initialized.
 */

public class SessionController {

  private final PrintStream output;
  private final BufferedReader input;
  private Cipher cipher;

  public SessionController(PrintStream output, BufferedReader input) {
    this.output = output;
    this.input = input;
  }

  /**
   * <p> This method initializes requirements for the game to run and displays the
   * String representation of the Session View and Game View. Allows user to play as many times as
   * desired. </p>
   * @throws IOException Unable to read user input; unable to continue
   */

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
      Game game = new Game(cipher, stats);
      Guess guess = new Guess(cipher);
      GameView gameView = new GameView();
      GameController gameController = new GameController(game, guess, cipher, gameView, output,
          this.input);
      gameController.playGame();
      output.print(view.getStats());
    } while (continuePlay());

  }

  private boolean continuePlay() throws IOException {
    output.print("\nDo you want to play again? (Y/n):");
    String input = this.input.readLine().strip().toLowerCase();
    boolean playAgain = input.isEmpty() || input.charAt(0) != 'n';
    if (!playAgain) {
      output.print(
          "\nYour choice to not play again means you have given up your life of crime. Good luck! \n"
              + "If you change your mind, come back and play Crack the Code!");
    }
    return playAgain;
  }

  private void chooseDifficulty() throws IOException {
    int difficultyLevel;
    String input = null;
    do {
      output.println("\nChoose Difficulty Level (1, 2, or 3): ");
      try {
        input = this.input.readLine().strip();
        difficultyLevel = Integer.parseInt(input);
        cipher = new Cipher(difficultyLevel);
      } catch (InvalidLevelException | NumberFormatException e) {
        output.printf("Invalid Input: %s%nLevel must be '1', '2', or '3' \n ", input);
      }
    } while (cipher == null);
  }
}

