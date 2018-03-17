package com.scotia;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Flight {
	public enum Status {
		AVAILABLE, CHECKING, BOARDING, CLOSED
	};

	private Status flightStatus;
	// array for seats
	private ArrayList<Seat> Seats = new ArrayList<>();// array for seats
	private String arrivalAirport;
	private String departureAirport;
	private Date arrivalDate;
	private Date departureDate;
	private double Cost;
	private String flightNumber;

	private int daysBetween(Date d1, Date d2) {  // get days between dates for cancellation
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	private Status checkStatusByDate() {  // Check status by date and time
		Status lStatus = Status.AVAILABLE; //initial status set as available
		
		Date date = new Date();
		long CurrentTime = date.getHours() * 3600 + date.getMinutes() * 60;  // we put the dates in seconds to compare 
		long DepartureTime = departureDate.getHours() * 3600 + departureDate.getMinutes() * 60; //time to seconds
		long DiffTime = DepartureTime - CurrentTime;
		long DiffDate = daysBetween(date, departureDate);
		Boolean EqualDate = daysBetween(date, departureDate) == 0; // boolean to state if the date is the same one

		// if statement to state flight status by date and time
		
		if (DiffDate >= 2) {
			lStatus = Status.AVAILABLE;
		} else if (DiffDate == 1 || DiffTime >= 3600) {
			lStatus = Status.CHECKING;
		} else if (DiffDate < 0) {
			lStatus = Status.CLOSED;
		} else {
			if (EqualDate) {
				if (DiffTime >= 0 && DiffTime <= 3600) {
					lStatus = Status.BOARDING;
				} else if (DiffTime < 0) {
					lStatus = Status.CLOSED;
				}
			}
		}

		return lStatus;
	}

	public Flight(String flightNum) { // we initialize var flightnumber
		flightNumber = flightNum;
		flightStatus = Status.AVAILABLE; // we initiate as available
		CreateSeats(); 
	}

	public void CreateSeats() {                      // we create all the seats in the plane
		Seats.add(new Seat("1A", Seat.Status.AVAILABLE));
		Seats.add(new Seat("1B", Seat.Status.AVAILABLE));
		Seats.add(new Seat("1C", Seat.Status.AVAILABLE));
		Seats.add(new Seat("1D", Seat.Status.AVAILABLE));

		Seats.add(new Seat("2A", Seat.Status.AVAILABLE));
		Seats.add(new Seat("2B", Seat.Status.AVAILABLE));
		Seats.add(new Seat("2C", Seat.Status.AVAILABLE));
		Seats.add(new Seat("2D", Seat.Status.AVAILABLE));

		Seats.add(new Seat("3A", Seat.Status.AVAILABLE));
		Seats.add(new Seat("3B", Seat.Status.AVAILABLE));
		Seats.add(new Seat("3C", Seat.Status.AVAILABLE));
		Seats.add(new Seat("3D", Seat.Status.AVAILABLE));

		Seats.add(new Seat("4A", Seat.Status.AVAILABLE));
		Seats.add(new Seat("4B", Seat.Status.AVAILABLE));
		Seats.add(new Seat("4C", Seat.Status.AVAILABLE));
		Seats.add(new Seat("4D", Seat.Status.AVAILABLE));

		Seats.add(new Seat("5A", Seat.Status.AVAILABLE));
		Seats.add(new Seat("5B", Seat.Status.AVAILABLE));
		Seats.add(new Seat("5C", Seat.Status.AVAILABLE));
		Seats.add(new Seat("5D", Seat.Status.AVAILABLE));

		Seats.add(new Seat("6A", Seat.Status.AVAILABLE));
		Seats.add(new Seat("6B", Seat.Status.AVAILABLE));
		Seats.add(new Seat("6C", Seat.Status.AVAILABLE));
		Seats.add(new Seat("6D", Seat.Status.AVAILABLE));

		Seats.add(new Seat("7A", Seat.Status.AVAILABLE));
		Seats.add(new Seat("7B", Seat.Status.AVAILABLE));
		Seats.add(new Seat("7C", Seat.Status.AVAILABLE));
		Seats.add(new Seat("7D", Seat.Status.AVAILABLE));

		Seats.add(new Seat("8A", Seat.Status.AVAILABLE));
		Seats.add(new Seat("8B", Seat.Status.AVAILABLE));
		Seats.add(new Seat("8C", Seat.Status.AVAILABLE));
		Seats.add(new Seat("8D", Seat.Status.AVAILABLE));

	}

	public Seat getSeatByNumber(String number) {
		Seat lSeat = null;

		for (int i = 0; i < Seats.size(); i++) { // We return a seat by number
													// from the array
			if (Seats.get(i).getSeatNo().equals(number.trim())) { 
				lSeat = Seats.get(i);
				break;
			}
		}

		return lSeat;
	}

	public Status getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(Status flightStatus) {
		this.flightStatus = flightStatus;
	}

	public List<Seat> getSeats() {
		return Seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		Seats = seats;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		//
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
		// sets the status of the flight depending on the current date
		this.flightStatus = checkStatusByDate();  
	}

	public double getCost() {
		return Cost;
	}

	public void setCost(double cost) {
		this.Cost = cost;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
}