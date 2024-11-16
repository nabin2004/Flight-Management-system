package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class BookingWithName implements Command {
    private final String customerName;
    private final String destination;
    private final LocalDate bookingDate;

    public BookingWithName(String customerName, String destination, LocalDate bookingDate) {
        this.customerName = customerName;
        this.destination = destination;
        this.bookingDate = bookingDate;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Customer customer = flightBookingSystem.getCustomerByName(this.customerName);
//        if (customer == null) {
//            throw new FlightBookingSystemException("Customer not found: " + this.customerName);
//        }
//
//        Flight outboundFlight = flightBookingSystem.getFlightByID(this.destination);
//        if (outboundFlight == null) {
//            throw new FlightBookingSystemException("Flight to destination not found: " + this.destination);
//        }
//
//        if (outboundFlight.getPassengerCount() >= outboundFlight.getCapacity()) {
//            throw new FlightBookingSystemException("Flight is at full capacity. Cannot issue booking.");
//        }
//
//        Booking booking = new Booking(customer, outboundFlight);
//        LocalDate bookingDate = outboundFlight.getDepartureDate()
//        flightBookingSystem.addBooking(booking);
//
//        System.out.println("Booking added successfully for " + customer.getName() + " to " + destination + " on " + bookingDate + ".");
//        FlightBookingSystemData.store(flightBookingSystem);
    }
}
