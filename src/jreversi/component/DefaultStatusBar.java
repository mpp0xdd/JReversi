package jreversi.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import jglib.util.StringDrawer;
import jreversi.common.Board;
import jreversi.common.StatusBar;
import jreversi.common.Stone;
import jreversi.resource.ColorFactory;

class DefaultStatusBar extends StatusBar {

  private final Font FONT_OF_CURRENT_STONE = new Font(Font.SANS_SERIF, Font.BOLD, height() - 2);
  private final Font FONT_OF_GAME_STATUS = new Font(Font.SANS_SERIF, Font.ITALIC, height() - 2);
  private final Font FONT_OF_COUNT_STONES = new Font(Font.MONOSPACED, Font.BOLD, height() - 2);

  public DefaultStatusBar(Board board, Point point) {
    super(board, point);
  }

  public DefaultStatusBar(Board board) {
    this(board, new Point());
  }

  @Override
  public int height() {
    return 20;
  }

  @Override
  public void draw(Graphics g) {
    drawBackground(g);

    drawCurrentStone(g);
    drawGameStatus(g);
    drawCountStones(g);
  }

  private Color toColor(Stone stone) {
    return switch (stone) {
      case BLACK -> ColorFactory.black();
      case WHITE -> ColorFactory.white();
      default -> throw new IllegalArgumentException("Stone to Color conversion failed: " + stone);
    };
  }

  private void drawBackground(Graphics g) {
    g.setColor(ColorFactory.statusBarColor());
    g.fillRect(getLocation().x, getLocation().y, width(), height());
  }

  private void drawCurrentStone(Graphics g) {
    g.setFont(FONT_OF_CURRENT_STONE);
    g.setColor(toColor(board.currentStone()));
    StringDrawer.LEFT.draw(g, getLocation().x, getLocation().y, board.currentStone().name());
  }

  private void drawGameStatus(Graphics g) {
    g.setFont(FONT_OF_GAME_STATUS);
    g.setColor(ColorFactory.beige());

    if (board.isGameOver()) {
      int numOfBlack = board.count(Stone.BLACK);
      int numOfWhite = board.count(Stone.WHITE);
      String result;
      if (numOfBlack > numOfWhite) {
        result = "Black wins!";
      } else if (numOfWhite > numOfBlack) {
        result = "White wins!";
      } else {
        result = "Draw!";
      }

      StringDrawer.CENTER.draw(g, width() / 2, height() / 2, "Game Over! " + result);
      return;
    }

    if (board.transcript().size() > 0) {
      if (board.transcript().latest().stone() == board.currentStone()) {
        StringDrawer.CENTER.draw(g, width() / 2, height() / 2, "PASS");
      }
    }
  }

  private void drawCountStones(Graphics g) {
    g.setFont(FONT_OF_COUNT_STONES);
    g.setColor(ColorFactory.beige());
    StringDrawer.RIGHT.draw(
        g,
        getLocation().x + width(),
        getLocation().y,
        String.format("B:%s W:%s", board.count(Stone.BLACK), board.count(Stone.WHITE)));
  }
}
