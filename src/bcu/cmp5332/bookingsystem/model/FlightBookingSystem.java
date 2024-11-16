package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Represents the flight booking system.
 */
public class FlightBookingSystem {

    private final LocalDate systemDate = LocalDate.now(); // The current date of the system

    private final Map<Integer, Customer> customers = new TreeMap<>(); // Collection of customers in the system
    private final Map<Integer, Flight> flights = new TreeMap<>(); // Collection of flights in the system
    private final List<Booking> bookings = new ArrayList<>(); // Collection of bookings in the system
    private final List<Feedback> feedbacks = new ArrayList<>(); 
    /**
     * Retrieves the current date of the system.
     *
     * @return The current date of the system
     */
    public LocalDate getSystemDate() {
        return systemDate;
    }

    /**
     * Adds a booking to the system.
     *
     * @param booking The booking to be added to the system
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Retrieves a list of all future flights in the system that have not departed.
     *
     * @param systemDate The current date of the system
     * @return A list of all future flights in the system that have not departed
     */
    public List<Flight> getFutureFlights(LocalDate systemDate) {
        List<Flight> futureFlights = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (!flight.getDepartureDate().isBefore(systemDate) && !flight.hasDeparted(systemDate)) {
                futureFlights.add(flight);
            }
        }
        return Collections.unmodifiableList(futureFlights);
    }

    /**
     * Retrieves a list of all customers in the system.
     *
     * @return A list of all customers in the system
     */
    public List<Customer> getCustomer() {
        List<Customer> out = new ArrayList<>(customers.values());
        return Collections.unmodifiableList(out);
    }

    /**
     * Retrieves a flight by its ID.
     *
     * @param delId The ID of the flight to retrieve
     * @return The flight with the specified ID
     * @throws FlightBookingSystemException If no flight exists with the given ID
     */
    public Flight getFlightByID(int delId) throws FlightBookingSystemException {
        Flight flight = flights.get(delId);
        if (flight == null) {
            throw new FlightBookingSystemException("There is no flight with that ID: " + delId);
        }
        return flight;
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id The ID of the customer to retrieve
     * @return The customer with the specified ID
     * @throws FlightBookingSystemException If no customer exists with the given ID
     */
    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID: " + id);
        }
        return customers.get(id);
    }

    /**
     * Adds a flight to the system.
     *
     * @param flight The flight to be added to the system
     * @throws FlightBookingSystemException If a flight with the same ID or same number and departure date already exists in the system
     */
    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber())
                    && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with the same number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }

    /**
     * Adds a customer to the system.
     *
     * @param customer The customer to be added to the system
     * @throws FlightBookingSystemException If a customer with the same ID already exists in the system
     */
    public void addCustomer(Customer customer) throws FlightBookingSystemException {
        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
        customers.put(customer.getId(), customer);
    }

    /**
     * Retrieves an array of all customers in the system.
     *
     * @return An array of all customers in the system
     */
    public Customer[] getCustomers() {
        return customers.values().toArray(new Customer[0]);
    }

    /**
     * Issues a booking for a customer on a flight with the specified booking date.
     *
     * @param customer    The customer for whom the booking is issued
     * @param flight      The flight for which the booking is issued
     * @param bookingDate The date on which the booking is made
     * @throws FlightBookingSystemException If the customer or flight does not exist in the system, or if the flight is at full capacity
     */
    public void issueBooking(Customer customer, Flight flight, LocalDate bookingDate) throws FlightBookingSystemException {
        if (!customers.containsValue(customer)) {
            throw new FlightBookingSystemException("Customer does not exist in the system.");
        }
        if (!flights.containsValue(flight)) {
            throw new FlightBookingSystemException("Flight does not exist in the system.");
        }
        if (flight.getPassengerCount() >= flight.getCapacity()) {
            throw new FlightBookingSystemException("Flight is at full capacity. Cannot issue booking.");
        }
        Booking booking = new Booking(customer, flight);
        customer.addBooking(booking);
        flight.addPassenger(customer); // Ensure the flight tracks its passengers
    }

    /**
     * Retrieves an array of all bookings in the system.
     *
     * @return An array of all bookings in the system
     */
    public Booking[] getBookings() {
        List<Booking> bookings = new ArrayList<>();
        for (Customer customer : customers.values()) {
            bookings.addAll(customer.getBookings());
        }
        return bookings.toArray(new Booking[0]);
    }

    /**
     * Cancels a booking for a given customer and flight.
     *
     * @param customerId The ID of the customer
     * @param flightId   The ID of the flight
     * @throws FlightBookingSystemException If the customer or flight does not exist in the system
     */
    public void cancelBooking(int customerId, int flightId) throws FlightBookingSystemException {
        Customer customer = this.getCustomerByID(customerId);
        Flight flight = this.getFlightByID(flightId);
        flight.removePassenger(customer);
    }

    /**
     * Removes a flight from the system.
     *
     * @param flightToDelete The flight to be removed from the system
     */
    public void removeFlight(Flight flightToDelete) {
        flightToDelete.deleteFlight();
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingID The ID of the booking to retrieve
     * @return The booking with the specified ID
     * @throws FlightBookingSystemException If no booking exists with the given ID
     */
    public Booking getBookingByID(int bookingID) throws FlightBookingSystemException {
        for (Booking booking : bookings) {
            if (booking.getId() == bookingID) {
                return booking;
            }
        }
        throw new FlightBookingSystemException("No booking found with ID: " + bookingID);
    }

    /**
     * Retrieves a customer by their name.
     *
     * @param customerName The name of the customer to retrieve
     * @return The customer with the specified name
     * @throws FlightBookingSystemException If no customer exists with the given name, or if multiple customers with the same name exist and none is selected
     */
    public Customer getCustomerByName(String customerName) throws FlightBookingSystemException {
        List<Customer> matchingCustomers = new ArrayList<>();
        for (Customer customer : customers.values()) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                matchingCustomers.add(customer);
            }
        }
        if (matchingCustomers.isEmpty()) {
            throw new FlightBookingSystemException("No customers found with the name: " + customerName);
        } else if (matchingCustomers.size() > 1) {
            System.out.println("Multiple customers found with the name: " + customerName);
            for (Customer customer : matchingCustomers) {
                System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getName() + ", Email: " + customer.getEmail());
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the email to select your customer: ");
            String selectedEmail = scanner.nextLine();
            for (Customer customer : matchingCustomers) {
                if (customer.getEmail().equalsIgnoreCase(selectedEmail)) {
                    return customer;
                }
            }
            throw new FlightBookingSystemException("No customer found with the email: " + selectedEmail);
        } else {
            return matchingCustomers.get(0);
        }
    }

    /**
     * Retrieves a list of all future flights in the system.
     *
     * @return A list of all future flights in the system
     */
    public List<Flight> getFlights() {
        LocalDate systemDate = LocalDate.now();
        List<Flight> futureFlights = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (!flight.getDepartureDate().isBefore(systemDate) && !flight.hasDeparted(systemDate)) {
                futureFlights.add(flight);
            }
        }
        return Collections.unmodifiableList(futureFlights);
    }
    /**
     * Adds feedback for a specific booking.
     *
     * @param bookingID The ID of the booking for which feedback is provided
     * @param customerID The ID of the customer providing feedback
     * @param message  The feedback message provided by the customer
     */
    public void addFeedback(int bookingID, int customerID, String message) {
        Feedback feedback = new Feedback(bookingID, customerID, message);
        feedbacks.add(feedback);
    }

    /**
     * Retrieves all feedback provided by customers.
     *
     * @return A list of all feedback provided by customers
     */
    public List<Feedback> getFeedbacks() {
        return Collections.unmodifiableList(feedbacks);
    }

	public void addFeedback(Feedback feedback) {
	        feedbacks.add(feedback);
		
	}


}
