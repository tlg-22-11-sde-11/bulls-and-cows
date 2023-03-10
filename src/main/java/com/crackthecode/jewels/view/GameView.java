package com.crackthecode.jewels.view;

import com.crackthecode.jewels.model.Cipher;
import com.crackthecode.jewels.model.Game;
import com.crackthecode.jewels.model.Guess;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>GameView</strong> class provides the view of the board that player sees.
 */

public class GameView {

  public static final String RUBY_AND_PEARL_DEFINITION =
      "[Reminder: A ruby is awarded when you have chosen a correct character and it is in the correct position.\n"
          + "A pearl is awarded when you have chosen a correct character but it is in the wrong position.] \n";

  /**
   * <p> This method generates the game board. </p>
   *
   * @param guess  the instance of Guess for this current game
   * @param game   the instance of Game for this current game
   * @param cipher the instance of Cipher for this current session
   * @return builder.toString() String representation of the board
   */

  public String getGameBoard(Guess guess, Game game, Cipher cipher) {
    StringBuilder builder = new StringBuilder();
    builder.append(getGuessTable(guess, game));
    if (game.isCompleted() && game.isWon()) {
      builder.append("\n")
          .append("CONGRATULATIONS! You have acquired the rarest jewels known to man! \n");
    } else if (game.isCompleted() && !game.isWon()) {
      builder.append("\n")
          .append(
              "You ran out of guesses and have been caught by the authorities! The correct cipher was ")
          .append(cipher.getCurrentCipher())
          .append(". GAME OVER! \n");
    } else {
      builder.append("\n")
          .append(RUBY_AND_PEARL_DEFINITION)
          .append("\n");
      builder.append("Remaining guesses: ")
          .append(Game.MAX_NUMBER_OF_TRIES - guess.getGuessSet().size())
          .append("\n")
          .append("\n");
    }
    return builder.toString();
  }

  private String getGuessTable(Guess guess, Game game) {
    StringBuilder builder = new StringBuilder();
    List<String> guesses = new ArrayList<>(guess.getGuessSet());
    builder.append("\n")
        .append("Guess Rubies Pearls \n");
    for (int i = 0; i < guesses.size(); i++) {
      builder.append(guesses.get(i))
          .append("   ")
          .append(game.getRuby().get(i))
          .append("      ")
          .append(game.getPearl().get(i))
          .append("\n");
    }
    return builder.toString();
  }

}
