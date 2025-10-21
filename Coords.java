import java.util.Objects;

public class Coords {
  private final int x;
  private final int y;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Coords(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return String.format("%d:%d", this.x, this.y);
  }

  // In order to do comparison operations in a set between Objects
  // we need to override the equals and hash methods and apply them correctly to
  // this object
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Coords coord = (Coords) obj;
    return x == coord.x && y == coord.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

}
