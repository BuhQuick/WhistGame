package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;

/**
 * This is a CardGameModel factory class. It is used to construct unique objects that implement
 * the CardGameModel interface.
 */
public class WhistModelCreator {

  /**
   * Factory method used for creating CardGameModel objects.
   * @param t the specified implementation of CardGameModel represented by an enum
   * @return the desired CardGameModel
   */
  public static CardGameModel<Card> create(ModelType t) {
    if (t.equals(ModelType.TRUMP)) {
      return new WhistTrumpModel();
    }
    else if (t.equals(ModelType.NOTRUMP)) {
      return new WhistModel();
    }
    else {
      throw new IllegalArgumentException("Unsupported ModelType for the WhistModelCreator");
    }
  }

  /**
   * An enum used two represent the supported model types of CardGame. Presently supported are
   * the WhistModel with and without trump.
   */
  public enum ModelType {
    TRUMP, NOTRUMP
  }
}
