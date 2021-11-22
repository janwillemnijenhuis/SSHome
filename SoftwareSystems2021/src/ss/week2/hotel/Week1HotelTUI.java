package ss.week2.hotel;

import ss.utils.TextIO;

/**
 * A simple interactive Hotel TUI
 *
 * @author Tom van Dijk
 */
public class Week1HotelTUI {
	// User commands (constants)
	static final String IN = "i";
	static final String OUT = "o";
	static final String ROOM = "r";
	static final String PRINT = "p";
	static final String HELP = "h";
	static final String EXIT = "x";

	static String roomName = "101";  // name of the room
	static String hotelName = "the U Parkhotel";  // name of the hotel
	static String currentGuest = null;  // the current guest in the room

	// NOTE: because classes/objects have not been introduced in week 1,
	// everything here is static so it can be used from static methods.
	// Obviously this is not how you should program once you know how
	// object oriented programming works.

	/**
	 * Implementation of the "check in" command
	 */
	static void doIn(String[] splittedCommand) {
		if (splittedCommand.length != 2) {
			System.out.println("Wrong parameters at check in");
		} else {
			String guestName = splittedCommand[1];
			if (currentGuest == null) {
				currentGuest = guestName;
				System.out.printf("Guest %s gets room %s%n", guestName, roomName);
			} else {
				System.out.printf("Checkin failed: room is taken.%n");
			}
		}
	}

	/**
	 * Implementation of the "check out" command
	 */
	static void doOut(String[] splittedCommand) {
		if (splittedCommand.length != 2) {
			System.out.println("Wrong parameters at check out");
		} else {
			String guestName = splittedCommand[1];
			// Check if we can actually check out this guest
			if (currentGuest != null && currentGuest.equals(guestName)) {
				currentGuest = null;
				System.out.printf("Guest %s successfully checked out.%n", guestName);
			} else {
				System.out.printf("Checkout failed: room is empty or is not taken by %s.%n", guestName);
			}
		}
	}

	/**
	 * Implementation of the "get room of guest" command
	 */
	static void doRoom(String[] splittedCommand) {
		if (splittedCommand.length != 2) {
			System.out.println("Wrong parameters at get room request");
		} else {
			String guestName = splittedCommand[1];
			// Since we only model a single room hotel, this is pretty easy to do
			if (currentGuest != null && currentGuest.equals(guestName)) {
				System.out.printf("Guest %s has room %s.%n", guestName, roomName);
			} else {
				System.out.printf("Guest %s doesn't have a room.%n", guestName);
			}
		}
	}

	/**
	 * Implementation of the "print state" command.
	 *
	 * For every room in the hotel, print who occupies it if anyone
	 */
	static void doPrint(String[] splittedCommand) {
		System.out.printf("Hotel %s:%n", hotelName);
		if (currentGuest != null) {
			System.out.printf("- Room %s is occupied by %s.%n", roomName, currentGuest);
		} else {
			System.out.printf("- Room %s is currently empty.%n", roomName);
		}
	}

	public static void main(String[] args) {
		// Let's prepare a little help Menu
		final String helpMenu =
				String.format("Commands:%n") +
				String.format("- %s name ........... check in guest with name%n", IN) +
				String.format("- %s name ........... check out guest with name%n", OUT) +
				String.format("- %s name ........... request room of guest%n", ROOM) +
				String.format("- %s ................ help (this menu)%n", HELP) +
				String.format("- %s ................ print state%n", PRINT) +
				String.format("- %s ................ exit%n", EXIT);

		// Let's start with a friendly welcoming message and show the help menu
		System.out.println("Welcome to the Hotel booking system of " + hotelName);
		System.out.println(helpMenu);

		// Now until we are done, ask for user input
		boolean exit = false;
		while (!exit) {
			// First get a line of text from the user
			String input = TextIO.getlnString();

			// We want to extract the command (first word) so we split on (repeated) whitespace
			// the "\\s" means whitespace, the "+" means 1-or-more whitespace
			String[] splittedCommand = input.split("\\s+");

			// By the way, we know that input != null so split will go fine, and split always
			// returns an array of length at least 1, so we can access split[0] without checking bounds

			String command = splittedCommand[0];

			// To make our code not to complicated, we do all the actual commands
			// in separate methods, so it is easier to read here

			switch(command) {
				case IN:
					doIn(splittedCommand);
					break;
				case OUT:
					doOut(splittedCommand);
					break;
				case ROOM:
					doRoom(splittedCommand);
					break;
				case PRINT:
					doPrint(splittedCommand);
					break;
				case HELP:
					// Just print the help menu...
					System.out.println(helpMenu);
					break;
				case EXIT:
					// Set exit to true so we can leave the while loop
					// If we just "return" here, we don't get the nice goodbye message!
					exit = true;
					break;
				default:
					System.out.println("Unknown command: " + command);
					System.out.println(helpMenu);
			}
		}

		System.out.println("Goodbye! We hope to see you again at " + hotelName);
	}
}
