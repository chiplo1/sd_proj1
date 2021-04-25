package ActiveEntity;

import DepartureAirport.SRDepartureAirport;
import DestinationAirport.SRDestinationAirport;
import Plane.SRPlane;

public class AEPilot extends Thread {
	
	/**
     * Identifier of the Pilot.
     */
	private final int id;
	
	/**
     * Current state of the Pilot.
     */
	private PilotState state = PilotState.AT_TRANSFER_GATE;
	
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
	
	/**
     * Function that implements Pilot life cycle
     */
	@Override
	public void run() {
        //System.out.println("Pilot: Hello!");
        boolean morePassengers=true;
    	while(morePassengers){
    		System.out.println("Pilot: "+getCurret_state());
            switch(getCurret_state()){
                case AT_TRANSFER_GATE:
                	depAirport.informPlaneReadyForBoarding();
                	setCurret_state(PilotState.READY_FOR_BOARDING);
                    break;
                case READY_FOR_BOARDING:
                	depAirport.waitForAllInBoard();
                	setCurret_state(PilotState.WAIT_FOR_BOARDING);
                    break;
                case WAIT_FOR_BOARDING://blocking state - The pilot is waken up by the operation informPlaneReadyToTakeOff of the hostess when boarding is complete.
                	plane.flyToDestinationPoint();
                	setCurret_state(PilotState.FLYING_FORWARD);
                    break;
                case FLYING_FORWARD://independent state with blocking - The pilot should be made to sleep for a random time interval in the simulation.
                	plane.announceArrival();
                	setCurret_state(PilotState.DEBOARDING);
                    break;
                case DEBOARDING://blocking state - The pilot is waken up by the operation leaveThePlane of the last passenger to leave the plane.
                	destAirport.flyToDeparturePoint();
                	setCurret_state(PilotState.FLYING_BACK);
                    break;
                case FLYING_BACK:// independent state with blocking - The pilot should be made to sleep for a random time interval in the simulation.
                	plane.parkAtTransferGate();
                	setCurret_state(PilotState.AT_TRANSFER_GATE);
                	morePassengers = destAirport.morePassengers();
                    break;
            }
        }
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
