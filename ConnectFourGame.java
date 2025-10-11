import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

// Connect Four Class should only control game event loop and user inputs
// as well as win conditions and computer player inputs
// Board class should contain and control board state
public class ConnectFourGame {
	public static void main(String[] args) {
		ConnectFourGame game = new ConnectFourGame(4, 4);
		game.startGame();
		game.printBoard();
	}

	private Board board;
	private int round;
	private BufferedReader reader;
	private final int WINNING_SCORE = 4;

	// TODO: modify this - we should have a different player service
	private Player player1;
	private Player player2;

	public ConnectFourGame(int row, int column) {
		this.board = new Board(row, column);
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}

	// main event Loop
	public void startGame() {
		// player 1 always goes first
		// this works for setting a square
		// board.getBoard().get(1).getSquares().get(1).setOwner('r');
		// board.getBoard().get(2).getSquares().get(3).setOwner('h');
		while (true) {
			this.printBoard();
			getUserInput();

		}

	}

	public Board getBoard() {
		return this.board;
	}

	public void setPlayer1(int playerChoice) {

	}

	public void setPlayer2(int playerChoice) {

	}

	public void printBoard() {
		System.out.println(this.board);
	}

	public void getUserInput() {
		try {
			String input = this.reader.readLine();
			// split string into array on comma value
			String[] a = input.split(",");
			// convert each value into integers
			int val1 = Integer.valueOf(a[0]);
			int val2 = Integer.valueOf(a[1]);
			// this.board.setSquare(val1, val2);

		} catch (IOException e) {

		}

	}

}
