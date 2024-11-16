package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The Command interface represents a command that can be executed within the flight booking system.
 * Implementations of Command interface define specific operations such as adding a new flight,
 * adding a new customer, cancelling a booking, etc.
 * 
 * <p>The Command interface requires implementing classes to provide an execute method
 * that executes the specific command within a FlightBookingSystem instance. This method may
 * throw FlightBookingSystemException and IOException, indicating issues with executing the command
 * or storing data, respectively.
 * 
 * <p>The interface also defines a HELP_MESSAGE constant that provides a summary of available commands
 * and their descriptions.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new Command instance
 * Command command = new AddFlight("ABC123", "New York", "London", LocalDate.now(), 200, 450.0);
 * 
 * // Execute the command within a FlightBookingSystem instance
 * command.execute(flightBookingSystem);
 * }</pre>
 */
public interface Command {

    /**
     * A help message that provides a summary of available commands and their descriptions.
     * This message is intended to guide users on how to interact with the flight booking system.
     */
    public static final String HELP_MESSAGE = "Commands:\n"
        + "\tlistflights                               print all flights\n"
        + "\tlistcustomers                             print all customers\n"
        + "\taddflight                                 add a new flight\n"
        + "\taddcustomer                               add a new customer\n"
        + "\tshowflight                                show flight details\n"
        + "\tshowcustomer                              show customer details\n"
        + "\taddbooking                                add a new booking\n"
        + "\tcancelbooking                             cancel a booking\n"
        + "\teditbooking                               update a booking\n"
        + "\tshowflights 							   shows details of flight\n"
        + "\tdeleteflight                              Delete flight\n"
        + "\tdeletecustomer	                           Delete customer\n"
        + "\tloadgui                                   loads the GUI version of the app\n"
        + "\thelp                                      prints this help message\n"
        + "\tbookwithname                              Book your flight with destination\n"
        + "\tgivefeedback                              Give feedback\n"
        + "\tallocatevipseat                           Allocate VIP or emergency seat\n"
        + "\tapplypromocode                            Apply promocode for discount\n"
        + "\texit                                      exits the program\n"
    	+ "\t!!!THIS TEXT REAPPEARS IF YOU OMIT ANY MISTAKE WHILE ENTERING COMMAND!!!\n ";

    /**
     * Executes the command within the provided FlightBookingSystem instance.
     * Implementing classes define specific operations to be performed within the system,
     * such as adding a flight, cancelling a booking, etc.
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is to be executed
     * @throws FlightBookingSystemException If there is an issue executing the command in the system
     * @throws IOException If there is an error storing data using FlightBookingSystemData
     */
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException;
    
}
