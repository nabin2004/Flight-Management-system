package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

/**
 * Represents a booking made by a customer for one or more flights.
 * A booking can have an outbound flight and optionally a return flight.
 */
public class Booking {

    private Customer customer; // The customer who made the booking
    private Flight outboundFlight; // The outbound flight of the booking
    private Flight returnFlight; // The return flight of the booking (optional)
    private LocalDate bookingDate; // The date when the booking was made
    private double price; // The price of the booking
    private boolean cancelled; // Indicates whether the booking is cancelled or not

    /**
     * Constructs a new Booking object with only an outbound flight.
     *
     * @param customer       The customer making the booking
     * @param outboundFlight The outbound flight to be booked
     */
    public Booking(Customer customer, Flight outboundFlight) {
        this.customer = customer;
        this.outboundFlight = outboundFlight;
        this.bookingDate = outboundFlight.getDepartureDate();
        this.cancelled = false; // By default, booking is not cancelled
        this.price = outboundFlight.getPrice();
    }

    /**
     * Constructs a new Booking object with both outbound and return flights.
     *
     * @param customer       The customer making the booking
     * @param outboundFlight The outbound flight to be booked
     * @param returnFlight   The return flight to be booked
     */
    public Booking(Customer customer, Flight outboundFlight, Flight returnFlight) {
        this.customer = customer;
        this.outboundFlight = outboundFlight;
        this.returnFlight = returnFlight;
        this.bookingDate = outboundFlight.getDepartureDate();
        this.cancelled = false; 
        this.price = outboundFlight.getPrice();
    }

    /**
     * Retrieves the customer who made the booking.
     *
     * @return The customer who made the booking
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Retrieves the outbound flight of the booking.
     *
     * @return The outbound flight of the booking
     */
    public Flight getOutboundFlight() {
        return outboundFlight;
    }

    /**
     * Retrieves the return flight of the booking.
     *
     * @return The return flight of the booking, or null if not available
     */
    public Flight getReturnFlight() {
        return returnFlight;
    }

    /**
     * Retrieves the date when the booking was made.
     *
     * @return The date when the booking was made
     */
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     * Checks if the booking is cancelled.
     *
     * @return True if the booking is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the customer associated with the booking.
     *
     * @param customer The customer to be associated with the booking
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Sets the outbound flight of the booking.
     *
     * @param outboundFlight The outbound flight to be set for the booking
     */
    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    /**
     * Sets the return flight of the booking.
     *
     * @param returnFlight The return flight to be set for the booking
     */
    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    /**
     * Sets the booking date of the booking.
     *
     * @param bookingDate The booking date to be set for the booking
     */
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * Cancels the booking by marking it as cancelled and applying a cancellation fee.
     */
    public void cancelBooking() {
        if (!cancelled) {
            this.cancelled = true;
            double flightPrice = this.price;
            double cancellationFee = 0.3 * flightPrice;
            this.setPrice(cancellationFee);
        }
    }

    /**
     * Sets the price of the booking.
     *
     * @param newPrice The new price to be set for the booking
     */
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    /**
     * Generates an ID for the booking based on the customer ID.
     *
     * @return The generated booking ID
     */
    public int getId() {
        return customer.getId();
    }

    /**
     * Provides a string representation of the booking.
     *
     * @return A string representation of the booking
     */
    @Override
    public String toString() {
        return "Booking{" +
                "customer=" + customer +
                ", outboundFlight=" + outboundFlight +
                (returnFlight != null ? ", returnFlight=" + returnFlight : "") +
                ", bookingDate=" + bookingDate +
                ", cancelled=" + cancelled +
                '}';
    }

    /**
     * Sets the flight number for the outbound flight.
     *
     * @param newFlightNumber The new flight number to be set
     */
    public void setFlightNumber(String newFlightNumber) {
        this.outboundFlight.setFlightNumber(newFlightNumber);
    }

    /**
     * Retrieves the price of the booking.
     *
     * @return The price of the booking
     */
    public Double getPrice() {
        return price;
    }

    public void applyPromocode(String promocode) {
        if ("nabinOpensFlightCompany20".equals(promocode)) {
            double discountedPrice = this.price * 0.8; // Applying 20% discount
            this.price = discountedPrice;
        }
    }

		
	}
