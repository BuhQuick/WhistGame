package cs3500.hw03;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import cs3500.hw04.WhistTrumpModel;

import static org.junit.Assert.*;

/**
 * This Class is used to test the functionality of the WhistController
 */
public class WhistControllerTest {
  InputStream in;
  ByteArrayOutputStream bytes;
  PrintStream out;
  WhistModel game;
  WhistController controller;

  public void initData() {
    in = new ByteArrayInputStream(("0 0 0 0 q").getBytes());
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    game = new WhistModel();
    controller = new WhistController(
            new BufferedReader(new InputStreamReader(in)), out);
  }

  @Test
  public void testWhistControllerPlayGameTwoTricks() {
    this.initData();
    controller.playGame(game, 2);
    assertEquals(false, game.isGameOver());
    assertEquals(0, game.getCurrentPlayer());
    assertEquals("Number of players: 2\n" +
            "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥," +
            " 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 2\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n", game.getGameState());
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥," +
            " 10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, " +
            "10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, " +
            "J♥, 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, " +
            "10♥, 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Number of players: 2\n" +
            "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥," +
            " 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Number of players: 2\n" +
            "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, " +
            "6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, " +
            "5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 2\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Game quit prematurely.", new String(bytes.toByteArray()));
  }

  @Test
  public void testPlayGameWithInvalidPlay() {
    this.initData();
    this.in = new ByteArrayInputStream("0 99 0 0 0 q".getBytes());
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    game = new WhistModel();
    controller = new WhistController(
            new BufferedReader(new InputStreamReader(in)), out);
    this.controller.playGame(this.game, 2);
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, " +
            "8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, " +
            "7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "The play:\n" +
            "Player 2 plays card 99 in his hand is invalid! 99 is not a valid card index.\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Number of players: 2\n" +
            "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥, " +
            "6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Number of players: 2\n" +
            "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥," +
            " 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥," +
            " 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 2\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Game quit prematurely.", new String(this.bytes.toByteArray()));
  }

  @Test
  public void testInvalidInputToController() {
    this.initData();
    this.in = new ByteArrayInputStream("0 n 0 q".getBytes());
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    game = new WhistModel();
    controller = new WhistController(
            new BufferedReader(new InputStreamReader(in)), out);
    this.controller.playGame(this.game, 2);
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥," +
            " 9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Invalid input, try again.\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, " +
            "8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, " +
            "7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Game quit prematurely.", new String(this.bytes.toByteArray()));
  }

  @Test
  public void testVeryInvalidInput() {
    this.initData();
    this.in = new ByteArrayInputStream("0 1nf z0 q".getBytes());
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    game = new WhistModel();
    controller = new WhistController(
            new BufferedReader(new InputStreamReader(in)), out);
    this.controller.playGame(this.game, 2);
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, " +
            "8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, " +
            "7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥," +
            " 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, " +
            "7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Invalid input, try again.\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥," +
            " 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Invalid input, try again.\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥," +
            " 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, " +
            "7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Game quit prematurely.", new String(this.bytes.toByteArray()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameWithInvalidNumberPlayers() {
    this.initData();
    this.in = new ByteArrayInputStream("0 0 q".getBytes());
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    game = new WhistModel();
    controller = new WhistController(
            new BufferedReader(new InputStreamReader(in)), out);
    this.controller.playGame(this.game, -1);
  }

  @Test
  public void testControllerWithNewModel() {
    this.initData();
    this.game = new WhistTrumpModel();
    controller.playGame(game, 2);
    assertEquals("Number of players: 2\n" +
            "Player 1: A♣, Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♣\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, " +
            "9♥, 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Trump suit: ♣\n" +
            "Number of players: 2\n" +
            "Player 1: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥," +
            " 8♥, 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♣\n" +
            "Number of players: 2\n" +
            "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥," +
            " 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: J♣, 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥," +
            " 7♥, 5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 1\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 2\n" +
            "Trump suit: ♣\n" +
            "Number of players: 2\n" +
            "Player 1: 10♣, 8♣, 6♣, 4♣, 2♣, K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♥, Q♥, 10♥, 8♥," +
            " 6♥, 4♥, 2♥, K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "Player 2: 9♣, 7♣, 5♣, 3♣, A♦, Q♦, 10♦, 8♦, 6♦, 4♦, 2♦, K♥, J♥, 9♥, 7♥, " +
            "5♥, 3♥, A♠, Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "Player 1 score: 2\n" +
            "Player 2 score: 0\n" +
            "Turn: Player 1\n" +
            "Trump suit: ♣\n" +
            "Game quit prematurely.", new String(this.bytes.toByteArray()));

  }
}