package cs3500.hw02;

import java.util.*;

/**
 * This is a class that represents a standard deck game.
 * In order to start playing, this game must have a valid deck
 * and people to play the game
 */
public class GenericStandardDeckGame implements GenericCardGameModel<Card> {

  private List<Card> deck;
  private List<List<Card>> players;

  /**
   * Constructs a game using the deck that is passed in
   * @param deck the deck to be used for the game
   * EDIT: made public as of hw02
   */
  public GenericStandardDeckGame(List<Card> deck) {
    this.deck = deck;
    this.players = new ArrayList<List<Card>>();
  }

  /**
   * Convenience constructor that will create
   * a valid playing deck through the getDeck() method
   * EDIT: made public as of hw02
   */
  public GenericStandardDeckGame() {
    this.deck = this.getDeck();
    this.players = new ArrayList<List<Card>>();
  }


  @Override
  public List<Card> getDeck() {
    ArrayList<Card> buildDeck = new ArrayList<Card>();

    // Constructs in order
    for (Suit s : Suit.values()) {
      for (Value v : Value.values()) {
        buildDeck.add(new Card(s, v));
      }
    }
    return buildDeck;
  }

  public List<List<Card>> getPlayers() {
    return this.players;
  }

  @Override
  public void startPlay(int numPlayers, List<Card> deck) {
    if (numPlayers < 1) {
      throw new IllegalArgumentException("There are not enough players to start a game!");
    }

    else if (!deckIsValid(deck)) {
      throw new IllegalArgumentException("The deck you are trying to use is invalid for a " +
              "standard deck game.");
    }

    else {
      this.deck = deck;
      this.players = new ArrayList<List<Card>>();
      this.initializePlayers(numPlayers);
      distributeCards();
    }
  }

  /**
   * EFFECT: initailizes the size of the players list such that it has
   * the correct number of players (as represented by lists of cards)
   * @param numPlayers the number of players that are playing the game
   */
  private void initializePlayers(int numPlayers) {
    for (int n = 0; n < numPlayers; n += 1) {
      this.players.add(new ArrayList<Card>());
    }
  }

  /**
   * EFFECT: Distributes the cards in the deck to a given number of players
   * in round robin fashion. The cards are then sorted in the players hand
   */
  private void distributeCards() {
    for (int c = 0; c < deck.size(); c += 1) {
      this.players.get(c % this.players.size()).add(deck.get(c));
    }

    for (int n = 0; n < this.players.size(); n += 1) {
      Collections.sort(this.players.get(n));
    }
  }

  @Override
  public String getGameState() {
    String gameState = "Number of players: " + Integer.toString(this.players.size()) + "\n";
    for (int n = 0; n < this.players.size(); n += 1) {
      gameState += "Player " + (n + 1) + ": ";
      for (int i = 0; i < this.players.get(n).size(); i += 1) {
        gameState += this.players.get(n).get(i).toString() + ", ";
      }
      gameState = gameState.substring(0, gameState.length() - 2);
      gameState += "\n";
    }

    return gameState;
  }


  /**
   * Determines if the given deck is a valid playing deck.
   * It must have 52 cards, with no duplicates or invalid cards
   *
   * @param deck the deck that is being tested
   * @return whether or not the deck is valid
   */
  private boolean deckIsValid(List<Card> deck) {
    if (deck.size() != 52) {
      return false;
    }

    else {
      List<Card> deckCopy = new ArrayList<Card>(deck);
      Collections.sort(deckCopy);
      List<Card> validDeck = this.getDeck();
      // Since getDeck builds the deck in sorted order, we do not need to sort it.
      for (int n = 0; n < validDeck.size(); n += 1) {
        if (!validDeck.get(n).equals(deckCopy.get(n))) {
          return false;
        }
      }

      return true;
    }

  }
}