package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The CancelBooking class represents a command to cancel a booking for a specific customer on a specific flight.
 * It implements the Command interface and requires execution within a FlightBookingSystem instance.
 * 
 * <p>The booking cancellation is performed by invoking the cancelBooking method on the flightBookingSystem with 
 * the specified customer ID and flight ID. Upon cancellation, the system updates and stores the modified booking data 
 * using FlightBookingSystemData.
 * 
 * <p>Example usage:
 * <pre>{@code
 * int customerId = 1;
 * int flightId = 101;
 * Command cancelBookingCommand = new CancelBooking(customerId, flightId);
 * cancelBookingCommand.execute(flightBookingSystem);
 * }</pre>
 */
public class CancelBooking implements Command {

    private final int customerId;
    private final int flightId;

    /**
     * Constructs a CancelBooking command with the specified customer ID and flight ID.
     *
     * @param customerId The ID of the customer whose booking is to be cancelled
     * @param flightId   The ID of the flight for which the booking is to be cancelled
     */
    public CancelBooking(int customerId, int flightId) {
        this.customerId = customerId;
        this.flightId = flightId;
    }

    /**
     * Executes the CancelBooking command within the provided FlightBookingSystem instance.
     * Cancels the booking of the specified customer on the specified flight by invoking
     * the cancelBooking method on flightBookingSystem. After cancellation, the updated booking
     * data is stored using FlightBookingSystemData.
     *
     * @param flightBookingSystem The FlightBookingSystem instance on which the booking cancellation is to be executed
     * @throws FlightBookingSystemException If there is an issue cancelling the booking in the system
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        flightBookingSystem.cancelBooking(customerId, flightId);
        System.out.println("Booking has been cancelled successfully.");
        FlightBookingSystemData.store(flightBookingSystem);
    }
}
