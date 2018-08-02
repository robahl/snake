package io.ahlberg.snake;

import java.awt.*;

public class GameGrid {
  public static final int EMPTY = 0;
  public static final int SNAKE = 1;
  public static final int FOOD  = 2;

  private static final int TILE_SIZE  = 16;
  private static final int TILE_GAP   = 4;
  private static final int TILE_TOTAL = TILE_SIZE + TILE_GAP;

  private int[][] grid = new int[32][32];

  public GameGrid() {
    grid[24][18] = SNAKE;
    grid[11][8] = FOOD;
  }

  public void paint(Graphics g) {
    g.setColor(Color.WHITE);

    for (int i = 0; i < grid.length; i++) {
     for (int j = 0; j < grid[i].length; j++) {
       switch (grid[i][j]) {
         case EMPTY:
           g.drawRect(TILE_TOTAL * i + (TILE_GAP / 2), TILE_TOTAL * j + (TILE_GAP / 2), TILE_SIZE, TILE_SIZE);
           break;
         case SNAKE:
           g.fillRect(TILE_TOTAL * i + (TILE_GAP / 2), TILE_TOTAL * j + (TILE_GAP / 2), TILE_SIZE, TILE_SIZE);
           break;
         case FOOD:
           g.fillOval(TILE_TOTAL * i + (TILE_GAP / 2), TILE_TOTAL * j + (TILE_GAP / 2), TILE_SIZE, TILE_SIZE);
       }

     }
    }


  }
}
