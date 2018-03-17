package com.scotia;

public class Ordinary extends Passenger {
  protected int Discount = 5;

  public Ordinary(String name) {
    super(name);
  }

  public int getDiscount() {
    return this.Discount;
  }
}