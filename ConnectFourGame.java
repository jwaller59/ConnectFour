import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

  // TODO: Add in correct exeptions for classes
  // TODO: Add custom Display class module (so we can easily use different
  // outputs)
  private Board board;
  private int round;
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

  // main event Loop
  public void startGame() {
    // player 1 always goes first
    while (true) {
      try {
        this.printBoard();
        String input = getUserInput();
        Coords<Integer, Integer> coords = retrieveCoords(input);
        this.board.setSquareOwner(coords, this.player1);
        HashSet<Coords<Integer, Integer>> boardState = new HashSet<>();
        this.player1.setScore(this.board.checkScore(coords, 0, this.player1, boardState));
        System.out.println(this.player1.getScore());
        this.printBoard();
        this.endGame();
        this.cpuPlay();
      } catch (IOException e) {
        System.exit(1);
      }

    }

  }

  public Board getBoard() {
    return this.board;
  }

  public void cpuPlay() {
    // We generate two random numbers between our row and column sizes
    // and then keep seeing if this location is avaiable in our board
    // once the space is available we enter the value and return
    Coords<Integer, Integer> coords = this.generate_coords();
    this.board.setSquareOwner(coords, this.player2);
    HashSet<Coords<Integer, Integer>> boardState = new HashSet<>();
    this.board.checkScore(coords, 0, this.player2, boardState);
    this.endGame();

  }

  private Coords<Integer, Integer> generate_coords() {
    Random gen = new Random(4);
    Coords<Integer, Integer> coord;
    do {
      int x = gen.nextInt() % 6;
      int y = gen.nextInt() % 4;
      coord = new Coords<Integer, Integer>(x, y);
    } while (!this.getBoard().canPlace(coord, this.player2));
    return coord;

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

  private Coords<Integer, Integer> retrieveCoords(String input) {
    // retrieve the coordinate input as a String and convert
    // to a Coordinate object by splitting our string on a ','
    String[] splitString = input.split(",");
    int x = Integer.valueOf(splitString[0]);
    int y = Integer.valueOf(splitString[1]);
    return new Coords<Integer, Integer>(x, y);
  }

  private boolean isGameOver() {
    if (this.player1.getScore() == 4 || this.player2.getScore() == 4) {
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
