package jreversi.component;

import java.awt.Point;
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

  public void add(IRecord record) {
    if (transcript.contains(record)) {
      return;
    }
    transcript.add(record);
  }

  public static class Record implements IRecord {
    private final Point point;
    private final Stone stone;

    public Record(Point point, Stone stone) {
      this.point = Objects.requireNonNull(point);
      this.stone = Objects.requireNonNull(stone);
    }

    @Override
    public Point point() {
      return point.getLocation();
    }

    @Override
    public Stone stone() {
      return stone;
    }

    @Override
    public int hashCode() {
      return Objects.hash(point, stone);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Record other = (Record) obj;
      return Objects.equals(point, other.point) && stone == other.stone;
    }
  }
}
