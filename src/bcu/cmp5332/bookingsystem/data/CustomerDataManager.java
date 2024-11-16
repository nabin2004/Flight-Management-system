package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The CustomerDataManager class implements the DataManager interface
 * to manage loading and storing of customer data to/from a text file.
 * It provides methods to load existing customer data into a FlightBookingSystem instance,
 * and to store current customer data from a FlightBookingSystem instance into a text file.
 * 
 * <p>The customer data is stored in a text file specified by the RESOURCE constant.
 * Each line in the file represents a customer record, separated by the SEPARATOR "::".
 * 
 * <p>The loadData method reads from the text file, parses each line to retrieve customer details,
 * and adds the parsed customers to the provided FlightBookingSystem instance.
 * 
 * <p>The storeData method retrieves all customers from the provided FlightBookingSystem instance,
 * formats them into a string representation, and writes them to the text file for storage.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new instance of FlightBookingSystem and CustomerDataManager
 * FlightBookingSystem fbs = new FlightBookingSystem();
 * CustomerDataManager customerDataManager = new CustomerDataManager();
 * 
 * // Load existing customer data into FlightBookingSystem
 * customerDataManager.loadData(fbs);
 * 
 * // Perform operations on fbs...
 * 
 * // Store current customer data from FlightBookingSystem
 * customerDataManager.storeData(fbs);
 * }</pre>
 * 
 * @see DataManager
 * @see Customer
 * @see FlightBookingSystem
 */
public class CustomerDataManager implements DataManager {

    /**
     * The path to the customers data file.
     */
    private final String RESOURCE = "./resources/data/customers.txt";

    /**
     * Retrieves the path to the customers data file.
     * 
     * @return The path to the customers data file
     */
    protected String getResourcePath() {
        return RESOURCE;
    }
    
    /**
     * Loads existing customer data from the customers data file into the provided FlightBookingSystem instance.
     * Each line in the file represents a customer record with fields separated by the SEPARATOR.
     * 
     * @param fbs The FlightBookingSystem instance to load customer data into
     * @throws IOException If there is an error reading the data file
     * @throws FlightBookingSystemException If there is an error parsing the customer data
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(getResourcePath()))){
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String name = properties[1];
                    String phone = properties[2];
                    String email = properties[3];
                    // Parse boolean value from string
                    boolean isDeleted = Boolean.parseBoolean(properties[4]);
                    boolean isVip = Boolean.parseBoolean(properties[5]);
                    Customer customer = new Customer(id, name, phone, email, isDeleted,isVip);
                    fbs.addCustomer(customer);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse customer id " + properties[0] + " on line " + line_idx
                            + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    /**
     * Stores current customer data from the provided FlightBookingSystem instance into the customers data file.
     * Each customer's details are formatted and written to the file, separated by the SEPARATOR.
     * 
     * @param fbs The FlightBookingSystem instance to store customer data from
     * @throws IOException If there is an error writing data to the file
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(getResourcePath()))) {
            for (Customer customer : fbs.getCustomers()) {
                out.print(customer.getId() + SEPARATOR);
                out.print(customer.getName() + SEPARATOR);
                out.print(customer.getPhone() + SEPARATOR);
                out.print(customer.getEmail() + SEPARATOR);
                out.print(customer.getDeleted() + SEPARATOR);
                out.print(customer.isVIP() + SEPARATOR);
                out.println();
            }
        }
    }
}
