package jreversi.component;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import jreversi.common.Direction;
import jreversi.common.IBoard;
import jreversi.common.ITranscript;
import jreversi.common.ITranscript.IRecord;
import jreversi.common.Stone;
import jreversi.resource.ColorFactory;

public class Board implements IBoard {

  private boolean isGameOver;
  private final Point point;
  private final Stone[][] board;
  private final Transcript transcript;
  private final Deque<IRecord> undoRecords;
  private boolean redoable;
  private Stone currentStone;

  public Board(int x, int y) {
    this.isGameOver = false;
    this.point = new Point(x, y);
    this.board = new Stone[rows()][columns()];
    this.transcript = new Transcript();
    this.undoRecords = new ArrayDeque<>();
    this.redoable = false;
    init();
  }

  public Board() {
    this(0, 0);
  }

  @Override
  public int squareSize() {
    return 70;
  }

  @Override
  public int stoneSize() {
    return 56;
  }

  @Override
  public int rows() {
    return 8;
  }

  @Override
  public int columns() {
    return 8;
  }

  @Override
  public void init() {
    this.isGameOver = false;

    // Initialize board state.
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        this.board[y][x] = Stone.NONE;
      }
    }
    this.board[3][3] = Stone.WHITE;
    this.board[3][4] = Stone.BLACK;
    this.board[4][3] = Stone.BLACK;
    this.board[4][4] = Stone.WHITE;

    this.transcript.clear();

    // Initialize turn.
    this.currentStone = Stone.BLACK;
  }

  @Override
  public void undo() {
    if (transcript.isEmpty()) {
      return;
    }

    IRecord latest = transcript.latest();
    setStone(latest.point(), Stone.NONE);
    this.currentStone = latest.stone();
    latest.points().forEach(this::flipStone);
    this.isGameOver = false;
    transcript.remove(latest);
    if (redoable) {
      undoRecords.addFirst(latest);
    } else {
      undoRecords.clear();
      undoRecords.addFirst(latest);
      redoable = true;
    }
  }

  @Override
  public void redo() {
    if (redoable) {
      IRecord undoRecord = undoRecords.removeFirst();
      currentStone = undoRecord.stone();
      putStone(undoRecord.point());
      redoable = !undoRecords.isEmpty();
    }
  }

  @Override
  public boolean isGameOver() {
    return isGameOver;
  }

  @Override
  public ITranscript transcript() {
    return transcript;
  }

  @Override
  public Stone currentStone() {
    return currentStone;
  }

  @Override
  public Stone getStone(int x, int y) {
    return board[y][x];
  }

  @Override
  public Stone getStone(Point p) {
    return getStone(p.x, p.y);
  }

  @Override
  public void putStone(int x, int y) {
    if (isGameOver()) {
      return;
    }

    if (putStoneImpl(new Point(x, y), true)) {
      redoable = false;
      currentStone = currentStone().flip();
      if (!canPutStone()) {
        currentStone = currentStone().flip();
        if (!canPutStone()) {
          isGameOver = true;
        }
      }
    }
  }

  @Override
  public void putStone(Point p) {
    putStone(p.x, p.y);
  }

  @Override
  public boolean canPutStone(int x, int y) {
    return putStoneImpl(new Point(x, y), false);
  }

  @Override
  public boolean canPutStone(Point p) {
    return canPutStone(p.x, p.y);
  }

  @Override
  public boolean canPutStone() {
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        if (canPutStone(x, y)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public int countStones(Stone stone) {
    int count = 0;
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        if (getStone(x, y) == stone) {
          count++;
        }
      }
    }
    return count;
  }

  @Override
  public Point getLocation() {
    return this.point.getLocation();
  }

  @Override
  public void setLocation(Point point) {
    this.point.setLocation(point);
  }

  @Override
  public void setLocation(int x, int y) {
    this.point.setLocation(x, y);
  }

  @Override
  public Rectangle asRectangle() {
    return new Rectangle(point.x, point.y, width(), height());
  }

  @Override
  public void draw(Graphics g) {
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        drawSquare(g, x, y);
        drawStone(g, x, y);
      }
    }
  }

  private boolean isInRange(Point p) {
    return (0 <= p.x && p.x < columns()) && (0 <= p.y && p.y < rows());
  }

  private void setStone(Point p, Stone stone) {
    this.board[p.y][p.x] = Objects.requireNonNull(stone);
  }

  private void flipStone(Point p) {
    this.board[p.y][p.x] = getStone(p).flip();
  }

  private boolean putStoneImpl(Point point, boolean putStone) {
    if (getStone(point) != Stone.NONE) {
      return false;
    }

    List<Point> points = new ArrayList<>();
    for (Direction d : Direction.values()) {
      Point cursor = new Point(point.x + d.X, point.y + d.Y);
      if (!isInRange(cursor)) {
        continue;
      }
      if (getStone(cursor) != currentStone().flip()) {
        continue;
      }

      for (cursor.translate(d.X, d.Y); isInRange(cursor); cursor.translate(d.X, d.Y)) {
        if (getStone(cursor) == Stone.NONE) {
          break;
        }
        if (getStone(cursor) != currentStone()) {
          continue;
        }
        if (!putStone) {
          return true;
        }

        for (cursor.translate(-d.X, -d.Y); !cursor.equals(point); cursor.translate(-d.X, -d.Y)) {
          points.add(cursor.getLocation());
        }
        break;
      }
    }

    if (!points.isEmpty()) {
      board[point.y][point.x] = currentStone();
      points.forEach(this::flipStone);
      transcript.add(new Transcript.Record(point, currentStone(), points));
      return true;
    }
    return false;
  }

  private void drawSquare(Graphics g, int x, int y) {
    g.setColor(canPutStone(x, y) ? ColorFactory.khaki() : ColorFactory.green());
    g.fill3DRect(
        point.x + x * squareSize(), point.y + y * squareSize(), squareSize(), squareSize(), false);
  }

  private void drawStone(Graphics g, int x, int y) {
    switch (getStone(x, y)) {
      default:
      case NONE:
        return;
      case BLACK:
        g.setColor(ColorFactory.black());
        break;
      case WHITE:
        g.setColor(ColorFactory.white());
        break;
    }
    final int padding = (squareSize() - stoneSize()) / 2;
    g.fillOval(
        point.x + x * squareSize() + padding,
        point.y + y * squareSize() + padding,
        stoneSize(),
        stoneSize());
  }
}
