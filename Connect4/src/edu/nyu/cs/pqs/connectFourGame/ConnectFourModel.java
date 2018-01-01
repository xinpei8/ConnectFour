package edu.nyu.cs.pqs.connectFourGame;

import java.util.Random;

/**
 * Game model of Connect Four. This is a Singleton class. The game is played by two players who 
 * alternate dropping chips into a 7-column, 6-row grid.  The first player to get 4 in a row wins. 
 * An application can setup to play with a computer opponent by passing a boolean when 
 * getting this game model instance
 * @author xinpeilin
 */
public class ConnectFourModel {
  private static ConnectFourModel instance = null;
  private static final int columns = 7;
  private static final int rows = 6;
  private int winner = -1;// draw:-1, player0:0, player1:1
  private boolean gameOver = false;
  private boolean playWithComputer;
  private ConnectFourBoard board;
  private ConnectFourListener listener;
  private int currentPlayer;
  private Random rand = new Random();

  private ConnectFourModel(){}

  /**
   * Get the only instance of ConnectFourModel object. 
   * Constructor is only called when instance is null.
   * @param playWithComputer setup to play with a computer opponent
   * @return The only instance of ConnectFourModel object
   */
  public static synchronized ConnectFourModel getInstance(boolean playWithComputer) {
      if (instance == null) {
          instance = new ConnectFourModel();
          instance.board = new ConnectFourBoard(columns, rows);
          instance.listener = null;
          instance.currentPlayer = 0;
          instance.playWithComputer = playWithComputer;
      }
      return instance;
  }
  /**
   * Start the game and signal GUI. Must register a ConnectFourListener object first.
   * @throws IllegalAccessException Must register a ConnectFourListener object first.
   */
  public void startGame() throws IllegalAccessException {
    if (listener == null) {
      throw new IllegalAccessException
          ("Must register a ConnectFourListener before start the game.");
    }
    listener.gameStarted();
  }
  /**
   * Observer patterns for listener to register itself 
   * @param listener GUI of the game
   */
  public void registerListener(ConnectFourListener listener) {
    this.listener = listener;
  }
  /**
   * Check the available y given x on board. if not available return -1
   * @param x horizontal location on the board
   * @return the available y location given x on board. if not available return -1
   */
  public int checkAvailableYatX(int x) {
    return board.checkAvailableYatX(x);
  }
  /**
   * Set the (x, y) on board as the current player move
   * @param x horizontal location on the board
   * @param y vertical location on the board
   */
  public void setGrid(int x, int y) {
    board.setGrid(x, y, currentPlayer);
    if (playerWinAfterMove(x, y, currentPlayer)) {
      winner = currentPlayer;
      gameOver = true;
      listener.playerWon();
    }
    else if (!board.hasEmptyCells()) {
      gameOver = true;
      listener.drawGame();
    }
    else {
      currentPlayer = (currentPlayer + 1 ) % 2;
      listener.changePlayer();
      if (currentPlayer == 1 && playWithComputer){
        computerMove();
      }
    }
  }
  /**
   * Computer opponent makes a move. It looks ahead a single move and makes that move 
   * if it results in a win. Otherwise, it makes a random move. 
   */
  private void computerMove() {
    int bestX = -1;
    int bestY = -1;

    /* Find if there is a winning move */
    for (int x = 0; x < board.getColumns(); x++) {
      bestY = checkAvailableYatX(x);
      if (playerWinAfterMove(x, bestY, currentPlayer)) {
        bestX = x;
        break;
      }
    }

    /* if no winning move, move randomly */
    if (bestX == -1) {
      bestY = -1;
      do {
        bestX = rand.nextInt(board.getColumns());
        bestY = checkAvailableYatX(bestX);
      } while (bestY == -1);
    }

    listener.updateComputerMove(bestX, bestY);
  }
  /**
   * Get the column size of the board
   * @return column size of the board
   */
  public int getColumns() {
    return columns;
  }
  /**
   * Get the row size of the board
   * @return row size of the board
   */
  public int getRows() {
    return rows;
  }
  /**
   * Get the currentPlayer
   * @return currentPlayer
   */
  public int getCurrentPlayer() {
    return currentPlayer;
  }
  /**
   * Get the winner
   * @return winner
   */
  public int getWinner() {
    return winner;
  }
  /**
   * Get gameOver
   * @return gameOver
   */
  public boolean getGameOver() {
    return gameOver;
  }

  private final int xDir[] = new int[] {-1, -1, -1, 0,  0,  1, 1, 1};
  
  private final int yDir[] = new int[] {-1,  0,  1, 1, -1, -1, 0, 1};
  
  private boolean playerWinAfterMove(int x, int y, int player) {
    for (int i = 0; i < xDir.length; i++) {
      if (connectFour(x, y, i, player)) {
        return true;
      }
    }
    return false;
  }
  private boolean connectFour(int x, int y, int direction, int player) {
    int count = 1;
    while (x + xDir[direction] >= 0 && x + xDir[direction] < getColumns() &&
           y + yDir[direction] >= 0 && y + yDir[direction] < getRows() &&
           board.getPlayer(x + xDir[direction], y + yDir[direction]) == player) {
      count++;
      if (count == 4) {
        return true;
      }
      x = x + xDir[direction];
      y = y + yDir[direction];
    }
    return false;
  }
}
