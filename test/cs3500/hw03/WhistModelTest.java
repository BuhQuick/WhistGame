package cs3500.hw03;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import cs3500.hw02.Card;

import static org.junit.Assert.*;

/**
 * This Class is used to test the functionality of the WhistModel
 */
public class WhistModelTest{

  CardGameModel<Card> game = new WhistModel();

  @Test
  public void testPlayForTrickFunctionality() {
    this.game.startPlay(2, this.game.getDeck());
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n", this.game.getGameState());
    this.game.play(0, 0);
    assertEquals("Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n", this.game.getGameState());
    this.game.play(1, 4);
    assertEquals("Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥," +
            " 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n", this.game.getGameState());
    this.game.play(0, 5);
    assertEquals("Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥," +
            " 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n", this.game.getGameState());
    this.game.play(1, 0);
    assertEquals("Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, " +
            "6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: J♣, 9♣, 7♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥," +
            " 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 1\n" +
            "Turn: Player 2\n", this.game.getGameState());
  }

  @Test
  public void testPlayForWinGame() {
    this.game.startPlay(2, this.game.getDeck());
    for (int i = 0; i < 52; i += 1) {
      this.game.play(i % 2, 0);
    }
    assertEquals("Number of players: 2\n" +
            "Player 1\n" +
            "Player 2\n" +
            "Player 1 score: 26\n" +
            "Player 2 score: 0\n" +
            "Game over. Player 1 won.", this.game.getGameState());
    this.game.startPlay(3, this.game.getDeck());
    while(!this.game.isGameOver()) {
      this.game.play(this.game.getCurrentPlayer(), 0);
    }
    assertEquals("Number of players: 3\n" +
            "Player 1: 2♠\n" +
            "Player 2\n" +
            "Player 3\n" +
            "Player 1 score: 17\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Game over. Player 1 won.", this.game.getGameState());
    List<Card> revDeck = this.game.getDeck();
    Collections.reverse(revDeck);
    this.game.startPlay(2, revDeck);
    for (int n = 0; n < 52; n += 1) {
      this.game.play(this.game.getCurrentPlayer(), 0);
    }
    assertEquals("Number of players: 2\n" +
            "Player 1\n" +
            "Player 2\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 26\n" +
            "Game over. Player 2 won.", this.game.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayForWrongTurn() {
    this.game.startPlay(2, this.game.getDeck());
    this.game.play(1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayForInvalidPlay() {
    this.game.startPlay(2, this.game.getDeck());
    this.game.play(0, 0);
    this.game.play(1, 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayForInvalidPlayer() {
    this.game.startPlay(2, this.game.getDeck());
    this.game.play(3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayForInvalidCardIndex() {
    this.game.startPlay(2, this.game.getDeck());
    this.game.play(0, 400);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayForPlayingAfterGameIsOver() {
    this.game.startPlay(2, this.game.getDeck());
    for (int i = 0; i < 52; i += 1) {
      this.game.play(i % 2, 0);
    }
    this.game.play(0, 0);
  }

  @Test
  public void testCurrentPlayer() {
    this.game.startPlay(3, this.game.getDeck());
    assertEquals(0, this.game.getCurrentPlayer());
    this.game.play(0, 1);
    assertEquals(1, this.game.getCurrentPlayer());
    this.game.play(1, 0);
    assertEquals(2, this.game.getCurrentPlayer());
    this.game.play(2, 1);
    assertEquals(1, this.game.getCurrentPlayer());
  }

  @Test
  public void testGameOver() {
    this.game.startPlay(2, this.game.getDeck());
    assertEquals(false, this.game.isGameOver());
    for (int i = 0; i < 51; i += 1) {
      this.game.play(i % 2, 0);
    }
    assertEquals(false, this.game.isGameOver());
    this.game.play(1, 0);
    assertEquals(true, this.game.isGameOver());
  }
}