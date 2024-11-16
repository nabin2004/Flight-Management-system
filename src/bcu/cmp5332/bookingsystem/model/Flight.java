package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * Represents a flight in the flight booking system.
 */
public class Flight {

    private int id; // The unique identifier of the flight
    private String flightNumber; // The flight number
    private String origin; // The origin of the flight
    private String destination; // The destination of the flight
    private LocalDate departureDate; // The departure date of the flight
    private int capacity; // The number of seats available on the flight
    private double price; // The price per seat of the flight
    private final Set<Customer> passengers; // The set of passengers booked on the flight
    private boolean isDeleted = false; // Indicates whether the flight is deleted from the system

    /**
     * Constructs a new Flight object with the specified attributes.
     *
     * @param id            The unique identifier of the flight
     * @param flightNumber  The flight number
     * @param origin        The origin of the flight
     * @param destination   The destination of the flight
     * @param departureDate The departure date of the flight
     * @param capacity      The number of seats available on the flight
     * @param price         The price per seat of the flight
     * @param isDeleted     Indicates whether the flight is deleted from the system
     */
    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, double price, boolean isDeleted) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
        this.isDeleted = isDeleted;
        passengers = new HashSet<>();
    }

    /**
     * Retrieves the unique identifier of the flight.
     *
     * @return The unique identifier of the flight
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the flight.
     *
     * @param id The new unique identifier to be set for the flight
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Adds a passenger to the flight.
     *
     * @param passenger The passenger to be added to the flight
     * @throws FlightBookingSystemException If the flight is at full capacity
     */
    public void addPassenger(Customer passenger) throws FlightBookingSystemException {
        if (this.getPassengerCount() >= this.getCapacity()) {
            throw new FlightBookingSystemException("Flight is at full capacity. Cannot issue booking.");
        }
        passengers.add(passenger);
    }

    /**
     * Removes a passenger from the flight.
     *
     * @param customer The customer to be removed from the flight
     * @return True if the passenger is removed successfully, false otherwise
     */
    public boolean removePassenger(Customer customer) {
        return passengers.remove(customer);
    }

    /**
     * Marks the flight as deleted from the system.
     */
    public void deleteFlight() {
        this.isDeleted = true;
    }

    /**
     * Checks if the flight is deleted from the system.
     *
     * @return True if the flight is deleted, false otherwise
     */
    public boolean getDeleteStatusFlight() {
        return this.isDeleted;
    }

    /**
     * Retrieves details of passengers booked on the flight (name and phone number).
     *
     * @return Details of passengers booked on the flight
     */
    public String getPassengerDetails() {
        StringBuilder details = new StringBuilder();
        for (Customer passenger : passengers) {
            details.append("Name: ").append(passenger.getName()).append(", Phone: ").append(passenger.getPhone()).append("\n");
        }
        return details.toString();
    }

    /**
     * Retrieves the flight number.
     *
     * @return The flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the flight number.
     *
     * @param newFlightNumber The new flight number to be set
     */
    public void setFlightNumber(String newFlightNumber) {
        this.flightNumber = newFlightNumber;
    }

    /**
     * Retrieves the origin of the flight.
     *
     * @return The origin of the flight
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of the flight.
     *
     * @param origin The new origin to be set for the flight
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Retrieves the destination of the flight.
     *
     * @return The destination of the flight
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination of the flight.
     *
     * @param destination The new destination to be set for the flight
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Retrieves the departure date of the flight.
     *
     * @return The departure date of the flight
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the departure date of the flight.
     *
     * @param departureDate The new departure date to be set for the flight
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Retrieves a copy of the list of passengers booked on the flight.
     *
     * @return A copy of the list of passengers booked on the flight
     */
    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }

    /**
     * Retrieves the number of passengers booked on the flight.
     *
     * @return The number of passengers booked on the flight
     */
    public int getPassengerCount() {
        return this.passengers.size();
    }

    /**
     * Retrieves a short string representation of the flight's details.
     *
     * @return A short string representation of the flight's details
     */
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " +
                destination + " on " + departureDate.format(dtf);
    }

    /**
     * Retrieves a detailed string representation of the flight's details and passengers.
     *
     * @return A detailed string representation of the flight's details and passengers
     */
    public String getDetailsLong() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        StringBuilder details = new StringBuilder();
        details.append("Flight ID: ").append(id).append("\n");
        details.append("Flight Number: ").append(flightNumber).append("\n");
        details.append("Origin: ").append(origin).append("\n");
        details.append("Destination: ").append(destination).append("\n");
        details.append("Departure Date: ").append(departureDate.format(dtf)).append("\n");
        details.append("Passenger Details:\n");
        for (Customer passenger : passengers) {
            details.append("  - ").append(passenger.getName()).append(", Phone: ").append(passenger.getPhone()).append("\n");
        }
        return details.toString();
    }

    /**
     * Retrieves the capacity of the flight (number of seats).
     *
     * @return The capacity of the flight
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the flight (number of seats).
     *
     * @param capacity The new capacity to be set for the flight
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Retrieves the price per seat of the flight.
     *
     * @return The price per seat of the flight
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price per seat of the flight.
     *
     * @param price The new price per seat to be set for the flight
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Calculates the price for the flight based on the number of days left for departure
     * and the current capacity.
     *
     * @param systemDate The current date of the system
     * @return The calculated price for the flight
     */
    public double calculatePrice(LocalDate systemDate) {
        // Calculates the number of days left for departure
        long daysLeft = ChronoUnit.DAYS.between(systemDate, departureDate);
        
        // Calculates the ratio of seats left to total capacity
        double seatsLeft = this.getCapacity();
		double capacityRatio = (double) seatsLeft  / capacity;
        
        // Calculates the base price (e.g., based on distance, demand, etc.)
        double basePrice = this.price;
        
        // Adjusts the price based on days left and capacity ratio
        double adjustedPrice = basePrice * daysLeft * capacityRatio;
        
        return adjustedPrice;
    }

    /**
     * Checks whether the flight has already departed.
     *
     * @param systemDate The current date of the system
     * @return True if the flight has departed, false otherwise
     */
    public boolean hasDeparted(LocalDate systemDate) {
        return departureDate.isBefore(systemDate);
    }

}
