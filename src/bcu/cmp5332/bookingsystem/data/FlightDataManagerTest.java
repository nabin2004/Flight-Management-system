package bcu.cmp5332.bookingsystem.data;

import static org.junit.jupiter.api.Assertions.*;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

class FlightDataManagerTest {

    private static final String TEST_FILE_PATH = "./test-flights.txt";

    private FlightBookingSystem fbs;
    private FlightDataManager flightDataManager;

    @BeforeEach
    void setUp() {
        fbs = new FlightBookingSystem();
        flightDataManager = new FlightDataManager();
    }

    @Test
    void testStoreData() throws IOException, FlightBookingSystemException {
        // Arrange
        Flight flight = new Flight(1, "FL001", "London", "New York", LocalDate.of(2024, 6, 1), 150, 500.0, false);
        fbs.addFlight(flight);

        // Act
        flightDataManager.storeData(fbs);

        // Assert
        File file = new File(FlightDataManager.RESOURCE);
        assertTrue(file.exists());

        try (Scanner scanner = new Scanner(file)) {
            assertTrue(scanner.hasNextLine());
            String line = scanner.nextLine();
            assertEquals("1::FL001::London::New York::2024-06-01::150::500.0", line);
        }
    }

    @Test
    void testLoadData() throws IOException, FlightBookingSystemException {
        // Arrange
        File file = new File(TEST_FILE_PATH);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("1::FL001::London::New York::2024-06-01::150::500.0\n");
        }

        // Act
        flightDataManager.loadData(fbs);

        // Assert
        List<Flight> flights = fbs.getFlights();
        assertEquals(1, flights.size());

        Flight flight = flights.get(0);
        assertEquals(1, flight.getId());
        assertEquals("FL001", flight.getFlightNumber());
        assertEquals("London", flight.getOrigin());
        assertEquals("New York", flight.getDestination());
        assertEquals(LocalDate.of(2024, 6, 1), flight.getDepartureDate());
        assertEquals(150, flight.getCapacity());
        assertEquals(500.0, flight.getPrice());
    }
}
