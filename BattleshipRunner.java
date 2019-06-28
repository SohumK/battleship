package battleship;

import java.util.Scanner;

public class BattleshipRunner {
	public static void main(String[] args) {
		System.out.println("Enter the number of what you would like to do:");
		System.out.println("1: Play against another human");
		System.out.println("2: Play against a CPU");
		System.out.println("3: Information");
		System.out.println("4: Quit");
		Scanner scn = new Scanner(System.in);
		int x = scn.nextInt();
		if (x == 1)
			oneV1();
		else if (x == 2)
			cpuGame(args);
		else if (x == 3)
			info(args);
		else if (x == 4)
			quit();
		else {
			System.out.println("Bad input! Try again!");
			main(args);
		}
	}

	public static void oneV1() {
		Player p1 = new Player();
		Player p2 = new Player();
		int turn = 0;
		while (!p1.lost() && !p2.lost()) {
			if (turn == 0) {
				System.out
						.println("First Player. It is your turn to place your ships.");
				for (int i = 0; i < 5; i++)
					choosePieces(p1);
				System.out
						.println("\n\n\n\nSecond Player. It is now your turn to place your ships.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < 5; i++)
					choosePieces(p2);
				System.out.println("Second player you go first!");
				turn++;
			} else if (turn % 2 == 1) {
				System.out.println("Player 2 it is your" + (turn/2+1) + "turn.");
				System.out
						.println("Enter the number of what you would like to do this round:");
				System.out.println("1: Fire");
				System.out.println("2: Check Board");
				System.out.println("3: Surrender");
				Scanner scn = new Scanner(System.in);
				int check = scn.nextInt();
				if (check == 1) {
					int x = 0;
					int y = 0;
					while (x > 10 || x < 1 || y > 10 || y < 1) {
						System.out.println("Enter the Starting X Value:");
						x = scn.nextInt();
						System.out.println("Enter the Starting Y Value:");
						y = scn.nextInt();
					}
					Coordinate c = new Coordinate(x, y);
					p2.fire(c);
					String s = p1.wasHit(c);
					p2.fireResult(s);
					System.out.println("Your torpedo " + hitShip(s) + " at "
							+ c);
					turn++;
				} else if (check == 2)
					p2.printBoards();
				else if (check == 3) {
					System.out.println("Player 2 surrenders! Player 1 wins!");
					String[] args = { "123" };
					main(args);
				}
			} else if (turn % 2 == 0) {
				System.out.println("Player 1 it is your" + (turn/2) + "turn.");
				System.out
						.println("Enter the number of what you would like to do this round:");
				System.out.println("1: Fire");
				System.out.println("2: Check Board");
				System.out.println("3: Surrender");
				Scanner scn = new Scanner(System.in);
				int check = scn.nextInt();
				if (check == 1) {
					int x = 0;
					int y = 0;
					while (x > 10 || x < 1 || y > 10 || y < 1) {
						System.out.println("Enter the Starting X Value:");
						x = scn.nextInt();
						System.out.println("Enter the Starting Y Value:");
						y = scn.nextInt();
					}
					Coordinate c = new Coordinate(x, y);
					p1.fire(c);
					String s = p2.wasHit(c);
					p1.fireResult(s);
					System.out.println("Your torpedo " + hitShip(s) + " at "
							+ c);
					turn++;
				} else if (check == 2)
					p1.printBoards();
				else if (check == 3) {
					System.out.println("Player 1 surrenders! Player 2 wins!");
					String[] args = { "123" };
					main(args);
				}
			}
		}
	}

	public static void cpuGame(String[]args) {
		Player p = new Player();
		CPU c = new CPU();
		int turn = 0;
		while (!p.lost() && !c.lost()) {
			if (turn == 0) {
				System.out.println("Player please place your ships!");
				for (int i = 0; i < 5; i++)
					choosePieces(p);
				turn++;
				System.out.println("Player you go first!");
			} else if (turn % 2 == 1) {
				System.out.println("Player it is your " + (turn/2+1) + " turn.");
				System.out
						.println("Enter the number of what you would like to do this round:");
				System.out.println("1: Fire");
				System.out.println("2: Check Board");
				System.out.println("3: Surrender");
				Scanner scn = new Scanner(System.in);
				int check = scn.nextInt();
				if (check == 1) {
					int x = 0;
					int y = 0;
					while (x > 10 || x < 1 || y > 10 || y < 1) {
						System.out.println("Enter the Starting X Value:");
						x = scn.nextInt();
						System.out.println("Enter the Starting Y Value:");
						y = scn.nextInt();
					}
					Coordinate a = new Coordinate(x, y);
					p.fire(a);
					String s = c.wasHit(a);
					p.fireResult(s);
					System.out.println("Your torpedo " + hitShip(s) + " at "
							+ a);
					turn++;
				} else if (check == 2)
					p.printBoards();
				else if (check == 3) {
					System.out.println("Player surrenders! Computer wins!");
					main(args);
				}
			} else if (turn % 2 == 0) {
				System.out.println("It is the Computers" + (turn/2) + "turn.");
				Coordinate a = c.chooseFire();
				String s = p.wasHit(a);
				c.fireResult(s);
				System.out.println("The computer torpedo " + hitShip(s)
						+ " at (" + a.getY() + "," + a.getX() + ")");
				turn++;
			}
		}
		main(args);
	}

	public static void choosePieces(Player p) {
		Scanner scn = new Scanner(System.in);
		p.printBoards();
		System.out.println("Enter Ship Name:");
		String s = scn.nextLine();
		System.out.println("Enter the Starting X Value:");
		int x = scn.nextInt();
		System.out.println("Enter the Starting Y Value:");
		int y = scn.nextInt();
		scn.nextLine();
		System.out
				.println("Enter Orientation: (Options: up, left, right, down)");
		String d = scn.nextLine();
		boolean tf = p.placePiece(new Ship(s), new Coordinate(x, y), d);
		if (!tf) {
			System.out
					.println("Sorry but these values are invalid, please try again!");
			choosePieces(p);
		}
	}

	public static void info(String[] args) {
		System.out.println("These are the ships and their sizes:\n");
		System.out.println("Aircraft Carrier, 5");
		System.out.println("Battleship, 4");
		System.out.println("Destroyer, 3");
		System.out.println("Submarine, 3");
		System.out.println("Patrol Boat, 2");
		System.out.println("\nObjectives and Rules:\n");
		System.out.println("Sink all enemy ships by landing 17 hits");
		System.out
				.println("The game is not perfectly designed!Please do not try to:");
		System.out.println("Put ships on top of each other!");
		System.out.println("Place multiple ships of the same kind!");
		System.out
				.println("Other things allowed by bugs that go against the rules!");
		System.out
				.println("\nPlease don't cheat and look at what your opponents setup is!");
		System.out.println("Play fair and have fun! I wish you good luck!\n");
		main(args);
	}

	public static String hitShip(String name) {
		switch (name) {
		case "a ":
			return "hit an aircraft carrier";
		case "b ":
			return "hit a battleship";
		case "s ":
			return "hit a submarine";
		case "d ":
			return "hit a destroyer";
		case "p ":
			return "hit a patrol boat";
		case "M ":
			return "missed";
		default:
			return "hit an invalid ship";
		}
	}

	public static void quit() {
		System.out.println("THANKS FOR PLAYING!!");
		System.exit(0);
	}
}
