import java.util.ArrayList;
import java.util.HashSet;

public class Board {
  private ArrayList<Row> board;
  private int rows;
  private int columns;
  private HashSet<Coords> boardState = new HashSet<>();

  public HashSet<Coords> getBoardState() {
    return boardState;
  }

  public void setBoardState(HashSet<Coords> boardState) {
    this.boardState = boardState;
  }

  public Board(int rows, int columns) {
    createBoard(rows, columns);
    this.rows = rows;
    this.columns = columns;
    this.boardState = new HashSet<Coords>();
  }

  public void setBoard(ArrayList<Row> board) {
    this.board = board;
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public int getColumns() {
    return columns;
  }

  public void setColumns(int columns) {
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

  public int checkScore(Coords coords, Player currentPlayer) {
    HashSet<Coords> boardState = new HashSet<>();
    int currentScore = 0;
    return this.calculateScore(coords, currentScore, currentPlayer, boardState);
  }

  public int calculateScore(Coords coords, int currentScore, Player currentPlayer,
      HashSet<Coords> set) {
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
    if (coords.getX() >= this.columns || coords.getX() < 0) {
      return currentScore;
    }

    if (coords.getY() >= this.rows || coords.getY() < 0) {
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
    int left = calculateScore(new Coords(coords.getX(), coords.getY() - 1), currentScore + 1, currentPlayer,
        set);

    // check right by increasing x axis by 1
    int right = calculateScore(new Coords(coords.getX(), coords.getY() + 1), currentScore + 1,
        currentPlayer,
        set);
    // sum left and right together as in connect four you can only by having 4
    // tokens
    // in opposite cardinal directions
    int horizontal = Integer.max(left, right);

    // now check vertically by incrementing and decrementing the x axis
    int down = calculateScore(new Coords(coords.getX() + 1, coords.getY()), currentScore + 1, currentPlayer,
        set);
    int up = calculateScore(new Coords(coords.getX() - 1, coords.getY()), currentScore + 1, currentPlayer,
        set);
    // get the total of up and down
    int vertical = Integer.max(up, down);
    // now we have to check diagonally - will start with north east and south west
    int northEast = calculateScore(new Coords(coords.getX() - 1, coords.getY() + 1), currentScore + 1,
        currentPlayer, set);
    int southWest = calculateScore(new Coords(coords.getX() + 1, coords.getY() - 1), currentScore + 1,
        currentPlayer, set);
    // sum them like the others
    int diagonalRight = Integer.max(northEast, southWest);
    // now we do north West + south East
    int southEast = calculateScore(new Coords(coords.getX() + 1, coords.getY() + 1), currentScore + 1,
        currentPlayer, set);
    int northWest = calculateScore(new Coords(coords.getX() - 1, coords.getY() - 1), currentScore + 1,
        currentPlayer, set);
    // sum the directions together
    int diagonalLeft = Integer.max(southEast, northWest);

    // now we return the maximum of vertical, horizontal, diagonalRight, diagonaLeft
    // as these represent the
    // directions in which a player can "win" the game. We only need to return 1 of
    // the values which will be whichever is the largest
    return Integer.max(Integer.max(Integer.max(horizontal, vertical), diagonalRight), diagonalLeft);
  }

  public void setSquareOwner(Coords coords, Player player) {
    // given a Pair containing our coordinates we need to set the square a the coord
    // n
    // to be owned by the Player provided
    if (!this.canPlace(coords)) {
      return;
    }
    int x = coords.getX();
    int y = coords.getY();
    Square square = this.board.get(x).squares.get(y);
    square.setOwner(player);
    this.boardState.add(coords);
    return;
  }

  public Player getSquareOwner(Coords coords) {
    return this.board.get(coords.getX()).squares.get(coords.getY()).getOwner();
  }

  public boolean canPlace(Coords coords) {
    // can only place when the square is empty and has no owner
    if (coords.getX() >= this.rows || coords.getX() < 0) {
      return false;
    }
    if (coords.getY() >= this.columns || coords.getY() < 0) {
      return false;
    }
    if (this.boardState.contains(coords)) {
      return false;
    }
    return true;

  }

}
