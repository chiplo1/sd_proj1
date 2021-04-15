package ActiveEntity;

import DepartureAirport.SRDepartureAirport;
import DestinationAirport.SRDestinationAirport;
import Plane.SRPlane;

/* Hostess
WAIT_FOR_NEXT_FLIGHT – blocking state (initial / final state) -- SRdepAirport
	The hostess is waken up by the operation informPlaneReadyForBoarding of the pilot after
	parking the plane at the departure gate.
WAIT_FOR_PASSENGER – blocking state -- SRdepAirport
	The hostess is waken up by the operation waitInQueue of the passenger while he / she waits
	to have his / her documents checked.
CHECK_PASSENGER – blocking state -- SRdepAirport
	The hostess is waken up by the operation showDocuments of the passenger when he / she
	hands his / her plane ticket to be checked.
READY_TO_FLY – transition state
*/
public class AEHostess extends Thread {
	
	/**
     * Identifier of the Hostess.
     */
	private final int id;
	
	/**
     * Current state of the Hostess.
     */
	private HostessState state;
	
	/**
     * Instance of the DepartureAirport.
     */
    private final SRDepartureAirport depAirport;
    
    /**
     * Instance of the DestinationAirport.
     */
    private final SRDestinationAirport destAirport;
    
    /**
     * Instance of the Plane.
     */
    private final SRPlane plane;
	
	public AEHostess(int id, SRDepartureAirport depAirport, SRDestinationAirport destAirport, SRPlane plane) {
		this.id = id;
		this.depAirport = depAirport;
		this.destAirport = destAirport;
		this.plane = plane;
	}
	
	public void run() {
        System.out.println("Hello from Hostess!");
        // CALL METHODS FROM SHARED REGIONS
    }
	
	/**
     * Get the ID of the Hostess
     * 
     * @return ID of the Hostess
     */
    public int getID(){
        return this.id;
    }
	
    /**
     * Get the Hostess state
     * 
     * @return String with Hostess state
     */
	public HostessState getCurret_state() {
		return this.state;
	}
	
	/**
     * Set the Hostess state
     * @param String new state of the Hostess
     */
	public void setCurret_state(HostessState state) {
		this.state = state;
	}
	
	/*
	current_state = WAIT_FOR_NEXT_FLIGHT;
	public void prepareForPassBoarding();
	while(passenger)
		current_state = WAIT_FOR_PASSENGER;
		public void checkDocuments();
		current_state = CHECK_PASSENGER;
		public void waitForNextPassenger();
	public void informPlaneReadyToTakeOff();
	current_state = READY_TO_FLY;
	public void waitForNextFlight();
	current_state = WAIT_FOR_NEXT_FLIGHT;
	*/
}
