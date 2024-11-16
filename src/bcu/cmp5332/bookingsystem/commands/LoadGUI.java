package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import bcu.cmp5332.bookingsystem.gui.MainWindow;

/**
 * The LoadGUI class represents a command to load the graphical user interface (GUI) for the Flight Booking System.
 * It implements the Command interface, allowing it to be executed within the context of a FlightBookingSystem instance.
 * 
 * <p>Upon execution, the command creates a JFrame with a specific size and layout to display the GUI components.
 * It sets the background color of the frame to white and adds borders for styling.
 * 
 * <p>The command loads image icons from specified absolute paths and adds them to JLabels for display within the frame.
 * It creates a JProgressBar to show loading progress and updates it incrementally.
 * 
 * <p>Two main panels, panelNorth and panelSouth, are created to organize the GUI components in the JFrame.
 * These panels include the logo, name, progress bar, and additional logo images.
 * 
 * <p>A Timer is used to simulate the loading process with incremental progress updates. Once the progress reaches 100%,
 * the JFrame is disposed, and a new MainWindow instance is created to display the main application window.
 * 
 * <p>Example usage:
 * <pre>{@code
 * // Create a new LoadGUI command instance
 * Command loadGUICommand = new LoadGUI();
 * 
 * // Execute the command within a FlightBookingSystem instance
 * loadGUICommand.execute(flightBookingSystem);
 * }</pre>
 * 
 * @see Command
 * @see MainWindow
 */
public class LoadGUI implements Command {

    /**
     * Executes the load GUI command within the provided FlightBookingSystem instance.
     * Initializes a JFrame and loads graphical components such as images, progress bar,
     * and additional logos to simulate the loading process.
     * 
     * <p>Once the loading completes (progress reaches 100%), disposes of the JFrame
     * and initializes the main application window (MainWindow).
     * 
     * @param flightBookingSystem The FlightBookingSystem instance on which the command is executed
     * @throws FlightBookingSystemException If there is an error while accessing or processing flight booking system data
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        JFrame frame = new JFrame("Flight Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        // Set the frame background color to white
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(165, 28, 48)));

        // Load and set the images using the specified absolute paths
        String logoPath = "src/images/fbslogo.png";
        String namePath = "src/images/fbsname.png";
        String otherLogoPath = "src/images/logo.jpg";


        ImageIcon logoIcon = new ImageIcon(logoPath);
        ImageIcon nameIcon = new ImageIcon(namePath);
        ImageIcon otherLogoIcon = new ImageIcon(otherLogoPath);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(nameIcon);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel otherLogoLabel = new JLabel(otherLogoIcon);
        otherLogoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a progress bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(30, 30, 30));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panels for North and South regions
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));
        panelNorth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelNorth.setBackground(Color.WHITE);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.Y_AXIS));
        panelSouth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelSouth.setBackground(Color.WHITE);

        // Add components to panels
        panelNorth.add(Box.createVerticalStrut(20));
        panelNorth.add(logoLabel);
        panelNorth.add(Box.createVerticalStrut(20));

        panelSouth.add(Box.createVerticalStrut(20));
        panelSouth.add(nameLabel);
        panelSouth.add(Box.createVerticalStrut(20));
        panelSouth.add(progressBar);
        panelSouth.add(Box.createVerticalStrut(20));
        panelSouth.add(otherLogoLabel);
        panelSouth.add(Box.createVerticalStrut(20));

        // Add panels to frame
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelSouth, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Simulate loading progress with a timer
        Timer timer = new Timer(1000, e -> {
            Thread progressThread = new Thread(() -> {
                for (int i = 0; i <= 100; i++) {
                    try {
                        if (i <= 3) {
                            Thread.sleep(100);
                        } else if (i <= 10) {
                            Thread.sleep(20);
                        } else if (i >= 89 && i < 92) {
                            Thread.sleep(500);
                        } else if (i == 100) {
                            Thread.sleep(10);
                            new MainWindow(flightBookingSystem); // Initialize main window after loading
                        } else {
                            Thread.sleep(50);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    final int progress = i;
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(progress);
                        if (progress == 100) {
                            frame.dispose(); // Dispose frame after loading completes
                        }
                    });
                }
            });
            progressThread.start();
        });

        timer.setRepeats(false);
        timer.start();
    }
}
