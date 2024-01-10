package jreversi.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import jreversi.common.Transcript;
import jreversi.common.Stone;

class DefaultTranscript implements Transcript {

  private final List<Record> transcript;

  public DefaultTranscript() {
    this.transcript = new ArrayList<>();
  }

  @Override
  public List<Record> records() {
    return Collections.unmodifiableList(transcript);
  }

  @Override
  public Record latest() {
    return transcript.get(transcript.size() - 1);
  }

  @Override
  public Record oldest() {
    return transcript.get(0);
  }

  @Override
  public Record get(int index) {
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

  public void add(Record record) {
    if (transcript.contains(record)) {
      return;
    }
    transcript.add(record);
  }

  public boolean remove(Record record) {
    return transcript.remove(record);
  }

  public void clear() {
    transcript.clear();
  }

  public static class DefaultPoint implements Point {

    public static DefaultPoint from(java.awt.Point point) {
      return new DefaultPoint(point.x, point.y);
    }

    private final int x;
    private final int y;

    private DefaultPoint(int x, int y) {
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
      DefaultPoint other = (DefaultPoint) obj;
      return x == other.x && y == other.y;
    }
  }

  public static class DefaultRecord implements Record {

    public static DefaultRecord of(Point point, Stone stone, List<Point> points) {
      return new DefaultRecord(point, stone, points);
    }

    private final Point point;
    private final Stone stone;
    private final List<Point> points;

    private DefaultRecord(Point point, Stone stone, List<Point> points) {
      this.point = Objects.requireNonNull(point);
      this.stone = Objects.requireNonNull(stone);
      this.points = Collections.unmodifiableList(points);
    }

    @Override
    public Point point() {
      return point;
    }

    @Override
    public Stone stone() {
      return stone;
    }

    @Override
    public List<Point> points() {
      return points;
    }

    @Override
    public String toString() {
      return "Record [point=" + point + ", stone=" + stone + ", points=" + points + "]";
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
      DefaultRecord other = (DefaultRecord) obj;
      return Objects.equals(point, other.point)
          && Objects.equals(points, other.points)
          && stone == other.stone;
    }
  }
}
