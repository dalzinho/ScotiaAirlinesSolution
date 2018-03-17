package com.scotia;

public class Business extends Passenger {
	
	protected int Discount = 25; // var overwritten for applying discount
	
	public Business(String name) {	
		super(name);
	}

	@Override
	public int getDiscount() {
		// TODO Auto-generated method stub
		return Discount;
	}
}
