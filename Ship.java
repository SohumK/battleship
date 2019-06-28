package battleship;

public class Ship {
	private String name; //name of the ship
	private int size; //size of the ship
	
	//based on a provided string determines a ships name and size
	public Ship(String title) {
		if(title.length()>=1)
			name = title.substring(0, 1).toLowerCase();
		else
			name = "";
		switch (name) {
		case "a":
			size = 5;
			break;
		case "b":
			size = 4;
			break;
		case "s":
			size = 3;
			break;
		case "d":
			size = 3;
			break;
		case "p":
			size = 2;
			break;
		default:
			size = 0;
		}
	}
	//returns the shorthand name of the ship
	public String getName() {
		switch (name) {
		case "a":
			return "a ";
		case "b":
			return "b ";
		case "s":
			return "s ";
		case "d":
			return "d ";
		case "p":
			return "p ";
		default:
			return "i ";
		}
	}
	// returns the size of the ship
	public int getSize(){
		return size;
	}
}
