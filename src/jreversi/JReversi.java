package jreversi;

import jglib.component.GameScreen;
import jglib.component.GameWindow;
import jreversi.screen.MainScreen;

public class JReversi {

  public static void main(String[] args) {
    GameWindow window = new GameWindow("JReversi");
    GameScreen screen = new MainScreen();

    window.switchGameScreen(screen);
    window.setVisible(true);
    screen.startGameLoop();
    screen.joinGameLoop();
    System.err.println("Done!");
  }
}
