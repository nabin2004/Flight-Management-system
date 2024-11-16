package bcu.cmp5332.bookingsystem.model;

/**
 * Represents a feedback provided by a customer regarding a booking in the flight booking system.
 * Each feedback is uniquely identified by an ID, and is linked to a specific booking and customer.
 * The feedback message can contain any comments or suggestions from the customer regarding their experience.
 */
public class Feedback {
    private static int lastFeedbackID = 0;

    private final int id;           // The unique identifier for the feedback
    private final int bookingID;    // The ID of the booking associated with the feedback
    private final int customerID;   // The ID of the customer providing the feedback
    private final String message;   // The feedback message provided by the customer

    /**
     * Constructs a new Feedback object with the specified booking ID, customer ID, and message.
     * The feedback ID is automatically assigned and incremented with each new feedback.
     *
     * @param bookingID The ID of the booking related to the feedback
     * @param customerID The ID of the customer providing the feedback
     * @param message The feedback message provided by the customer
     */
    public Feedback(int bookingID, int customerID, String message) {
        this.id = ++lastFeedbackID;
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.message = message;
    }

    /**
     * Retrieves the unique identifier of the feedback.
     *
     * @return The ID of the feedback
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the ID of the booking associated with the feedback.
     *
     * @return The booking ID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * Retrieves the ID of the customer who provided the feedback.
     *
     * @return The customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Retrieves the feedback message provided by the customer.
     *
     * @return The feedback message
     */
    public String getMessage() {
        return message;
    }
}
