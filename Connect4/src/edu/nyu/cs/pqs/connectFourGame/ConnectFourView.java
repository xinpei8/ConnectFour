package edu.nyu.cs.pqs.connectFourGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder; 
/**
 * GUI of Connect Four Game.
 * @author xinpeilin
 */
public class ConnectFourView implements ConnectFourListener {
  private ConnectFourModel model;
  private JFrame frame;
  private JLabel[][] slots;
  private JButton[] buttons;
  final private int xSize;
  final private int ySize;
  private ActionListener actionListener;
  /**
   * Construct a new ConnectFourView object. Set up the GUI of this game
   * @param model a ConnectFourModel that handles the game logic
   * @return The new ConnectFourView object
   */
  public ConnectFourView(ConnectFourModel model) {
    this.model = model;
    model.registerListener(this);
    xSize = this.model.getColumns();
    ySize = this.model.getRows();
    setUpActionListerner();

    frame = new JFrame("Connect Four Game");
    JPanel panel = (JPanel) frame.getContentPane();
    panel.setLayout(new GridLayout(xSize, ySize + 1));

    slots = new JLabel[xSize][ySize];
    buttons = new JButton[xSize];

    for (int i = 0; i < xSize; i++) {
      buttons[i] = new JButton("\u21D3");
      buttons[i].setActionCommand("" + i);
      buttons[i].addActionListener(actionListener);
	  panel.add(buttons[i]);
    }

    for (int y = 0; y < ySize; y++) {
	  for (int x = 0; x < xSize; x++) {
        slots[x][y] = new JLabel();
        slots[x][y].setHorizontalAlignment(SwingConstants.CENTER);
        slots[x][y].setBorder(new LineBorder(Color.gray));
	    slots[x][y].setOpaque(true);
        slots[x][y].setBackground(Color.white);
        panel.add(slots[x][y]);
      }
    }

    frame.setContentPane(panel);
    frame.setSize(700, 600);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
  /**
   * Set up actionListener behaviors for the buttons
   */
  private void setUpActionListerner() {
    actionListener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(e.getActionCommand());
        int y = model.checkAvailableYatX(x);
        if (y != -1) {
          if (model.getCurrentPlayer() == 0) {
            slots[x][y].setOpaque(true);
            slots[x][y].setBackground(Color.yellow);
		  }
          else {
            slots[x][y].setOpaque(true);
            slots[x][y].setBackground(Color.red);
		  }
          model.setGrid(x, y);
        }
        else {
          JOptionPane.showMessageDialog(null, "Invalid move ", 
              "column is filled", JOptionPane.INFORMATION_MESSAGE);
        }
      }
	};
  }

  /**
   * Current player results in a win. Terminate the game.
   */
  @Override
  public void playerWon() {
    frame.setTitle("Connect Four - player " + model.getCurrentPlayer() + " won!");
    frame.setEnabled(false);
  }
  /**
   * No more empty cells on board. Draw game. Terminate the game.
   */
  @Override
  public void drawGame() {
    frame.setTitle("Connect Four - DRAW!");
    frame.setEnabled(false);
  }
  /**
   * Configuration is finished. Signal game view(GUI) to start the game
   */
  @Override
  public void gameStarted() {
    frame.setTitle("Connect Four START! player " + model.getCurrentPlayer() + "'s turn");
    frame.setVisible(true);
  }
  /**
   * Signal game view(GUI) updating the current player
   */
  @Override
  public void changePlayer() {
    frame.setTitle("Connect Four - player " + model.getCurrentPlayer() + "'s turn");
  }

  /**
   * Signal game view(GUI) updating the move made by computer
   */
  @Override
  public void updateComputerMove(int x, int y) {
    if (model.getCurrentPlayer() == 0) {
      slots[x][y].setOpaque(true);
      slots[x][y].setBackground(Color.yellow);
    }
    else {
      slots[x][y].setOpaque(true);
      slots[x][y].setBackground(Color.red);
    }
    model.setGrid(x, y);
  }
}
