package jreversi.common;

import java.awt.Point;
import java.util.List;

public interface ITranscript {

  List<IRecord> records();

  IRecord latest();

  IRecord oldest();

  IRecord get(int index);

  int size();

  public interface IRecord {
    Point point();

    Stone stone();

    List<Point> points();

    @Override
    String toString();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);
  }
}
