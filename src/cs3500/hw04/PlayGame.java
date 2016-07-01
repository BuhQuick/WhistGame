package cs3500.hw04;

import java.io.InputStreamReader;

import cs3500.hw03.CardGameModel;
import cs3500.hw03.IWhistController;
import cs3500.hw03.WhistController;

/**
 * Created by nbuqu on 6/3/2016.
 */
public class PlayGame {
  public static void main(String[] args) {
    IWhistController controller = new WhistController(new InputStreamReader(System.in), System.out);
    CardGameModel<?> model = WhistModelCreator.create(WhistModelCreator.ModelType.TRUMP);
    controller.playGame(model, 3);
  }
}
