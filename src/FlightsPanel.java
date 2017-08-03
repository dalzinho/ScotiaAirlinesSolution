import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.JComboBox;

public class FlightsPanel extends JPanel {
	private Flight flightSelected = null;
	private JTextField textFieldDepartureAirport;
	private JTextField textFieldArrivalAirport;
	private JTextField textFieldDepartureDate;
	private JTextField textFieldArrivalDate;
	private JRadioButton rdbtnBoarding;
	private JRadioButton rdbtnChecking;
	private JRadioButton rdbtnAvailable;
	private JRadioButton rdbtnClosed;
	private JTextField textFieldCost;

	private DefaultListModel<String> populateFlights() {
		DefaultListModel<String> list = new DefaultListModel<String>();
		// show flights in listBox
		ArrayList<Flight> FlightList = MainMenu.getAirlineMgr().getFlightList();

		for (int i = 0; i < FlightList.size(); i++) {
			list.addElement(FlightList.get(i).getFlightNumber());
		}

		return list;
	}

	// show Flight info when selected, with function getFlightbynumber
	private void displayFlight(String number) {
		flightSelected = MainMenu.getAirlineMgr().getFlightByNumber(number);
		textFieldDepartureAirport.setText(flightSelected.getDepartureAirport());
		textFieldArrivalAirport.setText(flightSelected.getArrivalAirport());
		textFieldDepartureDate.setText(AirlineManager.formatter.format(flightSelected.getDepartureDate()));
		textFieldArrivalDate.setText(AirlineManager.formatter.format(flightSelected.getArrivalDate()));
		textFieldCost.setText(Double.toString(flightSelected.getCost()));

		if (flightSelected.getFlightStatus() == Flight.Status.AVAILABLE) {
			rdbtnAvailable.setSelected(true);
		} else {
			if (flightSelected.getFlightStatus() == Flight.Status.BOARDING) {
				rdbtnBoarding.setSelected(true);
			} else {
				if (flightSelected.getFlightStatus() == Flight.Status.CHECKING) {
					rdbtnChecking.setSelected(true);
				} else {
					if (flightSelected.getFlightStatus() == Flight.Status.CLOSED) {
						rdbtnClosed.setSelected(true);
					}
				}
			}
		}
	}

	private void saveFlight() {
		flightSelected.setDepartureAirport(textFieldDepartureAirport.getText());
		flightSelected.setArrivalAirport(textFieldArrivalAirport.getText());
		flightSelected.setCost(Double.parseDouble(textFieldCost.getText()));
		
		// flightSelected.setDepartureDate(textFieldDepartureDate.getText());
		// flightSelected.setArrivalDate(textFieldArrivalDate.getText());

		if (rdbtnAvailable.isSelected()) {
			flightSelected.setFlightStatus(Flight.Status.AVAILABLE);
		} else {
			if (rdbtnBoarding.isSelected()) {
				flightSelected.setFlightStatus(Flight.Status.BOARDING);
			} else {
				if (rdbtnChecking.isSelected()) {
					flightSelected.setFlightStatus(Flight.Status.CHECKING);
				} else {
					if (rdbtnClosed.isSelected()) {
						flightSelected.setFlightStatus(Flight.Status.CLOSED);
					}
				}
			}
		}
	}

	public FlightsPanel() {
		setLayout(null);

		JList list = new JList(populateFlights());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				displayFlight(list.getSelectedValue().toString());
			}
		});
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setBounds(10, 28, 89, 104);
		add(list);

		JLabel lblNewLabel = new JLabel("Available flights:");
		lblNewLabel.setBounds(10, 8, 121, 14);
		add(lblNewLabel);

		textFieldDepartureAirport = new JTextField();
		textFieldDepartureAirport.setBounds(109, 47, 112, 20);
		add(textFieldDepartureAirport);
		textFieldDepartureAirport.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Departure Airport:");
		lblNewLabel_1.setBounds(109, 30, 112, 14);
		add(lblNewLabel_1);

		textFieldArrivalAirport = new JTextField();
		textFieldArrivalAirport.setColumns(10);
		textFieldArrivalAirport.setBounds(109, 95, 112, 20);
		add(textFieldArrivalAirport);

		JLabel lblArrivalAirport = new JLabel("Arrival Airport:");
		lblArrivalAirport.setBounds(109, 78, 112, 14);
		add(lblArrivalAirport);

		textFieldDepartureDate = new JTextField();
		textFieldDepartureDate.setColumns(10);
		textFieldDepartureDate.setBounds(231, 47, 192, 20);
		add(textFieldDepartureDate);

		JLabel lblDepartureDate = new JLabel("Departure Date:");
		lblDepartureDate.setBounds(231, 30, 112, 14);
		add(lblDepartureDate);

		textFieldArrivalDate = new JTextField();
		textFieldArrivalDate.setColumns(10);
		textFieldArrivalDate.setBounds(231, 95, 192, 20);
		add(textFieldArrivalDate);

		JLabel lblArrivalDate = new JLabel("Arrival Date:");
		lblArrivalDate.setBounds(231, 78, 112, 14);
		add(lblArrivalDate);

		rdbtnBoarding = new JRadioButton("BOARDING");
		rdbtnBoarding.setBounds(109, 206, 112, 23);
		add(rdbtnBoarding);

		rdbtnChecking = new JRadioButton("CHECKING");
		rdbtnChecking.setBounds(109, 180, 112, 23);
		add(rdbtnChecking);

		rdbtnAvailable = new JRadioButton("AVAILABLE");
		rdbtnAvailable.setBounds(109, 154, 112, 23);
		add(rdbtnAvailable);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(109, 133, 220, 14);
		add(lblStatus);

		rdbtnClosed = new JRadioButton("CLOSED");
		rdbtnClosed.setBounds(109, 232, 112, 23);
		add(rdbtnClosed);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBoarding);
		group.add(rdbtnChecking);
		group.add(rdbtnAvailable);
		group.add(rdbtnClosed);

		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu.hideFlights();
			}
		});
		button.setBounds(381, 269, 89, 23);
		add(button);

		JButton button_1 = new JButton("OK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/* if(populateFlights().equals() ){ */
				if (textFieldCost.getText().equals("")) { // Panel dialog if
															// flight is not
															// selected
					JOptionPane.showMessageDialog(null, "Please select a flight");

				} else {

					saveFlight();
					MainMenu.hideFlights();
				}
			}
		});
		button_1.setBounds(480, 269, 89, 23);
		add(button_1);

		JLabel lblCost = new JLabel("Cost:");
		lblCost.setBounds(231, 126, 112, 14);
		add(lblCost);

		textFieldCost = new JTextField();
		textFieldCost.setColumns(10);
		textFieldCost.setBounds(231, 143, 112, 20);
		add(textFieldCost);
	}
}
