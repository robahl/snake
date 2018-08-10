package io.ahlberg.snake;

import java.awt.*;

public class GameGrid {
  public static final int EMPTY = 0;
  public static final int SNAKE = 1;
  public static final int FOOD  = 2;

  public static final int GRID_WIDTH  = 32;
  public static final int GRID_HEIGHT = 32;

  private static final int TILE_SIZE  = 16;
  private static final int TILE_GAP   = 4;
  private static final int TILE_TOTAL = TILE_SIZE + TILE_GAP;

  private int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];

  public GameGrid() {
  }

  public void setTile(int tileType, Point p) {
    // TODO - Throw exception at illegal tileTYpe (0-2 accepted)
    grid[p.x][p.y] = tileType;
  }

  public void clear() {
    grid = new int[GRID_WIDTH][GRID_HEIGHT];
  }

  public void paint(Graphics g) {
    g.setColor(Color.WHITE);

    for (int i = 0; i < grid.length; i++) {
     for (int j = 0; j < grid[i].length; j++) {
       switch (grid[i][j]) {
         case EMPTY:
           // g.drawRect(TILE_TOTAL * i + (TILE_GAP / 2), TILE_TOTAL * j + (TILE_GAP / 2), TILE_SIZE, TILE_SIZE);
           break;
         case SNAKE:
           g.fillRect(TILE_TOTAL * i + (TILE_GAP / 2), TILE_TOTAL * j + (TILE_GAP / 2), TILE_SIZE, TILE_SIZE);
           break;
         case FOOD:
           g.fillOval(TILE_TOTAL * i + (TILE_GAP / 2), TILE_TOTAL * j + (TILE_GAP / 2), TILE_SIZE, TILE_SIZE);
           break;
       }

     }
    }


  }
}
