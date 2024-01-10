package jreversi.component;

import jreversi.common.Board;
import jreversi.common.StatusBar;

public final class StatusBarFactory {

  private StatusBarFactory() {
    // restrict instantiation
  }

  public static StatusBar create(Board board) {
    return new DefaultStatusBar(board);
  }
}
