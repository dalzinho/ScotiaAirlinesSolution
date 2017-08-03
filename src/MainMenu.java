import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import java.util.Date;

public class MainMenu {
	private static JFrame frame;
	private static JMenuBar menuBar;
	// we get access to class manager in order to use the methods needed in the application
	private static AirlineManager AirlineMgr = new AirlineManager(); 
	// we set to null in order to invoke later
	public static PassengerPanel PassengerPanel = null; 
	public static FlightsPanel FlightsPanel = null;	
	public static BookPanel BookPanel;
	public static DisplayReservations ReservationsPanel;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	
	
	public static AirlineManager getAirlineMgr() {
		return AirlineMgr;
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	private static void enableMenus(Boolean enable) {
		for(int i=0; i<menuBar.getComponentCount(); i++) {
			menuBar.getComponent(i).setEnabled(enable);						
		}		
	}
	
	public static void displayNewPassenger() {
		enableMenus(false);
		
		if (PassengerPanel == null) {
			PassengerPanel = new PassengerPanel();
			frame.getContentPane().add(PassengerPanel, BorderLayout.CENTER);		//
		}
		
		PassengerPanel.setVisible(true); // set visible when selected
		frame.setVisible(true);
	}
	
	public static void hideNewPassenger() {
		enableMenus(true);
		
		PassengerPanel.setVisible(false);
		frame.setVisible(true);
	}
	
	public static void hideFlights() {
		enableMenus(true);
		
		FlightsPanel.setVisible(false);
		frame.setVisible(true);
	}
	
	public static void displayBook() { // show Book panel
		enableMenus(false); //disable menus
		
		BookPanel = new BookPanel();
		frame.getContentPane().add(BookPanel, BorderLayout.CENTER);		
		BookPanel.setVisible(true);
		frame.setVisible(true);
	}
	
	public static void hideBook() {
		enableMenus(true);
		
		BookPanel.setVisible(false);
		frame.setVisible(true);
	}
	
	public static void displayReservations() {
		enableMenus(false);
		
		ReservationsPanel = new DisplayReservations();
		frame.getContentPane().add(ReservationsPanel, BorderLayout.CENTER);		
		ReservationsPanel.setVisible(true);
		frame.setVisible(true);
	}
	
	public static void hideReservations() {
		enableMenus(true);
		
		ReservationsPanel.setVisible(false);
		frame.setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Date date = new Date();
		frame = new JFrame();	// open main panel 	
		frame.setBounds(100,100,630,450);
		frame.getContentPane().setForeground(Color.BLACK);
		
		JTextArea txtrScotiaAirlines = new JTextArea();  // main logo
		txtrScotiaAirlines.setEditable(false);
		txtrScotiaAirlines.setToolTipText("Scotia Airlines\r\n");
		txtrScotiaAirlines.setText("Scotia Airlines " + "  -  " + formatter.format(date));
		txtrScotiaAirlines.setForeground(Color.RED);
		txtrScotiaAirlines.setBackground(Color.LIGHT_GRAY);
		txtrScotiaAirlines.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(txtrScotiaAirlines, BorderLayout.NORTH);
		
		//still trying to add an image
		
		/*JLabel lblNewLabelplane = new JLabel("");
		try {
		Image image = ImageIO.read(new File("plane.png"));
	        Image imageScaled = image.getScaledInstance(600, 400, image.SCALE_DEFAULT); 
	        lblNewLabelplane.setIcon(new ImageIcon(imageScaled));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.getContentPane().add(lblNewLabelplane, BorderLayout.CENTER);
		
		if(JMenuItem.mntmNewPassenger().equals true {
			
		}*/
		
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);		
		
		JMenu mnPassenger = new JMenu("Passenger");
		menuBar.add(mnPassenger);  
		
		JMenuItem mntmNewPassenger = new JMenuItem("Add or Edit Passanger");
		mntmNewPassenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayNewPassenger();	// display when selected								
			}
		});
		mnPassenger.add(mntmNewPassenger);
		
		JMenu mnFlight = new JMenu("Flight");
		menuBar.add(mnFlight);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Book a flight");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayBook();
			}
		});
		mnFlight.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Display reservations");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				displayReservations();				
			}
			
			
		});
		mnFlight.add(mntmNewMenuItem_1);
	}	
}
