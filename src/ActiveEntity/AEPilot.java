package ActiveEntity;
/* Pilot
AT_TRANSFER_GATE � transition state (initial / final state)
READY_FOR_BOARDING � transition state
WAIT_FOR_BOARDING � blocking state -- SRdepAirport/SRplane
	The pilot is waken up by the operation informPlaneReadyToTakeOff of the hostess when
	boarding is complete.
FLYING_FORWARD � independent state with blocking -- SRplane
	The pilot should be made to sleep for a random time interval in the simulation.
DEBOARDING � blocking state -- SRarrAirport/SRplane
	The pilot is waken up by the operation leaveThePlane of the last passenger to leave the plane.
FLYING_BACK � independent state with blocking -- SRplane
	The pilot should be made to sleep for a random time interval in the simulation.
 */
public class AEPilot extends Thread {
	public void run() {
        System.out.println("Hello from Pilot!");
    }
	public void informPlaneReadyForBoarding() {
		
	}
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
}
