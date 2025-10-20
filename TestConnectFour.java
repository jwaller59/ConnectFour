import java.util.Random;
import java.util.HashSet;

public class TestConnectFour {

  public static void main(String[] args) {
    TestConnectFour tester = new TestConnectFour();
    tester.testBoardSetSquare();
    tester.testGetScore();
    tester.testRandomGen();

  }

  public void testBoardSetSquare() {
    Board board = new Board(4, 6);
    Coords coords = new Coords(2, 2);
    Player player = new Player("PlayerOne", '1', Player.PlayerType.PLAYER1);
    board.setSquareOwner(coords, player);
    Square sq = new Square();
    TestConnectFourHelper.testObjectEquals(
        board.getBoard().get(coords.getX()).getSquares().get(coords.getY()).getOwner(),
        player);
  }

  public void testGetScore() {
    Board board = new Board(4, 6);
    Coords coord_one = new Coords(2, 2);
    Coords coord_two = new Coords(2, 3);
    Coords coord_three = new Coords(2, 1);
    Coords coord_five = new Coords(0, 0);
    Player player = new Player("PlayerOne", '1', Player.PlayerType.PLAYER1);
    board.setSquareOwner(coord_one, player);
    board.setSquareOwner(coord_two, player);
    board.setSquareOwner(coord_three, player);
    board.setSquareOwner(coord_five, player);
    // checkTotalScore method should return 2
    HashSet<Coords> boardState = new HashSet<>();
    int score = board.calculateScore(coord_two, 0, player, boardState);
    System.out.println(score);
    TestConnectFourHelper.testObjectEquals(score, 3);

  }

  public void testRandomGen() {
    Random a = new Random(3);
    for (int i = 0; i < 10; i++) {
      int b = a.nextInt();
      System.out.println(-(b % 3));
      System.out.println(-(b % 5));
    }
  }

  public void testTokenOutSideIndex() {
    // This test should pass when a player
    // attempts to place a token outside of the array bounds of the board
    // by not throwing an index error but asking the player to try again
    Board board = new Board(4, 6);
    Coords coord = new Coords(5, 8);
    HashSet<Coords> boardState = new HashSet<>();
    Player player = new Player("player1", '1', Player.PlayerType.PLAYER1);
    board.calculateScore(coord, 0, player, boardState);
  }

}
