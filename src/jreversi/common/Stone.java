package jreversi.common;

public enum Stone {
  NONE,
  BLACK,
  WHITE;

  public Stone flip() {
    return switch (this) {
      case BLACK -> WHITE;
      case WHITE -> BLACK;
      default -> this;
    };
  }
}
