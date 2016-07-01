package cs3500.hw04;

import java.util.Comparator;

import cs3500.hw02.Card;

/**
 * This class is a Comparator of two Objects of type Card. It uses the compareTo method
 * as defined in the Card class.
 */
public class CardComparator implements Comparator<Card> {

  @Override
  public int compare(Card o1, Card o2) {
    return o1.compareTo(o2);
  }
}
