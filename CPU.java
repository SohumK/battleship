package battleship;

public class CPU extends Player {
	private Coordinate last; //last coordinate fired at
	private Coordinate success;	//last coordinate that ended in a success
	private Ship lastShip; //value of last ship that was successfully hit
	private int hitsLeft; //hits left before a ship was successfully destroyed
	private int dirTry; //direction current ship is facing
	private int count; //part of the fail safe

	//Constructor that places ship and initializes parent class
	public CPU() {
		super();
		last = new Coordinate();
		success = new Coordinate();
		lastShip = new Ship("x");
		placement();
		hitsLeft = 0;
		dirTry = 4;
		count = 0;
	}
	
	//method to choose location to fire at and fire
	public Coordinate chooseFire() {
		//guesses random coordinate
		if (hitsLeft == 0) {
			do {
				int x = (int) (Math.random() * 10) + 1;
				int y = (int) (Math.random() * 10) + 1;
				last = new Coordinate(x, y);
			} while (!playerFire.getValue(last).equals("- "));
			last = fire(last);
			dirTry = 5;
		//guesses direction that the ship last hit is facing
		} else if (hitsLeft == lastShip.getSize()) {
			dirTry--;
			switch (dirTry) {
			case 4:
				if (success.getY() == 1)
					chooseFire();
				else {
					last = fire(new Coordinate(success.getX(),
							success.getY() - 1));
					count=0;
				}
				break;
			case 3:
				if (success.getY() == 10)
					chooseFire();
				else {
					last = fire(new Coordinate(success.getX(),
							success.getY() + 1));
					count=0;
				}
				break;
			case 2:
				if (success.getX() == 1)
					chooseFire();
				else {
					last = fire(new Coordinate(success.getX() - 1,
							success.getY()));
					count=0;
				}
				break;
			case 1:
				if (success.getX() == 10)
					chooseFire();
				else {
					last = fire(new Coordinate(success.getX() + 1,
							success.getY()));
					count=0;
				}
				break;
			default:
				last = fire(new Coordinate(1, 1));
				hitsLeft = 0;
				dirTry = 4;
				break;
			}
		//fires in the direction of the last hit ship
		} else if (hitsLeft < lastShip.getSize()) {
			switch (dirTry) {
			case 4:
				if (success.getY() == 1) {
					hitsLeft = 0;
					chooseFire();
				} else {
					last = fire(new Coordinate(success.getX(),
							success.getY() - 1));
					hitsLeft--;
				}
				break;
			case 3:
				if (success.getY() == 10) {
					hitsLeft = 0;
					chooseFire();
				} else {
					last = fire(new Coordinate(success.getX(),
							success.getY() + 1));
					hitsLeft--;
				}
				break;
			case 2:
				if (success.getX() == 1) {
					hitsLeft = 0;
					chooseFire();
				} else {
					last = fire(new Coordinate(success.getX() - 1,
							success.getY()));
					hitsLeft--;
				}
				break;
			case 1:
				if (success.getX() == 10) {
					hitsLeft = 0;
					chooseFire();
				} else {
					last = fire(new Coordinate(success.getX() + 1,
							success.getY()));
					hitsLeft--;
				}
				break;
			default:
				last = fire(new Coordinate(1, 1));
				hitsLeft = 0;
				dirTry = 4;
				break;
			}
		}
		//fail safe in case loop is encountered
		if (count == 1)
			hitsLeft = 0;
		return last;
	}
	//sets result of hits on board and sets variables effecting next fire
	public void fireResult(String result) {
		if(hitsLeft>0&&hitsLeft<lastShip.getSize()&&result.equals("M ")){
			if(dirTry == 1)
				dirTry =2;
			if(dirTry == 2)
				dirTry = 1;
			if(dirTry == 4)
				dirTry = 3;
			if(dirTry == 3)
				dirTry = 4;
		}
		playerFire.setValue(result, lastFire);
		if (result.equals("M "))
			count++;
		if (!result.equals("M "))
			success = last;
		if (!result.equals("M ") && hitsLeft > 0)
			hitsLeft--;
		if (hitsLeft == 0) {
			lastShip = new Ship(result);
			hitsLeft = lastShip.getSize();
			count = 0;
		}
	}
	// places all ships at random coordinates
	private void placement() {
		int x = 1;
		Coordinate c;
		Ship s;
		int z;
		while (x < 6) {
			c = new Coordinate((int) (Math.random() * 10) + 1,
					(int) (Math.random() * 10) + 1);
			s = new Ship(chooseBoat(x));
			z = (int) (Math.random() * 4) + 1;
			//makes sure ship was validly set before incrementing
			if (super.placePiece(s, c, chooseDirection(z)))
				x++;
		}
	}
	// chooses one of the 5 ships based on provided value
	private String chooseBoat(int s) {
		switch (s) {
		case 1:
			return "aircraft carrier";
		case 2:
			return "battleship";
		case 3:
			return "submarine";
		case 4:
			return "destroyer";
		case 5:
			return "patrol boat";
		default:
			return "invalid ship";
		}
	}
	// chooses one of the 4 directions based on provided value
	private String chooseDirection(int x) {
		switch (x) {
		case 1:
			return "up";
		case 2:
			return "left";
		case 3:
			return "right";
		case 4:
			return "down";
		default:
			return "error";
		}
	}
}
