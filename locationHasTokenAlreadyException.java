public class locationHasTokenAlreadyException extends Exception {
  public locationHasTokenAlreadyException() {
    super("The target location already has an existing token present. Please try another square");
  }
}
