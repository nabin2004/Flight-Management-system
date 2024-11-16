package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;

/**
 * The DeleteFlight class represents a command to delete a flight from the flight booking system.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the flight with the specified flight number is removed from the system.
 * The modified system data is then stored using FlightBookingSystemData.
 * 
 * <p>If the flight with the specified flight number does not exist in the system, a FlightBookingSystemException is thrown.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new DeleteFlight command instance for flight with number "FL123"
 * Command deleteFlightCommand = new DeleteFlight("FL123");
 * 
 * // Execute the command within a FlightBookingSystem instance
 * deleteFlightCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 */
public class DeleteFlight implements Command {

    private final int flightNumber;

    /**
     * Constructs a DeleteFlight command object with the specified flight number.
     * 
     * @param flightNumber The number of the flight to be deleted
     */
    public DeleteFlight(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Executes the delete flight command within the provided FlightBookingSystem instance.
     * Removes the flight with the specified flight number from the system.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     * @throws FlightBookingSystemException If there is an issue with deleting the flight (e.g., flight not found)
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Flight flightToDelete = flightBookingSystem.getFlightByID(this.flightNumber);
        if (flightToDelete == null) {
            throw new FlightBookingSystemException("Flight with number " + flightNumber + " not found.");
        }

        flightBookingSystem.removeFlight(flightToDelete);
        System.out.println("Flight #" + flightToDelete.getId() + " deleted.");
        FlightBookingSystemData.store(flightBookingSystem);
    }
}
