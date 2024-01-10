package jreversi.component;

import jreversi.common.IBoard;
import jreversi.common.StatusBar;

public final class StatusBarFactory {

  private StatusBarFactory() {
    // restrict instantiation
  }

  public static StatusBar create(IBoard board) {
    return new DefaultStatusBar(board);
  }
}
