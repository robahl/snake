package io.ahlberg.snake;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {
  private Deque<Point> snake;
  private SnakeDirection direction;
  private boolean hasMovedInNewDirection;

  public enum SnakeDirection {
    UP,
    RIGHT,
    DOWN,
    LEFT
  }

  public Snake() {
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

  public Point getHead() {
    return snake.peekFirst().getLocation();
  }

  private void move() {
    Point head = snake.peekFirst().getLocation();

    Point newHead = getNextHeadPosition(head, getDirection());

    // Add new head and remove the last tail tile
    snake.addFirst(newHead);
    snake.removeLast();
  }

  public void changeDirection(SnakeDirection dir) {
    if (hasMovedInNewDirection) {
      direction = dir;
      hasMovedInNewDirection = false;
    }
  }

  public SnakeDirection getDirection() {
    return direction;
  }

  public void grow() {
    Point head = snake.peekFirst().getLocation();

    Point newHead = getNextHeadPosition(head, getDirection());

    snake.addFirst(newHead);
  }

  // Calculate the next head position base by the snakes direction
  private Point getNextHeadPosition(Point head, SnakeDirection direction)   {
    switch (direction) {
      case UP:
        if (head.y <= 0) {
          head.y = GameGrid.GRID_HEIGHT - 1;
        } else {
          head.y -= 1;
        }
        break;
      case RIGHT:
        if (head.x >= GameGrid.GRID_WIDTH - 1) {
          head.x = 0;
        } else {
          head.x += 1;
        }
        break;
      case DOWN:
        if (head.y >= GameGrid.GRID_HEIGHT - 1) {
          head.y = 0;
        } else {
          head.y += 1;
        }
        break;
      case LEFT:
        if (head.x <= 0) {
          head.x = GameGrid.GRID_WIDTH - 1;
        } else {
          head.x -= 1;
        }
        break;
    }

    return head;
  }

  public void setHasMovedInNewDirection(boolean moved) {
    hasMovedInNewDirection = true;
  }

  public boolean contains(Point p) {
    return snake.contains(p);
  }

  public void update() {
    move();
  }

}
