package jreversi.component;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import jglib.util.GameUtilities;
import jreversi.common.BoardObserver;
import jreversi.common.BotBase;
import jreversi.common.IBoard;
import jreversi.common.ITranscript.IRecord;
import jreversi.common.Stone;

public class Bot extends BotBase {

  public Bot(IBoard board, BoardObserver observer, Stone stone) {
    super(board, observer, stone);
  }

  @Override
  public void compute() {
    if (board().isGameOver()) {
      return;
    }

    if (board().currentStone() != stone()) {
      return;
    }

    List<Point> points = new ArrayList<>();
    for (int y = 0; y < board().rows(); y++) {
      for (int x = 0; x < board().columns(); x++) {
        if (board().canPutStone(x, y)) {
          points.add(new Point(x, y));
        }
      }
    }

    points.stream()
        .map(
            p -> {
              board().putStone(p);
              observer().ifPresent(BoardObserver::boardUpdate);
              GameUtilities.sleep(20);
              IRecord latest = board().transcript().latest();
              board().undo();
              observer().ifPresent(BoardObserver::boardUpdate);
              return latest;
            })
        .min((r1, r2) -> r1.points().size() - r2.points().size())
        .ifPresent(r -> board().putStone(r.point()));
  }
}
