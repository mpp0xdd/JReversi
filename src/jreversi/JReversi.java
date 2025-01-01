package jreversi;

import jglib.core.Game;
import jglib.screen.GameWindow;
import jglib.screen.SimpleGameScreen;
import jreversi.screen.MainScreen;

public class JReversi extends Game {

  public static void main(String[] args) {
    launch(JReversi.class);
  }

  @Override
  protected void start() {
    GameWindow window = new GameWindow("JReversi");
    SimpleGameScreen screen = new MainScreen();

    window.switchGameScreen(screen);
    window.setVisible(true);
  }
}
