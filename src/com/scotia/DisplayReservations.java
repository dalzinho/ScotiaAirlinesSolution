package com.scotia;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class DisplayReservations extends JPanel {
	private Passenger passenger = MainMenu.getAirlineMgr().getPassenger();  // get object passenger via airline manager
	private Book bookSelected;  

	private DefaultListModel<String> populateReservations() {    // populate reservations in box           
		DefaultListModel<String> list = new DefaultListModel<String>(); 
		int ReservationNum;
		for (int i = 0; i < passenger.getReservations().size(); i++) {
			ReservationNum = passenger.getReservations().get(i).getNumber();
			list.addElement(String.valueOf(ReservationNum));
		}

		return list;
	}

	public DisplayReservations() {
		setLayout(null);

		JLabel lblReservations = new JLabel("Reservations: ");
		lblReservations.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReservations.setBounds(10, 11, 121, 14);
		add(lblReservations);

		JLabel label = new JLabel("Departure Airport:");
		label.setBounds(109, 31, 112, 14);
		add(label);

		JLabel lbDepartureAirport = new JLabel("");
		lbDepartureAirport.setForeground(new Color(0, 128, 0));
		lbDepartureAirport.setBackground(Color.ORANGE);
		lbDepartureAirport.setBounds(109, 46, 121, 14);
		add(lbDepartureAirport);

		JLabel lbDepartureDate = new JLabel("");
		lbDepartureDate.setForeground(new Color(0, 128, 0));
		lbDepartureDate.setBackground(Color.ORANGE);
		lbDepartureDate.setBounds(227, 46, 183, 14);
		add(lbDepartureDate);

		JLabel label_3 = new JLabel("Departure Date:");
		label_3.setBounds(227, 31, 112, 14);
		add(label_3);

		JLabel lbArrivalAirport = new JLabel("");
		lbArrivalAirport.setForeground(Color.MAGENTA);
		lbArrivalAirport.setBackground(Color.ORANGE);
		lbArrivalAirport.setBounds(109, 84, 121, 14);
		add(lbArrivalAirport);

		JLabel label_5 = new JLabel("Arrival Airport:");
		label_5.setBounds(109, 67, 112, 14);
		add(label_5);

		JLabel lbArrivalDate = new JLabel("");
		lbArrivalDate.setForeground(Color.MAGENTA);
		lbArrivalDate.setBackground(Color.ORANGE);
		lbArrivalDate.setBounds(227, 84, 183, 14);
		add(lbArrivalDate);

		JLabel label_7 = new JLabel("Arrival Date:");
		label_7.setBounds(227, 67, 112, 14);
		add(label_7);

		JLabel lbFlightStatus = new JLabel("");
		lbFlightStatus.setForeground(Color.BLACK);
		lbFlightStatus
				.setFont(lbFlightStatus.getFont().deriveFont(lbFlightStatus.getFont().getStyle() | Font.BOLD, 13f));
		lbFlightStatus.setBackground(Color.ORANGE);
		lbFlightStatus.setBounds(109, 121, 99, 23);
		add(lbFlightStatus);

		JLabel label_9 = new JLabel("com.scotia.Flight Status:");
		label_9.setBounds(109, 106, 99, 14);
		add(label_9);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu.hideReservations();
			}
		});
		btnExit.setBounds(263, 268, 89, 23);
		add(btnExit);

		JLabel label_1 = new JLabel("Name:");
		label_1.setBounds(109, 180, 69, 14);
		add(label_1);

		JLabel label_2 = new JLabel("com.scotia.Passenger info:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(109, 155, 99, 14);
		add(label_2);

		JLabel lbName = new JLabel("");
		lbName.setForeground(new Color(0, 0, 255));
		lbName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbName.setBackground(Color.WHITE);
		lbName.setBounds(180, 181, 187, 14);
		add(lbName);

		JLabel label_6 = new JLabel("Sure Name:");
		label_6.setBounds(109, 205, 73, 14);
		add(label_6);

		JLabel lbSureName = new JLabel("");
		lbSureName.setForeground(new Color(0, 0, 255));
		lbSureName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbSureName.setBackground(Color.WHITE);
		lbSureName.setBounds(180, 206, 187, 14);
		add(lbSureName);

		JLabel label_10 = new JLabel("Type:");
		label_10.setBounds(109, 230, 73, 14);
		add(label_10);

		JLabel lbType = new JLabel("");
		lbType.setForeground(new Color(0, 0, 255));
		lbType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbType.setBounds(180, 231, 187, 14);
		add(lbType);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(366, 31, 13, 214);
		add(separator);

		JLabel label_4 = new JLabel("Cost:");
		label_4.setBounds(385, 155, 37, 14);
		add(label_4);

		JLabel lbCost = new JLabel("");
		lbCost.setFont(new Font("Dialog", Font.BOLD, 12));
		lbCost.setBounds(452, 155, 59, 14);
		add(lbCost);

		JLabel label_11 = new JLabel("Discount:");
		label_11.setBounds(385, 180, 59, 14);
		add(label_11);

		JLabel lbDiscount = new JLabel("");
		lbDiscount.setFont(new Font("Dialog", Font.BOLD, 12));
		lbDiscount.setBounds(452, 180, 59, 14);
		add(lbDiscount);

		JLabel label_13 = new JLabel("Total Cost:");
		label_13.setBounds(385, 228, 69, 14);
		add(label_13);

		JLabel lbTotalCost = new JLabel("");
		lbTotalCost.setFont(new Font("Dialog", Font.BOLD, 14));
		lbTotalCost.setBounds(449, 224, 73, 23);
		add(lbTotalCost);

		JLabel lblSeat = new JLabel("com.scotia.Seat:");
		lblSeat.setBounds(385, 130, 37, 14);
		add(lblSeat);

		JLabel lbSeat = new JLabel("");
		lbSeat.setFont(new Font("Dialog", Font.BOLD, 12));
		lbSeat.setBounds(452, 130, 59, 14);
		add(lbSeat);

		JLabel lbSuitCase = new JLabel("Suite case (+50\u20AC)");
		lbSuitCase.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSuitCase.setBounds(386, 202, 149, 14);
		add(lbSuitCase);

		JList ReservationsList = new JList(populateReservations());
		ReservationsList.addMouseListener(new MouseAdapter() {      // Mouse listener  
			@Override
			public void mouseClicked(MouseEvent e) {
				Flight flightSelected;
				bookSelected = passenger
						.getReservationByNumber(Integer.parseInt(ReservationsList.getSelectedValue().toString()));
				flightSelected = bookSelected.getFlightToBook();

				// flight info from text file
				lbDepartureAirport.setText(flightSelected.getDepartureAirport());
				lbArrivalAirport.setText(flightSelected.getArrivalAirport());
				lbDepartureDate.setText(AirlineManager.formatter.format(flightSelected.getDepartureDate()));
				lbArrivalDate.setText(AirlineManager.formatter.format(flightSelected.getArrivalDate()));
				lbCost.setText(Double.toString(flightSelected.getCost()) + "�");
				lbSeat.setText(bookSelected.getSeat().getSeatNo());
				lbTotalCost.setText(bookSelected.getTotalCost().toString() + "�");

				if (bookSelected.getSuitCase()) {
					lbSuitCase.setText("Suite case (+50�)");
				} else {
					lbSuitCase.setText("");
				}

				if (flightSelected.getFlightStatus() == Flight.Status.AVAILABLE) {
					lbFlightStatus.setText("AVAILABLE");
				}else {
					if (flightSelected.getFlightStatus() == Flight.Status.BOARDING) {
						lbFlightStatus.setText("BOARDING");
					} else {
						if (flightSelected.getFlightStatus() == Flight.Status.CHECKING) {
							lbFlightStatus.setText("CHECKING");
						} else {
							if (flightSelected.getFlightStatus() == Flight.Status.CLOSED) {
								lbFlightStatus.setText("CLOSED");
							}
						}
					}
				}

				// passenger info
				lbName.setText(passenger.getFname());
				lbSureName.setText(passenger.getSname());
				lbType.setText(passenger.getType());
				lbDiscount.setText(Integer.toString(passenger.getDiscount()));
			}
		});
		ReservationsList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		ReservationsList.setBounds(10, 30, 89, 261);
		add(ReservationsList);

		JButton btnCancelReservation = new JButton("Cancel reservation");
		btnCancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bookSelected != null /*|| bookSelected.cancelBook()*/) {
					
					
					
					try {
						passenger.removeReservation(bookSelected.getNumber());
						passenger.SaveTicket();
						ReservationsList.removeAll();
						ReservationsList.setModel(populateReservations());
						bookSelected = null;
						JOptionPane.showMessageDialog(null, "The reservation has been cancelled successfully");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					
					JOptionPane.showMessageDialog(null, "The reservation cannot be cancelled");
				}
				

			}
		});
		btnCancelReservation.setBounds(362, 268, 149, 23);
		add(btnCancelReservation);

	}
}
