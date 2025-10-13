import java.util.ArrayList;
import java.util.HashSet;

// should contain and maintain board state - including returning scores
record Coords<K, V>(K x, V y) {
};

public class Board {
  private ArrayList<Row> board;
  private int rows;
  private int columns;

  public Board(int rows, int columns) {
    createBoard(rows, columns);
    this.rows = rows;
    this.columns = columns;

  }

  public ArrayList<Row> getBoard() {
    return this.board;
  }

  private void createBoard(int rows, int columns) {
    // create our board based on the number of columns and rows provided
    ArrayList<Row> board = new ArrayList<Row>();
    for (int i = 0; i < columns; i++) {
      board.add(new Row(rows));
    }
    this.board = board;
  }

  @Override
  public String toString() {
    StringBuilder temp = new StringBuilder();
    for (Row row : board) {
      temp.append(row);
      temp.append('\n');

    }
    return temp.toString();
  }

  public int checkScore(Coords<Integer, Integer> coords, int currentScore, Player currentPlayer,
      HashSet<Coords<Integer, Integer>> set) {
    // recursively we check each direction around the original coordinates
    // and then return the maximum value of the opposite cardinal directions.
    // Logically if the output of the sum of our cardinal directions is greater or
    // equal than 4 then the player has won

    // begin our logical sequence
    // if score == 4 we return
    // if we have already visited this square before we return
    if (set.contains(coords)) {
      return currentScore;
    }
    set.add(coords);

    if (currentScore == 4) {
      return currentScore;
    }
    // if the current coords is outside of our boundry
    if (coords.x() >= this.columns || coords.x() < 0) {
      return currentScore;
    }

    if (coords.y() >= this.rows || coords.y() < 0) {
      return currentScore;
    }
    // if the owner of the square at the provided coordinate is not our current
    // player
    // then we also return - stopping our recursion
    if (this.getSquareOwner(coords) != currentPlayer) {
      return currentScore;
    }

    // the current location must have the same player as called this function
    // So we take our currentScore and add the result from our recursive function
    // but ran in differnt cardinal directions (and we increment the current_score
    // by 1)
    // increment our currentScore by 1
    // add our current coords to our boardState
    // we need to only increment the value of our current score when we actually
    // have a value which matches
    // our player
    // We can assume our values match

    // check to the left by decreasing our y axis by 1
    int left = checkScore(new Coords<Integer, Integer>(coords.x(), coords.y() - 1), currentScore + 1, currentPlayer,
        set);

    // check right by increasing x axis by 1
    int right = checkScore(new Coords<Integer, Integer>(coords.x(), coords.y() + 1), currentScore + 1, currentPlayer,
        set);
    // sum left and right together as in connect four you can only by having 4
    // tokens
    // in opposite cardinal directions
    int horizontal = Integer.max(left, right);

    // now check vertically by incrementing and decrementing the x axis
    int down = checkScore(new Coords<Integer, Integer>(coords.x() + 1, coords.y()), currentScore + 1, currentPlayer,
        set);
    int up = checkScore(new Coords<Integer, Integer>(coords.x() - 1, coords.y()), currentScore + 1, currentPlayer, set);
    // get the total of up and down
    int vertical = Integer.max(up, down);
    // now we have to check diagonally - will start with north east and south west
    int northEast = checkScore(new Coords<Integer, Integer>(coords.x() - 1, coords.y() + 1), currentScore + 1,
        currentPlayer, set);
    int southWest = checkScore(new Coords<Integer, Integer>(coords.x() + 1, coords.y() - 1), currentScore + 1,
        currentPlayer, set);
    // sum them like the others
    int diagonalRight = Integer.max(northEast, southWest);
    // now we do north West + south East
    int southEast = checkScore(new Coords<Integer, Integer>(coords.x() + 1, coords.y() - 1), currentScore + 1,
        currentPlayer, set);
    int northWest = checkScore(new Coords<Integer, Integer>(coords.x() - 1, coords.y() + 1), currentScore + 1,
        currentPlayer, set);
    // sum the directions together
    int diagonalLeft = Integer.max(southEast, northWest);

    // now we return the maximum of vertical, horizontal, diagonalRight, diagonaLeft
    // as these represent the
    // directions in which a player can "win" the game. We only need to return 1 of
    // the values which will be whichever is the largest
    return Integer.max(Integer.max(Integer.max(horizontal, vertical), diagonalRight), diagonalLeft);
  }

  public void setSquareOwner(Coords<Integer, Integer> coords, Player player) {
    // given a Pair containing our coordinates we need to set the square a the coord
    // n
    // to be owned by the Player provided
    int x = coords.x();
    int y = coords.y();
    // get the target square
    Square square = this.board.get(x).squares.get(y);
    square.setOwner(player);
  }

  public Player getSquareOwner(Coords<Integer, Integer> coords) {
    return this.board.get(coords.x()).squares.get(coords.y()).getOwner();
  }

  public boolean canPlace(Coords<Integer, Integer> coords, Player player) {
    // can only place when the square is empty and has no owner
    if (coords.x() >= this.rows || coords.x() < 0) {
      return false;
    }
    if (coords.y() >= this.columns || coords.y() < 0) {
      return false;
    }
    if (this.getSquareOwner(coords).getPlayertype() != Player.PlayerType.DEFAULT) {
      return false;
    }
    return true;

  }
  // TODO: Need to add in error stopping player from adding in a new token over
  // existing one
  // this also needs to allow the user to continue with their turn

}
