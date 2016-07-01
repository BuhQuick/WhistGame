package cs3500.hw04;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistController;
import cs3500.hw03.WhistModel;

import static org.junit.Assert.*;

/**
 * A class for testing the functionality of the WhistTrumpModel implementation.
 */
public class WhistTrumpModelTest {

  @Test
  public void testTrumpSuitDoesntChange() {
    CardGameModel<Card> whistStandardDeck =
            WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);
    whistStandardDeck.startPlay(3, whistStandardDeck.getDeck());
    assertEquals("Number of players: 3\n" +
            "Player 1: A♣, J♣, 8♣, 5♣, 2♣, Q♦, 9♦, 6♦, 3♦, K♥, 10♥, 7♥," +
            " 4♥, A♠, J♠, 8♠, 5♠, 2♠\n" +
            "Player 2: K♣, 10♣, 7♣, 4♣, A♦, J♦, 8♦, 5♦, 2♦, Q♥, 9♥, 6♥," +
            " 3♥, K♠, 10♠, 7♠, 4♠\n" +
            "Player 3: Q♣, 9♣, 6♣, 3♣, K♦, 10♦, 7♦, 4♦, A♥, J♥, 8♥, 5♥, " +
            "2♥, Q♠, 9♠, 6♠, 3♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♣\n", whistStandardDeck.getGameState());
    for(int n = 0; n < 26; n += 1) {
      whistStandardDeck.play(whistStandardDeck.getCurrentPlayer(), 0);
    }
    assertEquals("Number of players: 3\n" +
            "Player 1: K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠\n" +
            "Player 2: Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠\n" +
            "Player 3: A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠\n" +
            "Player 1 score: 8\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Turn: Player 3\n" +
            "Trump suit: ♣\n", whistStandardDeck.getGameState());
    while(!whistStandardDeck.isGameOver()) {
      whistStandardDeck.play(whistStandardDeck.getCurrentPlayer(), 0);
    }
    assertEquals("Number of players: 3\n" +
            "Player 1: 2♠\n" +
            "Player 2\n" +
            "Player 3\n" +
            "Player 1 score: 17\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Game over. Player 1 won.\n" +
            "Trump suit: ♣\n", whistStandardDeck.getGameState());
  }

  @Test
  public void testTrumpSuitAssignment() {
    List<Card> standardDeck = new WhistTrumpModel().getDeck();
    List<Card> heartInFront = new WhistTrumpModel().getDeck();
    List<Card> spadeInFront = new WhistTrumpModel().getDeck();
    List<Card> diamondInFront = new WhistTrumpModel().getDeck();
    Card putToFront;
    for (Card c : heartInFront) {
      if (c.getSuit() == 2) {
        putToFront = c;
        heartInFront.remove(c);
        heartInFront.add(0, putToFront);
        break;
      }
    }

    for (Card c : spadeInFront) {
      if (c.getSuit() == 3) {
        putToFront = c;
        spadeInFront.remove(c);
        spadeInFront.add(0, putToFront);
        break;
      }
    }

    for (Card c : diamondInFront) {
      if (c.getSuit() == 1) {
        putToFront = c;
        diamondInFront.remove(c);
        diamondInFront.add(0, putToFront);
        break;
      }
    }
    CardGameModel<Card> game = new WhistTrumpModel();
    game.startPlay(2, standardDeck);
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥," +
            " Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, " +
            "J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♣\n", game.getGameState());
    game.startPlay(2, heartInFront);
    assertEquals("Number of players: 2\n" +
            "Player 1: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, A♥, Q♥, " +
            "10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♥\n", game.getGameState());
    game.startPlay(2, spadeInFront);
    assertEquals("Number of players: 2\n" +
            "Player 1: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥," +
            " 9♥, 7♥, 5♥, 3♥, A♠, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥," +
            " 10♥, 8♥, 6♥, 4♥, 2♥, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♠\n", game.getGameState());
    game.startPlay(2, diamondInFront);
    assertEquals("Number of players: 2\n" +
            "Player 1: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥," +
            " Q♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥," +
            " J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♦\n", game.getGameState());
  }

  @Test
  public void testPlayingTrumpInTrickGivesPoint() {
    InputStream in;
    ByteArrayOutputStream bytes;
    PrintStream out;
    CardGameModel<Card> game;
    WhistController controller;
    in = new ByteArrayInputStream(("7 7 7 7 7 7 7 7 7 7 7 7 8 7 q").getBytes());
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    game = WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);
    controller = new WhistController(
            new BufferedReader(new InputStreamReader(in)), out);
    controller.playGame(game, 2);
    // no trump card has been played yet
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, A♥, 10♥, 8♥, 6♥, 4♥, " +
            "2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠," +
            " 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 6\n" +
            "Player 2 score: 1\n" +
            "Turn: Player 2\n" +
            "Trump suit: ♣\n", game.getGameState());
    game.play(game.getCurrentPlayer(), 6);
    game.play(game.getCurrentPlayer(), 0);
    // trump card played by player 1
    assertEquals("Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, A♥, 10♥, 8♥, 6♥, 4♥, 2♥, K♠," +
            " J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, " +
            "10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 7\n" +
            "Player 2 score: 1\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♣\n", game.getGameState());
  }

  @Test
  public void testBattleForTrumpPoint() {
    InputStream in;
    ByteArrayOutputStream bytes;
    PrintStream out;
    CardGameModel<Card> game;
    WhistController controller;
    in = new ByteArrayInputStream(("5 5 5 5 5 5 5 5 5 5 4 4 q").getBytes());
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    game = WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);
    controller = new WhistController(
            new BufferedReader(new InputStreamReader(in)), out);
    controller.playGame(game, 3);
    assertEquals("Number of players: 3\n" +
            "Player 1: A♣, J♣, 8♣, 5♣, 2♣, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠\n" +
            "Player 2: K♣, 10♣, 7♣, 4♣, 2♦, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠\n" +
            "Player 3: Q♣, 9♣, 6♣, 3♣, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠\n" +
            "Player 1 score: 3\n" +
            "Player 2 score: 1\n" +
            "Player 3 score: 0\n" +
            "Turn: Player 2\n" +
            "Trump suit: ♣\n", game.getGameState());
    game.play(game.getCurrentPlayer(), 4);
    game.play(game.getCurrentPlayer(), 0);
    game.play(game.getCurrentPlayer(), 0);
    assertEquals("Number of players: 3\n" +
            "Player 1: J♣, 8♣, 5♣, 2♣, K♥, 10♥, 7♥, 4♥, A♠, J♠, 8♠, 5♠, 2♠\n" +
            "Player 2: K♣, 10♣, 7♣, 4♣, Q♥, 9♥, 6♥, 3♥, K♠, 10♠, 7♠, 4♠\n" +
            "Player 3: 9♣, 6♣, 3♣, A♥, J♥, 8♥, 5♥, 2♥, Q♠, 9♠, 6♠, 3♠\n" +
            "Player 1 score: 4\n" +
            "Player 2 score: 1\n" +
            "Player 3 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♣\n", game.getGameState());
  }
}