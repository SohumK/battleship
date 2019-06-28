package battleship;

public class Board {
	private String[][] battleBoard;

	// initializing the battle board to an 11 by 11 array with the first row and
	// column being labeled
	public Board() {
		battleBoard = new String[11][11];
		for (int r = 0; r < 11; r++) {
			for (int c = 0; c < 11; c++) {
				if (r == 0) {
					battleBoard[r][c] = new String(c + " ");
				} else if (c == 0) {
					battleBoard[r][c] = new String(r + " ");
				} else {
					battleBoard[r][c] = "- ";
				}
			}
		}
	}

	// returns value at that coordinate of the array
	public String getValue(Coordinate c) {
		return battleBoard[c.getX()][c.getY()];
	}

	// sets the value at coordinate to provided String
	public void setValue(String s, Coordinate c) {
		battleBoard[c.getX()][c.getY()] = s;
	}

	// refer to method name
	public void printBoard() {
		for (int r = 0; r < 11; r++) {
			for (int c = 0; c < 11; c++)
				System.out.print(battleBoard[r][c]);
			System.out.println();
		}
	}

	// removes all instances of a string
	public void remove(String s) {
		for (int r = 0; r < 11; r++)
			for (int c = 0; c < 11; c++)
				if (battleBoard[r][c].equals(s))
					battleBoard[r][c] = "";
	}
}
