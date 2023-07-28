package jreversi.component;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import jglib.util.GameUtilities;
import jreversi.common.BoardObserver;
import jreversi.common.IBoard;
import jreversi.common.IBot;
import jreversi.common.ITranscript.IRecord;

public class Bot implements IBot {

  @Override
  public void compute(IBoard board, BoardObserver observer) {
    List<Point> points = new ArrayList<>();
    for (int y = 0; y < board.rows(); y++) {
      for (int x = 0; x < board.columns(); x++) {
        if (board.canPutStone(x, y)) {
          points.add(new Point(x, y));
        }
      }
    }

    points.stream()
        .map(
            p -> {
              board.putStone(p);
              observer.boardUpdate();
              GameUtilities.sleep(20);
              IRecord latest = board.transcript().latest();
              board.undo();
              observer.boardUpdate();
              return latest;
            })
        .min((r1, r2) -> r1.points().size() - r2.points().size())
        .ifPresent(r -> board.putStone(r.point()));
  }
}
