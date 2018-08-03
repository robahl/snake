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

    direction = SnakeDirection.RIGHT;
  }

  public Point[] getSnakePoints() {
    return snake.toArray(new Point[0]);
  }

  private void move() {
    switch (direction) {
      case UP:
        break;
      case RIGHT:
        previousPoint = snake.removeLast();

        Point newHead = snake.peekFirst().getLocation();
        if (newHead.x >= GameGrid.GRID_WIDTH - 1) {
          newHead.x = 0;
        } else {
          newHead.x += 1;
        }

        // add new head
        snake.addFirst(newHead);
        break;
      case DOWN:
        break;
      case LEFT:
        break;
    }
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
