package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;

/**
 * The ApplyPromocode class represents a command to apply a promotional code to a flight
 * in the flight booking system.
 *
 * <p>The promotional code is applied to a flight identified by its booking ID. Upon execution,
 * the system retrieves the booking with the specified ID, applies the promotional code to its associated flight,
 * and stores the updated data using FlightBookingSystemData.
 *
 * <p>Example usage:
 * <pre>{@code
 * int bookingID = 21;
 * String promocode = "nabinOpensFlightCompany20";
 * Command applyPromocodeCommand = new ApplyPromocode(bookingID, promocode);
 * applyPromocodeCommand.execute(flightBookingSystem);
 * }</pre>
 */
public class ApplyPromocode implements Command {

    private final int bookingID;
    private final String promocode;

    /**
     * Constructs an ApplyPromocode command with the specified booking ID and promotional code.
     *
     * @param bookingID The booking ID of the flight to apply the promocode
     * @param promocode The promotional code to apply
     */
    public ApplyPromocode(int bookingID, String promocode) {
        this.bookingID = bookingID;
        this.promocode = promocode;
    }

    /**
     * Executes the ApplyPromocode command within the provided FlightBookingSystem instance.
     * Retrieves the booking with the specified ID, applies the promotional code to its associated flight,
     * and stores the updated data using FlightBookingSystemData.
     *
     * @param flightBookingSystem The FlightBookingSystem instance on which the promocode is to be applied
     * @throws FlightBookingSystemException If there is an issue applying the promocode to the flight
     * @throws IOException                If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Booking booking = flightBookingSystem.getBookingByID(bookingID);
        if (booking == null) {
            throw new FlightBookingSystemException("Booking with ID " + bookingID + " does not exist.");
        }

        booking.applyPromocode(promocode);
        System.out.println("Promocode '" + promocode + "' applied to booking #" + bookingID);

        FlightBookingSystemData.store(flightBookingSystem);
    }
}
