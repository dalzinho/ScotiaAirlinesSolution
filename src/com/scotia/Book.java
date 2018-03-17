package com.scotia;

import java.util.Date;

public class Book {
	private int Number; // reservation number
	private Flight flightToBook;
	private Seat seat;
	private Passenger passenger;
	private Boolean suitCase;
	private Double totalCost;
	
	public Book(Passenger pPassenger) {
		passenger = pPassenger;
	}
	
	//setters and getters
	
	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		//Number = number;
	}
	
	public Seat getSeat() {
		return seat;
	}
	
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public Boolean getSuitCase() {
		return suitCase;
	}
	
	public void setSuitCase(Boolean suitCase) {
		this.suitCase = suitCase;
	}
	
	public Double getTotalCost() { // apply discount + suitcase for total cost
		totalCost = flightToBook.getCost() - ((passenger.getDiscount() * flightToBook.getCost()) / 100); 
		if (suitCase) {                                                  
			totalCost = totalCost + 50;
		}
		return totalCost;
	}
	
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	public Flight getFlightToBook() {
		return flightToBook;
	}
	
	// We initialize an object flight
	public void setFlightToBook(Flight flightToBook) { 
		this.flightToBook = flightToBook;
	}			
	
	//@SuppressWarnings("deprecation")
	
	public Boolean cancelBook() {
		Date date = new Date();		
		return flightToBook.getFlightStatus() == Flight.Status.AVAILABLE;
	}
	
}
