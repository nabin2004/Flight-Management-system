package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The ShowFlight class represents a command to display detailed information about a specific flight.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the command retrieves the flight information by its ID from the provided FlightBookingSystem instance.
 * It then prints the long details of the flight using the getDetailsLong() method of the Flight class.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new ShowFlight command instance for flight with ID 1001
 * Command showFlightCommand = new ShowFlight(1001);
 * 
 * // Execute the command within a FlightBookingSystem instance
 * showFlightCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 * @see Flight
 */
public class ShowFlight implements Command {

    private int id;

    /**
     * Constructs a ShowFlight command object with the specified flight ID.
     * 
     * @param id The ID of the flight whose details are to be displayed
     */
    public ShowFlight(int id) {
        this.id = id;
    }

    /**
     * Executes the show flight details command within the provided FlightBookingSystem instance.
     * Retrieves the flight information by its ID and prints its long details using the getDetailsLong() method.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     * @throws FlightBookingSystemException If there is an error while accessing or processing flight booking system data
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Flight flight = flightBookingSystem.getFlightByID(id);
        System.out.println(flight.getDetailsLong());
    }
}
