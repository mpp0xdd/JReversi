package jreversi.component;

import jreversi.common.IBoard;

public final class BoardFactory {

  private BoardFactory() {
    // restrict instantiation
  }

  public static IBoard create() {
    return new Board();
  }
}
