package edu.nyu.cs.pqs.connectFourGame;

/**
 * Board of Connect Four Game
 * @author xinpeilin
 */
public class ConnectFourBoard {
  final private int xSize;
  final private int ySize;
  private int[][] grid;
  private int emptyCells;
  /**
   * Construct a new ConnectFourBoard object. Keep a record of the number of empty cells
   * for checking the result of draw game. For each grid[x][y] on the board is initialized to -1.
   * When players made a move, grid[x][y] will be set to 0 or 1 according to the player value.
   * @param columns column size of the game, must be a positive integer
   * @param rows row size of the game, must be a positive integer
   * @return The new ConnectFourBoard object
   */
  public ConnectFourBoard(int columns, int rows) {
    xSize = columns;
    ySize = rows;
    emptyCells = 0;

    grid = new int[xSize][ySize];
    for (int i = 0; i < xSize; i++) {
      for (int j = 0; j < ySize; j++) {
        grid[i][j] = -1;
        emptyCells++;
      }
    }
  }
  /**
   * Set the (x, y) on board as the given player move. (x, y) must be within the board size.
   * (x, y) must be an empty cell for a move. Player value must not be -1
   * @param x horizontal location on the board, x must be within the board size
   * @param y vertical location on the board, y must be within the board size
   * @param player player who made this move, player can't be -1 
   * @throws IllegalArgumentException check (x, y) and player value are valid
   */
  public void setGrid(int x, int y, int player) throws IllegalArgumentException {
	if(player == -1){
		throw new IllegalArgumentException("player can't be -1");
	}
	if(x < 0 || x >= xSize || y < 0 || y >= ySize){
		throw new IllegalArgumentException("(x, y) must be within the board size");
	}
	if(grid[x][y] != -1){
		throw new IllegalArgumentException("(x, y) is marked. Can't overwrite the move");
	}
    grid[x][y] = player;
    emptyCells--;
  }
  /**
   * Check the available y given x on board. if not available return -1
   * @param x horizontal location on the board
   * @return the available y location given x on board. if not available return -1
   */
  public int checkAvailableYatX(int x) {
    for (int i = ySize-1; i >= 0; --i) {
      if (grid[x][i] == -1) {
        return i;
      }
    }
    return -1;
  }
  /**
   * Check if there is at least one empty cell on board
   * @return if there is at least one empty cell, return true. Otherwise, false.
   */
  public boolean hasEmptyCells() {
    return emptyCells > 0;
  }
  /**
   * Get the column size of the board
   * @return column size of the board
   */
  public int getColumns() {
    return xSize;
  }
  /**
   * Get the row size of the board
   * @return row size of the board
   */
  public int getRows() {
    return ySize;
  }
  /**
   * Get the player move at (x, y) in board
   * @return existed player move at (x, y). Player value can be 0 or 1. 
   * If no existed move, grid[x][y] was initialized as -1
   */
  public int getPlayer(int x, int y) {
    return grid[x][y];
  }
}
