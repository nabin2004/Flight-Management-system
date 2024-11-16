package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code FlightBookingSystemData} class is responsible for loading and storing
 * the flight booking system data. It uses multiple {@link DataManager} instances
 * to manage different aspects of the data.
 * 
 * <p>This class contains static methods to load and store data for the 
 * {@link FlightBookingSystem}. The data managers are initialized when the 
 * class is loaded into memory.
 * 
 * <p>Note: The {@code CustomerDataManager} and {@code BookingDataManager}
 * are included in the list of data managers but their {@code loadData()} 
 * and {@code storeData()} methods need to be implemented.
 * 
 * @see DataManager
 * @see FlightDataManager
 * @see CustomerDataManager
 * @see BookingDataManager
 * @see FlightBookingSystem
 */
public class FlightBookingSystemData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new FlightDataManager());
        
        /* Uncomment the two lines below when the implementation of their 
        loadData() and storeData() methods is complete */
         dataManagers.add(new CustomerDataManager());
         dataManagers.add(new BookingDataManager());
         dataManagers.add(new FeedbackDataManager());
    }
    
    /**
     * Loads the flight booking system data.
     * 
     * <p>This method creates a new instance of {@link FlightBookingSystem} and 
     * populates it with data from all registered {@link DataManager} instances.
     * 
     * @return a fully populated {@link FlightBookingSystem} instance
     * @throws FlightBookingSystemException if there is an issue with loading the data
     * @throws IOException if there is an I/O error during the loading process
     */
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

        FlightBookingSystem fbs = new FlightBookingSystem();
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        return fbs;
    }

    /**
     * Stores the flight booking system data.
     * 
     * <p>This method takes a {@link FlightBookingSystem} instance and saves its data
     * using all registered {@link DataManager} instances.
     * 
     * @param fbs the {@link FlightBookingSystem} instance to store
     * @throws IOException if there is an I/O error during the storing process
     */
    public static void store(FlightBookingSystem fbs) throws IOException {
        for (DataManager dm : dataManagers) {
            dm.storeData(fbs);
        }
    }
    
}
