package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddFeedback;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GiveFeedbackGUI extends JFrame {

    private JTextField bookingIDField;
    private JTextField customerIDField;
    private JTextArea messageArea;

    private MainWindow mainWindow;

    /**
     * Constructs a GiveFeedbackGUI object.
     *
     * @param mainWindow The main window of the application
     */
    public GiveFeedbackGUI(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        initialize();
    }

    /**
     * Initializes the GUI components and sets up the frame.
     */
    private void initialize() {
        setTitle("Give Feedback");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(mainWindow);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel bookingIDLabel = new JLabel("Booking ID:");
        bookingIDField = new JTextField();
        inputPanel.add(bookingIDLabel);
        inputPanel.add(bookingIDField);

        JLabel customerIDLabel = new JLabel("Customer ID:");
        customerIDField = new JTextField();
        inputPanel.add(customerIDLabel);
        inputPanel.add(customerIDField);

        JLabel messageLabel = new JLabel("Feedback Message:");
        messageArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        inputPanel.add(messageLabel);
        inputPanel.add(scrollPane);

        panel.add(inputPanel, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitFeedback();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    /**
     * Submits the feedback provided by the user.
     */
    private void submitFeedback() {
        try {
            int bookingID = Integer.parseInt(bookingIDField.getText().trim());
            int customerID = Integer.parseInt(customerIDField.getText().trim());
            String message = messageArea.getText().trim();

            // Create and execute the AddFeedback command
            AddFeedback command = new AddFeedback(bookingID, customerID, message);
            command.execute(mainWindow.getFlightBookingSystem());

            // Show success message and clear fields
            JOptionPane.showMessageDialog(this, "Feedback submitted successfully.");
            bookingIDField.setText("");
            customerIDField.setText("");
            messageArea.setText("");

            // Close the GiveFeedbackGUI window
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid IDs (numeric values).", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FlightBookingSystemException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Error submitting feedback: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
