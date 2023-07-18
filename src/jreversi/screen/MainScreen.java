package jreversi.screen;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jglib.component.GameScreen;
import jreversi.common.IBoard;
import jreversi.common.Stone;
import jreversi.component.Board;

public class MainScreen extends GameScreen implements MouseListener {

  private final IBoard board = new Board();

  public MainScreen() {
    setScreenSize(board.width(), board.height());
    addMouseListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    board.draw(g);
  }

  @Override
  protected void runGameLoop() {
    // nop
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int cx = e.getX() / board.squareSize();
    int cy = e.getY() / board.squareSize();
    board.put(Stone.BLACK, cx, cy);
    repaint();
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}
}
