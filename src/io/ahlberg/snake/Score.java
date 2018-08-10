package io.ahlberg.snake;

import java.awt.*;
import java.util.prefs.Preferences;

public class Score {
  private int score, highscore;
  private Preferences pref = Preferences.userRoot().node(this.getClass().getCanonicalName());

  public Score() {
    highscore = getHighscoreFromPrefs();
  }

  public void increase() {
    score += 1000;
  }

  public int getScore() {
    return score;
  }

  private int getHighscoreFromPrefs() {
    return pref.getInt("highscore", 0);
  }

  public boolean isHighscore() {
    if (score > highscore) {
      pref.putInt("highscore", score);
      highscore = getHighscoreFromPrefs();
      return true;
    } else {
      return false;
    }
  }

  public void paint(Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Courier New", Font.PLAIN, 36));
    FontMetrics fontMetrics = g.getFontMetrics();
    // Draw score
    g.drawString(String.valueOf(getScore()), 10, 40);
    // Draw highscore, right aligned
    String highscoreStr= "Best: " + String.valueOf(highscore);
    g.drawString(highscoreStr,
        (SnakeGame.WIDTH - 10) - fontMetrics.stringWidth(highscoreStr), 40);
  }

}
