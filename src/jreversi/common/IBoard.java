package jreversi.common;

import java.awt.Graphics;

public interface IBoard extends Locatable {

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

  int countStones(Stone stone);

  void draw(Graphics g);
}
