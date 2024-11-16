package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class IssueBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField flightNoText = new JTextField();
    private JTextField customerIDText = new JTextField();
    private JTextField bookDateText = new JTextField(); // New text field for booking date

    private JButton issueBtn = new JButton("Issue");
    private JButton cancelBtn = new JButton("Cancel");
    private JButton showFlightsBtn = new JButton("Show Flights");
    private JButton showCustomersBtn = new JButton("Show Customers");

    public IssueBookingWindow(MainWindow mainWindow) {
        this.mw = mainWindow;
        initialize();
    }

    private void initialize() {
        setTitle("Issue a New Booking");
        setSize(400, 250);
        JPanel topPanel = new JPanel();
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20)); // Top, Left, Bottom, Right padding
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Flight No : "));
        topPanel.add(flightNoText);
        topPanel.add(new JLabel("Customer ID : "));
        topPanel.add(customerIDText);
        topPanel.add(new JLabel("Booking Date (YYYY-MM-DD) : ")); // Label for booking date
        topPanel.add(bookDateText);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel flightHelpLabel = new JLabel("Need help finding ID? ");
        flightHelpLabel.setFont(new Font(flightHelpLabel.getFont().getName(), Font.PLAIN, 10));


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 3));
        bottomPanel.add(flightHelpLabel, gbc);
        bottomPanel.add(showFlightsBtn);
        bottomPanel.add(showCustomersBtn);
        bottomPanel.add(new JLabel("     "));
//        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(issueBtn);
        bottomPanel.add(cancelBtn);

        issueBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        showFlightsBtn.addActionListener(this);
        showCustomersBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == issueBtn) {
            try {
                issueBooking();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        } else if (ae.getSource() == showFlightsBtn) {
            mw.displayFlights();
        } else if (ae.getSource() == showCustomersBtn) {
            mw.displayCustomers();
        }
    }

    private void issueBooking() throws IOException {
        try {
            int flightNumber = Integer.parseInt(flightNoText.getText());
            int customerID = Integer.parseInt(customerIDText.getText());
            LocalDate bookDate = LocalDate.parse(bookDateText.getText());

            // Create and execute the IssueBooking Command
            Command issueBooking = new AddBooking(customerID, flightNumber, bookDate);
            issueBooking.execute(mw.getFlightBookingSystem());

            // Refresh the view with the list of bookings or flights
            mw.displayFlights();  // You can implement a method to display bookings if needed

            // Hide (close) the IssueBookingWindow
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Flight number and Customer ID must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException dtpe) {
            JOptionPane.showMessageDialog(this, "Booking date must be in YYYY-MM-DD format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
