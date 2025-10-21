public class CoordNotValidCoordException extends Exception {
  public CoordNotValidCoordException() {
    super(
        "The provided Coord Value was not valid. Please make sure to enter two integer numbers with a comma seperating them\n");
  }
}
