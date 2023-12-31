package jreversi.resource;

import java.awt.Color;

public final class ColorFactory {

  private ColorFactory() {
    // restrict instantiation
  }

  private static final Color STATUS_BAR_COLOR = new Color(47, 79, 79);
  private static final Color BEIGE = new Color(245, 245, 220);
  private static final Color GREEN = new Color(0, 158, 11);
  private static final Color KHAKI = new Color(240, 230, 140);
  private static final Color BLACK = Color.BLACK;
  private static final Color WHITE = Color.WHITE;

  public static Color statusBarColor() {
    return STATUS_BAR_COLOR;
  }

  public static Color beige() {
    return BEIGE;
  }

  public static Color green() {
    return GREEN;
  }

  public static Color khaki() {
    return KHAKI;
  }

  public static Color black() {
    return BLACK;
  }

  public static Color white() {
    return WHITE;
  }
}
