import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AirlineManager {
	// to show departure/arrival date with a certain format 
	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // format
	private ArrayList<Flight> FlightList = new ArrayList<>(); // Array for adding the flights
	private Passenger passenger = null;
	private String line = null;	//	
	
	// parse txt file and get info
	private String GetValueFromFile(String pLine) 
	{
		int pos;
		pos = pLine.indexOf("=");	// retrieves the position of = from the line			
		return pLine.substring(pos + 1);	// creates substring from pos + 1
	}
	
	// parsing reservation info
	private void LoadReservationsToPassenger(File pFile, Passenger pPassenger) 
	{	
		String lReservationNumber = null;	// 
		String lFlightNumber = null;
		String lSeatNumber = null;				
		Boolean lSuitCase = false;
		Flight lFlight = null;
		Seat lSeat = null;
		BufferedReader input; 		
		
		if (pPassenger != null) 
		{			
			try 
			{
				input = new BufferedReader(new FileReader(pFile));				
				line = input.readLine();	
				// parsing Flight info	
				while (line != null) 
				{		
					if (line.contains("Reservation_number")) {					
						lReservationNumber = GetValueFromFile(line);
					} else
					if (line.contains("Seat_number")) {											
						lSeatNumber = GetValueFromFile(line);						
					} else
					if (line.contains("Flight_number")) {
						lFlightNumber = GetValueFromFile(line);								
					} else						
					if (line.contains("Suit_case")) {
						lSuitCase = true;
					} else
					if (line.startsWith("-")) {						
						lFlight = getFlightByNumber(lFlightNumber);						
						lSeat = lFlight.getSeatByNumber(lSeatNumber.toString());						
						pPassenger.addReservation(Integer.parseInt(lReservationNumber), lFlight, lSeat, lSuitCase, 0.0);					
						
						// initialize the variables for the next line 
						lReservationNumber = null;		
						lFlightNumber = null;
						lSeatNumber = null;
						lSuitCase = false;
						lFlight = null;
						lSeat = null;						
					}				
					
					line = input.readLine(); //  iteration, returns to null
				} 		
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
	}
	
	// initialize passenger variables
	private void LoadBookFile(String filename) throws IOException {		
		File myFile = new File(filename);		
		String lPassengerName = null;
		String lSureName = null;			
		String lStreet = null;
		String lPostCode = null;
		String lCity = null;
		String lPassengerType = null;
		Passenger lPassenger;		
		BufferedReader input;		
		
		if (myFile.exists()) {
			input = new BufferedReader(new FileReader(myFile));
			
			line = input.readLine(); //  iteration, returns to null					
			
			// parsing passenger info			
			while (line !=null)
			{
				if (line.contains("Name"))
				{					
					lPassengerName = GetValueFromFile(line);
				} else 
				if (line.contains("Sure_name"))
				{			
					lSureName = GetValueFromFile(line);
				} else 
				if (line.contains("Street"))
				{					
					lStreet = GetValueFromFile(line);
				} else 
				if (line.contains("Post_code"))
				{					
					lPostCode = GetValueFromFile(line);
				} else 
				if (line.contains("City"))
				{					
					lCity = GetValueFromFile(line);
				} else 
				if (line.contains("Type"))
				{					
					lPassengerType = GetValueFromFile(line);
				} 
				
				line = input.readLine(); //  iteration, returns to null		
			}
			
			if (lPassengerName != null)
			{
				switch (lPassengerType) {
				/*	case "ORDINARY":
						lPassenger = CreatePassenger(lPassengerName, "O");
						break;*/
					case "ISLAND":
						lPassenger = CreatePassenger(lPassengerName, "I");
						break;
					case "BUSINESS":
						lPassenger = CreatePassenger(lPassengerName, "B");
						break;
					default:
						lPassenger = CreatePassenger(lPassengerName, "O");			//		
				}						
				
				lPassenger.setSname(lSureName);
				lPassenger.setAdStreet(lStreet);				
				lPassenger.setAdPostcode(lPostCode);
				lPassenger.setAdCity(lCity);
				
				// parsing reservations info	
				LoadReservationsToPassenger(myFile, lPassenger);				
			}
			
			input.close();
		}
	}
	
	private Date addMonthsAndHour(Date date, int months, int hour)
	{
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MONTH, months);	    
	    cal.add(Calendar.HOUR, hour);
	    return cal.getTime();
	}
	
	public AirlineManager() { // flight info
		try {		
			Flight flight1 = new Flight("123GF");
			flight1.setDepartureAirport("Skye");		
			flight1.setDepartureDate(formatter.parse("07/04/2017 10:00"));						
			flight1.setArrivalAirport("Glasgow");
			flight1.setArrivalDate(formatter.parse("07/04/2017 11:00"));
			flight1.setCost(180);
			
			Flight flight2 = new Flight("473HR");
			flight2.setDepartureAirport("Arran");
			flight2.setDepartureDate(formatter.parse("11/04/2017 11:00"));		
			flight2.setArrivalAirport("Glasgow");
			flight2.setArrivalDate(formatter.parse("11/04/2017 12:00"));
			flight2.setCost(70);
			
			Flight flight3 = new Flight("365RT");
			flight3.setDepartureAirport("Glasgow");		
			flight3.setDepartureDate(formatter.parse("17/04/2017 10:00"));		
			flight3.setArrivalAirport("Skye");
			flight3.setArrivalDate(formatter.parse("17/04/2017 11:00"));
			flight3.setCost(180);
			
			Flight flight4 = new Flight("589ME");
			flight4.setDepartureAirport("Glasgow");
			flight4.setDepartureDate(formatter.parse("21/04/2017 11:00"));		
			flight4.setArrivalAirport("Arran");
			flight4.setArrivalDate(formatter.parse("21/04/2017 12:00"));
			flight4.setCost(70);
			
			FlightList.add(flight1); //to array
			FlightList.add(flight2);
			FlightList.add(flight3);
			FlightList.add(flight4);	
		
			//load txt file
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			LoadBookFile("FlightScotia.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Flight> getFlightList() {
		return FlightList;
	}
	
	public Flight getFlightByNumber(String number) {
		Flight lflight = null;
		// loop to find flight
		for(int i=0; i<FlightList.size(); i++) {
			if (FlightList.get(i).getFlightNumber().equals(number.trim())) {  
				lflight = FlightList.get(i);				
				break;
			}						
		}
		
		return lflight;		
	}

	public void setFlightList(ArrayList<Flight> flightList) {
		FlightList = flightList;
	}

	public Passenger getPassenger() {
		return passenger;
	}	
	
	public Passenger CreatePassenger(String name, String type) {
		// If statement to create passenger with its type 	
		if (type.trim() == "I") {
			passenger = new Island(name);
		} 
		else if (type.trim() == "B") {
				passenger = new Business(name);
			}
		else if (type.trim() == "O") {
					passenger = new Ordinary(name);
				}					
		
		return passenger;
	}
	
}
