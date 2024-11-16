package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

public class CustomerTest {

    private Customer customer;
    private Booking booking1;
    private Booking booking2;
    private Flight outboundFlight;
    private Flight returnFlight;

    @BeforeEach
    public void setUp() {
        customer = new Customer(1, "John Doe", "1234567890", "john@example.com", false,false);

        outboundFlight = new Flight(1, "ABC123", "OriginCity", "DestinationCity",
                LocalDate.of(2024, 7, 1), 100, 200.0, false);
        returnFlight = new Flight(2, "XYZ456", "DestinationCity", "OriginCity",
                LocalDate.of(2024, 7, 15), 100, 200.0, false);

        customer = new Customer(1, "John Doe", "1234567890", "john@example.com", false,false);
        outboundFlight = new Flight(1, "OUT123", "CityA", "CityB", LocalDate.of(2024, 7, 1), 100, 150.0, false);
        returnFlight = new Flight(2, "RET123", "CityB", "CityA", LocalDate.of(2024, 7, 10), 100, 150.0, false);

        booking1 = new Booking(customer, outboundFlight);
        booking2 = new Booking(customer, outboundFlight, returnFlight);
    }

    @Test
    public void testGetId() {
        assertEquals(1, customer.getId());
    }

    @Test
    public void testSetId() {
        customer.setId(2);
        assertEquals(2, customer.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testSetName() {
        customer.setName("Jane Doe");
        assertEquals("Jane Doe", customer.getName());
    }

    @Test
    public void testGetPhone() {
        assertEquals("1234567890", customer.getPhone());
    }

    @Test
    public void testSetPhone() {
        customer.setPhone("0987654321");
        assertEquals("0987654321", customer.getPhone());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john@example.com", customer.getEmail());
    }

    @Test
    public void testSetEmail() {
        customer.setEmail("jane@example.com");
        assertEquals("jane@example.com", customer.getEmail());
    }

    @Test
    public void testGetBookings() {
        customer.addBooking(booking1);
        List<Booking> bookings = customer.getBookings();
        assertEquals(1, bookings.size());
        assertTrue(bookings.contains(booking1));
    }

    @Test
    public void testAddBooking() {
        customer.addBooking(booking2);
        List<Booking> bookings = customer.getBookings();
        assertEquals(1, bookings.size());
        assertTrue(bookings.contains(booking2));
    }

    @Test
    public void testGetDetailsShort() {
        String expectedDetails = "Customer #1 - John Doe - 1234567890";
        assertEquals(expectedDetails, customer.getDetailsShort());
    }

//    @Test
//    public void testGetShowDetails() {
//        customer.addBooking(booking1);
//        customer.addBooking(booking2);
//        String details = customer.getShowDetails();
//        assertTrue(details.contains("Customer ID: 1"));
//        assertTrue(details.contains("Name: John Doe"));
//        assertTrue(details.contains("Phone Number: 1234567890"));
//        assertTrue(details.contains("Booking ID: 1"));
//        assertTrue(details.contains("Outbound Flight: ABC123"));
//        assertTrue(details.contains("Booking ID: 2"));
//        assertTrue(details.contains("Return Flight: XYZ456"));
//    }

    @Test
    public void testSetDeleted() {
        customer.setDeleted();
        assertTrue(customer.getDeleted());
    }

    @Test
    public void testGetDeleted() {
        assertFalse(customer.getDeleted());
        customer.setDeleted();
        assertTrue(customer.getDeleted());
    }
}
