package io.ahlberg.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame  implements ActionListener, KeyListener {
  private JFrame gameFrame;
  private JPanel gamePanel;
  private GameGrid gg;
  private Timer timer;

  private Snake snake = new Snake();

  public static final int WIDTH = 640;
  public static final int HEIGHT = 640;

  private final int TARGET_FPS = 20;

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

    // Game loop will run at TARGET_FPS
    timer = new Timer(1000/TARGET_FPS, this);

    gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    gamePanel.setBackground(Color.RED);
    gamePanel.addKeyListener(this);
    gamePanel.setFocusable(true);

    gameFrame.add(gamePanel);
    gameFrame.pack();
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setResizable(false);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.setVisible(true);

    timer.start();
  }

  public static void main(String[] args) {
    new SnakeGame();
  }

  private void updateLogic() {
    // zero the grid
    gg.clear();

    snake.update();
    // zero the last deque point, ie. making the snake move


    Point[] snakePoints = snake.getSnakePoints();
    for (Point p : snakePoints) {
      gg.setTile(GameGrid.SNAKE, p);
    }

    // Prevent illegal moves pre frame
    snake.setHasMovedInNewDirection(true);

  }

  // Timer

  @Override
  public void actionPerformed(ActionEvent e) {
    updateLogic();
    gamePanel.repaint();
  }

  // Input

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();

    switch (keyCode) {
      case KeyEvent.VK_UP:
        if (snake.getDirection() != Snake.SnakeDirection.DOWN)
          snake.changeDirection(Snake.SnakeDirection.UP);
        break;
      case KeyEvent.VK_RIGHT:
        if (snake.getDirection() != Snake.SnakeDirection.LEFT)
          snake.changeDirection(Snake.SnakeDirection.RIGHT);
        break;
      case KeyEvent.VK_DOWN:
        if (snake.getDirection() != Snake.SnakeDirection.UP)
          snake.changeDirection(Snake.SnakeDirection.DOWN);
        break;
      case KeyEvent.VK_LEFT:
        if (snake.getDirection() != Snake.SnakeDirection.RIGHT)
          snake.changeDirection(Snake.SnakeDirection.LEFT);
        break;
      case KeyEvent.VK_A:
        // Debug - pressing A increase snake length
        snake.grow();
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {}

}
