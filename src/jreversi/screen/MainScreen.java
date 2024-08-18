package jreversi.screen;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jglib.component.GameScreen;
import jreversi.common.Board;
import jreversi.common.Bot;
import jreversi.common.Rectangular;
import jreversi.common.StatusBar;
import jreversi.common.Stone;
import jreversi.component.BoardFactory;
import jreversi.component.BotFactory;
import jreversi.component.StatusBarFactory;

public class MainScreen extends GameScreen implements MouseListener {

  private final Board board = BoardFactory.create();
  private final StatusBar statusBar = StatusBarFactory.create(board);
  private final Stone yourTurn = Stone.BLACK;
  private final Bot bot = BotFactory.create(board, yourTurn.flip());

  public MainScreen() {
    setScreenSize(board.width(), statusBar.height() + board.height());
    addMouseListener(this);
    statusBar.setLocation(0, 0);
    board.setLocation(0, statusBar.height());
  }

  @Override
  protected void paintGameComponent(Graphics g) {
    statusBar.draw(g);
    board.draw(g);

    if (board.isGameOver()) {
      board.transcript().records().forEach(System.err::println);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    handleYourTurn(e);
    paintImmediately(statusBar, board);

    handleBotTurn();
    paintImmediately(statusBar, board);
  }

  private void handleYourTurn(MouseEvent e) {
    if (board.currentStone() != yourTurn) {
      return;
    }
    if (!board.asRectangle().contains(e.getPoint())) {
      return;
    }

    int cx = (e.getX() - board.getLocation().x) / board.squareSize();
    int cy = (e.getY() - board.getLocation().y) / board.squareSize();
    board.putStone(cx, cy);
  }

  private void handleBotTurn() {
    while (board.currentStone() == bot.stone() && !board.isGameOver()) {
      bot.compute();
      paintImmediately(statusBar, board);
    }
  }

  private void paintImmediately(Rectangular... rectangulars) {
    for (Rectangular rectangular : rectangulars) {
      paintImmediately(rectangular.asRectangle());
    }
  }

  @Override
  protected void runGameLoop() {}

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}
}
