package cs3500.hw03;

/**
 * This is the interface for a WhistController. It has the ability to play a game of
 * a given CardGameModel, over a certain number of players.
 */
public interface IWhistController {

  /**
   * Sets the gameplay in motion. This method runs through until the game is over.
   * Until then, it handles any plays a user tries to make and takes care of potential
   * exceptions that could be thrown by the game model.
   * @param game The type of game being played through this controller
   * @param numPlayers The number of players playing the game
   * @param <K> The type of card being used for the given game
   */
  <K> void playGame(CardGameModel<K> game, int numPlayers);

}
