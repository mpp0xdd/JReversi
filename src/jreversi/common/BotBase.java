package jreversi.common;

import java.util.Objects;

public abstract class BotBase {

  private final IBoard board;
  private final Stone stone;

  public BotBase(IBoard board, Stone stone) {
    this.board = Objects.requireNonNull(board);
    this.stone = Objects.requireNonNull(stone);
  }

  public final IBoard board() {
    return board;
  }

  public final Stone stone() {
    return stone;
  }

  public abstract void compute();
}
