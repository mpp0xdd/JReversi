package jreversi.component;

import java.awt.Graphics;
import java.awt.Point;
import jreversi.common.IBoard;
import jreversi.common.Stone;
import jreversi.resource.ColorFactory;

public class Board implements IBoard {

  private final Point point;
  private final Stone[][] board;

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
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        this.board[y][x] = Stone.NONE;
      }
    }
    put(Stone.WHITE, 3, 3);
    put(Stone.BLACK, 3, 4);
    put(Stone.BLACK, 4, 3);
    put(Stone.WHITE, 4, 4);
  }

  @Override
  public Stone get(int x, int y) {
    return board[y][x];
  }

  @Override
  public void put(Stone stone, int x, int y) {
    board[y][x] = stone;
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
