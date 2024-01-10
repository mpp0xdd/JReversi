package jreversi.component;

import jreversi.common.Bot;
import jreversi.common.IBoard;
import jreversi.common.Stone;

public final class BotFactory {

  private BotFactory() {
    // restrict instantiation
  }

  public static Bot create(IBoard board, Stone stone) {
    return new DefaultBot(board, stone);
  }
}
