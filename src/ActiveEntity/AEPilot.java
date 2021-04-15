package ActiveEntity;

import DepartureAirport.SRDepartureAirport;
import DestinationAirport.SRDestinationAirport;
import Plane.SRPlane;

/* Pilot
AT_TRANSFER_GATE – transition state (initial / final state)
READY_FOR_BOARDING – transition state
WAIT_FOR_BOARDING – blocking state -- SRdepAirport/SRplane
	The pilot is waken up by the operation informPlaneReadyToTakeOff of the hostess when
	boarding is complete.
FLYING_FORWARD – independent state with blocking -- SRplane
	The pilot should be made to sleep for a random time interval in the simulation.
DEBOARDING – blocking state -- SRarrAirport/SRplane
	The pilot is waken up by the operation leaveThePlane of the last passenger to leave the plane.
FLYING_BACK – independent state with blocking -- SRplane
	The pilot should be made to sleep for a random time interval in the simulation.
 */
public class AEPilot extends Thread {
	
	/**
     * Identifier of the Pilot.
     */
	private final int id;
	
	/**
     * Current state of the Pilot.
     */
	private PilotState state;
	
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
	
	public AEPilot(int id, SRDepartureAirport depAirport, SRDestinationAirport destAirport, SRPlane plane) {
		this.id = id;
		this.depAirport = depAirport;
		this.destAirport = destAirport;
		this.plane = plane;
	}
	
	public void run() {
        System.out.println("Hello from Pilot!");
    	/*	
    	curret_state = AT_TRANSFER_GATE;
    	public void informPlaneReadyForBoarding();
    	curret_state = READY_FOR_BOARDING;
    	public void waitForAllInBoard();
    	curret_state = WAIT_FOR_BOARDING;
    	public void flyToDestinationPoint();
    	curret_state = FLYING_FORWARD;
    	public void announceArrival();
    	curret_state = DEBOARDING;
    	public void flyToDeparturePoint();
    	curret_state = FLYING_BACK;
    	public void parkAtTransferGate();
    	curret_state = AT_TRANSFER_GATE;
    	*/
        // CALL METHODS FROM SHARED REGIONS
        depAirport.informPlaneReadyForBoarding();
        
        plane.waitForAllInBoard();
        
        plane.flyToDestinationPoint();
        
        plane.announceArrival();
        
        plane.flyToDeparturePoint();
        
        plane.parkAtTransferGate();
    }
	
	/**
     * Get the ID of the Pilot
     * 
     * @return ID of the Pilot
     */
    public int getID(){
        return this.id;
    }
	
    /**
     * Get the Pilot state
     * 
     * @return String with Pilot state
     */
	public PilotState getCurret_state() {
		return this.state;
	}
	
	/**
     * Set the Pilot state
     * @param String new state of the Pilot
     */
	public void setCurret_state(PilotState state) {
		this.state = state;
	}
}
