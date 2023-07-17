package jreversi.resource;

import java.awt.Color;

public final class ColorFactory {

  private ColorFactory() {
    // restrict instantiation
  }

  private static final Color GREEN = new Color(0, 158, 11);
  private static final Color BLACK = Color.BLACK;
  private static final Color WHITE = Color.WHITE;

  public static Color green() {
    return GREEN;
  }

  public static Color black() {
    return BLACK;
  }

  public static Color white() {
    return WHITE;
  }
}
