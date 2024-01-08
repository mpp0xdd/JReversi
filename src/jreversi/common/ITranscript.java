package jreversi.common;

import java.util.List;

public interface ITranscript {

  List<IRecord> records();

  IRecord latest();

  IRecord oldest();

  IRecord get(int index);

  int size();

  boolean isEmpty();

  interface IPoint {
    int x();

    int y();

    @Override
    String toString();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);
  }

  interface IRecord {
    IPoint point();

    Stone stone();

    List<IPoint> points();

    @Override
    String toString();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);
  }
}
