package io.ahlberg.snake;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class Food {
  private Set<Point> foodOnGrid;
  private Random rand;

  public Food() {
    foodOnGrid = new HashSet<Point>();
    rand = new Random();
  }

  public void spawnFoodAtPoint(Point p) {
    foodOnGrid.add(p);
  }

  public void ateFoodAtPoint(Point p) {
    foodOnGrid.remove(p);
  }

  public Point[] getFoodPoints() {
    return foodOnGrid.toArray(new Point[0]);
  }

  public Point generateValidRandomPoint(Snake snake) {
    // Valid random point = not a snake point and not a point where food is
    // already present.
    while (true) {
      Point randomPoint = new Point(
          rand.nextInt(GameGrid.GRID_WIDTH), rand.nextInt(GameGrid.GRID_HEIGHT));

      if (!snake.contains(randomPoint) && !foodOnGrid.contains(randomPoint)) {
        return randomPoint;
      }
    }
  }

}
