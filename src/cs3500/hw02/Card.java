package cs3500.hw02;

import java.util.Objects;

/**
 * Representation of a standard playing card that has a suit and a value
 */
public class Card implements Comparable<Card> {
  private Suit suit;
  private Value value;

  /**
   * Constructs a Card object that has a suit and a value
   *
   * @param suit  The suit of the card
   * @param value The value of the card
   */
  Card(Suit suit, Value value) {
    this.suit = suit;
    this.value = value;
  }

  // return the ordinal of this card's suit
  // EDIT: DID NOT EXIST IN HW02
  public int getSuit() { return this.suit.getValue(); }

  // return the ordinal of this card's value
  // EDIT: DID NOT EXIST IN HW02
  public int getValue() { return this.value.getValue(); }

  @Override
  public int compareTo(Card c) {
    if (this.suit.getValue() == c.suit.getValue()) {
      return (c.value.getValue() - this.value.getValue());
    }
    else {
      return (this.suit.getValue() - c.suit.getValue());
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) { return true; }

    if (o instanceof Card) {
      Card c = (Card) o;
      return this.suit.getValue() == c.suit.getValue()
              && this.value.getValue() == c.value.getValue();
    }

    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.suit, this.value);
  }

  @Override
  public String toString() {
    return this.value.toString() + this.suit.toString();
  }
}


/**
 * An enumeration of values for the standard playing card
 */
enum Value {
  Ace(14), King(13), Queen(12), Jack(11), Ten(10), Nine(9), Eight(8),
  Seven(7), Six(6), Five(5), Four(4), Three(3), Two(2);

  private final int value; // The worth of this card

  /**
   * Constructs a Value with the given value
   * @param val The value
   */
  Value(int val) { this.value = val; }

  int getValue() { return this.value; }

  @Override
  public String toString() {
    switch(this.value) {
      case 14: return "A";
      case 13: return "K";
      case 12: return "Q";
      case 11: return "J";
      default: return Integer.toString(this.value);
    }
  }
}

/**
 * An enumeration of the possible suits a card can have
 */
enum Suit {
  Clubs, Diamonds, Hearts, Spades;

  int getValue() { return this.ordinal(); }

  @Override
  public String toString() {
    switch (ordinal()) {
      case 0:
        return "♣";
      case 1:
        return "♦";
      case 2:
        return "♥";
      default:
        return "♠";
    }
  }
}

