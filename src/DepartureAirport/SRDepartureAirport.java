package DepartureAirport;

import ActiveEntity.*;

public class SRDepartureAirport implements 	IDepartureAirport_Hostess,
											IDepartureAirport_Passenger,
											IDepartureAirport_Pilot{
	
	private AEPilot pilot;
	private AEHostess hostess;
	private AEPassenger[] passengers;
	
	public SRDepartureAirport(AEPilot pilot, AEHostess hostess, AEPassenger[] passengers) {
        this.pilot = pilot;
        this.hostess = hostess;
        this.passengers = passengers;
    }
	
	//Pilot
	public void informPlaneReadyForBoarding() {
		
	}
	//Hostess
	public void prepareForPassBoarding() {
		
	}
	public void checkDocuments() {
		
	}
	public void waitForNextPassenger() {
		
	}
	public void waitForNextFlight() {
		
	}
	//Passenger
	public void travelToAirport() {
		
	}
	public void waitInQueue() {
		
	}
	public void showDocuments() {
		
	}
}
