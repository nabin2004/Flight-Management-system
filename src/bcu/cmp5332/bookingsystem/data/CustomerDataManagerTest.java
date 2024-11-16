package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataManagerTest {

    @TempDir
    Path tempDir;

    private FlightBookingSystem fbs;
    private CustomerDataManager customerDataManager;
    private String tempFilePath;

    @BeforeEach
    void setUp() throws IOException {
        fbs = new FlightBookingSystem();

        // Use a temporary file for testing
        File tempFile = tempDir.resolve("customers.txt").toFile();
        tempFilePath = tempFile.getAbsolutePath();

        // Override the RESOURCE path in CustomerDataManager for testing
        customerDataManager = new CustomerDataManager() {
            @Override
            protected String getResourcePath() {
                return tempFilePath;
            }
        };
    }

    @Test
    void testStoreData() throws IOException, FlightBookingSystemException {
        // Add a customer to the system
        Customer customer = new Customer(1, "Nabin Oli", "980000", "nabin@gmail.com", false,false);
        fbs.addCustomer(customer);

        // Store the data
        customerDataManager.storeData(fbs);

        // Read the file to check if the data was stored correctly
        File file = new File(tempFilePath);
        assertTrue(file.exists());

        try (Scanner sc = new Scanner(file)) {
            assertTrue(sc.hasNextLine());
            String line = sc.nextLine();
            assertEquals("1::Nabin Oli::980000::nabin@gmail.com::", line);
        }
    }

    @Test
    void testLoadData() throws IOException, FlightBookingSystemException {
        try (FileWriter writer = new FileWriter(tempFilePath)) {
            writer.write("1::Nabin Oli::980000::nabin@gmail.com::\n");
        }

        // Load the data
        customerDataManager.loadData(fbs);

        // Check if the customer data was loaded correctly
        List<Customer> customers = fbs.getCustomer();
        assertEquals(1, customers.size());

        Customer customer = customers.get(0);
        assertEquals(1, customer.getId());
        assertEquals("Nabin Oli", customer.getName());
        assertEquals("980000", customer.getPhone());
        assertEquals("nabin@gmail.com", customer.getEmail());
    }
}
