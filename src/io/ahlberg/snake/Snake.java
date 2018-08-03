package io.ahlberg.snake;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {
  private int length;
  private Deque<Point> snake;
  private SnakeDirection direction;
  private Point previousPoint;

  private enum SnakeDirection {
    UP,
    RIGHT,
    DOWN,
    LEFT
  }

  public Snake(int length) {
    this.length = length;
    snake = new ArrayDeque<Point>();

    snake.add(new Point(10, 15));
    snake.add(new Point(9, 15));
    snake.add(new Point(8, 15));
    snake.add(new Point(7, 15));

    direction = SnakeDirection.UP;
  }

  public Point[] getSnakePoints() {
    return snake.toArray(new Point[0]);
  }

  private void move() {
    Point newHead = snake.peekFirst().getLocation();

    switch (direction) {
      case UP:
        if (newHead.y <= 0) {
          newHead.y = GameGrid.GRID_HEIGHT - 1;
        } else {
          newHead.y -= 1;
        }
        break;
      case RIGHT:
        if (newHead.x >= GameGrid.GRID_WIDTH - 1) {
          newHead.x = 0;
        } else {
          newHead.x += 1;
        }
        break;
      case DOWN:
        if (newHead.y >= GameGrid.GRID_HEIGHT - 1) {
          newHead.y = 0;
        } else {
          newHead.y += 1;
        }
        break;
      case LEFT:
        if (newHead.x <= 0) {
          newHead.x = GameGrid.GRID_WIDTH - 1;
        } else {
          newHead.x -= 1;
        }
        break;
    }

    // Add new head and remove the last tail tile
    snake.addFirst(newHead);
    previousPoint = snake.removeLast();
  }

  public void changeDirection() {

  }

  public void update() {
    move();
  }

  public Point getPreviousPoint() {
    return previousPoint;
  }

}
