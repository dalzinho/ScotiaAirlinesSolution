package com.scotia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JRadioButton;

import javax.swing.ButtonGroup;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class PassengerPanel extends JPanel {
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textStreet;
	private JTextField textCity;
	private JTextField textPostCode;
	private JRadioButton rdbtnOrdinary; 
	private JRadioButton rdbtnIsland;
	private JRadioButton rdbtnBusiness;
	private JLabel lblDiscount;
	private Passenger passenger = MainMenu.getAirlineMgr().getPassenger();  
	
	/**
	 * Create the panel.
	 */
	private void CreatePassenger() { // 
		if (rdbtnBusiness.isSelected()) {
			passenger = MainMenu.getAirlineMgr().CreatePassenger(textName.getText(), "B");	
		} 
		else
		{
			if (rdbtnIsland.isSelected()) {
				passenger = MainMenu.getAirlineMgr().CreatePassenger(textName.getText(), "I");
			}
			else if (rdbtnOrdinary.isSelected()) {
					passenger = MainMenu.getAirlineMgr().CreatePassenger(textName.getText(), "O");
				}			
		}
		
		passenger.setFname(textName.getText());  
		passenger.setSname(textSurname.getText());
		passenger.setAdCity(textCity.getText());
		passenger.setAdPostcode(textPostCode.getText());
		passenger.setAdStreet(textStreet.getText());
		
		ClearFields();
		MainMenu.hideNewPassenger();
	}
	
	private void DisplayPassenger() {		
		
		if (passenger != null) {
			textName.setText(passenger.getFname());  //
			textSurname.setText(passenger.getSname());
			textCity.setText(passenger.getAdCity());
			textPostCode.setText(passenger.getAdPostcode());
			textStreet.setText(passenger.getAdStreet());
			lblDiscount.setText(Integer.toString(passenger.getDiscount()) + "%");
			
			if (passenger.getClass() == Business.class) {				
				rdbtnBusiness.setSelected(true);
			} 
			else 
			{
				if (passenger.getClass() == Ordinary.class) {				
					rdbtnOrdinary.setSelected(true);
				}
				else
				{
					if (passenger.getClass() == Island.class) {				
						rdbtnIsland.setSelected(true);
					}
				}
			}			
		} 
		else
		{
			rdbtnOrdinary.setSelected(true);
			lblDiscount.setText("0%");		
		}
	}
	
	private void ClearFields() {
		textName.setText("");
		textSurname.setText("");
		textCity.setText("");
		textPostCode.setText("");
		textStreet.setText("");	
	}
	
	public PassengerPanel() {
		addComponentListener(new ComponentAdapter() {  // reciving events
			public void componentShown(ComponentEvent arg0) {
				DisplayPassenger();
			}
		});		
		setLayout(null);
		
		textName = new JTextField();
		textName.setBounds(10, 48, 105, 20);
		add(textName);
		textName.setColumns(10);
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 32, 105, 14);
		add(lblNewLabel);
		
		textSurname = new JTextField();
		textSurname.setColumns(10);
		textSurname.setBounds(125, 48, 105, 20);
		add(textSurname);
		
		JLabel lblSureName = new JLabel("SurName");
		lblSureName.setBounds(125, 32, 105, 14);
		add(lblSureName);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(10, 79, 105, 14);
		add(lblStreet);
		
		textStreet = new JTextField();
		textStreet.setColumns(10);
		textStreet.setBounds(10, 95, 220, 20);
		add(textStreet);
		
		textCity = new JTextField();
		textCity.setColumns(10);
		textCity.setBounds(240, 95, 105, 20);
		add(textCity);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(240, 79, 105, 14);
		add(lblCity);
		
		JLabel lblPostcode = new JLabel("PostCode");
		lblPostcode.setBounds(355, 79, 85, 14);
		add(lblPostcode);
		
		textPostCode = new JTextField();
		textPostCode.setColumns(10);
		textPostCode.setBounds(355, 95, 86, 20);
		add(textPostCode);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClearFields();  // when cancel is pressed clear field and hide new passenger menu
				MainMenu.hideNewPassenger();
			}
		});	
		
		btnNewButton.setBounds(336, 266, 89, 23);
		add(btnNewButton);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
				//if statement for blank fields
				
				if(textName.getText().equals("") || textSurname.getText().equals("") || textStreet.getText().equals("") 
						|| textCity.getText().equals("") || textPostCode.getText().equals("")){		
					JOptionPane.showMessageDialog(null,"Please enter details");  
					}
				
				
				else{
					CreatePassenger();	
				}
			}
		});
		btnOk.setBounds(434, 266, 89, 23);
		add(btnOk);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("com.scotia.Passenger info:");
		lblNewJgoodiesTitle.setBackground(Color.ORANGE);
		lblNewJgoodiesTitle.setForeground(Color.BLACK);
		lblNewJgoodiesTitle.setBounds(10, 7, 430, 14);
		add(lblNewJgoodiesTitle);
		
		//com.scotia.Passenger type
		
		JLabel lblNewLabel_1 = new JLabel("com.scotia.Passenger type:");
		lblNewLabel_1.setBounds(10, 129, 220, 14);
		add(lblNewLabel_1);
		
		rdbtnOrdinary = new JRadioButton("com.scotia.Ordinary");
		rdbtnOrdinary.setBounds(10, 150, 80, 23);
		add(rdbtnOrdinary);		
		
	    rdbtnIsland = new JRadioButton("com.scotia.Island");
		rdbtnIsland.setBounds(92, 150, 69, 23);
		add(rdbtnIsland);
		
		rdbtnBusiness = new JRadioButton("com.scotia.Business");
		rdbtnBusiness.setBounds(163, 150, 86, 23);
		add(rdbtnBusiness);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOrdinary);
		group.add(rdbtnIsland);
		group.add(rdbtnBusiness);
		
		//discount label
		
		JLabel lblNewLabel_2 = new JLabel("Discount");
		lblNewLabel_2.setBounds(250, 32, 80, 14);
		add(lblNewLabel_2);
		
		lblDiscount = new JLabel("");
		lblDiscount.setBackground(Color.YELLOW);
		lblDiscount.setBounds(249, 51, 81, 14);
		add(lblDiscount);
		
		DisplayPassenger();	
	}
}
