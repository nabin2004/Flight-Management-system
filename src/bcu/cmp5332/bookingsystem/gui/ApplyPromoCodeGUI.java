package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.ApplyPromocode;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The ApplyPromoCodeGUI class represents a GUI window for applying a promotional code to a booking.
 */
public class ApplyPromoCodeGUI extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField bookingIDField = new JTextField();
    private JTextField promocodeField = new JTextField();

    private JButton applyBtn = new JButton("Apply");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an ApplyPromoCodeGUI object.
     *
     * @param mainWindow The main window of the application
     */
    public ApplyPromoCodeGUI(MainWindow mainWindow) {
        this.mw = mainWindow;
        initialize();
    }

    /**
     * Initializes the contents of the frame.
     */
    private void initialize() {
        setTitle("Apply Promocode");
        setSize(350, 150);
        JPanel topPanel = new JPanel();
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20)); // Top, Left, Bottom, Right padding
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("Booking ID : "));
        topPanel.add(bookingIDField);
        topPanel.add(new JLabel("Promocode : "));
        topPanel.add(promocodeField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(applyBtn);
        bottomPanel.add(cancelBtn);

        applyBtn.addActionListener(this);
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
        if (ae.getSource() == applyBtn) {
            try {
                applyPromocode();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }

    /**
     * Applies the promocode to the booking.
     *
     * @throws IOException If an I/O error occurs
     */
    private void applyPromocode() throws IOException {
        try {
            int bookingID = Integer.parseInt(bookingIDField.getText());
            String promocode = promocodeField.getText();

            // Create and execute the ApplyPromocode Command
            Command applyPromocode = new ApplyPromocode(bookingID, promocode);
            applyPromocode.execute(mw.getFlightBookingSystem());

            // Refresh the view with the list of bookings
            mw.displayBookings();

            // Hide (close) the ApplyPromoCodeGUI
            this.setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Booking ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
