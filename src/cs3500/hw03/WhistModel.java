package cs3500.hw03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.GenericStandardDeckGame;

/**
 * ***************************************** UPDATES *****************************************
 * Allowed for abstraction with get / set methods for certain fields.
 * Improved readability in play class by pulling out a common pattern into its own private method.
 * ***************************************** UPDATES *****************************************
 *
 * This class represents a game of Whist.
 * The game is played in rounds of tricks, and the winner of each trick gets one point.
 */
public class WhistModel extends GenericStandardDeckGame implements CardGameModel<Card> {

  private List<Card> deck;
  private List<List<Card>> players;
  private List<Card> trick;
  private HashMap<Card, Integer> playedBy;
  private HashMap<Integer, Integer> scores;
  private int trickSuit;
  private int currentTurn;

  /**
   * Constructs a Whist game by calling the super constructor and initializing values for later
   * use
   *
   * @param deck the deck to be used for the game
   */
  public WhistModel(List<Card> deck) {
    super(deck);
    this.trick = new ArrayList<Card>();
    this.playedBy = new HashMap<>();
    this.scores = new HashMap<>();
    this.currentTurn = 0;
  }

  /**
   * Constructs a Whist game by calling the super constructor and initializing values for later
   * use
   */
  public WhistModel() {
    super();
    this.trick = new ArrayList<Card>();
    this.playedBy = new HashMap<>();
    this.scores = new HashMap<>();
    this.currentTurn = 0;
  }

  /**
   * EFFECT: sets the score of each player to 0
   */
  private void initScores() {
    for (int n = 0; n < this.players.size(); n += 1) {
      this.scores.put(n, 0);
    }
  }


  /**
   * determines if a play from the given player is valid. if the player has a card of the current
   * trick suit, they must play that card. otherwise, they may play whatever card they please.
   *
   * @param playerNo the player who is trying to take a turn
   * @param cardIdx  the card they are trying to play from their hand
   * @return whether this is a valid play for the current trick
   */
  private boolean validPlay(int playerNo, int cardIdx) {
    if (this.trickSuit == this.players.get(playerNo).get(cardIdx).getSuit()) {
      return true;
    } else {
      for (Card c : this.players.get(playerNo)) {
        if (c.getSuit() == this.trickSuit) {
          return false;
        }
      }
      return true;
    }

  }

  // UPDATE : added to increase readability of play method, recurring code abstracted to it's own
  // method
  // EFFECT : increments the current player by one, or sets the current player to 0
  // if a full round of plays has been made.
  private void incrementCurrentPlayer() {
    if (this.currentTurn == this.players.size() - 1) {
      this.setCurrentPlayer(0);
    } else {
      this.setCurrentPlayer(this.currentTurn + 1);
    }
  }

  // UPDATE HW04: IllegalArgumentException messages have been updated to be more meaningful
  @Override
  public void play(int playerNo, int cardIdx) {
    if (isGameOver()) {
      throw new IllegalArgumentException("The game is over, no play can be made!");
    }

    if (playerNo < 0 || playerNo >= this.players.size()) {
      throw new IllegalArgumentException("There is no such player" + (playerNo + 1) +
              "in this game!");
    }

    if (this.players.get(playerNo).size() == 0) {
      this.incrementCurrentPlayer();
      return;
    }

    if (cardIdx < 0 || cardIdx >= this.players.get(playerNo).size()) {
      throw new IllegalArgumentException("The play:\nPlayer " + (playerNo + 1) + " plays card " +
              cardIdx + " in his hand is invalid! " + cardIdx + " is not a valid card index.");
    }

    if (playerNo != this.getCurrentPlayer()) {
      throw new IllegalArgumentException("Wait your turn to play, player " + playerNo);
    }


    Card addToTrick;
    if (this.trick.size() == 0) {
      addToTrick = this.players.get(playerNo).remove(cardIdx);
      this.trick.add(addToTrick);
      this.playedBy.put(addToTrick, playerNo);
      this.trickSuit = this.trick.get(0).getSuit();
      this.incrementCurrentPlayer();
    }
    else if (!validPlay(playerNo, cardIdx)) {
      throw new IllegalArgumentException("The play:\nPlayer " + (playerNo + 1) + " plays card " +
              cardIdx + " in his hand is invalid!");
    }
    else {
      addToTrick = this.players.get(playerNo).remove(cardIdx);
      this.trick.add(addToTrick);
      this.playedBy.put(addToTrick, playerNo);
      this.incrementCurrentPlayer();
      if (this.trick.size() == this.players.size()) {
        this.endTrick();
      }
    }
  }

