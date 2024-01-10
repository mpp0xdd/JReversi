package jreversi.common;

import java.util.List;

public interface Transcript {

  List<Record> records();

  Record latest();

  Record oldest();

  Record get(int index);

  int size();

  boolean isEmpty();

  interface Point {
    int x();

    int y();

    @Override
    String toString();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);
  }

  interface Record {
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
