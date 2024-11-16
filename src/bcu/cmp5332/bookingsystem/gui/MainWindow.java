package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Insets;
import java.awt.PopupMenu;

import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import java.awt.Toolkit;
import javax.swing.JScrollBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;

    private JMenuItem adminExit;
    private JMenuItem adminLogin;
    private JMenuItem customerLogin;

    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;
    
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;

    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;

    private FlightBookingSystem fbs;
    private JPanel panel;
    private JLabel logoLabel_1;
    private JPanel panel_1;
    private JLabel lblNewLabel;
    private JToolBar toolBar;
    private JMenuItem flightsView_1;
    private JToolBar toolBar_1;
    private JToolBar toolBar_2;
    private JToolBar toolBar_3;
    private JToolBar toolBar_4;
    private JToolBar toolBar_5;
    private JMenuItem flightsAdd_1;
    private JMenuItem bookingsIssue_1;
    private JMenuItem bookingsCancel_1;
    private JMenuItem custAdd_1;
    private JTable table_1;
    private JMenuItem homeMenuItem;
	private JMenuItem applyPromoCode;
	private JToolBar toolBarPromocode;
//	private JMenu feedBackForm;
	private JMenuItem feedbackForm;


    
    public MainWindow(FlightBookingSystem fbs) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\Downloads\\menuBarImg.jpg"));

        initialize();
        this.fbs = fbs;
    }
    public void closeWindow() {
        dispose(); 
    }
    
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }
    
    

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            // Handle the exception
        }

        setTitle("Flight Booking Management System");
        getContentPane().setLayout(new BorderLayout()); // Set the layout to BorderLayout
        
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(0, 0, 0, 0)); // Top, Left, Bottom, Right padding
        JPanel panelTop = new JPanel();
        String logopath = "C:\\Users\\Lenovo\\Desktop\\Java\\Final Project\\fmsfinal\\FlightBookingSystem_Dist (2).zip_expanded\\FlightBookingSystem_Dist\\src\\images\\menuBarImg.jpg";
        ImageIcon logoIcon = new ImageIcon(logopath);

        menuBar = new JMenuBar();
        menuBar.setMargin(new Insets(12, 0, 0, 0));
        setJMenuBar(menuBar);
        JLabel logoLabel = new JLabel(logoIcon);
        menuBar.add(logoLabel);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeMenuItem = new JMenu("Home");
        homeMenuItem.addActionListener(this);
        menuBar.add(homeMenuItem);
        
        // Adding Flights menu and menu items
        flightsMenu = new JMenu("Flights");
        flightsMenu.setForeground(UIManager.getColor("Button.shadow"));
        flightsMenu.setBackground(UIManager.getColor("Button.disabledForeground"));
        flightsMenu.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(flightsMenu);

        flightsView = new JMenuItem("View");
        flightsMenu.add(flightsView);
        flightsAdd = new JMenuItem("Add");
        flightsMenu.add(flightsAdd);
        flightsDel = new JMenuItem("Delete");
        flightsMenu.add(flightsDel);
        // Adding action listener for Flights menu items
        for (int i = 0; i < flightsMenu.getItemCount(); i++) {
            flightsMenu.getItem(i).addActionListener(this);
        }
        
        // Adding Bookings menu and menu items
        bookingsMenu = new JMenu("Bookings");
        bookingsMenu.setForeground(UIManager.getColor("Button.shadow"));
        bookingsMenu.setBackground(UIManager.getColor("Button.disabledForeground"));
        bookingsMenu.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(bookingsMenu);
        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        applyPromoCode = new JMenuItem("Apply Promocode");
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        bookingsMenu.add(applyPromoCode);
        // Adding action listener for Bookings menu items
        for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
            bookingsMenu.getItem(i).addActionListener(this);
        }

        // Adding Customers menu and menu items
        customersMenu = new JMenu("Customers");
        customersMenu.setForeground(UIManager.getColor("Button.shadow"));
        customersMenu.setBackground(UIManager.getColor("Button.disabledForeground"));
        customersMenu.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(customersMenu);
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");
        
        custView = new JMenuItem("View");
        feedbackForm = new JMenuItem("Feedback Form");
        customersMenu.add(feedbackForm);
        feedbackForm.addActionListener(this);
        
        customersMenu.add(custView);
        // Adding action listener for Customers menu items
        custView.addActionListener(this);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);
        
        // Adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        adminMenu.setForeground(UIManager.getColor("Button.shadow"));
        adminMenu.setBackground(UIManager.getColor("Button.disabledForeground"));
        adminMenu.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(adminMenu);
                
