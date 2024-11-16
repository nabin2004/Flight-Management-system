package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The AddFeedback class represents a command to add feedback to a booking in the flight booking system.
 * It implements the Command interface and requires execution within a FlightBookingSystem instance.
 * 
 * <p>The feedback is identified by the booking ID, customer ID, and feedback message. Upon execution,
 * the feedback is added to the system and stored using FlightBookingSystemData.
 */
public class AddFeedback implements Command {

    private final int bookingID;
    private final int customerID;
    private final String message;

    /**
     * Constructs an AddFeedback command with the specified booking ID, customer ID, and feedback message.
     *
     * @param bookingID The ID of the booking
     * @param customerID The ID of the customer providing feedback
     * @param message The feedback message
     */
    public AddFeedback(int bookingID, int customerID, String message) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.message = message;
    }

    /**
     * Executes the AddFeedback command within the provided FlightBookingSystem instance.
     * Adds the feedback to the system using the specified booking ID, customer ID, and feedback message.
     * The updated system data is then stored using FlightBookingSystemData.
     *
     * @param flightBookingSystem The FlightBookingSystem instance on which the feedback is to be added
     * @throws FlightBookingSystemException If there is an issue adding the feedback to the system
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        flightBookingSystem.addFeedback(bookingID, customerID, message);
        System.out.println("Feedback added for booking #" + bookingID + " by customer #" + customerID + ".");

        FlightBookingSystemData.store(flightBookingSystem);
    }
}
