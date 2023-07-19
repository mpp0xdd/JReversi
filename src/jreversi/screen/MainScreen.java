package jreversi.screen;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jglib.component.GameScreen;
import jglib.util.GameUtilities;
import jreversi.common.IBoard;
import jreversi.component.Board;

public class MainScreen extends GameScreen implements MouseListener {

  private final int STATUS_BAR_HEIGHT = 20;
  private final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, STATUS_BAR_HEIGHT - 2);

  private final IBoard board = new Board(0, STATUS_BAR_HEIGHT);

  public MainScreen() {
    setScreenSize(board.width(), board.height() + STATUS_BAR_HEIGHT);
    addMouseListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(FONT);
    GameUtilities.drawString(g, 0, 0, board.currentStone().name());
    board.draw(g);
  }

  @Override
  protected void runGameLoop() {
    // nop
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int cx = (e.getX() - board.getLocation().x) / board.squareSize();
    int cy = (e.getY() - board.getLocation().y) / board.squareSize();
    board.putStone(cx, cy);
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
