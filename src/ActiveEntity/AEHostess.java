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
	private HostessState state = HostessState.WAIT_FOR_NEXT_FLIGHT;
	
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

	/**
     * Function that implements Hostess life cycle
     */
	@Override
	public void run() {
        System.out.println("Hello from Hostess!");

    	while(true){
            switch(this.state){
                case WAIT_FOR_NEXT_FLIGHT:
                	depAirport.prepareForPassBoarding();
                    this.state = HostessState.WAIT_FOR_PASSENGER;
                    break;
                case WAIT_FOR_PASSENGER:
                	depAirport.checkDocuments();
                	this.state = HostessState.CHECK_PASSENGER;
                    break;
                case CHECK_PASSENGER:
                	boolean morePassengers = depAirport.waitForNextPassenger();
                	if(morePassengers) {
                		this.state = HostessState.WAIT_FOR_PASSENGER;
                	}
                	else {
                		depAirport.informPlaneReadyForBoarding();
                		this.state = HostessState.WAIT_FOR_PASSENGER;
                	}
                	this.state = HostessState.READY_TO_FLY;
                    break;
                case READY_TO_FLY:
                	depAirport.waitForNextFlight();
                	this.state = HostessState.WAIT_FOR_NEXT_FLIGHT;
                    break;
            }
        }
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
	
}
