package cs3500.hw02;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This Class is used to test the functionality of the GenericStandardDeckGame
 */
public class GenericStandardDeckGameTest {
  GenericStandardDeckGame validGame;
  List<Card> emptyDeck;
  List<Card> invalidDeck;
  List<Card> reverseDeck;
  GenericStandardDeckGame invalidGame;

  // initailizes some useful data for testing
  public void initData() {
    this.validGame = new GenericStandardDeckGame();
    this.emptyDeck = new ArrayList<Card>();
    this.invalidDeck = this.validGame.getDeck();
    this.invalidDeck.remove(new Card(Suit.Clubs, Value.Ace)); // remove one valid card
    this.invalidDeck.add(new Card(Suit.Clubs, Value.Five)); // add a duplicate valid card
    this.reverseDeck = this.validGame.getDeck();
    Collections.reverse(this.reverseDeck);
    this.invalidGame = new GenericStandardDeckGame(this.emptyDeck);
  }

  @Test
  public void testGetDeck() {
    this.initData();
    assertNotEquals(this.emptyDeck, this.invalidGame.getDeck());
    assertEquals(this.validGame.getDeck(), this.invalidGame.getDeck());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartPlayForErrorNoPlayers() {
    this.initData();
    this.validGame.startPlay(0, this.validGame.getDeck());
    this.invalidGame.startPlay(0, this.emptyDeck);
    this.invalidGame.startPlay(0, this.invalidGame.getDeck());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartPlayForErrorInvalidDeck() {
    this.initData();
    this.validGame.startPlay(2, this.emptyDeck);
    this.validGame.startPlay(3, this.invalidDeck);
    this.invalidGame.startPlay(4, this.emptyDeck);
    this.invalidGame.startPlay(5, this.invalidDeck);
  }

  @Test
  public void testStartPlayUsingGameState() {
    this.initData();
    assertEquals("Number of players: 0\n", this.validGame.getGameState());
    this.validGame.startPlay(2, this.validGame.getDeck());
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n", this.validGame.getGameState());
    this.validGame.startPlay(2, this.reverseDeck);
    assertEquals("Number of players: 2\n" +
            "Player 1: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 2: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n", this.validGame.getGameState());

    this.validGame.startPlay(5, this.validGame.getDeck());
    assertEquals("Number of players: 5\n" +
            "Player 1: A♣, 9♣, 4♣, Q♦, 7♦, 2♦, 10♥, 5♥, K♠, 8♠, 3♠\n" +
            "Player 2: K♣, 8♣, 3♣, J♦, 6♦, A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
            "Player 3: Q♣, 7♣, 2♣, 10♦, 5♦, K♥, 8♥, 3♥, J♠, 6♠\n" +
            "Player 4: J♣, 6♣, A♦, 9♦, 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n" +
            "Player 5: 10♣, 5♣, K♦, 8♦, 3♦, J♥, 6♥, A♠, 9♠, 4♠\n",
            this.validGame.getGameState());
    this.validGame.startPlay(5, this.reverseDeck);
    assertEquals("Number of players: 5\n" +
                    "Player 1: K♣, 8♣, 3♣, J♦, 6♦, A♥, 9♥, 4♥, Q♠, 7♠, 2♠\n" +
                    "Player 2: A♣, 9♣, 4♣, Q♦, 7♦, 2♦, 10♥, 5♥, K♠, 8♠, 3♠\n" +
                    "Player 3: 10♣, 5♣, K♦, 8♦, 3♦, J♥, 6♥, A♠, 9♠, 4♠\n" +
                    "Player 4: J♣, 6♣, A♦, 9♦, 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n" +
                    "Player 5: Q♣, 7♣, 2♣, 10♦, 5♦, K♥, 8♥, 3♥, J♠, 6♠\n",
            this.validGame.getGameState());
  }

  @Test
  public void testDeckDistributedInOrderAsGiven() {
    this.initData();
    // distribute the cards to only one player to make sure the deck is distributed
    // in the same order it is passed into the method
    this.validGame.startPlay(1, this.validGame.getDeck());
    assertEquals("Number of players: 1\n" +
            "Player 1: A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♦, K♦, Q♦," +
            " J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♥, K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥," +
            " 6♥, 5♥, 4♥, 3♥, 2♥, A♠, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n",
            this.validGame.getGameState());
    this.validGame.startPlay(1, this.reverseDeck);
    assertEquals("Number of players: 1\n" +
            "Player 1: A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♦, " +
            "K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♥, K♥, Q♥, J♥, 10♥, 9♥," +
            " 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♠, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠," +
            " 3♠, 2♠\n", this.validGame.getGameState());
  }

  @Test
  public void testGetGameStateWithErrors() {
    this.initData();
    // empty deck
    try {
      this.validGame.startPlay(2, this.emptyDeck);
    }
    catch(IllegalArgumentException e) {
      assertEquals("Number of players: 0\n" , this.validGame.getGameState());
    }

    // invalid number of players
    try {
      this.validGame.startPlay(0, this.validGame.getDeck());
    }
    catch(IllegalArgumentException e) {
      assertEquals("Number of players: 0\n", this.validGame.getGameState());
    }

    // invalid deck (has copies)
    try {
      this.validGame.startPlay(3, this.invalidDeck);
    }
    catch(IllegalArgumentException e) {
      assertEquals("Number of players: 0\n", this.validGame.getGameState());
    }

    // valid then invalid
    // should maintain the state of the game before the invalid call
    try {
      this.validGame.startPlay(3, this.validGame.getDeck());
      this.validGame.startPlay(0, this.validGame.getDeck());
      this.validGame.startPlay(4, this.reverseDeck);
    }
    catch(IllegalArgumentException e) {
      assertEquals("Number of players: 3\n" +
              "Player 1: A♣, J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦," +
              " K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠\n" +
              "Player 2: K♣, 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, " +
              "2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠\n" +
              "Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, " +
              "J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠\n", this.validGame.getGameState());
    }
  }

}
