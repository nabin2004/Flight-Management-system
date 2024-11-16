package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The CancelBookingWindow class represents a GUI window for canceling a booking.
 */
public class CancelBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField bookingIdText = new JTextField();
    private JTextField customerIdText = new JTextField();

    private JButton cancelBtn = new JButton("Cancel Booking");
    private JButton closeBtn = new JButton("Close");

    /**
     * Constructs a CancelBookingWindow object.
     *
     * @param mainWindow The main window of the application
     */
    public CancelBookingWindow(MainWindow mainWindow) {
        this.mw = mainWindow;
        initialize();
    }

    /**
     * Initializes the contents of the frame.
     */
    private void initialize() {
        setTitle("Cancel a Booking");
        setSize(350, 150);
        JPanel topPanel = new JPanel();
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20)); // Top, Left, Bottom, Right padding
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("Booking ID : "));
        topPanel.add(bookingIdText);
        topPanel.add(new JLabel("Customer ID : "));
        topPanel.add(customerIdText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(cancelBtn);
        bottomPanel.add(closeBtn);

        cancelBtn.addActionListener(this);
        closeBtn.addActionListener(this);

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
        if (ae.getSource() == cancelBtn) {
            try {
                cancelBooking();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == closeBtn) {
            this.setVisible(false);
        }
    }

    /**
     * Cancels a booking in the system.
     *
     * @throws IOException If an I/O error occurs
     */
    private void cancelBooking() throws IOException {
        try {
            int bookingId = Integer.parseInt(bookingIdText.getText());
            int customerId = Integer.parseInt(customerIdText.getText());

            // Create and execute the CancelBooking Command
            Command cancelBooking = new CancelBooking(customerId, bookingId);
            cancelBooking.execute(mw.getFlightBookingSystem());

            // Refresh the view with the list of bookings
            mw.displayBookings();

            // Hide (close) the CancelBookingWindow
            this.setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Booking ID or Customer ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
