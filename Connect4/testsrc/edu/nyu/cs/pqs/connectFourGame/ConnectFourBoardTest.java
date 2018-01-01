package edu.nyu.cs.pqs.connectFourGame;

import static org.junit.Assert.*;

import java.util.logging.Logger;
import org.junit.Test;

import edu.nyu.cs.pqs.connectFourGame.ConnectFourBoard;

public class ConnectFourBoardTest {
  private static final Logger logger = 
      Logger.getLogger("ConnectFourTest.ConnectFourBoardUtilTest");

  @Test
  public void test() {
    ConnectFourBoard board = new ConnectFourBoard(7, 6);
    assertTrue(board.getColumns() == 7);
    assertTrue(board.getRows() == 6);
    for (int i = 0 ; i < 7; i++) {
      for(int j = 0; j < 6; j++) {
        assertTrue(board.getPlayer(i, j) == -1);
      }
    }
    assertTrue(board.hasEmptyCells());

    int player1 = 1;
    int player2 = 2;
    int x = 0;
    int y =  board.checkAvailableYatX(x);
    board.setGrid(x, y, player1);
    assertEquals(board.getPlayer(x, y), player1);

    // Test can't set a (x, y) to -1 because we reserve -1 as empty cell 
    try {
      board.setGrid(1, board.checkAvailableYatX(1), -1);
    } catch (Exception e) {
      logger.info(e.toString());
    }
    assertTrue(board.getPlayer(1, board.checkAvailableYatX(1)) == -1);

    // Test can't set (x, y) that is out of board size
    try {
      board.setGrid(-1, -1, player2);
    } catch (Exception e) {
      logger.info(e.toString());
    }

    //Fill up all the cells and check if no empty cells
    for (int i = 0 ; i < 7; i++) {
      for (int j = 0; j < 6; j++) {
        if (board.getPlayer(i, j) == -1) {
          board.setGrid(i, j, player2);
        }
      }
    }
    assertFalse(board.hasEmptyCells());
    assertEquals(board.checkAvailableYatX(3), -1);
    logger.info("ConnectFourBoardUtilTest is complete.");
  }
}
