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
  private Stone turn;

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
    this.turn = Stone.BLACK;
  }

  @Override
  public Stone currentTurn() {
    return turn;
  }

  private void changeTurn() {
    this.turn = turn.flip();
  }

  private boolean isInRange(Point p) {
    return (0 <= p.x && p.x < columns()) && (0 <= p.y && p.y < rows());
  }

  @Override
  public Stone get(int x, int y) {
    return board[y][x];
  }

  private Stone get(Point p) {
    return get(p.x, p.y);
  }

  @Override
  public void put(int x, int y) {
    final Point cursor = new Point(x, y);
    if (get(cursor) != Stone.NONE) {
      return;
    }

    for (Direction direction : Direction.values()) {
      cursor.setLocation(x, y);
      cursor.translate(direction.X, direction.Y);
      if (!isInRange(cursor)) {
        continue;
      }
      if (get(cursor) != currentTurn().flip()) {
        continue;
      }

      while (isInRange(cursor)) {
        cursor.translate(direction.X, direction.Y);
        if (!isInRange(cursor)) {
          break;
        }
        if (get(cursor) == Stone.NONE) {
          break;
        }
        if (get(cursor) == currentTurn().flip()) {
          continue;
        }
        assert get(cursor) == currentTurn();

        board[y][x] = currentTurn();
        cursor.translate(-direction.X, -direction.Y);
        while (cursor.x != x || cursor.y != y) {
          board[cursor.y][cursor.x] = board[cursor.y][cursor.x].flip();
          cursor.translate(-direction.X, -direction.Y);
        }
        break;
      }
    }

    if (get(x, y) == currentTurn()) {
      changeTurn();
    }
  }

  @Override
  public Point getLocation() {
    return this.point.getLocation();
  }

  private void drawSquare(Graphics g, int x, int y) {
    g.setColor(ColorFactory.green());
    g.fill3DRect(
        point.x + x * squareSize(), point.y + y * squareSize(), squareSize(), squareSize(), false);
  }

  private void drawStone(Graphics g, int x, int y) {
    switch (get(x, y)) {
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