//        feedBackForm = new JMenu("Feedback Form");
//        feedBackForm.setForeground(UIManager.getColor("Button.shadow"));
//        feedBackForm.setBackground(UIManager.getColor("Button.disabledForeground"));
//        feedBackForm.setHorizontalAlignment(SwingConstants.LEFT);
//        feedBackForm.addActionListener(this);
//        menuBar.add(feedBackForm);
        
        
        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);
        
        adminLogin = new JMenuItem("Admin Login");
        adminMenu.add(adminLogin);
        adminLogin.addActionListener(this);
        
        customerLogin = new JMenuItem("Customer Login");
        adminMenu.add(customerLogin);
        adminLogin.addActionListener(this);
        
        
        custAdd.addActionListener(this);
        custDel.addActionListener(this);

        getContentPane().add(panelTop, BorderLayout.NORTH); 
        
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        panel_1 = new JPanel();
        panel_1.setBounds(393, 5, 0, 0);
        panel.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        logoLabel_1 = new JLabel((Icon) null);
        logoLabel_1.setBounds(398, 5, 0, 0);
        logoLabel_1.setAlignmentX(0.5f);
        panel.add(logoLabel_1);
        
        lblNewLabel = new JLabel("\r\n");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Lenovo\\Downloads\\istockphoto-868924070-612x612.jpg"));
        lblNewLabel.setBounds(139, 5, 610, 240);
        panel.add(lblNewLabel);
        
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(759, 5, 17, 392);
        panel.add(scrollBar);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new CompoundBorder());
        panel_2.setBounds(0, 5, 129, 392);
        panel.add(panel_2);
        
        toolBar = new JToolBar();
        panel_2.add(toolBar);
        
        flightsView_1 = new JMenuItem("View Customers");
        flightsView_1.addActionListener(this);
        toolBar.add(flightsView_1);

        toolBar_1 = new JToolBar();
        panel_2.add(toolBar_1);

        flightsAdd_1 = new JMenuItem("Add Booking");
        flightsAdd_1.addActionListener(this); 
        toolBar_1.add(flightsAdd_1);

        toolBar_2 = new JToolBar();
        panel_2.add(toolBar_2);

        bookingsIssue_1 = new JMenuItem("Issue Booking");
        bookingsIssue_1.addActionListener(this); 
        toolBar_2.add(bookingsIssue_1);

        toolBar_3 = new JToolBar();
        panel_2.add(toolBar_3);

        bookingsCancel_1 = new JMenuItem("Cancel Booking");
        bookingsCancel_1.addActionListener(this); 
        toolBar_3.add(bookingsCancel_1);
        
        toolBarPromocode = new JToolBar();
        panel_2.add(toolBarPromocode);
        