  /**
   * UPDATE : made protected over private for inheritance
   * determines the winner of the trick
   * EFFECT: increments the winner's score by 1,
   * empties the trick, has winner start the next trick.
   */
  protected void endTrick() {
    Card winner;
    List<Card> candidates = new ArrayList<Card>();
    int highestValue = 0;
    for (int n = 0; n < this.trick.size(); n += 1) {
      if (this.trick.get(n).getSuit() == this.trickSuit) {
        candidates.add(this.trick.get(n));
      }
    }
    winner = candidates.get(0);
    for (int n = 0; n < candidates.size(); n += 1) {
      if (candidates.get(n).getValue() > highestValue) {
        highestValue = candidates.get(n).getValue();
        winner = candidates.get(n);
      }
    }
    int curScore = this.scores.get(this.playedBy.get(winner));
    this.scores.put(this.playedBy.get(winner), (curScore + 1));
    this.trick = new ArrayList<Card>();
    this.setCurrentPlayer(this.playedBy.get(winner));
  }


  @Override
  public void startPlay(int numPlayers, List<Card> deck) {
    if (numPlayers <= 1) {
      throw new IllegalArgumentException("There are not enough players to start a game!");
    }
    super.startPlay(numPlayers, deck);
    this.players = super.getPlayers();
    this.setCurrentPlayer(0);
    this.initScores();
  }

  @Override
  public int getCurrentPlayer() {
    if (isGameOver()) {
      throw new IllegalStateException("The game has ended, no player is taking a turn");
    }
    return this.currentTurn;
  }

  @Override
  public boolean isGameOver() {
    int n = 0;
    for (List<Card> hand : this.players) {
      if (hand.size() > 0) {
        n += 1;
      }
    }

    if (!trickInProgress()) {
      return n < 2;
    } else {
      return n < 1;
    }
  }

  private boolean trickInProgress() {
    return this.trick.size() != 0;
  }

  private HashMap<Integer, Boolean> getWinners() {
    int victor = 0;
    HashMap<Integer, Boolean> victors = new HashMap<>();
    victors.put(victor, true);
    for (int n = 1; n < this.players.size(); n += 1) {
      if (this.scores.get(n) > this.scores.get(victor)) {
        victors.put(victor, false);
        victors.put(n, true);
        victor = n;
      } else if (this.scores.get(n) == this.scores.get(victor)) {
        victors.put(n, true);
      } else {
        victors.put(n, false);
      }
    }
    return victors;
  }

  @Override
  public String getGameState() {
    String gameState = super.getGameState();
    for (int n = 0; n < this.players.size(); n += 1) {
      gameState += "Player " + (n + 1) + " score: " + this.scores.get(n) + "\n";
    }
    if (isGameOver()) {
      HashMap<Integer, Boolean> victors = this.getWinners();
      gameState += "Game over. ";
      for (int k = 0; k < this.players.size(); k += 1) {
        if (victors.get(k)) {
          gameState += "Player " + (k + 1) + ", ";
        }
      }
      gameState = gameState.substring(0, gameState.length() - 2);
      gameState += " won.";
    } else {
      gameState += "Turn: Player " + (this.currentTurn + 1) + "\n";
    }
    return gameState;
  }

  // UPDATE : added getTrick method to improve abstraction
  // returns the list of cards in the current trick
  protected List<Card> getTrick() {
    return this.trick;
  }

  // UPDATE : added getTrickSuit method to improve abstraction
  // returns the current trick suit
  protected int getTrickSuit() {
    return this.trickSuit;
  }

  // UPDATE : added setTrickSuit method to improve abstraction
  // sets the current trick suit to a new suit
  protected void setTrickSuit(int suit) {
    this.trickSuit = suit;
  }

  // UPDATE : added setCurrentPlayer method to improve abstraction
  // sets the current player to a new player
  protected void setCurrentPlayer(int player) {
    if (player > this.players.size() || player < 0) {
      throw new IllegalArgumentException("Can not set the current player as desired, " +
              "invalid value.");
    }
    else { this.currentTurn = player; }
  }
}
