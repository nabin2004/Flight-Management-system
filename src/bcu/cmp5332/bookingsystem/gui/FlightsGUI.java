//package bcu.cmp5332.bookingsystem.gui;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import java.awt.BorderLayout;
//import javax.swing.SpringLayout;
//import net.miginfocom.swing.MigLayout;
//
//public class FlightsGUI {
//
//	private JFrame frame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FlightsGUI window = new FlightsGUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public FlightsGUI() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 924, 579);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(new MigLayout("", "[910px]", "[13px][516px][13px]"));
//		
//		JLabel lblNewLabel = new JLabel("Enter Name");
//		frame.getContentPane().add(lblNewLabel, "cell 0 1,grow");
//		
//		JLabel lblNewLabel_1 = new JLabel("Surname");
//		frame.getContentPane().add(lblNewLabel_1, "cell 0 0,growx,aligny top");
//		
//		JLabel lblNewLabel_2 = new JLabel("ID");
//		frame.getContentPane().add(lblNewLabel_2, "cell 0 2,growx,aligny top");
//	}
//
//}
