package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.*;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The CommandParser class is responsible for parsing user input and creating corresponding Command objects.
 */
public class CommandParser {

    /**
     * Parses the user input and creates a Command object accordingly.
     *
     * @param line The user input string
     * @return The Command object corresponding to the user input
     * @throws IOException                If an I/O error occurs
     * @throws FlightBookingSystemException If the input command is invalid or cannot be processed
     */
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            if(cmd.equals("showflights")) {
            	return new ShowFlights();
            			
            }if (cmd.equals("givefeedback")) {
            	    Scanner scanner = new Scanner(System.in);

            	    // Prompting for the feedback message
            	    System.out.print("Enter your feedback message: ");
            	    String message = scanner.nextLine();

            	    // Prompting for the booking ID
            	    System.out.print("Enter your booking ID: ");
            	    int bookingId = scanner.nextInt();

            	    // Prompting for the flight ID
            	    System.out.print("Enter your flight ID: ");
            	    int flightId = scanner.nextInt();

            	    // Creating a new AddFeedback command with the provided details
            	    return new AddFeedback(bookingId, flightId, message);
            	}
            if (cmd.equals("allocatevipseat")) {
            	 Scanner scanner = new Scanner(System.in);
                System.out.println("Enter customerId:");
                int customerId = scanner.nextInt();

                System.out.println("Enter flightId:");
                int flightId = scanner.nextInt();

                // You can handle LocalDate input based on your application's requirements
                // Here, we're setting bookingDate to null as per the original example
                LocalDate bookingDate = null;

                System.out.println("Enter bookingId:");
                int bookingId = scanner.nextInt();

                VIPSeatAllocation vipSeatAllocation = new VIPSeatAllocation(customerId, flightId, bookingDate, bookingId);
                // Now you can return or use vipSeatAllocation as needed
                System.out.println("VIPSeatAllocation object created: " + vipSeatAllocation);
            }

            if (cmd.equals("addflight")) {
                Scanner sc2 = new Scanner(System.in);
                System.out.print("Flight Number: ");
                String flightNumber = sc2.nextLine();
                System.out.print("Origin: ");
                String origin = sc2.nextLine();
                System.out.print("Destination: ");
                String destination = sc2.nextLine();
                System.out.print("Enter Date (yyyy-MM-dd): ");
                LocalDate departureDate = LocalDate.parse(sc2.nextLine());
                System.out.print("Enter capacity: ");
                int capacity = sc2.nextInt();
                System.out.print("Enter price: ");
                int price = sc2.nextInt();

                return new AddFlight(flightNumber, origin, destination, departureDate, capacity, price);
                
            } else if (cmd.equals("addcustomer")) {
                System.out.print("Customer name: ");
                String name = reader.readLine();
                System.out.print("Customer phone: ");
                String phone = reader.readLine();
                System.out.println("Customer email: ");
                String email = reader.readLine();
                return new AddCustomer(name, phone, email);
            } else if(cmd.equals("bookwithname")){
//            	return new BookingWithName();
            }  else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (cmd.equals("deletecustomer")) {
                Scanner sc2 = new Scanner(System.in);
                System.out.print("Enter customer id: ");
                int custId = sc2.nextInt();
                return new DeleteCustomer(custId);
            } else if (cmd.equals("deleteflight")) {
                System.out.print("Enter flight ID: ");
                Scanner sc1 = new Scanner(System.in);
                int delId = sc1.nextInt();
                return new DeleteFlight(delId);
            } else if (parts.length == 1) {
                if (line.equals("listflights")) {
                    return new ListFlights();
                } else if (line.equals("listcustomers")) {
                    return new ListCustomer();
                } else if (line.equals("help")) {
                    return new Help();
                }
            } 
                if (cmd.equals("showflight")) {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter flight no. :");
                    int fid = sc.nextInt();
                    return new ShowFlight(fid);
                } else if (cmd.equals("showcustomer")) {
                    System.out.print("Enter customer id: ");
                    Scanner sc = new Scanner(System.in);
                    int id1 = sc.nextInt();
                    return new ShowCustomer(id1);
                }
            
                if (cmd.equals("addbooking")) {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Customer ID: ");
                    int cid = sc.nextInt();
                    System.out.print("outbound Flight ID: ");
                    int inflightId = sc.nextInt();
                    System.out.print("inbound Flight ID: ");
                    int outflightId = sc.nextInt();
                    System.out.print("bookDate: ");
                    LocalDate date = parseDateWithAttempts(reader);
                    return new AddBooking(cid, outflightId, date);
                } else if (cmd.equals("editbooking")) {
                	 Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter bookingID:");
                    int bookingID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter newFlightNumber:");
                    String newFlightNumber = scanner.nextLine();

                    System.out.println("Enter new booking date (YYYY-MM-DD):");
                    String dateString = scanner.nextLine();
                    LocalDate newBookDate = LocalDate.parse(dateString);
                    
                    return new UpdateBooking(bookingID, newFlightNumber, newBookDate);
                    
                }else if (cmd.equals("applypromocode")) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter booking ID: ");
                    int bookingID = sc.nextInt();
                    sc.nextLine(); // Consume the newline character left by nextInt()

                    System.out.println("Enter promocode: ");
                    String promocode = sc.nextLine();

                    System.out.println("Applying the promocode....");
                    return new ApplyPromocode(bookingID, promocode);
                }
                	else if (cmd.equals("cancelbooking")) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter customer id: ");
                    int cid = sc.nextInt();
                    System.out.println("Enter flight id: ");
                    int fid = sc.nextInt();
                    System.out.println("Canceling the booking....");
                    return new CancelBooking(cid, fid);
                }else {
                	return new Help();
                }
            
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }

    /**
     * Parses the date with multiple attempts.
     *
     * @param br The BufferedReader object to read input
     * @return The parsed LocalDate object
     * @throws IOException                If an I/O error occurs
     * @throws FlightBookingSystemException If the date cannot be parsed after multiple attempts
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }

    /**
     * Parses the date with multiple attempts.
     *
     * @param br       The BufferedReader object to read input
     * @param attempts The number of attempts to parse the date
     * @return The parsed LocalDate object
     * @throws IOException                If an I/O error occurs
     * @throws FlightBookingSystemException If the date cannot be parsed after multiple attempts
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }

        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
}

