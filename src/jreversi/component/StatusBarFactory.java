package jreversi.component;

import jreversi.common.IBoard;
import jreversi.common.StatusBarBase;

public final class StatusBarFactory {

  private StatusBarFactory() {
    // restrict instantiation
  }

  public static StatusBarBase create(IBoard board) {
    return new StatusBar(board);
  }
}
