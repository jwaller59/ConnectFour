import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input implements InputReader {

  BufferedReader reader;

  public Input() {
    this.reader = new BufferedReader(new InputStreamReader(System.in));
  }

  public String readInput() throws IOException {
    String input = this.reader.readLine();
    return input;
  }

}
