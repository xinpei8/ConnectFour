package ConnectFourTest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Test;

import edu.nyu.cs.pqs.connectFourGame.ConnectFourModel;
import edu.nyu.cs.pqs.connectFourGame.ConnectFourView;

public class ConnectFourModelComputeMoveTest {
  private static final Logger logger = 
      Logger.getLogger("ConnectFourTest.ConnectFourModelUtilTest");

  @Test
  public void testComputerMove() {
    ConnectFourModel model = ConnectFourModel.getInstance(true);
    new ConnectFourView(model);
    assertFalse(model.getGameOver());
    while (!model.getGameOver()) {
      for (int i = 0; i < model.getColumns(); i++) {
        if (model.checkAvailableYatX(i) != -1) {
          model.setGrid(i, model.checkAvailableYatX(i));
          break;
        }
      }
	    
    }
    assertTrue(model.getGameOver());
    logger.info("ConnectFourModelComputeMoveTest testComputerMove is complete.");
  }
}
