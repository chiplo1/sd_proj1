package ActiveEntity;

import DepartureAirport.SRDepartureAirport;
import DestinationAirport.SRDestinationAirport;
import Plane.SRPlane;

public class AEPassenger extends Thread {
	
	/**
     * Identifier of the Passenger.
     */
	private final int id;
	
	/**
     * Current state of the Passenger.
     */
	private PassengerState state = PassengerState.GOING_TO_AIRPORT;
	
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
		
	/**
     * Function that implements Passenger life cycle
     */
	@Override
	public void run() {
        System.out.println("Hello from Passenger!");

    	while(true){
            switch(this.state){
                case GOING_TO_AIRPORT://independent state with blocking (initial state)
                	//The passenger should be made to sleep for a random time interval in the simulation.
                	depAirport.travelToAirport(id);
                    this.state = PassengerState.IN_QUEUE;
                    break;
                case IN_QUEUE://double blocking state
                	//The passenger is waken up first by the operation checkDocuments of the hostess requesting him/ her
                	//to present the plane ticket and is waken up next by the operation waitForNextPassenger of the
                	//hostess after the checking is being made.
                	depAirport.waitInQueue();
                	depAirport.showDocuments();
                	plane.boardThePlane(id);
                	this.state = PassengerState.IN_FLIGHT;
                    break;
                case IN_FLIGHT://blocking state
                	//The passenger is waken up by the operation announceArrival of the pilot after parking the plane at
                	//the arrival gate.
                	plane.waitForEndOfFlight();
                	this.state = PassengerState.AT_DESTINATION;
                    break;
                case AT_DESTINATION:
                	plane.leaveThePlane();
                	destAirport.arrivedAtDestinationAirport(id);
                    break;
            }
        }
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

