package cs3500.hw04;

import org.junit.Test;

import cs3500.hw03.WhistModel;

import static org.junit.Assert.*;

/**
 * Test Class used to ensure the create method is creating an object with the correct
 * implementation of CardGameModel
 */
public class WhistModelCreatorTest {

  @Test
  public void testCreatorConstructor() {
    assertTrue(WhistModelCreator.create(
            WhistModelCreator.ModelType.NOTRUMP) instanceof WhistModel);
    assertTrue(WhistModelCreator.create(
            WhistModelCreator.ModelType.TRUMP) instanceof WhistModel);
    assertTrue(WhistModelCreator.create(
            WhistModelCreator.ModelType.TRUMP) instanceof WhistTrumpModel);
    assertFalse(WhistModelCreator.create(
            WhistModelCreator.ModelType.NOTRUMP) instanceof WhistTrumpModel);
  }
}