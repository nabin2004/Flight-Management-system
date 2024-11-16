package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;

/**
 * The DeleteCustomer class represents a command to delete a customer from the flight booking system.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the customer with the specified ID is marked as deleted by setting the 'deleted' flag.
 * The modified system data is then stored using FlightBookingSystemData.
 * 
 * <p>If the customer with the specified ID does not exist in the system, a FlightBookingSystemException is thrown.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new DeleteCustomer command instance for customer with ID 101
 * Command deleteCustomerCommand = new DeleteCustomer(101);
 * 
 * // Execute the command within a FlightBookingSystem instance
 * deleteCustomerCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 */
public class DeleteCustomer implements Command {

    private final int customerID;

    /**
     * Constructs a DeleteCustomer command object with the specified customer ID.
     * 
     * @param customerID The ID of the customer to be deleted
     */
    public DeleteCustomer(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Executes the delete customer command within the provided FlightBookingSystem instance.
     * Marks the customer with the specified ID as deleted by setting the 'deleted' flag.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     * @throws FlightBookingSystemException If there is an issue with deleting the customer (e.g., customer not found)
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Customer customerToDelete = flightBookingSystem.getCustomerByID(this.customerID);
        if (customerToDelete == null) {
            throw new FlightBookingSystemException("Customer with ID " + customerID + " not found.");
        }

        customerToDelete.setDeleted();  // Soft delete by setting the flag
        System.out.println("Customer #" + customerToDelete.getId() + " marked as deleted.");
        FlightBookingSystemData.store(flightBookingSystem);
    }
}
