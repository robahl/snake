package io.ahlberg.snake;

import javax.swing.*;
import java.awt.*;

public class SnakeGame {
  private JFrame gameFrame;
  private JPanel gamePanel;
  private GameGrid gg;

  public static final int WIDTH = 640;
  public static final int HEIGHT = 640;

  public SnakeGame() {
    gameFrame = new JFrame("Snake");
    gg = new GameGrid();

    gamePanel = new JPanel() {
      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gg.paint(g);
      }
    };

    gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    gamePanel.setBackground(Color.RED);

    gameFrame.add(gamePanel);
    gameFrame.pack();
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setResizable(false);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.setVisible(true);

  }

  public static void main(String[] args) {
    new SnakeGame();
  }

}
