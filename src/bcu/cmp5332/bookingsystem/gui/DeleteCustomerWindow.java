package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.DeleteCustomer;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The DeleteCustomerWindow class represents a GUI window for deleting a customer from the system.
 */
public class DeleteCustomerWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField customerIdText = new JTextField();
    private JButton deleteBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs a DeleteCustomerWindow object.
     *
     * @param mainWindow The main window of the application
     */
    public DeleteCustomerWindow(MainWindow mainWindow) {
        this.mw = mainWindow;
        initialize();
    }

    /**
     * Initializes the contents of the frame.
     */
    private void initialize() {
        setTitle("Delete a Customer");
        setSize(350, 150);
        JPanel topPanel = new JPanel();
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20)); // Top, Left, Bottom, Right padding
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("Customer ID : "));
        topPanel.add(customerIdText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(deleteBtn);
        bottomPanel.add(cancelBtn);

        deleteBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setVisible(true);
    }

    /**
     * Handles the action events triggered by the buttons in the window.
     *
     * @param ae The action event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteBtn) {
            try {
                deleteCustomer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }

    /**
     * Deletes a customer from the system.
     *
     * @throws IOException If an I/O error occurs
     */
    private void deleteCustomer() throws IOException {
        try {
            int customerId = Integer.parseInt(customerIdText.getText());

            // Create and execute the DeleteCustomer Command
            DeleteCustomer deleteCustomer = new DeleteCustomer(customerId);
            deleteCustomer.execute(mw.getFlightBookingSystem());

            // Refresh the view with the list of customers
            mw.displayCustomers();

            // Hide (close) the DeleteCustomerWindow
            this.setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Customer ID must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
