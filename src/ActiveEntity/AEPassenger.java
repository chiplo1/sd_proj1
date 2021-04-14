package ActiveEntity;
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
	
	private String curret_state;
	private String last_state;
	
	public void run() {
		setCurret_state("GOING_TO_AIRPORT");
		last_state = getCurret_state();
        System.out.println("Hello from Passenger!");
        for(;;) {
        	String new_state = getCurret_state();
        	if(new_state != last_state) {
        		System.out.printf("Passenger new state: %s\n",new_state);
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
}

