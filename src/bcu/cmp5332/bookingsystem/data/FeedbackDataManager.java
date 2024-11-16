package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Feedback;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FeedbackDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/feedbacks.txt";
    private final String SEPARATOR = "::";

    /**
     * Retrieves the path to the feedback data file.
     *
     * @return The path to the feedback data file
     */
    protected String getResourcePath() {
        return RESOURCE;
    }

    /**
     * Loads existing feedback data from the feedback data file into the provided FlightBookingSystem instance.
     * Each line in the file represents a feedback record with fields separated by the SEPARATOR.
     *
     * @param fbs The FlightBookingSystem instance to load feedback data into
     * @throws IOException If there is an error reading the data file
     * @throws FlightBookingSystemException If there is an error parsing the feedback data
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(getResourcePath()))) {
            int lineIdx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int bookingId = Integer.parseInt(properties[0]);
                    int customerId = Integer.parseInt(properties[1]);
                    String message = properties[2];
                    Feedback feedback = new Feedback(bookingId, customerId, message);
                    fbs.addFeedback(feedback);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse feedback on line " + lineIdx + "\nError: " + ex);
                }
                lineIdx++;
            }
        }
    }

    /**
     * Stores current feedback data from the provided FlightBookingSystem instance into the feedback data file.
     * Each feedback's details are formatted and written to the file, separated by the SEPARATOR.
     *
     * @param fbs The FlightBookingSystem instance to store feedback data from
     * @throws IOException If there is an error writing data to the file
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(getResourcePath()))) {
            for (Feedback feedback : fbs.getFeedbacks()) {
                out.print(feedback.getBookingID() + SEPARATOR);
                out.print(feedback.getCustomerID() + SEPARATOR);
                out.print(feedback.getMessage() + SEPARATOR);
                out.println();
            }
        }
    }
}
