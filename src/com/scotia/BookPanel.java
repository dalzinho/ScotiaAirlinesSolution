package com.scotia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookPanel extends JPanel {
	private Passenger passenger;
	private Flight flightSelected = null;

	// we populate available flights into the list
	private DefaultListModel<String> populateFlights() {
		// show flights in listBox
		DefaultListModel<String> list = new DefaultListModel<String>();
		// get flights
		ArrayList<Flight> FlightList = MainMenu.getAirlineMgr().getFlightList();

		// for loop to return flights
		for (int i = 0; i < FlightList.size(); i++) {
			list.addElement(FlightList.get(i).getFlightNumber());
		}

		return list;
	}

	// we add available flights to combo box
	private void PopulateAvailableSeats(Flight flight, JComboBox<String> cb) {
		Seat lSeat;
		cb.removeAllItems();
		for (int i = 0; i < flight.getSeats().size(); i++) {
			lSeat = flight.getSeats().get(i);
			if (lSeat.getSeatstatus() == Seat.Status.AVAILABLE) {
				cb.addItem(lSeat.getSeatNo());
			}
		}
	}

	private Double CalculateTotalCost(Flight flight, int discount, Boolean suitCase) {
		Double total;
		// Applying disccount percentage
		total = flight.getCost() - ((discount * flight.getCost()) / 100);

		if (suitCase) { // if statement to add suitcase
			total = total + 50; // total + suitcase fee
		}
		return total;
	}

	private void ConfirmBooking(Flight flight, Passenger passenger, String seatNumber, Boolean suitCase) {

		if (passenger.isReserved(flight.getFlightNumber(), seatNumber) == false) {
			Double totalCost = CalculateTotalCost(flight, passenger.getDiscount(), suitCase);
			Seat lSeat = flight.getSeatByNumber(seatNumber);

			// Create a booking
			passenger.addReservation(0, flight, lSeat, suitCase, totalCost);

			try {
				passenger.SaveTicket();
				JOptionPane.showMessageDialog(null, "Your Reservation has been saved");
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "File Error");
			}
		}
	}

	private Boolean ValidateBooking(Flight flight) {

		// checks com.scotia.Flight status in order to proceed with booking
		return (flight.getFlightStatus() == Flight.Status.AVAILABLE)
				|| (flight.getFlightStatus() == Flight.Status.CHECKING);

	}

	public BookPanel() {
		setLayout(null);

		JLabel label = new JLabel("Departure Date:");
		label.setBounds(227, 33, 112, 14);
		add(label);

		JLabel lblSelectAFlight = new JLabel("Select a flight:");
		lblSelectAFlight.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectAFlight.setBounds(10, 11, 121, 14);
		add(lblSelectAFlight);

		JLabel label_2 = new JLabel("Departure Airport:");
		label_2.setBounds(109, 33, 112, 14);
		add(label_2);

		JLabel label_3 = new JLabel("Arrival Airport:");
		label_3.setBounds(109, 69, 112, 14);
		add(label_3);

		JLabel label_4 = new JLabel("Arrival Date:");
		label_4.setBounds(227, 69, 112, 14);
		add(label_4);

		JLabel lblFlightStatus1 = new JLabel("com.scotia.Flight Status:");
		lblFlightStatus1.setBounds(109, 108, 99, 14);
		add(lblFlightStatus1);

		JLabel lbDepartureAirport = new JLabel("");
		lbDepartureAirport.setForeground(new Color(0, 128, 0));
		lbDepartureAirport.setBackground(Color.ORANGE);
		lbDepartureAirport.setBounds(109, 48, 121, 14);
		add(lbDepartureAirport);

		JLabel lbDepartureDate = new JLabel("");
		lbDepartureDate.setForeground(new Color(0, 128, 0));
		lbDepartureDate.setBackground(Color.ORANGE);
		lbDepartureDate.setBounds(227, 48, 183, 14);
		add(lbDepartureDate);

		JLabel lbArrivalAirport = new JLabel("");
		lbArrivalAirport.setForeground(Color.MAGENTA);
		lbArrivalAirport.setBackground(Color.ORANGE);
		lbArrivalAirport.setBounds(109, 86, 121, 14);
		add(lbArrivalAirport); // info

		JLabel lbArrivalDate = new JLabel("");
		lbArrivalDate.setForeground(Color.MAGENTA);
		lbArrivalDate.setBackground(Color.ORANGE);
		lbArrivalDate.setBounds(227, 86, 183, 14);
		add(lbArrivalDate); // adds info

		JLabel lblFlightStatus = new JLabel("");
		lblFlightStatus
				.setFont(lblFlightStatus.getFont().deriveFont(lblFlightStatus.getFont().getStyle() | Font.BOLD, 13f));
		lblFlightStatus.setForeground(Color.BLACK);
		lblFlightStatus.setBackground(Color.ORANGE);
		lblFlightStatus.setBounds(109, 123, 99, 23);
		add(lblFlightStatus); // adds info

		JLabel lblPassengerInfo = new JLabel("com.scotia.Passenger info:");
		lblPassengerInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassengerInfo.setBounds(109, 157, 99, 14);
		add(lblPassengerInfo);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(109, 182, 69, 14);
		add(lblName);

		JLabel lbPassengerName = new JLabel("");
		lbPassengerName.setForeground(Color.BLUE);
		lbPassengerName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbPassengerName.setBackground(Color.WHITE);
		lbPassengerName.setBounds(180, 183, 187, 14);
		add(lbPassengerName);

		JLabel lbSureName = new JLabel("");
		lbSureName.setForeground(Color.BLUE);
		lbSureName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbSureName.setBackground(Color.WHITE);
		lbSureName.setBounds(180, 208, 187, 14);
		add(lbSureName);

		JLabel lblSureName = new JLabel("Sure Name:");
		lblSureName.setBounds(109, 207, 73, 14);
		add(lblSureName);

		JLabel lblDiscount1 = new JLabel("Discount:");
		lblDiscount1.setBounds(431, 182, 59, 14);
		add(lblDiscount1);

		JLabel lblDiscount = new JLabel("");
		lblDiscount.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDiscount.setBounds(498, 182, 59, 14);
		add(lblDiscount);

		JLabel lblCost = new JLabel("Cost:");
		lblCost.setBounds(431, 157, 37, 14);
		add(lblCost);

		JSeparator separator = new JSeparator();
		separator.setBounds(449, 144, 1, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(412, 33, 13, 214);
		add(separator_1);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(109, 232, 73, 14);
		add(lblType);

		JLabel lbType = new JLabel("");
		lbType.setForeground(Color.BLUE);
		lbType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbType.setBounds(180, 233, 187, 14);
		add(lbType);

		JComboBox<String> cbSeats = new JComboBox<String>();
		cbSeats.setBounds(431, 86, 112, 20);
		add(cbSeats);

		JLabel lblAvailableSeats = new JLabel("Available seats:");
		lblAvailableSeats.setBounds(431, 69, 112, 14);
		add(lblAvailableSeats);

		JLabel lbCost = new JLabel("");
		lbCost.setFont(new Font("Dialog", Font.BOLD, 12));
		lbCost.setBounds(498, 157, 59, 14);
		add(lbCost);

		JLabel lblTotalCost1 = new JLabel("Total Cost:");
		lblTotalCost1.setBounds(431, 230, 69, 14);
		add(lblTotalCost1);

		JLabel lblTotalCost = new JLabel("");
		lblTotalCost.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTotalCost.setBounds(498, 227, 73, 23);
		add(lblTotalCost);

		// Action for CheckBox
		JCheckBox chckbxNewCheckBox = new JCheckBox("Suite case (+50\u20AC)"); // ?????
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTotalCost.setText(Double.toString(
						CalculateTotalCost(flightSelected, passenger.getDiscount(), chckbxNewCheckBox.isSelected()))
						+ "�");
			}
		});
		chckbxNewCheckBox.setBounds(427, 201, 142, 23);
		add(chckbxNewCheckBox);

		// Action for button cancel
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu.hideBook();
			}
		});
		button.setBounds(381, 272, 89, 23);
		add(button);

		// Action for button confirm
		JButton btnPay = new JButton("Confirm");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Panel dialog if passenger has not been created

				if (lbPassengerName.getText().equals("")) { 
					JOptionPane.showMessageDialog(null, "Please go back to com.scotia.Passenger menu and enter details");

				}

				else if (ValidateBooking(flightSelected)) {
					ConfirmBooking(flightSelected, passenger, cbSeats.getSelectedItem().toString(),
							chckbxNewCheckBox.isSelected());

					// JOptionPane.showMessageDialog(null,
					// directory.getAbsolutePath()));
					MainMenu.hideBook();
				} else {
					JOptionPane.showMessageDialog(null, "The flight is not available");
				}
			}
		});
		btnPay.setBounds(480, 272, 89, 23);
		add(btnPay);

		// show passenger info from manager
		passenger = MainMenu.getAirlineMgr().getPassenger();
		if (passenger != null) {
			lbPassengerName.setText(passenger.getFname());
			lbSureName.setText(passenger.getSname());
			lblDiscount.setText(Integer.toString(passenger.getDiscount()) + "%");
			lbType.setText(passenger.getType());
		}

		// flight info
		JList list = new JList(populateFlights());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				flightSelected = MainMenu.getAirlineMgr().getFlightByNumber(list.getSelectedValue().toString());
				lbDepartureAirport.setText(flightSelected.getDepartureAirport());
				lbArrivalAirport.setText(flightSelected.getArrivalAirport());
				lbDepartureDate.setText(AirlineManager.formatter.format(flightSelected.getDepartureDate()));
				lbArrivalDate.setText(AirlineManager.formatter.format(flightSelected.getArrivalDate()));
				lbCost.setText(Double.toString(flightSelected.getCost()) + "�");

				PopulateAvailableSeats(flightSelected, cbSeats);

				if (passenger != null) {

					lblTotalCost.setText(Double.toString(
							CalculateTotalCost(flightSelected, passenger.getDiscount(), chckbxNewCheckBox.isSelected()))
							+ "�");

				}
				// if statement for STATUS
				if (flightSelected.getFlightStatus() == Flight.Status.AVAILABLE) {
					lblFlightStatus.setText("AVAILABLE");
				} else {
					if (flightSelected.getFlightStatus() == Flight.Status.BOARDING) {
						lblFlightStatus.setText("BOARDING");
					} else {
						if (flightSelected.getFlightStatus() == Flight.Status.CHECKING) {
							lblFlightStatus.setText("CHECKING");
						} else {
							if (flightSelected.getFlightStatus() == Flight.Status.CLOSED) {
								lblFlightStatus.setText("CLOSED");
							}
						}
					}
				}
			}
		});
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setBounds(10, 31, 89, 261);
		add(list);
	}
}
