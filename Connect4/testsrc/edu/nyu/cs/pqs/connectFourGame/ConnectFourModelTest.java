package edu.nyu.cs.pqs.connectFourGame;

import static org.junit.Assert.*;

import java.util.logging.Logger;

//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.connectFourGame.ConnectFourModel;
import edu.nyu.cs.pqs.connectFourGame.ConnectFourView;

public class ConnectFourModelTest {
  private static final Logger logger = 
      Logger.getLogger("ConnectFourTest.ConnectFourModelUtilTest");


  @Test
  public void testBasic() {
    ConnectFourModel model = ConnectFourModel.getInstance(false);

    assertEquals(model.checkAvailableYatX(0), model.checkAvailableYatX(1));

    try {
      model.startGame();
      fail("Exception is not thrown. No registered ConnectFourListener.");
    } catch (Exception e) {
      logger.info(e.toString());
    }
    
    
    new ConnectFourView(model);
    int player1 = model.getCurrentPlayer();
    for (int move = 0; move < 4; move++) {
    	assertFalse(model.getGameOver());
    	assertEquals(player1, model.getCurrentPlayer());
    	model.setGrid(0, model.checkAvailableYatX(0));
    	model.setGrid(1, model.checkAvailableYatX(1));
    }
    assertTrue(model.getGameOver());
    assertEquals(player1, model.getWinner());
    
    logger.info("ConnectFourModelUtilTest testBasic is complete.");
  }
}
