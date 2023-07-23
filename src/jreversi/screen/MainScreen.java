package jreversi.screen;

import java.awt.Graphics;
import jglib.component.GameScreen;
import jreversi.common.BoardObserver;
import jreversi.common.IBoard;
import jreversi.common.IBot;
import jreversi.common.StatusBarBase;
import jreversi.component.Board;
import jreversi.component.Bot;
import jreversi.component.StatusBar;

public class MainScreen extends GameScreen implements BoardObserver {

  private final IBoard board = new Board();
  private final StatusBarBase statusBar = new StatusBar(board);
  private final IBot bot = new Bot();

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
    bot.compute(board, this);
    repaint();
    if (board.isGameOver()) {
      stopGameLoop();
    }
  }
}
