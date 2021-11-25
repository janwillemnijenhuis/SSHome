package ss.week2.hotel;

import ss.utils.TextIO;

/**
 * A simple interactive Hotel TUI
 *
 * @author Tom van Dijk
 */
public class HotelTUI {
	// User commands (constants)
	static final String IN = "i";
	static final String OUT = "o";
	static final String ROOM = "r";
	static final String PRINT = "p";
	static final String HELP = "h";
	static final String EXIT = "x";

	private Hotel hotel;

	public HotelTUI(String name) {
		this.hotel = new Hotel(name);
		this.hotel.MAXSIZE = 5; // we have a hotel with 5 rooms
	}

	public static void main(String[] args) {
		new HotelTUI("U Parkhotel").start();
	}

	public void start() {
		// Let's start with a friendly welcoming message and show the help menu
		System.out.println("Welcome to the Hotel booking system of " + this.hotel.name);
		this.printHelpMenu();
		boolean exit = false;
		while (!exit) {

			String input = TextIO.getlnString();
			String[] splittedCommand = input.split("\\s+");
			String command = splittedCommand[0];

			switch(command) {
				case IN:
					if(correctInput(splittedCommand)) {
						this.hotel.checkIn(this.getName(splittedCommand));
					}
					break;
				case OUT:
					if(correctInput(splittedCommand)) {
						this.hotel.checkOut(this.getName(splittedCommand));
					}
					break;
				case ROOM:
					if (correctInput(splittedCommand)) {
						System.out.println(this.hotel.getRoom(this.getName(splittedCommand)).toString());
					}
					break;
				case PRINT:
					System.out.println(this.hotel.toString());
					break;
				case HELP:
					this.printHelpMenu();
					break;
				case EXIT:
					exit = true;
					break;
				default:
					System.out.println("Unknown command: " + command);
					this.printHelpMenu();
			}
		}

		System.out.println("Goodbye! We hope to see you again at " + this.hotel.name);
	}

	public String getName(String[] splittedCommand) {
		return splittedCommand[1];
	}

	public boolean correctInput(String[] splittedCommand) {
		if (splittedCommand.length != 2) {
			System.out.println("Wrong parameters at check in");
			return false;
		}
		return true;
	}

	public void printHelpMenu() {
		final String helpMenu =
				String.format("Commands:%n") +
						String.format("- %s name ........... check in guest with name%n", IN) +
						String.format("- %s name ........... check out guest with name%n", OUT) +
						String.format("- %s name ........... request room of guest%n", ROOM) +
						String.format("- %s ................ help (this menu)%n", HELP) +
						String.format("- %s ................ print state%n", PRINT) +
						String.format("- %s ................ exit%n", EXIT);
		System.out.println(helpMenu);
	}
}
