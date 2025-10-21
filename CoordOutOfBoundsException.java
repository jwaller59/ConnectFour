public class CoordOutOfBoundsException extends Exception {
  public CoordOutOfBoundsException() {
    super("Attempted Token placement is outside of the bounds of the board. Try again\n");
  }
}
