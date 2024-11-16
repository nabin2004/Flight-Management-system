package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * The ShowFlights class represents a command to display a list of flights with their short details.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the command retrieves the list of flights from the provided FlightBookingSystem instance
 * using the getFlights() method. It then iterates through each flight in the list, checks if the flight is not
 * marked as deleted (using getDeleteStatusFlight()), and prints its short details using getDetailsShort() method.
 * 
 * <p>Finally, it prints the total number of flights displayed.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new ShowFlights command instance
 * Command showFlightsCommand = new ShowFlights();
 * 
 * // Execute the command within a FlightBookingSystem instance
 * showFlightsCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 * @see Flight
 */
public class ShowFlights implements Command {

    /**
     * Executes the show flights command within the provided FlightBookingSystem instance.
     * Retrieves the list of flights, iterates through each flight, prints its short details if not deleted,
     * and then prints the total number of flights displayed.
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
