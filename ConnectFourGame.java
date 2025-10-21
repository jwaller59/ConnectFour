import java.io.IOException;
import java.util.stream.Stream;

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

  // TODO: Add custom input class for retrieving user input so we could easily
  // change input
  // to come from IO or from web console etc instead of just Std in/out
  private Board board;
  private int round;

  public int getRound() {
    return round;
  }

  public void setRound(int round) {
    this.round = round;
  }

  private InputReader input;
  private final int WINNING_SCORE = 4;
  private Display output;

  private Player player1;
  private Player player2;

  public ConnectFourGame(int row, int column) {
    this.board = new Board(row, column);
    this.input = new Input();
    this.output = new Output();
    this.player1 = new Player("Player 1", '1', Player.PlayerType.PLAYER1);
    this.player2 = new Player("Player 2", '2', Player.PlayerType.CPU);
  }

  // TODO: Have player1 and player2 setting prompts during setup (as well as game
  // size):

  // main event Loop
  public void startGame() {
    // player 1 always goes first
    while (true) {
      Stream.of(this.player1, this.player2).forEach(player -> {
        this.printSeperator();
        this.printBoard();
        this.printSeperator();
        this.printTurn(player);
        this.eventLoopRouting(player);
        this.isEndGame(player);
      });
    }
  }

  private void eventLoopRouting(Player player) {
    if (player.getPlayertype() == Player.PlayerType.CPU) {
      this.cpuPlay(player);
      return;
    } else {
      this.PlayerEventLoop(player);
    }
  }

  private void PlayerEventLoop(Player player) {
    // player event loop is as follows
    // 1) we prompt user for their coord input.
    // 2) We attempt to add the players token to the required board location (if it
    // fails we prompt them for location again)
    // 3) We calculate the score for the player after token successfully entered
    // 4) We exit the player event loop (and return to the game event loop which
    // prints the board)
    while (true) {
      try {
        this.promptUserInput();
        String input = getUserInput();
        Coords coords = retrieveCoords(input);
        if (!this.board.canPlace(coords)) {
          System.out.println("Cannot place a token here: Try again");
        } else {
          this.board.setSquareOwner(coords, player);
          player.setScore(this.board.checkScore(coords, player));
          break;
        }
      } catch (CoordNotValidCoordException | CoordOutOfBoundsException e) {
        String output = e.toString();
        this.output.displayOutput(output);
      } catch (IOException e) {
        System.exit(1);
      }
    }
  }

  public Board getBoard() {
    return this.board;
  }

  private void promptUserInput() {
    String output = "Please add your token placement:";
    this.output.displayOutput(output);
  }

  private void cpuPlay(Player player) {
    if (this.player2.getCoords().isEmpty()) {
      // if our coord list is empty for whatever reason - we just regenerate them
      this.player2.generate_coords(this.board.getRows(), this.board.getColumns());
    }
    Coords coord = this.player2.getCoords().removeFirst();
    this.board.setSquareOwner(coord, player);
    this.board.checkScore(coord, player);
  }

  public void setPlayer1(int playerChoice) {

  }

  public void setPlayer2(int playerChoice) {

  }

  public void printBoard() {
    this.output.displayOutput(this.board.toString());
  }

  public String getUserInput() throws IOException {
    String input = this.input.readInput();
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

  private boolean isGameOver(Player player) {
    if (player.getScore() != this.WINNING_SCORE) {
      return false;
    }
    return true;
  }

  private void isEndGame(Player player) {
    if (!isGameOver(player)) {
      return;
    }
    printWinningMessage(player);
    System.exit(1);
  }

  private void printWinningMessage(Player player) {
    String winner = String.format("Player %s has won!", player.getName());
    this.output.displayOutput(winner);
  }

  private void printSeperator() {
    String sep = "=".repeat(this.board.getRows() * 3);
    this.output.displayOutput(sep + "\n");
  }

  private void printTurn(Player player) {
    String turn = String.format("Its now %s turn! \n", player.getName());
    this.output.displayOutput(turn);
  }

}
