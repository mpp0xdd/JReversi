package jreversi.common;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Objects;

public abstract class StatusBar implements Locatable, Rectangular, Drawable {
  protected final Board board;
  private final Point point;

  public StatusBar(Board board, Point point) {
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

  @Override
  public Rectangle asRectangle() {
    return new Rectangle(point.x, point.y, width(), height());
  }

  public int width() {
    return board.width();
  }

  public abstract int height();
}
