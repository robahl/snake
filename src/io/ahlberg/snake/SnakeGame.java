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
  private Food food = new Food();
  private Score score = new Score();

  public static final int WIDTH = 640;
  public static final int HEIGHT = 640;

  private final int TARGET_FPS = 20;

  private boolean paused = false;

  public SnakeGame() {
    gameFrame = new JFrame("Snake");
    gg = new GameGrid();

    gamePanel = new JPanel() {
      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gg.paint(g);
        score.paint(g);
      }
    };

    // Game loop will run at TARGET_FPS
    timer = new Timer(1000/TARGET_FPS, this);

    gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    gamePanel.setBackground(new Color(0x2c2c2c));
    gamePanel.addKeyListener(this);
    gamePanel.setFocusable(true);

    gameFrame.add(gamePanel);
    gameFrame.pack();
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setResizable(false);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.setVisible(true);

    timer.start();

    // Initial food placement
    addFood();
  }

  public static void main(String[] args) {
    new SnakeGame();
  }

  private void updateLogic() {
    // zero the grid
    gg.clear();

    // game over?
    if (snake.hasCollidedWithSelf()) {
      timer.stop();
      JOptionPane.showMessageDialog(gameFrame, "Game over!\nScore: " +
          String.valueOf(score.getScore()), "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    }

    snake.update();

    if (snakeCollidedWithFood()) {
      snake.grow();
      addFood();
      score.increase();
    }

    for (Point p : food.getFoodPoints()) {
      gg.setTile(GameGrid.FOOD, p);
    }

    for (Point p : snake.getSnakePoints()) {
      gg.setTile(GameGrid.SNAKE, p);
    }

    // Prevent illegal moves pre frame
    snake.setHasMovedInNewDirection(true);

  }

  private boolean snakeCollidedWithFood() {
    Point head = snake.getHead();

    for (Point p : food.getFoodPoints()) {
      if (p.equals(head)) {
        food.ateFoodAtPoint(p);
        return true;
      }
    }

    return false;
  }

  private void addFood() {
    food.spawnFoodAtPoint(food.generateValidRandomPoint(snake));
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
      case KeyEvent.VK_P:
        if (paused) {
          timer.start();
        } else {
          timer.stop();
        }

        paused = !paused; // flip paused
        break;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {}

}
