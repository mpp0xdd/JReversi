package jreversi.common;

import java.util.Objects;
import java.util.Optional;

public abstract class BotBase {

  private final IBoard board;
  private final BoardObserver observer;
  private final Stone stone;

  public BotBase(IBoard board, BoardObserver observer, Stone stone) {
    this.board = Objects.requireNonNull(board);
    this.observer = observer;
    this.stone = Objects.requireNonNull(stone);
  }

  public final IBoard board() {
    return board;
  }

  public final Optional<BoardObserver> observer() {
    return Optional.ofNullable(observer);
  }

  public final Stone stone() {
    return stone;
  }

  public abstract void compute();
}
