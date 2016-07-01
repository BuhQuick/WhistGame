package cs3500.hw04;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw03.WhistModel;

/**
 * This is a class to represent a modified version of the WhistModel.
 * This version overrides certain methods in the WhistModel to ensure the functionality of the
 * "trump" suit. If a card of the trump suit has been played, the winner of that trick is
 * instead determined by the trump suit.
 */
public class WhistTrumpModel extends WhistModel {

  private int trumpSuit;

  /**
   * Calls the super constructor to create a WhistTrumpModel
   */
  public WhistTrumpModel() {
    super();
  }

  /**
   * Calls the super constructor to create a WhistTrumpModel
   */
  public WhistTrumpModel(List<Card> deck) {
    super(deck);
  }

  @Override
  public void startPlay(int numPlayers, List<Card> deck) {
    this.trumpSuit = deck.get(0).getSuit();
    super.startPlay(numPlayers, deck);
  }

  @Override
  public String getGameState() {
    String gameState = super.getGameState();
    String trump;
    switch(this.trumpSuit) {
      case 0:
        trump = "♣";
        break;
      case 1:
        trump = "♦";
        break;
      case 2:
        trump = "♥";
        break;
      default:
        trump = "♠";
    }
    if (isGameOver()) {
      gameState += "\n";
    }
    gameState += "Trump suit: " + trump + "\n";
    return gameState;
  }

  @Override
  protected void endTrick() {
    for (Card c : super.getTrick()) {
      if (c.getSuit() == this.trumpSuit) {
        super.setTrickSuit(this.trumpSuit);
      }
    }
    super.endTrick();
  }
}
