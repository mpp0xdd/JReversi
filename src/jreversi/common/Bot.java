package jreversi.common;

import java.util.Objects;

public abstract class Bot {

  private final Board board;
  private final Stone stone;

  public Bot(Board board, Stone stone) {
    this.board = Objects.requireNonNull(board);
    this.stone = Objects.requireNonNull(stone);
  }

  public final Board board() {
    return board;
  }

  public final Stone stone() {
    return stone;
  }

  public abstract void compute();
}
