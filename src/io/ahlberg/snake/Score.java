package io.ahlberg.snake;

import java.awt.*;

public class Score {
  private int score;

  public void increase() {
    score += 1000;
  }

  public int getScore() {
    return score;
  }

  public void paint(Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Courier New", Font.PLAIN, 36));
    g.drawString(String.valueOf(getScore()), 10, 40);
  }

}
