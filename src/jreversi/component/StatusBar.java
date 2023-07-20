package jreversi.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import jglib.util.GameUtilities;
import jreversi.common.IBoard;
import jreversi.common.StatusBarBase;
import jreversi.common.Stone;
import jreversi.resource.ColorFactory;

public class StatusBar extends StatusBarBase {

  private final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, height() - 2);

  public StatusBar(IBoard board, Point point) {
    super(board, point);
  }

  public StatusBar(IBoard board) {
    this(board, new Point());
  }

  @Override
  public int height() {
    return 20;
  }

  private Color toColor(Stone stone) {
    return switch (stone) {
      case BLACK -> ColorFactory.black();
      case WHITE -> ColorFactory.white();
      default -> throw new IllegalArgumentException("Stone to Color conversion failed: " + stone);
    };
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(ColorFactory.statusBarColor());
    g.fillRect(getLocation().x, getLocation().y, width(), height());

    g.setColor(toColor(board.currentStone()));
    g.setFont(FONT);
    GameUtilities.drawString(g, getLocation().x, getLocation().y, board.currentStone().name());
  }
}
