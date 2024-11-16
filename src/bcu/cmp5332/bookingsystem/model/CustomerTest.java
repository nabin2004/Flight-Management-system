package bcu.cmp5332.bookingsystem.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void testGetEmail() {
        // (int id, String name, String phone, String email)
        Customer customer = new Customer(1, "Nabin Oli", "980000", "nabin@gmail.com", false,false);
        assertEquals("nabin@gmail.com", customer.getEmail());
    }

    @Test
    void testSetEmail() {
        Customer customer = new Customer(1, "Nabin Oli", "980000", "nabin@gmail.com", false,false);
        customer.setEmail("nabinoli2004@gmail.com");
        assertEquals("nabinoli2004@gmail.com", customer.getEmail());
    }
}
