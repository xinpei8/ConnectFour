package edu.nyu.cs.pqs.connectFourGame;
/**
* A listener object that can be used for observing ConnectFourModel actions.
*
*/
public interface ConnectFourListener {
	
  void playerWon();
  void drawGame();
  void gameStarted();
  void changePlayer();
  void updateComputerMove(int x, int y);
}