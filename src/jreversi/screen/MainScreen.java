package jreversi.screen;

import java.awt.Graphics;
import jglib.component.GameScreen;
import jreversi.common.IBoard;
import jreversi.component.Board;

public class MainScreen extends GameScreen {

  private final IBoard board = new Board();

  public MainScreen() {
    setScreenSize(board.width(), board.height());
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
}