//        applyPromoCode = new JMenuItem("Apply Promocode");
//        applyPromoCode.addActionListener(this);
//        toolBarPromocode.add(applyPromoCode);

        toolBar_4 = new JToolBar();
        panel_2.add(toolBar_4);

        custAdd_1 = new JMenuItem("Add Customer");
        custAdd_1.addActionListener(this);
        toolBar_4.add(custAdd_1);

        toolBar_5 = new JToolBar();
        panel_2.add(toolBar_5);

        JMenuItem adminExit_1 = new JMenuItem("Exit");
        adminExit_1.addActionListener(this); 
        toolBar_5.add(adminExit_1);

        JSeparator separator = new JSeparator();
        separator.setBounds(142, 268, 610, 0);
        panel.add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(142, 5, 1, 402);
        panel.add(separator_1);
        
        table_1 = new JTable();
        table_1.setBounds(142, 255, 610, 127);
        panel.add(table_1);

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Uncomment the following line to not terminate the console app when the window is closed
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }    

    // Uncomment the following code to run the GUI version directly from the IDE
    public static void main(String[] args) throws IOException, FlightBookingSystemException {
        FlightBookingSystem fbs = FlightBookingSystemData.load();
        
        new MainWindow(fbs);    
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == adminExit) {
            disableAdminOptions();
            try {
                FlightBookingSystemData.store(fbs);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == applyPromoCode) {
        	applyPromocode();
        }else if (source == homeMenuItem) { 
            try {
            	dispose(); 
                displayHomePage();
            } catch (FlightBookingSystemException | IOException e) {
                e.printStackTrace();
            }
        } else if(source == feedbackForm) {
        	giveFeedback();		
        }else if (source == adminLogin) {
            showAdminLoginDialog();
        } else if (source == flightsView || source == flightsView_1) {
            displayFlights();
        } else if (source == flightsAdd || source == flightsAdd_1) {
            new AddFlightWindow(this);
        } else if (source == flightsDel) {
            new DeleteFlightWindow(this);
        } else if (source == bookingsIssue || source == bookingsIssue_1) {
            issueBookingWindow(this);
        } else if (source == bookingsCancel || source == bookingsCancel_1) {
            cancelBooking(this);
        } else if (source == bookingsUpdate) {
            updateBooking(this);
        } else if (source == custView) {
            displayCustomers();
        } else if (source == custAdd || source == custAdd_1) {
            new AddCustomerWindow(this);
        } else if (source == custDel) {
            new DeleteCustomerWindow(this);
        }
    }

    private void giveFeedback() {
        new GiveFeedbackGUI(this);
    }

    
    
	private void showAdminLoginDialog() {
        JDialog adminLoginDialog = new JDialog(this, "Admin Login", true);
        adminLoginDialog.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            if (validateAdminCredentials(username, password)) {
                JOptionPane.showMessageDialog(adminLoginDialog, "Login Successful");
                adminLoginDialog.dispose();
                enableAdminFeatures(); 
                
                
            } else {
                JOptionPane.showMessageDialog(adminLoginDialog, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);
        
        adminLoginDialog.add(panel, BorderLayout.CENTER);
        adminLoginDialog.setSize(300, 150);
        adminLoginDialog.setLocationRelativeTo(this);
        adminLoginDialog.setVisible(true);
    }


    private boolean validateAdminCredentials(String username, String password) {
        // Simple validation logic. Replace with actual credential validation.
        return "admin".equals(username) && "admin123".equals(password);
    }

    private void enableAdminFeatures() {
        // Enable features accessible to admin
        flightsAdd.setEnabled(true);
        flightsDel.setEnabled(true);
        bookingsIssue.setEnabled(true);
        bookingsUpdate.setEnabled(true);
        bookingsCancel.setEnabled(true);
        custAdd.setEnabled(true);
        custDel.setEnabled(true);
        applyPromoCode.setEnabled(true);
    }


	private void displayHomePage() throws FlightBookingSystemException, IOException {
	       FlightBookingSystem fbs = FlightBookingSystemData.load();
	        
	        new MainWindow(fbs);
	        
	}

	private void issueBookingWindow(MainWindow mainWindow) {
    	new IssueBookingWindow(this);
		
	}
    
    private void cancelBooking(MainWindow mainWindow) {
        new CancelBookingWindow(this);
    }
    
    private void updateBooking(MainWindow mainWindow) {
		new UpdateBookingWindow(this);
		
	}
    
    private void applyPromocode() {
    	 new ApplyPromoCodeGUI(this);
    }
    



    private void disableAdminOptions() {
    	flightsAdd.setEnabled(false);
    	flightsDel.setEnabled(false);
    }
	public void displayCustomers() {
        List<Customer> customersList = fbs.getCustomer();
        // Headers for the table
        String[] columns = new String[]{"Customer ID", "Name", "Phone", "Email", "Bookings Count"};
        
        List<Customer> validCustomers = new ArrayList<>();
        for (Customer customer : customersList) {
            if (!customer.getDeleted()) {
                validCustomers.add(customer);
            }
        }
        
        Object[][] data = new Object[validCustomers.size()][5];
        for (int i = 0; i < validCustomers.size(); i++) {
            Customer customer = validCustomers.get(i);
            data[i][0] = customer.getId();
            data[i][1] = customer.getName();
            data[i][2] = customer.getPhone();
            data[i][3] = customer.getEmail();
            data[i][4] = customer.getBookings().size(); // Number of bookings
        }

        JTable table = new JTable(data, columns);
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20)); // Top, Left, Bottom, Right padding
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Assuming the customer ID is stored in the first column (index 0)
                        int customerId = (int) table.getValueAt(selectedRow, 0);
                        
                        FlightBookingSystem fbs = getFlightBookingSystem();
                        Customer customer = null;
                        try {
                            customer = fbs.getCustomerByID(customerId);
                        } catch (FlightBookingSystemException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        if (customer != null) {
                            String customerDetails = customer.getShowDetails();
                            JOptionPane.showMessageDialog(null, "Hi, you clicked on customer with ID: " + customerId + "\n\n" + customerDetails);
                        } else {
                            JOptionPane.showMessageDialog(null, "Customer with ID " + customerId + " not found.");
                        }
                    }
                }
            }
        });

        getContentPane().removeAll();
        getContentPane().add(new JScrollPane(table));
        revalidate();
    }

    public void displayFlights() {
		List<Flight> flightsList = fbs.getFlights();
        // Headers for the table
        String[] columns = new String[]{"FLight ID","Flight No", "Origin", "Destination", "Departure Date","Price","Remaining City"};

        Object[][] data = new Object[flightsList.size()][7];
        // Do not include flights in flightsList if its getDeleteStatusFlight is deleted
        List<Flight> validFlights = new ArrayList<>();

        for (Flight flight : flightsList) {
            if (!flight.getDeleteStatusFlight()) {
                validFlights.add(flight);
            }
        }

        for (int i = 0; i < validFlights.size(); i++) {
            Flight flight = validFlights.get(i);
            if (!flight.getDeleteStatusFlight()) {
                data[i][0] = flight.getId();
                data[i][1] = flight.getFlightNumber();
                data[i][2] = flight.getOrigin();
                data[i][3] = flight.getDestination();
                data[i][4] = flight.getDepartureDate();
                data[i][5] = flight.getPrice();
                data[i][6] = flight.getCapacity() - flight.getPassengerCount();
            }
        }

        JTable table = new JTable(data, columns);
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20)); // Top, Left, Bottom, Right padding
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int flightId = (int) table.getValueAt(selectedRow, 0);
                        FlightBookingSystem fbs = getFlightBookingSystem();
                        Flight flight = null;
						try {
							flight = fbs.getFlightByID(flightId);
						} catch (FlightBookingSystemException e1) {
							e1.printStackTrace();
						}
                        for (Flight f : fbs.getFlights()) {
                            if (f.getFlightNumber().equals(flightId)) {
                                flight = f;
                                break;
                            }
                        }
                        if (flight != null) {
                            String flightDetails = flight.getDetailsLong();
                            JOptionPane.showMessageDialog(null, "Hi, you clicked on flight ID: " + flightId + "\n\n" + flightDetails);
                            JFrame mainFrame = new JFrame("Flight Information");
                            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            mainFrame.setSize(400, 200);

                        } else {
                            JOptionPane.showMessageDialog(null, "Flight with ID " + flightId + " not found.");
                        }
                    }
                }
            }
        });

        getContentPane().removeAll();
        getContentPane().add(new JScrollPane(table));
        revalidate();
    }

	public void displayBookings() {
		// TODO Auto-generated method stub
		
	}
	

    private void initializeHomePage() {
        getContentPane().removeAll(); 
        getContentPane().add(panel); 
        
        revalidate(); 
    }
}
