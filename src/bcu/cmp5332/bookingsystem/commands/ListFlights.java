package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * The ListFlights class represents a command to list all flights in the flight booking system.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the command retrieves a list of all flights from the FlightBookingSystem instance.
 * It then iterates through the list and prints the short details (using getDetailsShort()) of each flight to the console,
 * excluding any flights marked as deleted.
 * 
 * <p>After listing all flights, it prints the total number of flights retrieved.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new ListFlights command instance
 * Command listFlightsCommand = new ListFlights();
 * 
 * // Execute the command within a FlightBookingSystem instance
 * listFlightsCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 */
public class ListFlights implements Command {

    /**
     * Executes the list flights command within the provided FlightBookingSystem instance.
     * Retrieves a list of all flights and prints their short details to the console.
     * Excludes flights marked as deleted.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     * @throws FlightBookingSystemException If there is an error while accessing or processing flight booking system data
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> flights = flightBookingSystem.getFlights();
        for (Flight flight : flights) {
            if (!flight.getDeleteStatusFlight()) {
                System.out.println(flight.getDetailsShort());
            }
        }
        System.out.println(flights.size() + " flight(s)");
    }
}
