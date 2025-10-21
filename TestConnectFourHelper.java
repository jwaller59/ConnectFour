public class TestConnectFourHelper {
  public static boolean testObjectEquals(Object a, Object b) {
    if (!a.equals(b)) {
      String errorMessage = String.format("Error, the input of %s did not match the input of %s", a, b);
      throw new Error(errorMessage);
    }
    return true;

  }
}
