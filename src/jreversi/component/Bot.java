package jreversi.component;

import java.awt.Point;
import java.util.Random;
import jglib.util.GameUtilities;
import jreversi.common.IBoard;
import jreversi.common.IBot;

public class Bot implements IBot {

  @Override
  public Point compute(IBoard board) {
    Random random = new Random();
    int x, y;
    do {
      GameUtilities.sleep(10);
      x = random.nextInt(board.columns());
      y = random.nextInt(board.rows());
    } while (!board.canPutStone(x, y));
    return new Point(x, y);
  }
}
