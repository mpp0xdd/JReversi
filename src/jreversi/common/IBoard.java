package jreversi.common;

import java.awt.Graphics;
import java.awt.Point;

public interface IBoard {

  int squareSize();

  int stoneSize();

  int rows();

  int columns();

  default int width() {
    return columns() * squareSize();
  }

  default int height() {
    return rows() * squareSize();
  }

  void init();

  Stone currentStone();

  Stone getStone(int x, int y);

  void putStone(int x, int y);

  Point getLocation();

  void setLocation(Point point);

  void setLocation(int x, int y);

  void draw(Graphics g);
}
