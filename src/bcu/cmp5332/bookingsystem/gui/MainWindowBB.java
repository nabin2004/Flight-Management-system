//package bcu.cmp5332.bookingsystem.gui;
//
//import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
//import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
//import bcu.cmp5332.bookingsystem.model.Customer;
//import bcu.cmp5332.bookingsystem.model.Flight;
//import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
//
//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.UIManager;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//
//public class MainWindowBB extends JFrame implements ActionListener {
//
//    private JMenuBar menuBar;
//    private JMenu adminMenu;
//    private JMenu flightsMenu;
//    private JMenu bookingsMenu;
//    private JMenu customersMenu;
//
//    private JMenuItem adminExit;
//
//    private JMenuItem flightsView;
//    private JMenuItem flightsAdd;
//    private JMenuItem flightsDel;
//    
//    private JMenuItem bookingsIssue;
//    private JMenuItem bookingsUpdate;
//    private JMenuItem bookingsCancel;
//
//    private JMenuItem custView;
//    private JMenuItem custAdd;
//    private JMenuItem custDel;
//
//    private FlightBookingSystem fbs;
//
//    public MainWindowBB(FlightBookingSystem fbs) {
//
//        initialize();
//        this.fbs = fbs;
//    }
//    
//    public FlightBookingSystem getFlightBookingSystem() {
//        return fbs;
//    }
//
//    /**
//     * Initialize the contents of the frame.
//     */
//    private void initialize() {
//
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception ex) {
//            // Handle the exception
//        }
//
//        setTitle("Flight Booking Management System");
//        setLayout(new BorderLayout()); // Set the layout to BorderLayout
//
//        JPanel panelTop = new JPanel();
//        String logopath = "C:\\Users\\Lenovo\\Desktop\\Java\\Final Project\\fmsfinal\\FlightBookingSystem_Dist (2).zip_expanded\\FlightBookingSystem_Dist\\src\\images\\menuBarImg.jpg";
//        ImageIcon logoIcon = new ImageIcon(logopath);
//        JLabel logoLabel = new JLabel(logoIcon);
//        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        
//        
//        
//        
//        panelTop.add(logoLabel);
//
//        menuBar = new JMenuBar();
//        setJMenuBar(menuBar);
//
//        // Adding adminMenu menu and menu items
//        adminMenu = new JMenu("Admin");
//        menuBar.add(adminMenu);
//
//        adminExit = new JMenuItem("Exit");
//        adminMenu.add(adminExit);
//        adminExit.addActionListener(this);
//
//        // Adding Flights menu and menu items
//        flightsMenu = new JMenu("Flights");
//        menuBar.add(flightsMenu);
//
//        flightsView = new JMenuItem("View");
//        flightsMenu.add(flightsView);
//        flightsAdd = new JMenuItem("Add");
//        flightsMenu.add(flightsAdd);
//        flightsDel = new JMenuItem("Delete");
//        flightsMenu.add(flightsDel);
//        // Adding action listener for Flights menu items
//        for (int i = 0; i < flightsMenu.getItemCount(); i++) {
//            flightsMenu.getItem(i).addActionListener(this);
//        }
//        
//        // Adding Bookings menu and menu items
//        bookingsMenu = new JMenu("Bookings");
//        menuBar.add(bookingsMenu);
//        bookingsIssue = new JMenuItem("Issue");
//        bookingsUpdate = new JMenuItem("Update");
//        bookingsCancel = new JMenuItem("Cancel");
//        bookingsMenu.add(bookingsIssue);
//        bookingsMenu.add(bookingsUpdate);
//        bookingsMenu.add(bookingsCancel);
//        // Adding action listener for Bookings menu items
//        for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
//            bookingsMenu.getItem(i).addActionListener(this);
//        }
//
//        // Adding Customers menu and menu items
//        customersMenu = new JMenu("Customers");
//        menuBar.add(customersMenu);
//
//        custView = new JMenuItem("View");
//        custAdd = new JMenuItem("Add");
//        custDel = new JMenuItem("Delete");
//
//        customersMenu.add(custView);
//        customersMenu.add(custAdd);
//        customersMenu.add(custDel);
//        // Adding action listener for Customers menu items
//        custView.addActionListener(this);
//        custAdd.addActionListener(this);
//        custDel.addActionListener(this);
//
//        add(panelTop, BorderLayout.NORTH); // Add panelTop to the top of the main window
//
//        setSize(800, 500);
//
//        setVisible(true);
//        setAutoRequestFocus(true);
//        toFront();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        // Uncomment the following line to not terminate the console app when the window is closed
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//    }    
//
//    // Uncomment the following code to run the GUI version directly from the IDE
//    public static void main(String[] args) throws IOException, FlightBookingSystemException {
//        FlightBookingSystem fbs = FlightBookingSystemData.load();
//        new MainWindowBB(fbs);            
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//
//        if (ae.getSource() == adminExit) {
//            try {
//                FlightBookingSystemData.store(fbs);
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
//            }
//            System.exit(0);
//        } else if (ae.getSource() == flightsView) {
//            displayFlights();
//            
//        } else if (ae.getSource() == flightsAdd) {
//            new AddFlightWindow(this);
//            
//        } else if (ae.getSource() == flightsDel) {
//            new DeleteFlightWindow(this);
//            
//        } else if (ae.getSource() == bookingsIssue) {
//            // Implement bookings issue functionality
//            
//        } else if (ae.getSource() == bookingsCancel) {
//            // Implement bookings cancel functionality
//            
//        } else if (ae.getSource() == custView) {
//            displayCustomers();
//            
//        } else if (ae.getSource() == custAdd) {
//            new AddCustomerWindow(this);
//            
//        } else if (ae.getSource() == custDel) {
//            new DeleteCustomerWindow(this);
//            
//        }
//    }
//
//    public void displayCustomers() {
//        List<Customer> customersList = fbs.getCustomer();
//        // Headers for the table
//        String[] columns = new String[]{"Customer ID", "Name", "Phone", "Email", "Bookings Count"};
//        
//        List<Customer> validCustomers = new ArrayList<>();
//        for (Customer customer : customersList) {
//            if (!customer.getDeleted()) {
//                validCustomers.add(customer);
//            }
//        }
//        
//        Object[][] data = new Object[validCustomers.size()][5];
//        for (int i = 0; i < validCustomers.size(); i++) {
//            Customer customer = validCustomers.get(i);
//            data[i][0] = customer.getId();
//            data[i][1] = customer.getName();
//            data[i][2] = customer.getPhone();
//            data[i][3] = customer.getEmail();
//            data[i][4] = customer.getBookings().size(); // Number of bookings
//        }
//
//        JTable table = new JTable(data, columns);
//        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    int selectedRow = table.getSelectedRow();
//                    if (selectedRow != -1) {
//                        // Assuming the customer ID is stored in the first column (index 0)
//                        int customerId = (int) table.getValueAt(selectedRow, 0);
//                        
//                        FlightBookingSystem fbs = getFlightBookingSystem();
//                        Customer customer = null;
//                        try {
//                            customer = fbs.getCustomerByID(customerId);
//                        } catch (FlightBookingSystemException ex) {
//                            JOptionPane.showMessageDialog(null, ex.getMessage());
//                        }
//                        if (customer != null) {
//                            String customerDetails = customer.getShowDetails();
//                            JOptionPane.showMessageDialog(null, "Hi, you clicked on customer with ID: " + customerId + "\n\n" + customerDetails);
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Customer with ID " + customerId + " not found.");
//                        }
//                    }
//                }
//            }
//        });
//
//        getContentPane().removeAll();
//        getContentPane().add(new JScrollPane(table));
//        revalidate();
//    }
//
//    public void displayFlights() {
//        List<Flight> flightsList = fbs.getFlights();
//        // Headers for the table
//        String[] columns = new String[]{"Flight No", "Origin", "Destination", "Departure Date"};
//
//        Object[][] data = new Object[flightsList.size()][4];
//        // Do not include flights in flightsList if its getDeleteStatusFlight is deleted
//        List<Flight> validFlights = new ArrayList<>();
//
//        for (Flight flight : flightsList) {
//            if (!flight.getDeleteStatusFlight()) {
//                validFlights.add(flight);
//            }
//        }
//
//        for (int i = 0; i < validFlights.size(); i++) {
//            Flight flight = validFlights.get(i);
//            if (!flight.getDeleteStatusFlight()) {
//                data[i][0] = flight.getFlightNumber();
//                data[i][1] = flight.getOrigin();
//                data[i][2] = flight.getDestination();
//                data[i][3] = flight.getDepartureDate();
//            }
//        }
//
//        JTable table = new JTable(data, columns);
//        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    int selectedRow = table.getSelectedRow();
//                    if (selectedRow != -1) {
//                        String flightId = (String) table.getValueAt(selectedRow, 0);
//
//                        FlightBookingSystem fbs = getFlightBookingSystem();
//                        Flight flight = null;
//                        for (Flight f : fbs.getFlights()) {
//                            if (f.getFlightNumber().equals(flightId)) {
//                                flight = f;
//                                break;
//                            }
//                        }
//                        if (flight != null) {
//                            String flightDetails = flight.getDetailsLong();
//                            JOptionPane.showMessageDialog(null, "Hi, you clicked on flight ID: " + flightId + "\n\n" + flightDetails);
//                            JFrame mainFrame = new JFrame("Flight Information");
//                            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                            mainFrame.setSize(400, 200);
//
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Flight with ID " + flightId + " not found.");
//                        }
//                    }
//                }
//            }
//        });
//
//        getContentPane().removeAll();
//        getContentPane().add(new JScrollPane(table));
//        revalidate();
//    }
//}
//
//
