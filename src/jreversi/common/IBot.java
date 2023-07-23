package jreversi.common;

public interface IBot {
  void compute(IBoard board, BoardObserver observer);
}
