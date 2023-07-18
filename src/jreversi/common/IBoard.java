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

  Stone currentTurn();

  Stone get(int x, int y);

  void put(int x, int y);

  Point getLocation();

  void draw(Graphics g);
}
