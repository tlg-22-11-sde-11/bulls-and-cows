package com.crackthecode.jewels.view;

import com.crackthecode.jewels.model.Game;
import com.crackthecode.jewels.model.Guess;
import java.util.ArrayList;
import java.util.List;

public class GameView {

  public static final String RUBY_AND_PEARL_DEFINITION = "A ruby is awarded when you have chosen a correct character and it is in the correct position.\n"
      + "A pearl is awarded when you have chosen a correct character but it is in the wrong position. \n";

  public String getGameBoard(Guess guess, Game game) {
      StringBuilder builder = new StringBuilder();
      List<String> guesses = new ArrayList<>(guess.getGuessSet());
      builder.append(RUBY_AND_PEARL_DEFINITION)
          .append("\n")
          .append("Guess Rubies Pearls \n");
      for (int i = 0; i < guesses.size(); i++) {
        builder.append(guesses.get(i))
            .append("   ")
            .append(game.getRuby().get(i))
            .append("      ")
            .append(game.getPearl().get(i))
            .append("\n");
      }
      if (game.isCompleted() && game.isWon()) {
        builder.append("You won!\n");
      } else if (game.isCompleted() && !game.isWon()) {
        builder.append("Ran out of guesses! Game over.\n");
      } else {
        builder.append("Remaining guesses: ")
            .append(Game.MAX_NUMBER_OF_TRIES - guess.getGuessSet().size())
            .append("\n");
    }
    return builder.toString();
  }

}
