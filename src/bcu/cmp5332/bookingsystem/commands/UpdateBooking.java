package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.time.LocalDate;

/**
 * The UpdateBooking class represents a command to update an existing booking's flight number
 * and booking date within a Flight Booking System.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the command retrieves the booking identified by bookingID from the provided
 * FlightBookingSystem instance using getBookingByID() method. If the booking does not exist, it throws
 * a FlightBookingSystemException.
 * 
 * <p>If the booking exists, it updates the booking's flight number and booking date to the new values
 * specified during object construction.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new UpdateBooking command instance
 * Command updateBookingCommand = new UpdateBooking(bookingID, newFlightNumber, newBookDate);
 * 
 * // Execute the command within a FlightBookingSystem instance
 * updateBookingCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 * @see Booking
 */
public class UpdateBooking implements Command {

    private final int bookingID;
    private final String newFlightNumber;
    private final LocalDate newBookDate;

    /**
     * Constructs an UpdateBooking command with the specified booking ID, new flight number,
     * and new booking date.
     * 
     * @param bookingID The ID of the booking to update
     * @param newFlightNumber The new flight number for the booking
     * @param newBookDate The new booking date for the booking
     */
    public UpdateBooking(int bookingID, String newFlightNumber, LocalDate newBookDate) {
        this.bookingID = bookingID;
        this.newFlightNumber = newFlightNumber;
        this.newBookDate = newBookDate;
    }

    /**
     * Executes the update booking command within the provided FlightBookingSystem instance.
     * Retrieves the booking by its ID, updates its flight number and booking date to the new values,
     * and throws an exception if the booking is not found.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     * @throws FlightBookingSystemException If the booking with the specified ID is not found
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Booking booking = flightBookingSystem.getBookingByID(bookingID);
        if (booking == null) {
            throw new FlightBookingSystemException("Booking not found.");
        }

        booking.setFlightNumber(newFlightNumber);
        booking.setBookingDate(newBookDate);
        System.out.println("Updated Successfully!");
    }
}
