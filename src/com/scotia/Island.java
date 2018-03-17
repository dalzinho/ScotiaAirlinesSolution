package com.scotia;

public class Island extends Passenger {
	
	protected int Discount = 10; // var overwritten for applying discount

	public Island(String name) { // We get passengers name from superclass passenger
		super(name);
	}
	
	@Override
	public int getDiscount() {
		
		return Discount;
	}
}
