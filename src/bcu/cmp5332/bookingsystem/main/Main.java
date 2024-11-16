package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;

/**
 * The main class of the Flight Booking System.
 * It handles user interactions and executes commands.
 */
public class Main {

    /**
     * The main method of the Flight Booking System.
     * It initializes the system, handles user input, and executes commands.
     *
     * @param args Command-line arguments (not used)
     * @throws IOException                If an I/O error occurs
     * @throws FlightBookingSystemException If there's an error related to the Flight Booking System
     */
    public static void main(String[] args) throws IOException, FlightBookingSystemException {
        
        // Load the Flight Booking System data
        FlightBookingSystem fbs = FlightBookingSystemData.load();

        // Create a BufferedReader object to read user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Display welcome message and instructions
        System.out.println("Flight Booking System");
        System.out.println("Enter 'help' to see a list of available commands.");

        // Main loop for user interaction
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line.equals("exit")) {
                break;
            }

            try {
                // Parse the user input and execute the corresponding command
                Command command = CommandParser.parse(line);
                command.execute(fbs);
            } catch (FlightBookingSystemException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // Exit the program
        System.exit(0);
    }
}
