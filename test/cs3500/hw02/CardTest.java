package cs3500.hw02;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This Class is used to test all methods pertaining to
 * the Card class, as well as the enumerations it makes use of.
 */
public class CardTest {
  Card sixOfClubs = new Card(Suit.Clubs, Value.Six);
  Card aceOfSpades = new Card(Suit.Spades, Value.Ace);
  Card sixOfSpades = new Card(Suit.Spades, Value.Six);
  Card sixOfSpades2 = new Card(Suit.Spades, Value.Six);

  @Test
  public void testSuitGetValue() {
    assertEquals(0, Suit.Clubs.getValue());
    assertEquals(1, Suit.Diamonds.getValue());
    assertEquals(2, Suit.Hearts.getValue());
    assertEquals(3, Suit.Spades.getValue());
  }

  @Test
  public void testSuitToString() {
    assertEquals("♣", Suit.Clubs.toString());
    assertEquals("♦", Suit.Diamonds.toString());
    assertEquals("♥", Suit.Hearts.toString());
    assertEquals("♠", Suit.Spades.toString());
  }

  @Test
  public void testValueGetValue() {
    int val = 14; // value starts at ace
    for (Value v : Value.values()) {
      assertEquals(val, v.getValue());
      val -= 1;
    }
  }

  @Test
  public void testValueToString() {
    assertEquals("A", Value.Ace.toString());
    assertEquals("K", Value.King.toString());
    assertEquals("Q", Value.Queen.toString());
    assertEquals("J", Value.Jack.toString());
    assertEquals("6", Value.Six.toString());
    assertEquals("2", Value.Two.toString());
    assertEquals("10", Value.Ten.toString());
  }

  @Test
  public void testCompareToSameSuit() {
    assertEquals(0, this.sixOfSpades.compareTo(this.sixOfSpades2));
    assertEquals(8, this.sixOfSpades.compareTo(this.aceOfSpades));
    assertEquals(-8, this.aceOfSpades.compareTo(this.sixOfSpades));
  }

  @Test
  public void testCompareToDifferentSuit() {
    assertEquals(3, this.sixOfSpades.compareTo(this.sixOfClubs));
    assertEquals(-3, this.sixOfClubs.compareTo(this.sixOfSpades));
    assertEquals(-3, this.sixOfClubs.compareTo(this.aceOfSpades));
    assertEquals(3, this.aceOfSpades.compareTo(this.sixOfClubs));
  }

  @Test
  public void testEquals() {
    assertTrue(this.sixOfSpades.equals(this.sixOfSpades2));
    assertTrue(this.sixOfSpades2.equals(this.sixOfSpades));
    assertEquals(false, this.sixOfSpades.equals(this.aceOfSpades));
    assertEquals(false, this.sixOfClubs.equals(this.sixOfSpades));
    assertEquals(false, this.sixOfSpades.equals(this.sixOfClubs));
  }

  @Test
  public void testHashCode() {
    assertTrue(this.sixOfSpades.hashCode() == this.sixOfSpades2.hashCode());
    assertEquals(false, this.sixOfSpades.hashCode() == this.sixOfClubs.hashCode());
    assertEquals(false, this.sixOfSpades.hashCode() == this.aceOfSpades.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("6♠", this.sixOfSpades.toString());
    assertEquals("6♠", this.sixOfSpades2.toString());
    assertEquals("A♠", this.aceOfSpades.toString());
    assertEquals("6♣", this.sixOfClubs.toString());
  }
}
