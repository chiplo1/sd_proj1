package ActiveEntity;
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
	
	private String curret_state;
	private String last_state;
	
	public void run() {
		setCurret_state("WAIT_FOR_NEXT_FLIGHT");
		last_state = getCurret_state();
        System.out.println("Hello from Hostess!");
        for(;;) {
        	String new_state = getCurret_state();
        	if(new_state != last_state) {
        		System.out.printf("Hostess new state: %s\n",new_state);
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
