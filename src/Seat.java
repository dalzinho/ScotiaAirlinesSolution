
public class Seat {
	
	public enum Status {AVAILABLE, RESERVED, BOOKED}  
	
	private Status Seatstatus; // We get the status by the enum above
	private String SeatNo;
	private Passenger passenger;
	
	public Seat(String number, Status status) {
		SeatNo = number;
		Seatstatus = status;	
	}
	
	public Passenger getPassenger() {  
		return passenger;
	}
	
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public Status getSeatstatus() {
		return Seatstatus;
	}
	
	public void setSeatstatus(Status seatstatus) {
		Seatstatus = seatstatus;
	}
	
	public String getSeatNo() {
		return SeatNo;
	}
	
	public void setSeatNo(String seatNo) {
		SeatNo = seatNo;
	}

}

