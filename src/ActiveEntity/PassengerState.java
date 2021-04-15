package ActiveEntity;

/**
 * Enum with the possible states of the Passenger on his lifecycle.
 */
public enum PassengerState{
    /**
     * Passenger is going to the departure airport.
     */
	GOING_TO_AIRPORT ("GTA"),

    /**
     * Passenger waiting in queue to check his documents.
     */
	IN_QUEUE ("IQ"),
	
    /**
     * Passenger in flight waiting to arrive in the destination.
     */
	IN_FLIGHT ("IF"),

    /**
     * Passenger at final destination.
     */
	AT_DESTINATION ("AD");

    private final String description;

    private PassengerState(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return this.description;
    }
}
