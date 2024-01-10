package jreversi.component;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import jglib.util.GameUtilities;
import jreversi.common.Board;
import jreversi.common.Bot;
import jreversi.common.Stone;
import jreversi.common.Transcript;
import jreversi.common.Transcript.Record;

class DefaultBot extends Bot {

  private final List<Point> points;

  public DefaultBot(Board board, Stone stone) {
    super(board, stone);
    this.points = new ArrayList<>();
    for (int y = 0; y < board.rows(); y++) {
      for (int x = 0; x < board.columns(); x++) {
        this.points.add(new Point(x, y));
      }
    }
  }

  @Override
  public void compute() {
    if (board().isGameOver()) {
      return;
    }

    if (board().currentStone() != stone()) {
      return;
    }

    sleep();
    pointStream()
        .filter(board()::canPutStone)
        .map(this::getLatestRecord)
        .min(this::compare)
        .map(this::toAWTPoint)
        .ifPresent(board()::putStone);
  }

  private void sleep() {
    GameUtilities.sleep(500);
  }

  private Stream<Point> pointStream() {
    return this.points.stream();
  }

  private Record getLatestRecord(Point point) {
    board().putStone(point);
    Record latest = board().transcript().latest();
    board().undo();
    return latest;
  }

  private Point toAWTPoint(Record r) {
    Transcript.Point p = r.point();
    return new Point(p.x(), p.y());
  }

  private int compare(Record r1, Record r2) {
    return r1.points().size() - r2.points().size();
  }
}
