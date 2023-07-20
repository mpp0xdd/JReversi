package jreversi.common;

import java.awt.Point;

public interface Locatable {
  Point getLocation();

  void setLocation(Point point);

  void setLocation(int x, int y);
}
