package jreversi.screen;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jglib.component.GameScreen;
import jreversi.common.BotBase;
import jreversi.common.IBoard;
import jreversi.common.Rectangular;
import jreversi.common.StatusBarBase;
import jreversi.common.Stone;
import jreversi.component.BoardFactory;
import jreversi.component.BotFactory;
import jreversi.component.StatusBarFactory;

public class MainScreen extends GameScreen implements MouseListener {

  private final IBoard board = BoardFactory.create();
  private final StatusBarBase statusBar = StatusBarFactory.create(board);
  private final Stone yourTurn = Stone.BLACK;
  private final BotBase bot = BotFactory.create(board, yourTurn.flip());

  public MainScreen() {
    setScreenSize(board.width(), board.height() + statusBar.height());
    addMouseListener(this);
    statusBar.setLocation(0, 0);
    board.setLocation(0, statusBar.height());
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    statusBar.draw(g);
    board.draw(g);
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
