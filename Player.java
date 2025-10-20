import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player implements Turn {
  public static enum PlayerType {
    PLAYER1,
    PLAYER2,
    DEFAULT,
    CPU
  }

  // Player class should have the following fields
  // 1) char that represents them
  // 2) String Name
  // 3) Current score
  String name;
  int score;
  char repre;
  PlayerType playertype;
  ArrayList<Coords> coords;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public char getRepre() {
    return repre;
  }

  public void setRepre(char repre) {
    this.repre = repre;
  }

  public Player(String name, char repr, PlayerType playertype) {
    this.name = name;
    this.repre = repr;
    this.playertype = playertype;
    this.coords = new ArrayList<Coords>();
  }

  public PlayerType getPlayertype() {
    return playertype;
  }

  public void setPlayertype(PlayerType playertype) {
    this.playertype = playertype;
  }

  public void generate_coords(int row, int column) {
    // generate the coords on instantuation of our player
    // we then map over the
    int max_stream_size = 100;
    IntStream gen = new Random().ints(max_stream_size);
    // for each value in the stream
    int[] co = gen.toArray();
    for (int a = 0; a < co.length; a += 2) {
      int x = Math.abs(co[a]) % row;
      int y = Math.abs(co[a + 1]) % column;
      System.out.printf("coords of %d:%d\n", x, y);
      Coords coord = new Coords(x, y);
      this.coords.add(coord);
    }

  }

  public ArrayList<Coords> getCoords() {
    return coords;
  }

  public void setCoords(ArrayList<Coords> coords) {
    this.coords = coords;
  }

}
