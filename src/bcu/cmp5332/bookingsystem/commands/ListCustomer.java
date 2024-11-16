package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.util.List;

/**
 * The ListCustomer class represents a command to list all customers in the flight booking system.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the command retrieves a list of all customers from the FlightBookingSystem instance.
 * It then iterates through the list and prints the short details (using getDetailsShort()) of each customer to the console,
 * excluding any customers marked as deleted.
 * 
 * <p>After listing all customers, it prints the total number of customers retrieved.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new ListCustomer command instance
 * Command listCustomerCommand = new ListCustomer();
 * 
 * // Execute the command within a FlightBookingSystem instance
 * listCustomerCommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 */
public class ListCustomer implements Command {

    /**
     * Executes the list customer command within the provided FlightBookingSystem instance.
     * Retrieves a list of all customers and prints their short details to the console.
     * Excludes customers marked as deleted.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     * @throws FlightBookingSystemException If there is an error while accessing or processing flight booking system data
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Customer> customers = flightBookingSystem.getCustomer();
        for (Customer customer : customers) {
            if (!customer.getDeleted()) {
                System.out.println(customer.getDetailsShort());
            }
        }
        System.out.println(customers.size() + " customer(s)");
    }
}
