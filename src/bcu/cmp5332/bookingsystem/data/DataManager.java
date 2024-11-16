package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;

/**
 * The DataManager interface defines methods for loading and storing data
 * to/from a data source into a FlightBookingSystem instance.
 * 
 * <p>The SEPARATOR constant defines the delimiter used to separate fields
 * in the data source.
 * 
 * <p>Implementing classes should provide implementations for the loadData
 * and storeData methods to load data into and store data from a
 * FlightBookingSystem instance, respectively.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new instance of FlightBookingSystem and DataManager implementation
 * FlightBookingSystem fbs = new FlightBookingSystem();
 * DataManager dataManager = new CustomDataManager(); // Replace with actual implementation
 * 
 * // Load existing data into FlightBookingSystem
 * dataManager.loadData(fbs);
 * 
 * // Perform operations on fbs...
 * 
 * // Store current data from FlightBookingSystem
 * dataManager.storeData(fbs);
 * }</pre>
 * 
 * @see CustomDataManager
 * @see FlightBookingSystem
 * @see SEPARATOR
 */
public interface DataManager {

    /**
     * The delimiter used to separate fields in the data source.
     */
    public static final String SEPARATOR = "::";
    
    /**
     * Loads data from a data source into the provided FlightBookingSystem instance.
     * Implementing classes should define how data is read from the data source,
     * parsed, and added to the FlightBookingSystem.
     * 
     * @param fbs The FlightBookingSystem instance to load data into
     * @throws IOException If there is an error reading the data source
     * @throws FlightBookingSystemException If there is an error parsing the data or updating the FlightBookingSystem
     */
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException;
    
    /**
     * Stores current data from the provided FlightBookingSystem instance
     * into a data source. Implementing classes should define how data is
     * retrieved from the FlightBookingSystem and formatted for storage.
     * 
     * @param fbs The FlightBookingSystem instance to store data from
     * @throws IOException If there is an error writing data to the data source
     */
    public void storeData(FlightBookingSystem fbs) throws IOException;
}
