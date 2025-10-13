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
    Coords<Integer, Integer> coords = new Coords<Integer, Integer>(2, 2);
    Player player = new Player("PlayerOne", '1', Player.PlayerType.PLAYER1);
    board.setSquareOwner(coords, player);
    Square sq = new Square();
    TestConnectFourHelper.testObjectEquals(board.getBoard().get(coords.x()).getSquares().get(coords.y()).getOwner(),
        player);
  }

  public void testGetScore() {
    Board board = new Board(4, 6);
    Coords<Integer, Integer> coord_one = new Coords<Integer, Integer>(2, 2);
    Coords<Integer, Integer> coord_two = new Coords<Integer, Integer>(2, 3);
    Coords<Integer, Integer> coord_three = new Coords<Integer, Integer>(2, 1);
    Coords<Integer, Integer> coord_five = new Coords<Integer, Integer>(0, 0);
    Player player = new Player("PlayerOne", '1', Player.PlayerType.PLAYER1);
    board.setSquareOwner(coord_one, player);
    board.setSquareOwner(coord_two, player);
    board.setSquareOwner(coord_three, player);
    board.setSquareOwner(coord_five, player);
    // checkTotalScore method should return 2
    HashSet<Coords<Integer, Integer>> boardState = new HashSet<>();
    int score = board.checkScore(coord_two, 0, player, boardState);
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

}
