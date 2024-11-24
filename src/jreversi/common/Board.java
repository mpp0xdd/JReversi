package jreversi.common;

import java.awt.Point;
import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;

public interface Board extends Locatable, Rectangular, Drawable {

  int squareSize();

  int stoneSize();

  int rows();

  int columns();

  @Override
  default int width() {
    return columns() * squareSize();
  }

  @Override
  default int height() {
    return rows() * squareSize();
  }

  void init();

  void undo();

  void redo();

  boolean isGameOver();

  Transcript transcript();

  Stone currentStone();

  Stone getStone(int x, int y);

  Stone getStone(Point p);

  void putStone(int x, int y);

  void putStone(Point p);

  boolean canPutStone(int x, int y);

  boolean canPutStone(Point p);

  boolean canPutStone();

  int count(Stone stone);
}
