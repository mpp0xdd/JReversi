package jreversi.screen;

import java.awt.Graphics;
import jglib.component.GameScreen;
import jreversi.common.BoardObserver;
import jreversi.common.BotBase;
import jreversi.common.IBoard;
import jreversi.common.StatusBarBase;
import jreversi.common.Stone;
import jreversi.component.Board;
import jreversi.component.Bot;
import jreversi.component.StatusBar;

public class MainScreen extends GameScreen implements BoardObserver {

  private final IBoard board = new Board();
  private final StatusBarBase statusBar = new StatusBar(board);
  private final BotBase blackBot = new Bot(board, this, Stone.BLACK);
  private final BotBase whiteBot = new Bot(board, this, Stone.WHITE);

  public MainScreen() {
    setScreenSize(board.width(), board.height() + statusBar.height());
    statusBar.setLocation(0, 0);
    board.setLocation(0, statusBar.height());
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    statusBar.draw(g);
    board.draw(g);
  }

  @Override
  public void boardUpdate() {
    paintImmediately(board.asRectangle());
  }

  @Override
  protected void runGameLoop() {
    blackBot.compute();
    whiteBot.compute();
    repaint();
    if (board.isGameOver()) {
      stopGameLoop();
    }
  }
}
