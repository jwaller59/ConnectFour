public class Player {
  public static enum PlayerType {
    PLAYER1,
    PLAYER2,
    DEFAULT,
  }

  // Player class should have the following fields
  // 1) char that represents them
  // 2) String Name
  // 3) Current score
  String name;
  int score;
  char repre;
  PlayerType playertype;

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
  }

  public PlayerType getPlayertype() {
    return playertype;
  }

  public void setPlayertype(PlayerType playertype) {
    this.playertype = playertype;
  }
}
