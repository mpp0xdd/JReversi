package jreversi.common;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;

public abstract class StatusBarBase implements Locatable {
  protected final IBoard board;
  private final Point point;

  public StatusBarBase(IBoard board, Point point) {
    this.board = Objects.requireNonNull(board);
    this.point = Objects.requireNonNull(point).getLocation();
  }

  @Override
  public Point getLocation() {
    return point.getLocation();
  }

  @Override
  public void setLocation(Point point) {
    this.point.setLocation(point);
  }

  @Override
  public void setLocation(int x, int y) {
    this.point.setLocation(x, y);
  }

  public int width() {
    return board.width();
  }

  public abstract int height();

  public abstract void draw(Graphics g);
}
