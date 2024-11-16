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

public class BookingTest {

    private Customer customer;
    private Flight outboundFlight;
    private Flight returnFlight;
    private Booking bookingWithOutbound;
    private Booking bookingWithReturn;

    @BeforeEach
    public void setUp() {
        customer = new Customer(1, "John Doe", "1234567890", "john@example.com", false,false);
        outboundFlight = new Flight(1, "OUT123", "CityA", "CityB", LocalDate.of(2024, 7, 1), 100, 150.0, false);
        returnFlight = new Flight(2, "RET123", "CityB", "CityA", LocalDate.of(2024, 7, 10), 100, 150.0, false);

        bookingWithOutbound = new Booking(customer, outboundFlight);
        bookingWithReturn = new Booking(customer, outboundFlight, returnFlight);
    }

    @Test
    public void testGetCustomer() {
        assertEquals(customer, bookingWithOutbound.getCustomer());
    }

    @Test
    public void testGetOutboundFlight() {
        assertEquals(outboundFlight, bookingWithOutbound.getOutboundFlight());
    }

    @Test
    public void testGetReturnFlight() {
        assertNull(bookingWithOutbound.getReturnFlight());
        assertEquals(returnFlight, bookingWithReturn.getReturnFlight());
    }

    @Test
    public void testGetBookingDate() {
        assertEquals(outboundFlight.getDepartureDate(), bookingWithOutbound.getBookingDate());
    }

    @Test
    public void testIsCancelled() {
        assertFalse(bookingWithOutbound.isCancelled());
    }

    @Test
    public void testSetCustomer() {
        Customer newCustomer = new Customer(2, "Jane Smith", "9876543210", "jane@example.com", false,false);
        bookingWithOutbound.setCustomer(newCustomer);
        assertEquals(newCustomer, bookingWithOutbound.getCustomer());
    }

    @Test
    public void testSetOutboundFlight() {
        Flight newOutboundFlight = new Flight(3, "NEWOUT123", "CityC", "CityD", LocalDate.of(2024, 8, 1), 100, 200.0, false);
        bookingWithOutbound.setOutboundFlight(newOutboundFlight);
        assertEquals(newOutboundFlight, bookingWithOutbound.getOutboundFlight());
    }

    @Test
    public void testSetReturnFlight() {
        Flight newReturnFlight = new Flight(4, "NEWRET123", "CityD", "CityC", LocalDate.of(2024, 8, 10), 100, 200.0, false);
        bookingWithReturn.setReturnFlight(newReturnFlight);
        assertEquals(newReturnFlight, bookingWithReturn.getReturnFlight());
    }

    @Test
    public void testSetBookingDate() {
        LocalDate newBookingDate = LocalDate.of(2024, 6, 1);
        bookingWithOutbound.setBookingDate(newBookingDate);
        assertEquals(newBookingDate, bookingWithOutbound.getBookingDate());
    }

    @Test
    public void testCancelBooking() {
        bookingWithOutbound.cancelBooking();
        assertTrue(bookingWithOutbound.isCancelled());
        assertEquals(45.0, bookingWithOutbound.getPrice());
    }

    @Test
    public void testSetPrice() {
        double newPrice = 300.0;
        bookingWithOutbound.setPrice(newPrice);
        assertEquals(newPrice, bookingWithOutbound.getPrice());
    }

    @Test
    public void testGetId() {
        assertEquals(customer.getId(), bookingWithOutbound.getId());
    }
    

    @Test
    public void testSetFlightNumber() {
        String newFlightNumber = "NEWOUT123";
        bookingWithOutbound.setFlightNumber(newFlightNumber);
        assertEquals(newFlightNumber, outboundFlight.getFlightNumber());
    }
}
