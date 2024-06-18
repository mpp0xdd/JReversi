package jreversi;

import jglib.base.Game;
import jglib.component.GameScreen;
import jglib.component.GameWindow;
import jreversi.screen.MainScreen;

public class JReversi extends Game {

  public static void main(String[] args) {
    launch(JReversi.class);
  }

  @Override
  protected void start() {
    GameWindow window = new GameWindow("JReversi");
    GameScreen screen = new MainScreen();

    window.switchGameScreen(screen);
    window.setVisible(true);
  }
}
