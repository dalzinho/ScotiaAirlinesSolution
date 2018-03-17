package com.scotia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Passenger {
	private String Fname;
	private String Sname;
	private String AdStreet;
	private String AdCity;
	private String AdPostcode;
	private Boolean HasPaid;
	// We declare this var protected in order to overwrite on the extended
	// classes
	protected int Discount = 0;

	// private com.scotia.Book Reservation;
	private ArrayList<Book> Reservations = new ArrayList<>();
	private File ReservationFile;
	private String Type;

	// we create an abstract method, because the extent classes(com.scotia.Ordinary,com.scotia.Business,com.scotia.Island) have their own discounts.
	public abstract int getDiscount();

	public Book getReservationByNumber(int number) {
		Book lbook = null;

		// loop for reservations array
		for (int i = 0; i < Reservations.size(); i++) {
			if (Reservations.get(i).getNumber() == number) {
				lbook = Reservations.get(i);
				break;
			}
		}

		return lbook;
	}
	

	public ArrayList<Book> getReservations() {
		return Reservations;
	}

	
	public Boolean isReserved(String FlightNumber, String SeatNumber) { // 
		Boolean found = false;

		for (Book lbook : Reservations) { // Check existing reservation
			if (lbook.getFlightToBook().getFlightNumber() == FlightNumber
					|| lbook.getSeat().getSeatNo() == SeatNumber) {
				found = true;
				break;
			}
		}

		return found;
	}

	public Book addReservation(int number) { 
		Book booking = new Book(this);
		if (number == 0) {
			booking.setNumber(Reservations.size());
		} else {
			booking.setNumber(number);
		}

		Reservations.add(booking);
		return booking;
	}

	// Adding reservation to array
	public Book addReservation(int pNumber, Flight pFlight, Seat pSeat, Boolean pSuitCase, Double pTotalCost) {
		Book booking = addReservation(pNumber);
		pSeat.setPassenger(this);
		pSeat.setSeatstatus(Seat.Status.RESERVED); // set seat to reserved
		booking.setFlightToBook(pFlight);
		booking.setSeat(pSeat);
		booking.setSuitCase(pSuitCase);
		booking.setTotalCost(pTotalCost);

		return booking;
	}

	public void removeReservation(int number) {
		for (Book lbook : Reservations) {
			if (lbook.getNumber() == number) {
				Reservations.remove(lbook);
				break;
			}
		}
	}

	/*
	 * public com.scotia.Book getReservation() { return Reservation; }
	 */

	public String getType() {
		if (this.getClass() == Business.class) {
			Type = "BUSINESS";
		} else {
			if (this.getClass() == Ordinary.class) {
				Type = "ORDINARY";
			} else {
				if (this.getClass() == Island.class) {
					Type = "ISLAND";
				}
			}
		}

		return Type;
	}

	/*
	 * public void setReservation(com.scotia.Book reservation) { Reservation = reservation;
	 * }
	 */

	public Passenger(String name) {
		Fname = name;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getAdStreet() {
		return AdStreet;
	}

	public void setAdStreet(String adStreet) {
		AdStreet = adStreet;
	}

	public String getAdCity() {
		return AdCity;
	}

	public void setAdCity(String adCity) {
		AdCity = adCity;
	}

	public String getAdPostcode() {
		return AdPostcode;
	}

	public void setAdPostcode(String adPostcode) {
		AdPostcode = adPostcode;
	}

	public Boolean getHasPaid() {
		return HasPaid;
	}

	public void setHasPaid(Boolean hasPaid) {
		HasPaid = hasPaid;
	}

	// print reservation on txt file
	private void WriteReservation(BufferedWriter writer, Book pBook) {
		Flight flightToBook = pBook.getFlightToBook();

		try {
			writer.newLine();
			writer.write("Reservation_number=" + pBook.getNumber());
			writer.newLine();
			writer.write("Seat_number=" + pBook.getSeat().getSeatNo());
			writer.newLine();
			writer.write("Flight_number=" + flightToBook.getFlightNumber());
			writer.newLine();
			writer.write("Departure_airport=" + flightToBook.getDepartureAirport());
			writer.newLine();
			writer.write("Departure_date=" + AirlineManager.formatter.format(flightToBook.getDepartureDate()));
			writer.newLine();
			writer.write("Arrival_airport=" + flightToBook.getArrivalAirport());
			writer.newLine();
			writer.write("Arrival_date=" + AirlineManager.formatter.format(flightToBook.getArrivalDate()));
			writer.newLine();
			writer.write("Flight_cost=" + Double.toString(flightToBook.getCost()));
			writer.newLine();
			writer.write("Discount=" + Integer.toString(this.getDiscount()) + "%");
			writer.newLine(); // Skip a line
			if (pBook.getSuitCase()) { // Suitcase Boolean
				writer.write("Suit_case=50�");
				writer.newLine();
			}
			// Casting from double to String for text file
			writer.write("Total_cost=" + Double.toString(pBook.getTotalCost()));
			writer.newLine();
			writer.write("------------------------------------------------------");
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SaveTicket() throws IOException { // Text File creation
		String PassengerType = null;
		ReservationFile = new File("FlightScotia.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(ReservationFile));

		if (this.getClass() == Business.class) {
			PassengerType = "BUSINESS";
		} else {
			if (this.getClass() == Ordinary.class) {
				PassengerType = "ORDINARY";
			} else {
				if (this.getClass() == Island.class) {
					PassengerType = "ISLAND";
				}
			}
		}
		
		//Print com.scotia.Passenger info on txt file

	    writer.write("******* com.scotia.Passenger details *******");
		writer.newLine();
		writer.write("Name=" + this.getFname());
		writer.newLine();
		writer.write("Sure_name=" + this.getSname());
		writer.newLine();
		writer.write("Street=" + this.getAdStreet());
		writer.newLine();
		writer.write("Post_code=" + this.getAdPostcode());
		writer.newLine();
		writer.write("City=" + this.getAdCity());
		writer.newLine();
		writer.write("Type=" + PassengerType);
		writer.newLine();

		writer.write("******* Reservations details *******");
		for (Book lbook : Reservations) {
			WriteReservation(writer, lbook);
		}

		writer.newLine();
		writer.write("Thank you for choosing SCOTIA AIRLINES");
		writer.newLine();
		writer.close();
	}
}
