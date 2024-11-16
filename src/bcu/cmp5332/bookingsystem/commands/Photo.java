package bcu.cmp5332.bookingsystem.commands;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Photo {
    public JLabel getLogoLabel() {
        // Load the image and create a JLabel
        ImageIcon icon = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\Java\\Calculator\\src\\calculator\\logo.jpg");
        JLabel label = new JLabel(icon);
        return label;
    }
        public static void main(String[] args) {
            JFrame frame = new JFrame("Flight Booking System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLayout(new BorderLayout());

            // Create an instance of Photo
            Photo photo = new Photo();
            // Get the JLabel with the logo
            JLabel logoLabel = photo.getLogoLabel();
            // Add the logo label to the frame
            frame.add(logoLabel, BorderLayout.CENTER);

            // Other components...
            
            frame.setVisible(true);
        }
    }
