package jreversi.component;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import jglib.util.GameUtilities;
import jreversi.common.BotBase;
import jreversi.common.IBoard;
import jreversi.common.ITranscript.IRecord;
import jreversi.common.Stone;

class Bot extends BotBase {

  private final List<Point> points;

  public Bot(IBoard board, Stone stone) {
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
        .map(IRecord::point)
        .ifPresent(board()::putStone);
  }

  private void sleep() {
    GameUtilities.sleep(500);
  }

  private Stream<Point> pointStream() {
    return this.points.stream();
  }

  private IRecord getLatestRecord(Point point) {
    board().putStone(point);
    IRecord latest = board().transcript().latest();
    board().undo();
    return latest;
  }

  private int compare(IRecord r1, IRecord r2) {
    return r1.points().size() - r2.points().size();
  }
}
