package battleship;

import java.util.ArrayList;

public class Player {
	private Board playerBoard; //board with ships and damage
	protected Board playerFire;	//board with shot attempts
	private ArrayList<String> pieces; //set ship register
	protected Coordinate lastFire;	//location last fired at
	
	//constructor initializes variables
	public Player() {
		playerBoard = new Board();
		playerFire = new Board();
		pieces = new ArrayList<String>(6);
		lastFire = new Coordinate();
	}

	// prints out your attempts and your ships
	public void printBoards() {
		System.out.println("Current Attempts");
		playerFire.printBoard();
		System.out.println("Your Ships and Damage");
		playerBoard.printBoard();
	}

	// places a ship with proper orientation
	public boolean placePiece(Ship name, Coordinate loc, String direction) {
		int x = name.getSize();
		//if user places ship going north
		if (direction.equals("up")) {
			//checks that piece has yet to be set and is in bounds
			if ((loc.getY() - x) <= 0||pieceSet(name.getName()))
				return false;
			else {
				//checks that it doesn't overlap with other ships
				for (int i = 0; i < x; i++) {
					String s = playerBoard.getValue(new Coordinate(loc.getY() - i, loc.getX()));
					if(!s.equals("- "))
						return false;
				}
				//places ship
				for (int i = 0; i < x; i++) {
					playerBoard.setValue(name.getName(),
							new Coordinate(loc.getY() - i, loc.getX()));
				}
				if (!pieceSet(name.getName()))
					pieces.add(name.getName());
				return true;
			}
		}
		//if user places ship going west
		if (direction.equals("left")) {
			//checks that piece has yet to be set and is in bounds
			if ((loc.getX() - x) <= 0||pieceSet(name.getName()))
				return false;
			else {
				//checks that it doesn't overlap with other ships
				for (int i = 0; i < x; i++) {
					String s = playerBoard.getValue(new Coordinate(loc.getY(), loc.getX() - i));
					if(!s.equals("- "))
						return false;
				}
				//places ship
				for (int i = 0; i < x; i++) {
					playerBoard.setValue(name.getName(),
							new Coordinate(loc.getY(), loc.getX() - i));
				}
				if (!pieceSet(name.getName()))
					pieces.add(name.getName());
				return true;
			}
		}
		//if user places ship going east
		if (direction.equals("right")) {
			//checks that piece has yet to be set and is in bounds
			if ((loc.getX() + x) >= 11||pieceSet(name.getName()))
				return false;
			else {
				//checks that it doesn't overlap with other ships
				for (int i = 0; i < x; i++) {
					String s = playerBoard.getValue(new Coordinate(loc.getY(), loc.getX() + i));
					if(!s.equals("- "))
						return false;
				}
				//places ship
				for (int i = 0; i < x; i++) {
					playerBoard.setValue(name.getName(),
							new Coordinate(loc.getY(), loc.getX() + i));
				}
				if (!pieceSet(name.getName()))
					pieces.add(name.getName());
				return true;
			}
		}
		//if user places ship going south
		if (direction.equals("down")) {
			//checks that piece has yet to be set and is in bounds
			if ((loc.getY() + x) >= 11||pieceSet(name.getName()))
				return false;
			else {
				//checks that it doesn't overlap with other ships
				for (int i = 0; i < x; i++) {
					String s = playerBoard.getValue(new Coordinate(loc.getY() + i, loc.getX()));
					if(!s.equals("- "))
						return false;
				}
				//places ship
				for (int i = 0; i < x; i++) {
					playerBoard.setValue(name.getName(),
							new Coordinate(loc.getY() + i, loc.getX()));
				}
				if (!pieceSet(name.getName()))
					pieces.add(name.getName());
				return true;
			}
		}
		return false;
	}

	// checks if a ship is already on the field
	private boolean pieceSet(String s) {
		for (int i = 0; i < pieces.size(); i++)
			if (pieces.get(i).equals(s))
				return true;
		return false;
	}

	// removes a ship from the board and allows you to place again
	public void removePiece(String s) {
		if (pieceSet(s)) {
			pieces.remove(s);
			playerBoard.remove(s);
		}
	}

	// called to check if all this players ships have been destroyed
	public boolean lost() {
		int count = 0;
		for (int r = 0; r < 11; r++) {
			for (int c = 0; c < 11; c++) {
				if (playerBoard.getValue(new Coordinate(r, c)).equals("H ")) {
					count++;
				}
			}
		}
		return count == 17;
	}

	// checks to see if the passed Coordinate hit a ship
	// returns ship name if a ship was hit or M if a miss
	public String wasHit(Coordinate c) {
		String s = playerBoard.getValue(c);
		if (!s.equals("- ") && !s.equals("H ")) {
			playerBoard.setValue("H ", c);
			return s;
		}
		return "M ";
	}

	// Choosing which coordinate to fire at
	public Coordinate fire(Coordinate c) {
		lastFire = new Coordinate(c.getY(), c.getX());
		return c;
	}
	
	// updating playerFire board for last fire result
	public void fireResult(String result) {
		playerFire.setValue(result, lastFire);
	}
}