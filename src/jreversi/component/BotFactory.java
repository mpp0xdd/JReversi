package jreversi.component;

import jreversi.common.BotBase;
import jreversi.common.IBoard;
import jreversi.common.Stone;

public final class BotFactory {

  private BotFactory() {
    // restrict instantiation
  }

  public static BotBase create(IBoard board, Stone stone) {
    return new Bot(board, stone);
  }
}
