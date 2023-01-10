package com.crackthecode.jewels.view;

import com.crackthecode.jewels.model.Game;
import com.crackthecode.jewels.model.Guess;
import java.util.ArrayList;
import java.util.List;

public class GameView {

  public static final String RUBY_AND_PEARL_DEFINITION = "A ruby is awarded for each correct character in the correct position. %n" +
  "A pearl is awarded for each correct character in the wrong position.";

  public String getGameBoard(Guess guess, Game game) {
    StringBuilder builder = new StringBuilder();
    List<String> guesses = new ArrayList<>(guess.getGuessSet());
    builder.append(RUBY_AND_PEARL_DEFINITION)
            .append("%n")
            .append("Guess Rubies Pearls %n");
    for(int i=0; i < Game.MAX_NUMBER_OF_TRIES; i++) {
      builder.append(guesses.get(i))
          .append("   ")
          .append(game.getRuby())
          .append("       ")
          .append(game.getPearl())
          .append("%n");
    }
    builder.append("Remaining guesses: ")
        .append(Game.MAX_NUMBER_OF_TRIES - guess.getGuessSet().size())
        .append("%n");
    return builder.toString();
  }

}
