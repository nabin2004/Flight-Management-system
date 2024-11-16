package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The Help class represents a command to print the help message of available commands in the flight booking system.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the command prints out the predefined HELP_MESSAGE from the Command interface.
 * This message includes a list of available commands and their descriptions for user reference.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new Help command instance
 * Command helpCommand = new Help();
 * 
 * // Execute the command within a FlightBookingSystem instance
 * helpCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 */
public class Help implements Command {

    /**
     * Executes the help command within the provided FlightBookingSystem instance.
     * Prints the predefined HELP_MESSAGE to the console.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
        System.out.println(Command.HELP_MESSAGE);
    }
}
