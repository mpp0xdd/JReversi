package jreversi.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jglib.component.GameScreen;
import jreversi.common.IBoard;
import jreversi.common.StatusBarBase;
import jreversi.component.Board;
import jreversi.component.StatusBar;

public class MainScreen extends GameScreen implements MouseListener, KeyListener {

  private final IBoard board = new Board();
  private final StatusBarBase statusBar = new StatusBar(board);

  public MainScreen() {
    setScreenSize(board.width(), board.height() + statusBar.height());
    addMouseListener(this);
    addKeyListener(this);
    setFocusable(true);
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
  protected void runGameLoop() {
    // nop
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (!board.asRectangle().contains(e.getPoint())) {
      return;
    }
    int cx = (e.getX() - board.getLocation().x) / board.squareSize();
    int cy = (e.getY() - board.getLocation().y) / board.squareSize();
    board.putStone(cx, cy);
    if (board.isGameOver()) {
      board.transcript().records().stream().forEach(System.out::println);
    }
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyChar() == 'u') {
      board.undo();
      repaint();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {}
}
