package jreversi.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import jreversi.common.ITranscript;
import jreversi.common.Stone;

class Transcript implements ITranscript {

  private final List<IRecord> transcript;

  public Transcript() {
    this.transcript = new ArrayList<>();
  }

  @Override
  public List<IRecord> records() {
    return Collections.unmodifiableList(transcript);
  }

  @Override
  public IRecord latest() {
    return transcript.get(transcript.size() - 1);
  }

  @Override
  public IRecord oldest() {
    return transcript.get(0);
  }

  @Override
  public IRecord get(int index) {
    return transcript.get(index);
  }

  @Override
  public int size() {
    return transcript.size();
  }

  @Override
  public boolean isEmpty() {
    return transcript.isEmpty();
  }

  public void add(IRecord record) {
    if (transcript.contains(record)) {
      return;
    }
    transcript.add(record);
  }

  public boolean remove(IRecord record) {
    return transcript.remove(record);
  }

  public void clear() {
    transcript.clear();
  }

  public static class Point implements IPoint {

    public static Point from(java.awt.Point point) {
      return new Point(point.x, point.y);
    }

    private final int x;
    private final int y;

    private Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int x() {
      return x;
    }

    @Override
    public int y() {
      return y;
    }

    @Override
    public String toString() {
      return "Point [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Point other = (Point) obj;
      return x == other.x && y == other.y;
    }
  }

  public static class Record implements IRecord {

    public static Record of(IPoint point, Stone stone, List<IPoint> points) {
      return new Record(point, stone, points);
    }

    private final IPoint point;
    private final Stone stone;
    private final List<IPoint> points;

    private Record(IPoint point, Stone stone, List<IPoint> points) {
      this.point = Objects.requireNonNull(point);
      this.stone = Objects.requireNonNull(stone);
      this.points = Collections.unmodifiableList(points);
    }

    @Override
    public IPoint point() {
      return point;
    }

    @Override
    public Stone stone() {
      return stone;
    }

    @Override
    public List<IPoint> points() {
      return points;
    }

    @Override
    public String toString() {
      return String.format("(%s,%s,%s) %s", point.x(), point.y(), stone.name(), points);
    }

    @Override
    public int hashCode() {
      return Objects.hash(point, points, stone);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Record other = (Record) obj;
      return Objects.equals(point, other.point)
          && Objects.equals(points, other.points)
          && stone == other.stone;
    }
  }
}
