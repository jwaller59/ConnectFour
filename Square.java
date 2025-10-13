public class Square {
  private Player owner;

  public Square() {
    this.owner = new Player("default", ' ', Player.PlayerType.DEFAULT);
  }

  public Player getOwner() {
    return this.owner;
  }

  public final void setOwner(Player player) {
    this.owner = player;
  }

  public String printSquare() {
    String r = String.format("[%c]", this.owner.getRepre());
    return r;
  }

  public boolean isAvailableSquare() {
    if (this.getOwner().getPlayertype() != Player.PlayerType.DEFAULT) {
      return false;
    }
    return true;

  }

}
