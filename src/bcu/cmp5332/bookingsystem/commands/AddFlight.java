package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;
import java.time.LocalDate;

/**
 * The AddFlight class represents a command to add a new flight to the flight booking system.
 * It implements the Command interface and requires execution within a FlightBookingSystem instance.
 * 
 * <p>The flight is identified by its flight number, origin, destination, departure date, capacity,
 * and price. Upon execution, the system assigns a unique ID to the new flight, creates a Flight object 
 * with the provided details, adds it to the system using flightBookingSystem, and stores the updated
 * data using FlightBookingSystemData.
 * 
 * <p>Example usage:
 * <pre>{@code
 * String flightNumber = "BA123";
 * String origin = "London";
 * String destination = "New York";
 * LocalDate departureDate = LocalDate.of(2024, 6, 1);
 * int capacity = 250;
 * double price = 500.00;
 * Command addFlightCommand = new AddFlight(flightNumber, origin, destination, departureDate, capacity, price);
 * addFlightCommand.execute(flightBookingSystem);
 * }</pre>
 */
public class AddFlight implements Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private final int capacity;
    private final double price;

    /**
     * Constructs an AddFlight command with the specified flight details.
     *
     * @param flightNumber   The flight number of the flight
     * @param origin         The origin of the flight
     * @param destination    The destination of the flight
     * @param departureDate  The departure date of the flight
     * @param capacity       The capacity of the flight (number of seats)
     * @param price          The price of the flight
     */
    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
    }

    /**
     * Executes the AddFlight command within the provided FlightBookingSystem instance.
     * Generates a unique ID for the new flight, creates a Flight object with the provided
     * flight details, adds it to the system using flightBookingSystem, and stores the updated
     * data using FlightBookingSystemData.
     *
     * @param flightBookingSystem The FlightBookingSystem instance on which the flight is to be added
     * @throws FlightBookingSystemException If there is an issue adding the flight to the system
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }

        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate, (int) price, capacity, false);
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added.");

        FlightBookingSystemData.store(flightBookingSystem);
    }
}
