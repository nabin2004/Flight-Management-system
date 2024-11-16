package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightBookingSystemTest {

    private FlightBookingSystem flightBookingSystem;
    private Customer customer;
    private Flight flight;

    @BeforeEach
    public void setUp() {
        flightBookingSystem = new FlightBookingSystem();
        customer = new Customer(1, "John Doe", "1234567890", "john@example.com", false,false);
        flight = new Flight(1, "ABC123", "OriginCity", "DestinationCity",
                LocalDate.of(2024, 7, 1), 100, 200.0, false);
    }

    @Test
    public void testGetSystemDate() {
        LocalDate expectedDate = LocalDate.now();
        assertEquals(expectedDate, flightBookingSystem.getSystemDate());
    }

    @Test
    public void testAddBooking() {
        Booking booking = new Booking(customer, flight);
        flightBookingSystem.addBooking(booking);
        Booking[] bookings = flightBookingSystem.getBookings();
        assertEquals(1, bookings.length);
        assertEquals(booking, bookings[0]);
    }

    @Test
    public void testGetFutureFlights() throws FlightBookingSystemException {
        Flight futureFlight = new Flight(2, "DEF456", "AnotherCity", "DestinationCity",
                LocalDate.of(2024, 8, 1), 150, 250.0, false);
        flightBookingSystem.addFlight(futureFlight);

        List<Flight> futureFlights = flightBookingSystem.getFutureFlights(LocalDate.now());
        assertEquals(1, futureFlights.size());
        assertTrue(futureFlights.contains(futureFlight));
    }

    @Test
    public void testAddFlight() throws FlightBookingSystemException {
        flightBookingSystem.addFlight(flight);
        Flight[] flights = flightBookingSystem.getFlights().toArray(new Flight[0]);
        assertEquals(1, flights.length);
        assertEquals(flight, flights[0]);
    }

    @Test
    public void testAddCustomer() throws FlightBookingSystemException {
        flightBookingSystem.addCustomer(customer);
        Customer[] customers = flightBookingSystem.getCustomers();
        assertEquals(1, customers.length);
        assertEquals(customer, customers[0]);
    }

    @Test
    public void testIssueBooking() throws FlightBookingSystemException {
        flightBookingSystem.addCustomer(customer);
        flightBookingSystem.addFlight(flight);

        flightBookingSystem.issueBooking(customer, flight, LocalDate.now());
        Booking[] bookings = flightBookingSystem.getBookings();
        assertEquals(1, bookings.length);
        assertEquals(customer, bookings[0].getCustomer());
        assertEquals(flight, bookings[0].getOutboundFlight());
    }

    @Test
    public void testCancelBooking() throws FlightBookingSystemException {
        flightBookingSystem.addCustomer(customer);
        flightBookingSystem.addFlight(flight);
        flightBookingSystem.issueBooking(customer, flight, LocalDate.now());

        Booking booking = flightBookingSystem.getBookings()[0];
        flightBookingSystem.cancelBooking(customer.getId(), flight.getId());

        assertTrue(booking.isCancelled());
        assertNull(flight.getPassengers().get(customer.getId()));
    }

    @Test
    public void testRemoveFlight() throws FlightBookingSystemException {
        flightBookingSystem.addFlight(flight);
        flightBookingSystem.removeFlight(flight);

        assertFalse(flight.getDeleteStatusFlight());
    }

    @Test
    public void testGetCustomerByName() throws FlightBookingSystemException {
        flightBookingSystem.addCustomer(customer);
        Customer foundCustomer = flightBookingSystem.getCustomerByName("John Doe");
        assertEquals(customer, foundCustomer);
    }

    @Test
    public void testGetFlightByID() throws FlightBookingSystemException {
        flightBookingSystem.addFlight(flight);
        Flight foundFlight = flightBookingSystem.getFlightByID(flight.getId());
        assertEquals(flight, foundFlight);
    }

    @Test
    public void testGetCustomerByID() throws FlightBookingSystemException {
        flightBookingSystem.addCustomer(customer);
        Customer foundCustomer = flightBookingSystem.getCustomerByID(customer.getId());
        assertEquals(customer, foundCustomer);
    }

    @Test
    public void testGetBookingByID() throws FlightBookingSystemException {
        flightBookingSystem.addCustomer(customer);
        flightBookingSystem.addFlight(flight);
        flightBookingSystem.issueBooking(customer, flight, LocalDate.now());
        Booking booking = flightBookingSystem.getBookings()[0] ;
        
        Booking foundBooking = flightBookingSystem.getBookingByID(booking.getId());
        assertEquals(booking, foundBooking);
    }
}
