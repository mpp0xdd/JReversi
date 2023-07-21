package jreversi.component;

import java.awt.Graphics;
import java.awt.Point;
import jreversi.common.Direction;
import jreversi.common.IBoard;
import jreversi.common.Stone;
import jreversi.resource.ColorFactory;

public class Board implements IBoard {

  private final Point point;
  private final Stone[][] board;
  private Stone currentStone;

  public Board(int x, int y) {
    this.point = new Point(x, y);
    this.board = new Stone[rows()][columns()];
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

    // Initialize turn.
    this.currentStone = Stone.BLACK;
  }

  @Override
  public Stone currentStone() {
    return currentStone;
  }

  private boolean isInRange(Point p) {
    return (0 <= p.x && p.x < columns()) && (0 <= p.y && p.y < rows());
  }

  @Override
  public Stone getStone(int x, int y) {
    return board[y][x];
  }

  private Stone getStone(Point p) {
    return getStone(p.x, p.y);
  }

  @Override
  public void putStone(int x, int y) {
    if (putStoneImpl(x, y, true)) {
      if (!canPutStone()) {
        this.currentStone = currentStone().flip();
      }
    }
  }

  private boolean putStoneImpl(int x, int y, boolean putStone) {
    final Point cursor = new Point(x, y);
    if (getStone(cursor) != Stone.NONE) {
      return false;
    }

    for (Direction direction : Direction.values()) {
      cursor.setLocation(x, y);
      cursor.translate(direction.X, direction.Y);
      if (!isInRange(cursor)) {
        continue;
      }
      if (getStone(cursor) != currentStone().flip()) {
        continue;
      }

      while (isInRange(cursor)) {
        cursor.translate(direction.X, direction.Y);
        if (!isInRange(cursor)) {
          break;
        }
        if (getStone(cursor) == Stone.NONE) {
          break;
        }
        if (getStone(cursor) == currentStone().flip()) {
          continue;
        }
        assert getStone(cursor) == currentStone();
        if (!putStone) {
          return true;
        }

        board[y][x] = currentStone();
        cursor.translate(-direction.X, -direction.Y);
        while (cursor.x != x || cursor.y != y) {
          board[cursor.y][cursor.x] = board[cursor.y][cursor.x].flip();
          cursor.translate(-direction.X, -direction.Y);
        }
        break;
      }
    }

    if (getStone(x, y) == currentStone()) {
      this.currentStone = currentStone().flip();
      return true;
    }
    return false;
  }

  private boolean canPutStone(int x, int y) {
    return putStoneImpl(x, y, false);
  }

  private boolean canPutStone() {
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

  @Override
  public void draw(Graphics g) {
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        drawSquare(g, x, y);
        drawStone(g, x, y);
      }
    }
  }
}
