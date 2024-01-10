package jreversi.component;

import jreversi.common.Bot;
import jreversi.common.Board;
import jreversi.common.Stone;

public final class BotFactory {

  private BotFactory() {
    // restrict instantiation
  }

  public static Bot create(Board board, Stone stone) {
    return new DefaultBot(board, stone);
  }
}
