package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The {@code FlightDataManager} class is responsible for managing the data 
 * related to flights in the flight booking system. It implements the 
 * {@link DataManager} interface and provides methods to load and store flight data.
 * 
 * <p>The flight data is stored in a text file located at {@code ./resources/data/flights.txt}.
 * This class reads from and writes to this file to persist the flight information.
 * 
 * @see DataManager
 * @see FlightBookingSystem
 * @see Flight
 */
public class FlightDataManager implements DataManager {

    /** The path to the resource file containing flight data. */
    final static String RESOURCE = "./resources/data/flights.txt";
    
    /**
     * Loads the flight data from the resource file and populates the 
     * {@link FlightBookingSystem} with flight information.
     * 
     * @param fbs the {@link FlightBookingSystem} to be populated with flight data
     * @throws IOException if there is an I/O error during the loading process
     * @throws FlightBookingSystemException if there is an error in the flight data format
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String flightNumber = properties[1];
                    String origin = properties[2];
                    String destination = properties[3];
                    LocalDate departureDate = LocalDate.parse(properties[4]);
                    int capacity = Integer.parseInt(properties[5]);
                    double price = Double.parseDouble(properties[6]);
                    boolean isDeleted = Boolean.parseBoolean(properties[7]);  
                    Flight flight = new Flight(id, flightNumber, origin, destination, departureDate, capacity, price, isDeleted);
                    fbs.addFlight(flight);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse flight id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    /**
     * Stores the flight data from the {@link FlightBookingSystem} to the resource file.
     * 
     * @param fbs the {@link FlightBookingSystem} containing flight data to be stored
     * @throws IOException if there is an I/O error during the storing process
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Flight flight : fbs.getFlights()) {
                out.print(flight.getId() + SEPARATOR);
                out.print(flight.getFlightNumber() + SEPARATOR);
                out.print(flight.getOrigin() + SEPARATOR);
                out.print(flight.getDestination() + SEPARATOR);
                out.print(flight.getDepartureDate() + SEPARATOR);
                out.print(flight.getCapacity() + SEPARATOR);
                out.print(flight.getPrice() + SEPARATOR);
                out.print(flight.getDeleteStatusFlight() + SEPARATOR);
                out.println();
            }
        }
    }
}
