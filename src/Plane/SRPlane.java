package Plane;

import ActiveEntity.*;

public class SRPlane implements IPlane_Hostess,
								IPlane_Passenger,
								IPlane_Pilot {
	
	private AEPilot pilot;
	private AEHostess hostess;
	private AEPassenger[] passengers;
	
	public SRPlane(AEPilot pilot, AEHostess hostess, AEPassenger[] passengers) {
        this.pilot = pilot;
        this.hostess = hostess;
        this.passengers = passengers;
    }
	
	//Pilot
	public void waitForAllInBoard() {
		
	}
	public void flyToDestinationPoint() {
		
	}
	public void announceArrival() {
		
	}
	public void flyToDeparturePoint() {
		
	}
	public void parkAtTransferGate() {
		
	}
	//Hostess
	public void informPlaneReadyToTakeOff() {
		
	}
	//Passenger
	public void boardThePlane() {
		
	}
	public void waitForEndOfFlight() {
		
	}
	public void leaveThePlane() {
		
	}
}
