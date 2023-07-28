package jreversi.common;

import java.awt.Graphics;
import java.awt.Point;

public interface IBoard extends Locatable, Rectangular {

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

  void undo();

  void redo();

  boolean isGameOver();

  ITranscript transcript();

  Stone currentStone();

  Stone getStone(int x, int y);

  Stone getStone(Point p);

  void putStone(int x, int y);

  void putStone(Point p);

  boolean canPutStone(int x, int y);

  boolean canPutStone(Point p);

  boolean canPutStone();

  int countStones(Stone stone);

  void draw(Graphics g);
}
