package jreversi.component;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import jglib.util.GameUtilities;
import jreversi.common.BoardObserver;
import jreversi.common.BotBase;
import jreversi.common.IBoard;
import jreversi.common.ITranscript.IRecord;
import jreversi.common.Stone;

class PointsHolder {

  private final List<Point> points;

  public PointsHolder(IBoard board) {
    Objects.requireNonNull(board);
    this.points = new ArrayList<>();
    for (int y = 0; y < board.rows(); y++) {
      for (int x = 0; x < board.columns(); x++) {
        this.points.add(new Point(x, y));
      }
    }
  }

  public Stream<Point> stream() {
    return this.points.stream();
  }
}

public class Bot extends BotBase {

  private PointsHolder holder;

  public Bot(IBoard board, BoardObserver observer, Stone stone) {
    super(board, observer, stone);
    this.holder = new PointsHolder(board);
  }

  @Override
  public void compute() {
    if (board().isGameOver()) {
      return;
    }

    if (board().currentStone() != stone()) {
      return;
    }

    pointStream()
        .filter(board()::canPutStone)
        .map(this::getLatestRecord)
        .min(this::compare)
        .map(IRecord::point)
        .ifPresent(board()::putStone);
  }

  private Stream<Point> pointStream() {
    return holder.stream();
  }

  private IRecord getLatestRecord(Point point) {
    board().putStone(point);
    observer().ifPresent(BoardObserver::boardUpdate);
    GameUtilities.sleep(20);
    IRecord latest = board().transcript().latest();
    board().undo();
    observer().ifPresent(BoardObserver::boardUpdate);
    GameUtilities.sleep(20);
    return latest;
  }

  private int compare(IRecord r1, IRecord r2) {
    return r1.points().size() - r2.points().size();
  }
}
