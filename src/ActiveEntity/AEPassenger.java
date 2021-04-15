package ActiveEntity;

import DepartureAirport.SRDepartureAirport;
import DestinationAirport.SRDestinationAirport;
import Plane.SRPlane;

/* Passenger
GOING_TO_AIRPORT – independent state with blocking (initial state) -- SRdepAirport
	The passenger should be made to sleep for a random time interval in the simulation.
IN_QUEUE – double blocking state -- SRdepAirport
	The passenger is waken up first by the operation checkDocuments of the hostess requesting him/ her
	to present the plane ticket and is waken up next by the operation waitForNextPassenger of the
	hostess after the checking is being made.
IN_FLIGHT – blocking state --SRplane
	The passenger is waken up by the operation announceArrival of the pilot after parking the plane at
	the arrival gate.
AT_DESTINATION – transition state (final state)
*/
public class AEPassenger extends Thread {
	
	/**
     * Identifier of the Passenger.
     */
	private final int id;
	
	/**
     * Current state of the Passenger.
     */
	private PassengerState state;
	
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
	
	public AEPassenger(int id, SRDepartureAirport depAirport, SRDestinationAirport destAirport, SRPlane plane) {
		this.id = id;
		this.depAirport = depAirport;
		this.destAirport = destAirport;
		this.plane = plane;
	}
	
	public void run() {
        System.out.println("Hello from Passenger!");
    	/*
    	public void travelToAirport();
    	current_state = GOING_TO_AIRPORT;
    	public void waitInQueue();
    	current_state = IN_QUEUE;
    	public void showDocuments();
    	public void boardThePlane();
    	current_state = IN_FLIGHT;
    	public void waitForEndOfFlight();
    	public void leaveThePlane();
    	current_state = AT_DESTINATION;
    	*/
        // CALL METHODS FROM SHARED REGIONS
        
        depAirport.travelToAirport();
        
        depAirport.waitInQueue();
        
        depAirport.showDocuments();
        
        plane.boardThePlane();
        
        plane.waitForEndOfFlight();
        
        plane.leaveThePlane();
    }
	
	/**
     * Get the ID of the Passenger
     * 
     * @return ID of the Passenger
     */
    public int getID(){
        return this.id;
    }
	
    /**
     * Get the Passenger state
     * 
     * @return Passenger state
     */
	public PassengerState getCurret_state() {
		return this.state;
	}
	
	/**
     * Set the Passenger state
     * @param String new state of the Passenger
     */
	public void setCurret_state(PassengerState state) {
		this.state = state;
	}
	
}

