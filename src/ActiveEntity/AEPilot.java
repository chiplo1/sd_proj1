package ActiveEntity;
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
	
	private String curret_state;
	private String last_state;
	
	public void run() {
		setCurret_state("AT_TRANSFER_GATE");
		last_state = getCurret_state();
        System.out.println("Hello from Pilot!");
        for(;;) {
        	String new_state = getCurret_state();
        	if(new_state != last_state) {
        		System.out.printf("Pilot new state: %s\n",new_state);
        		last_state = new_state;
        	}
        }
    }
	
	public String getCurret_state() {
		return curret_state;
	}
	public void setCurret_state(String curret_state) {
		this.curret_state = curret_state;
	}
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
}
