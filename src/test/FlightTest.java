package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

public class FlightTest {

    private Flight flight;
    private Customer passenger1;
    private Customer passenger2;

    @BeforeEach
    public void setUp() {
        flight = new Flight(1, "ABC123", "OriginCity", "DestinationCity",
                LocalDate.of(2024, 7, 1), 100, 200.0, false);

        passenger1 = new Customer(7, "Matthew Martinez", "1111111111", "matthew@example.com", false,false);
        passenger2 = new Customer(8, "Olivia Robinson", "9999999999", "olivia@example.com", true,false);

    }

    @Test
    public void testAddPassenger() throws FlightBookingSystemException {
        flight.addPassenger(passenger1);
        assertEquals(1, flight.getPassengerCount());
        assertTrue(flight.getPassengers().contains(passenger1));
    }

    @Test
    public void testAddPassengerAtFullCapacity() throws FlightBookingSystemException {
        for (int i = 0; i < 100; i++) {
            Customer passenger = new Customer(10, "Ava Thomas", "7777777777", "ava@example.com", true,false);
            flight.addPassenger(passenger);
        }
        Customer extraPassenger = new Customer(11, "Lucas Hernandez", "6666666666", "lucas@example.com", false,false);
        assertThrows(FlightBookingSystemException.class, () -> flight.addPassenger(extraPassenger));
    }

    @Test
    public void testRemovePassenger() throws FlightBookingSystemException {
        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);
        assertTrue(flight.removePassenger(passenger1));
        assertEquals(1, flight.getPassengerCount());
        assertFalse(flight.getPassengers().contains(passenger1));
        assertTrue(flight.getPassengers().contains(passenger2));
    }

    @Test
    public void testDeleteFlight() {
        flight.deleteFlight();
        assertTrue(flight.getDeleteStatusFlight());
    }

    @Test
    public void testCalculatePrice() {
        LocalDate currentDate = LocalDate.of(2024, 6, 15);
        double expectedPrice = flight.getPrice() * 16 * (double) flight.getCapacity() / 100;
        assertEquals(expectedPrice, flight.calculatePrice(currentDate));
    }

    @Test
    public void testHasDeparted() {
        LocalDate currentDate = LocalDate.of(2024, 6, 15);
        assertFalse(flight.hasDeparted(currentDate));
        LocalDate departedDate = LocalDate.of(2024, 7, 2);
        assertTrue(flight.hasDeparted(departedDate));
    }

    @Test
    public void testGetPassengerDetails() throws FlightBookingSystemException {
        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);
        String passengerDetails = flight.getPassengerDetails();
        assertTrue(passengerDetails.contains(passenger1.getName()) && passengerDetails.contains(passenger1.getPhone()));
        assertTrue(passengerDetails.contains(passenger2.getName()) && passengerDetails.contains(passenger2.getPhone()));
    }

    @Test
    public void testGetDetailsShort() {
        LocalDate currentDate = LocalDate.of(2024, 6, 15);
        String expectedDetails = "Flight #1 - ABC123 - OriginCity to DestinationCity on 01/07/2024";
        assertEquals(expectedDetails, flight.getDetailsShort());
    }

    @Test
    public void testGetDetailsLong() throws FlightBookingSystemException {
        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);
        String detailsLong = flight.getDetailsLong();
        assertTrue(detailsLong.contains("Flight ID: 1"));
        assertTrue(detailsLong.contains("Flight Number: ABC123"));
        assertTrue(detailsLong.contains("Origin: OriginCity"));
        assertTrue(detailsLong.contains("Destination: DestinationCity"));
        assertTrue(detailsLong.contains("Departure Date: 01/07/2024"));
        assertTrue(detailsLong.contains(passenger1.getName()) && detailsLong.contains(passenger1.getPhone()));
        assertTrue(detailsLong.contains(passenger2.getName()) && detailsLong.contains(passenger2.getPhone()));
    }
}
