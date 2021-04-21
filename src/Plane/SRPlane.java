package Plane;

import Main.GeneralRepositoryInformation;

public class SRPlane implements IPlane_Hostess,
								IPlane_Passenger,
								IPlane_Pilot {
	private final int MIN = 1;
	private final int MAX;
	private final GeneralRepositoryInformation airport;
	
	public SRPlane(GeneralRepositoryInformation airport, int maxPassengers) {
        this.airport = airport;
        this.MAX = maxPassengers;
    }
	
	//Pilot
	public void waitForAllInBoard() {
		System.out.println("waitForAllInBoard");
	}
	public void flyToDestinationPoint() {
		System.out.println("flyToDestinationPoint");
	}
	public void announceArrival() {
		System.out.println("announceArrival");
	}
	public void flyToDeparturePoint() {
		System.out.println("flyToDeparturePoint");
	}
	public void parkAtTransferGate() {
		System.out.println("parkAtTransferGate");
	}
	//Hostess
	public void informPlaneReadyToTakeOff() {
		System.out.println("informPlaneReadyToTakeOff");
	}
	//Passenger
	public void boardThePlane() {
		System.out.println("boardThePlane");
	}
	public void waitForEndOfFlight() {
		System.out.println("waitForEndOfFlight");
	}
	public void leaveThePlane() {
		System.out.println("leaveThePlane");
	}
}
