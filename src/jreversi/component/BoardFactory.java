package jreversi.component;

import jreversi.common.Board;

public final class BoardFactory {

  private BoardFactory() {
    // restrict instantiation
  }

  public static Board create() {
    return new DefaultBoard();
  }
}
