package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The BookingDataManager class implements the DataManager interface
 * to manage loading and storing of booking data to/from a text file.
 * It provides methods to load existing bookings data into a FlightBookingSystem instance,
 * and to store current bookings data from a FlightBookingSystem instance into a text file.
 * 
 * <p>The booking data is stored in a text file specified by the RESOURCE constant.
 * Each line in the file represents a booking record, separated by the SEPARATOR "::".
 * 
 * <p>The loadData method reads from the text file, parses each line to retrieve booking details,
 * and adds the parsed bookings to the provided FlightBookingSystem instance.
 * 
 * <p>The storeData method retrieves all bookings from the provided FlightBookingSystem instance,
 * formats them into a string representation, and writes them to the text file for storage.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new instance of FlightBookingSystem and BookingDataManager
 * FlightBookingSystem fbs = new FlightBookingSystem();
 * BookingDataManager bookingDataManager = new BookingDataManager();
 * 
 * // Load existing booking data into FlightBookingSystem
 * bookingDataManager.loadData(fbs);
 * 
 * // Perform operations on fbs...
 * 
 * // Store current booking data from FlightBookingSystem
 * bookingDataManager.storeData(fbs);
 * }</pre>
 * 
 * @see DataManager
 * @see Booking
 * @see Customer
 * @see Flight
 * @see FlightBookingSystem
 */
public class BookingDataManager implements DataManager {
    
    /**
     * The path to the bookings data file.
     */
    public final String RESOURCE = "./resources/data/bookings.txt";
    
    /**
     * The separator used to separate fields in the bookings data file.
     */
    private static final String SEPARATOR = "::"; // Define the SEPARATOR

    /**
     * Loads existing booking data from the bookings data file into the provided FlightBookingSystem instance.
     * Each line in the file represents a booking record with fields separated by the SEPARATOR.
     * 
     * @param fbs The FlightBookingSystem instance to load booking data into
     * @throws IOException If there is an error reading the data file
     * @throws FlightBookingSystemException If there is an error parsing the booking data
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))){
            int line_idx = 1;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    int customerId = Integer.parseInt(properties[1]);
                    int flightId = Integer.parseInt(properties[2]);
                    
                    Customer customer = fbs.getCustomerByID(customerId);
                    Flight flight = fbs.getFlightByID(flightId);
                    Booking booking = new Booking(customer, flight);
                    fbs.addBooking(booking);
                    flight.addPassenger(customer);
                    customer.addBooking(booking);
                    
                } catch (NumberFormatException | DateTimeParseException ex) {
                    throw new FlightBookingSystemException("Unable to parse booking on line " + line_idx
                            + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    /**
     * Stores current booking data from the provided FlightBookingSystem instance into the bookings data file.
     * Each booking's details are formatted and written to the file, separated by the SEPARATOR.
     * 
     * @param fbs The FlightBookingSystem instance to store booking data from
     * @throws IOException If there is an error writing data to the file
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Booking booking : fbs.getBookings() ) {
                out.print(booking.getId() + SEPARATOR);
                out.print(booking.getCustomer().getId() + SEPARATOR);
                out.print(booking.getOutboundFlight().getId() + SEPARATOR);
                out.print(booking.getBookingDate() + SEPARATOR); 
                out.println();
            }
        }
    }
}
