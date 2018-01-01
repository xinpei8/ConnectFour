package edu.nyu.cs.pqs.connectFourGame;

import java.util.logging.Logger;

public class ConnectFourApp {
  private static final Logger logger = Logger.getLogger("edu.nyu.cs.pqs.connectFourGame");

  private void go() throws IllegalAccessException {
    ConnectFourModel model = ConnectFourModel.getInstance(true);
    new ConnectFourView(model);
    model.startGame();
  }

  public static void main(String[] args) {
    try{
      new ConnectFourApp().go();
    } catch (Exception e) {
      logger.info(e.toString());
    }
  }
}
