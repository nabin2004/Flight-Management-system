package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer of the flight booking system.
 */
public class Customer {

    private int id; // The unique identifier of the customer
    private String name; // The name of the customer
    private String phone; // The phone number of the customer
    private String email; // The email address of the customer
    private final List<Booking> bookings = new ArrayList<>(); // The list of bookings made by the customer
    private boolean isDeleted = false; // Indicates whether the customer is deleted from the system
    private boolean isVIP = false;

    /**
     * Constructs a new Customer object with the specified attributes.
     *
     * @param id        The unique identifier of the customer
     * @param name      The name of the customer
     * @param phone     The phone number of the customer
     * @param email     The email address of the customer
     * @param isDeleted Indicates whether the customer is deleted from the system
     */
    public Customer(int id, String name, String phone, String email, boolean isDeleted,boolean isVIP) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isDeleted = isDeleted;
        this.isVIP = isVIP;
    }

    /**
     * Retrieves the unique identifier of the customer.
     *
     * @return The unique identifier of the customer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the customer.
     *
     * @param id The new unique identifier to be set for the customer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The new name to be set for the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of the customer.
     *
     * @return The phone number of the customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone The new phone number to be set for the customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the email address of the customer.
     *
     * @return The email address of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email The new email address to be set for the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves a copy of the list of bookings made by the customer.
     *
     * @return A copy of the list of bookings made by the customer
     */
    public List<Booking> getBookings() {
        return new ArrayList<>(this.bookings);
    }

    /**
     * Adds a booking to the list of bookings made by the customer.
     *
     * @param booking The booking to be added
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Retrieves a short string representation of the customer's details.
     *
     * @return A short string representation of the customer's details
     */
    public String getDetailsShort() {
        return "Customer #" + this.id + " - " + this.name + " - " + this.phone;
    }

    /**
     * Prints detailed information about the customer and their bookings to the console.
     */
    public void showDetails() {
        System.out.println("Customer ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phone);

        System.out.println("Bookings:");
        for (Booking booking : bookings) {
            System.out.println("  Booking ID: " + booking.getId());
            System.out.println("  Outbound Flight: " + booking.getOutboundFlight().getFlightNumber() +
                    " From: " + booking.getOutboundFlight().getOrigin() +
                    " To: " + booking.getOutboundFlight().getDestination() +
                    " Date: " + booking.getOutboundFlight().getDepartureDate() +
                    " Price: " + booking.getOutboundFlight().getPrice());
            if (booking.getReturnFlight() != null) {
                System.out.println("  Return Flight: " + booking.getReturnFlight().getFlightNumber() +
                        " From: " + booking.getReturnFlight().getOrigin() +
                        " To: " + booking.getReturnFlight().getDestination() +
                        " Date: " + booking.getReturnFlight().getDepartureDate() +
                        " Price: " + booking.getReturnFlight().getPrice());
            }
        }
    }

    /**
     * Retrieves a detailed string representation of the customer's details and bookings.
     *
     * @return A detailed string representation of the customer's details and bookings
     */
    public String getShowDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Customer ID: ").append(id).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Phone Number: ").append(phone).append("\n");
        details.append("\nBookings:\n");
        for (Booking booking : bookings) {
            details.append("  Booking ID: ").append(booking.getId()).append("\n");
            details.append("  Outbound Flight: ").append(booking.getOutboundFlight().getFlightNumber())
                    .append(" From: ").append(booking.getOutboundFlight().getOrigin())
                    .append(" To: ").append(booking.getOutboundFlight().getDestination())
                    .append(" Date: ").append(booking.getOutboundFlight().getDepartureDate())
                    .append(" Price: ").append(booking.getOutboundFlight().getPrice()).append("\n");
            if (booking.getReturnFlight() != null) {
                details.append("  Return Flight: ").append(booking.getReturnFlight().getFlightNumber())
                        .append(" From: ").append(booking.getReturnFlight().getOrigin())
                        .append(" To: ").append(booking.getReturnFlight().getDestination())
                        .append(" Date: ").append(booking.getReturnFlight().getDepartureDate())
                        .append(" Price: ").append(booking.getReturnFlight().getPrice()).append("\n");
            }
        }
        return details.toString();
    }

    /**
     * Marks the customer as deleted from the system.
     */
    public void setDeleted() {
        this.isDeleted = true;
    }

    /**
     * Checks if the customer is deleted from the system.
     *
     * @return True if the customer is deleted, false otherwise
     */
    public boolean getDeleted() {
        return this.isDeleted;
    }
    
    public boolean isVIP() {
    	return this.isVIP;
    }
}
