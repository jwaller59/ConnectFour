import java.util.ArrayList;
import java.util.HashMap;

// should contain and maintain board state - including returning scores
record Pair<K, V>(K first, V second) {
};

public class Board {
	private ArrayList<Row> board;

	private HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>> boardState;

	public Board(int rows, int columns) {
		createBoard(rows, columns);
		this.boardState = new HashMap<>();

	}

	public ArrayList<Row> getBoard() {
		return this.board;
	}

	private void createBoard(int rows, int columns) {
		// create our board based on the number of columns and rows provided
		ArrayList<Row> board = new ArrayList<Row>();
		for (int i = 0; i < columns; i++) {
			board.add(new Row(rows));
		}
		this.board = board;
	}

	@Override
	public String toString() {
		StringBuilder temp = new StringBuilder();
		for (Row row : board) {
			temp.append(row);
			temp.append('\n');

		}
		return temp.toString();

	}

	public void setSquare() {
		// update our statemap and push down our square ownership

	}

}
