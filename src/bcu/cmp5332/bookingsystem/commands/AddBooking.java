package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The AddBooking class represents a command to add a booking to the flight booking system.
 * It implements the Command interface and requires execution within a FlightBookingSystem instance.
 * 
 * <p>The booking is associated with a specific customer and outbound flight on a specified booking date.
 * If the outbound flight is at full capacity, the booking cannot be issued, and an exception is thrown.
 * If the booking date is in the past, the booking cannot be issued, and an exception is thrown.
 * Upon successful booking, the updated system data is stored using FlightBookingSystemData.
 * 
 * <p>Example usage:
 * <pre>{@code
 * int customerId = 1;
 * int outboundFlightId = 101;
 * LocalDate bookingDate = LocalDate.now();
 * Command addBookingCommand = new AddBooking(customerId, outboundFlightId, bookingDate);
 * addBookingCommand.execute(flightBookingSystem);
 * }</pre>
 */
public class AddBooking implements Command {

    private final int customerId;
    private final int outboundFlightId;
    private final LocalDate bookingDate;

    /**
     * Constructs an AddBooking command with the specified customer ID, outbound flight ID,
     * and booking date.
     *
     * @param customerId      The ID of the customer making the booking
     * @param outboundFlightId The ID of the outbound flight to be booked
     * @param bookingDate     The date on which the booking is made
     */
    public AddBooking(int customerId, int outboundFlightId, LocalDate bookingDate) {
        this.customerId = customerId;
        this.outboundFlightId = outboundFlightId;
        this.bookingDate = bookingDate;
    }

    /**
     * Executes the AddBooking command within the provided FlightBookingSystem instance.
     * Retrieves the customer and outbound flight based on IDs, checks if the outbound flight
     * has capacity for additional passengers, checks if the booking date is in the future,
     * and issues the booking if possible.
     * If successful, the updated system data is stored using FlightBookingSystemData.
     *
     * @param flightBookingSystem The FlightBookingSystem instance on which the booking is to be added
     * @throws FlightBookingSystemException If the customer or flight does not exist,
     *                                      if the flight is at full capacity,
     *                                      or if the booking date is in the past
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Customer customer = flightBookingSystem.getCustomerByID(this.customerId);
        Flight outboundFlight = flightBookingSystem.getFlightByID(this.outboundFlightId);
        LocalDate bookingDate = this.bookingDate;

        if (outboundFlight.getPassengerCount() >= outboundFlight.getCapacity()) {
            throw new FlightBookingSystemException("Flight is at full capacity. Cannot issue booking.");
        }

        if (bookingDate.isBefore(LocalDate.now())) {
            throw new FlightBookingSystemException("Booking date must be in the future.");
        }

        flightBookingSystem.issueBooking(customer, outboundFlight, bookingDate);
        System.out.println("Booking added successfully.");

        FlightBookingSystemData.store(flightBookingSystem);
    }
}
