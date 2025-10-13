import java.util.ArrayList;

public class Row extends Square {
  ArrayList<Square> squares;

  public Row(int num_squares) {
    super();
    this.squares = new ArrayList<Square>();
    createRows(num_squares);
  }

  @Override
  public String toString() {
    StringBuilder temp = new StringBuilder();
    for (Square square : squares) {
      temp.append(square.printSquare());

    }
    return temp.toString();
  }

  private void createRows(int num) {
    // Create an ArrayList containing the number of squares entered
    for (int i = 0; i < num; i++) {
      this.squares.add(new Square());

    }

  }

  public ArrayList<Square> getSquares() {
    return this.squares;
  }

}
