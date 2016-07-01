package cs3500.hw03;

import cs3500.hw02.GenericCardGameModel;

/**
 * This is an interface for the CardGameModel. It extends the GenericCardGameModel,
 * gaining some functionalities while adding on to the list some of it's own.
 * The parameter K refers to the card type being used by the model.
 */
public interface CardGameModel<K> extends GenericCardGameModel<K> {

  /*
   * EFFECT: plays the card at cardIdx in the player playerNo's hand
   * Assumes the player's hand is sorted
   */
  void play(int playerNo, int cardIdx);

  /**
   * returns the player whose turn it is to play
   * throws an exception if the game has ended
   * @return the player for this turn
   */
  int getCurrentPlayer();

  /**
   * determines whether or not the game has ended.
   * A game has ended if less than two people have cards in their hand
   * @return whether the game has ended
   */
  boolean isGameOver();
}
