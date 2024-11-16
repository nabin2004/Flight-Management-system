package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The ShowCustomer class represents a command to display details of a specific customer.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the command retrieves the customer information by their ID from the provided FlightBookingSystem instance.
 * It then invokes the showDetails() method of the Customer class to display the details of the customer.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new ShowCustomer command instance for customer with ID 101
 * Command showCustomerCommand = new ShowCustomer(101);
 * 
 * // Execute the command within a FlightBookingSystem instance
 * showCustomerCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 * @see Customer
 */
public class ShowCustomer implements Command {
    
    private int id;
    
    /**
     * Constructs a ShowCustomer command object with the specified customer ID.
     * 
     * @param id The ID of the customer whose details are to be displayed
     */
    public ShowCustomer(int id) {
        this.id = id;
    }
    
    /**
     * Executes the show customer details command within the provided FlightBookingSystem instance.
     * Retrieves the customer information by their ID and displays their details using the showDetails() method.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     * @throws FlightBookingSystemException If there is an error while accessing or processing flight booking system data
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Customer customer = flightBookingSystem.getCustomerByID(id);
        customer.showDetails();
    }
}
