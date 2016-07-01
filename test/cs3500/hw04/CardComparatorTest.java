package cs3500.hw04;

import org.junit.Test;


import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw03.WhistModel;

import static org.junit.Assert.*;

/**
 * A class for testing the functionality of the CardComparator
 */
public class CardComparatorTest {
  WhistModel game = new WhistModel();
  List<Card> deck = game.getDeck();
  CardComparator comp = new CardComparator();

  @Test
  public void testCompareSameSuit() {
    assertEquals(0, comp.compare(deck.get(0), deck.get(0)));
    assertEquals(-1, comp.compare(deck.get(0), deck.get(1)));
    assertEquals(-2, comp.compare(deck.get(0), deck.get(2)));
    assertEquals(1, comp.compare(deck.get(1), deck.get(0)));
    assertEquals(2, comp.compare(deck.get(2), deck.get(0)));
    assertEquals(9, comp.compare(deck.get(10), deck.get(1)));
    assertEquals(8, comp.compare(deck.get(10), deck.get(2)));
    assertEquals(-9, comp.compare(deck.get(1), deck.get(10)));
    assertEquals(-8, comp.compare(deck.get(2), deck.get(10)));
  }

  @Test
  public void testCompareDifferentSuit() {
    assertEquals(-3, comp.compare(deck.get(0), deck.get(50)));
    assertEquals(-3, comp.compare(deck.get(0), deck.get(49)));
    assertEquals(3, comp.compare(deck.get(50), deck.get(0)));
    assertEquals(3, comp.compare(deck.get(49), deck.get(0)));

    assertEquals(-2, comp.compare(deck.get(0), deck.get(32)));
    assertEquals(-2, comp.compare(deck.get(0), deck.get(31)));
    assertEquals(2, comp.compare(deck.get(32), deck.get(0)));
    assertEquals(2, comp.compare(deck.get(31), deck.get(0)));

    assertEquals(-1, comp.compare(deck.get(20), deck.get(32)));
    assertEquals(-1, comp.compare(deck.get(20), deck.get(31)));
    assertEquals(1, comp.compare(deck.get(32), deck.get(21)));
    assertEquals(1, comp.compare(deck.get(31), deck.get(21)));
  }
}