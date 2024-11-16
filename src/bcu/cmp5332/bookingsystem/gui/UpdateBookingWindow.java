package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.UpdateBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UpdateBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField bookingIDText = new JTextField();
    private JTextField newFlightNoText = new JTextField();
    private JTextField newBookDateText = new JTextField(); // New text field for booking date

    private JButton updateBtn = new JButton("Update");
    private JButton cancelBtn = new JButton("Cancel");

    public UpdateBookingWindow(MainWindow mainWindow) {
        this.mw = mainWindow;
        initialize();
    }

    private void initialize() {
        setTitle("Update Booking");
        setSize(350, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("Booking ID : "));
        topPanel.add(bookingIDText);
        topPanel.add(new JLabel("New Flight No : "));
        topPanel.add(newFlightNoText);
        topPanel.add(new JLabel("New Booking Date (YYYY-MM-DD) : ")); // Label for booking date
        topPanel.add(newBookDateText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(updateBtn);
        bottomPanel.add(cancelBtn);

        updateBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            try {
                updateBooking();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }

    private void updateBooking() throws IOException {
        try {
            int bookingID = Integer.parseInt(bookingIDText.getText());
            String newFlightNumber = Integer.toString(Integer.parseInt(newFlightNoText.getText()));
            LocalDate newBookDate = LocalDate.parse(newBookDateText.getText());

            // Create and execute the UpdateBooking Command
            Command updateBooking = new UpdateBooking(bookingID, newFlightNumber, newBookDate);
            updateBooking.execute(mw.getFlightBookingSystem());

            // Refresh the view with the list of bookings or flights
            mw.displayBookings();  // You can implement a method to display bookings if needed

            // Hide (close) the UpdateBookingWindow
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Booking ID and Flight number must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException dtpe) {
            JOptionPane.showMessageDialog(this, "Booking date must be in YYYY-MM-DD format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
