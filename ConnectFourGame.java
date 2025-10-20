import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Random;

// Connect Four Class should only control game event loop and user inputs
// as well as win conditions and computer player inputs
// Board class should contain and control board state
public class ConnectFourGame {
  public static void main(String[] args) {
    ConnectFourGame game = new ConnectFourGame(6, 6);
    game.startGame();
    game.printBoard();
  }

  // TODO: Create some kind Turn Interface for CPU and Player Turns
  // Turn interface will ensure that they will have the correct output

  // TODO: Add custom Display class module (so we can easily use different
  // outputs)
  private Board board;
  private int round;

  public int getRound() {
    return round;
  }

  public void setRound(int round) {
    this.round = round;
  }

  private BufferedReader reader;
  private final int WINNING_SCORE = 4;

  private Player player1;
  private Player player2;

  public ConnectFourGame(int row, int column) {
    this.board = new Board(row, column);
    this.reader = new BufferedReader(new InputStreamReader(System.in));
    this.player1 = new Player("Player 1", '1', Player.PlayerType.PLAYER1);
    this.player2 = new Player("Player 2", '2', Player.PlayerType.PLAYER2);
  }

  // TODO: Have player1 and player2 setting prompts during setup (as well as game
  // size):
  // provide ability for second player to be CPU or human player. If CPU selected
  // then we
  // do different actions for event loop. If player2 selected then we loop through
  // our normal player event loop

  // main event Loop
  public void startGame() {
    // player 1 always goes first
    while (true) {
      try {
        this.printBoard();
        this.PlayerEventLoop(this.player1);
        this.printBoard();
        this.endGame();
        this.CpuEventLoop(this.player2);
        this.endGame();
      } catch (IOException e) {
        System.exit(1);
      } catch (CoordOutOfBoundsException e) {
        System.out.println(
            "The coord provided is out of range for the game board. Please re-enter new coords between the range of");
      } catch (CoordNotValidCoordException e) {
        System.out.println(e);
      }

    }

  }

  private void PlayerEventLoop(Player player)
      throws IOException, CoordNotValidCoordException, CoordOutOfBoundsException {
    // player event loop is as follows
    // 1) we prompt user for their coord input.
    // 2) We attempt to add the players token to the required board location (if it
    // fails we prompt them for location again)
    // 3) We calculate the score for the player after token successfully entered
    // 4) We exit the player event loop (and return to the game event loop which
    // prints the board)
    while (true) {
      String input = getUserInput();
      Coords coords = retrieveCoords(input);
      if (!this.board.canPlace(coords)) {
        System.out.println("Cannot place a token here: Try again");
      } else
        this.board.setSquareOwner(coords, this.player1);
      this.player1.setScore(this.board.checkScore(coords, this.player1));
      System.out.printf("Current Score is %d\n", this.player1.getScore());
      break;
    }
  }

  private void CpuEventLoop(Player player) {
    // CPU event loop
    // cpu event loop needs to have a random generator which is stored outside the
    // CpuEventLoop
    // This is to ensure we aren't repeatadly calling random on the same seed -
    // resulting in us
    // trying to insert the same values in on each CPU turn in subsequent order
    cpuPlay();
  }

  public Board getBoard() {
    return this.board;
  }

  public void cpuPlay() {
    if (this.player2.getCoords().isEmpty()) {
      // if our coord list is empty for whatever reason - we just regenerate them
      this.player2.generate_coords(this.board.getRows(), this.board.getColumns());
    }
    Coords coord = this.player2.getCoords().removeFirst();
    this.board.setSquareOwner(coord, this.player2);
    this.board.checkScore(coord, this.player2);
  }

  public void setPlayer1(int playerChoice) {

  }

  public void setPlayer2(int playerChoice) {

  }

  public void printBoard() {
    System.out.println(this.board);
  }

  public String getUserInput() throws IOException {
    String input = this.reader.readLine();
    // split string into array on comma value
    // this.board.setSquare(val1, val2);
    return input;
  }

  private Coords retrieveCoords(String input)
      throws CoordOutOfBoundsException, CoordNotValidCoordException {
    // retrieve the coordinate input as a String and convert
    // to a Coordinate object by splitting our string on a ','
    // Also have to throw error if the entered value is not a coord or integer
    try {
      String[] splitString = input.split(",");
      int x = Integer.valueOf(splitString[0]);
      int y = Integer.valueOf(splitString[1]);
      if (x > this.board.getRows() || x < 0 || y > this.board.getColumns() || y < 0) {
        throw new CoordOutOfBoundsException();
      }
      return new Coords(x, y);
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      // If a user does not put in a comma then split function throws a
      // ArrayIndexOutOfBoundsException
      // If the user enters any char value e.g a b c etc - then Integer.valueOf throws
      // a NumberFormatException
      // NumberFormatException
      throw new CoordNotValidCoordException();
    }

  }

  private boolean isGameOver() {
    if (this.player1.getScore() == this.WINNING_SCORE || this.player2.getScore() == this.WINNING_SCORE) {
      return true;
    }
    return false;
  }

  private void endGame() {
    if (!isGameOver()) {
      return;
    }
    if (this.player1.getScore() == 4) {
      System.out.println("Player 1 has won!");
      System.exit(1);
    } else
      System.out.println("Player 2 has won!");
    System.exit(1);
  }

}
