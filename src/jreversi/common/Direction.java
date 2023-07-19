package jreversi.common;

public enum Direction {
  NORTH(0, -1),
  NORTHEAST(1, -1),
  EAST(1, 0),
  SOUTHEAST(1, 1),
  SOUTH(0, 1),
  SOUTHWEST(-1, 1),
  WEST(-1, 0),
  NORTHWEST(-1, -1);

  public final int X;
  public final int Y;

  private Direction(int X, int Y) {
    this.X = X;
    this.Y = Y;
  }
}
