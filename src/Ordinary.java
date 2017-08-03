
public class Ordinary extends Passenger {
	
	protected int Discount = 5; // var overwritten for applying discount
	
	public Ordinary(String name) {  //get name from superclass 
		super(name);
	}
	
	@Override
	public int getDiscount() {
		// TODO Auto-generated method stub
		return Discount;
	}
}

