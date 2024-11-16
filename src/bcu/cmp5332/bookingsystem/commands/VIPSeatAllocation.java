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
 * The VIPSeatAllocation class represents a command to allocate a VIP seat to a specific customer
 * on a specific flight, even if the flight is fully booked. If the flight is full, a regular
 * booking is cancelled to make room for the VIP booking.
 * 
 * <p>Example usage:
 * <pre>{@code
 * int customerId = 1;
 * int flightId = 101;
 * LocalDate bookingDate = LocalDate.now();
 * int bookingid = 123; // Replace with actual booking ID
 * Command vipSeatAllocationCommand = new VIPSeatAllocation(customerId, flightId, bookingDate, bookingid);
 * vipSeatAllocationCommand.execute(flightBookingSystem);
 * }</pre>
 */
public class VIPSeatAllocation implements Command {

    private final int customerId;
    private final int flightId;
    private final int bookingid;
    private final LocalDate bookingDate;

    /**
     * Constructs a VIPSeatAllocation command with the specified customer ID, flight ID,
     * booking ID, and booking date.
     *
     * @param customerId The ID of the customer making the VIP booking
     * @param flightId   The ID of the flight to be booked
     * @param bookingDate The date on which the booking is made
     * @param bookingid The ID of the booking to be displaced if necessary
     */
    public VIPSeatAllocation(int customerId, int flightId, LocalDate bookingDate, int bookingid) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
        this.bookingid = bookingid;
    }

    /**
     * Executes the VIPSeatAllocation command within the provided FlightBookingSystem instance.
     * Retrieves the customer, flight, and booking based on IDs, checks if the flight has capacity,
     * and if not, cancels the specified regular booking to make room for the VIP booking.
     * If successful, the updated system data is stored using FlightBookingSystemData.
     *
     * @param flightBookingSystem The FlightBookingSystem instance on which the VIP booking is to be added
     * @throws FlightBookingSystemException If the customer, flight, or booking does not exist, or if there is an error during the process
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Customer customer = flightBookingSystem.getCustomerByID(this.customerId);
        Flight flight = flightBookingSystem.getFlightByID(this.flightId);
        Booking bookingToDisplace = flightBookingSystem.getBookingByID(this.bookingid);

        if (flight.getPassengerCount() >= flight.getCapacity()) {
            // Flight is full, cancel the specified regular booking
            if (bookingToDisplace == null || bookingToDisplace.getCustomer().isVIP()) {
                throw new FlightBookingSystemException("Invalid booking ID provided or booking is not eligible for displacement.");
            }

            // Cancel the regular booking
            Command cancelBookingCommand = new CancelBooking(bookingToDisplace.getCustomer().getId(), flightId);
            cancelBookingCommand.execute(flightBookingSystem);
        }

        // Issue the VIP booking
        flightBookingSystem.issueBooking(customer, flight, bookingDate);
        System.out.println("VIP booking added successfully.");

        FlightBookingSystemData.store(flightBookingSystem);
    }
}
