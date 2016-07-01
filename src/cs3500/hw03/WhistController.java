package cs3500.hw03;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the controller through which a user might play a game of Whist
 * The controller handles any input the player may have, delegating the work to the CardGameModel.
 */
public class WhistController implements IWhistController {
  private Scanner input;
  private Appendable output;

  /**
   * Constructs a WhistController with a means of handling input / output
   * @param rd A readable used to handle user input
   * @param ap An appendable used to write the output of the game in progress
   */
  public WhistController(Readable rd, Appendable ap) {
    this.input = new Scanner(rd);
    this.output = ap;
  }

  @Override
  public <K> void playGame(CardGameModel<K> game, int numPlayers) {
    game.startPlay(numPlayers, game.getDeck());
    while(!game.isGameOver()) {
      try {
        this.output.append(game.getGameState());
      }
      catch (IOException e) {
        System.err.print(e);
      }
      if (this.input.hasNextInt()) {
        int nextPlay = this.input.nextInt();
        try {
          game.play(game.getCurrentPlayer(), nextPlay);
        }
        catch (IllegalArgumentException e) {
          try {
            this.output.append(e.getMessage() + "\n");
          }
          catch (IOException err) {
            System.err.print(err);
          }
        }
      }
      else if (this.input.hasNext()) {
        String playerRequest = this.input.next();
        if (playerRequest.equalsIgnoreCase("q")) {
          try {
            this.output.append("Game quit prematurely.");
            return;
          }
          catch (IOException e) {
            System.err.print(e);
          }
        }
        else {
          try {
            this.output.append("Invalid input, try again.\n");
          }
          catch (IOException e) {
            System.err.print(e);
          }
        }
      }
    }
    try {
      this.output.append(game.getGameState());
    }
    catch (IOException e) {
      System.err.print(e);
    }
  }
}
