package ActiveEntity;

/**
 * Enum with the possible states of the Pilot on his lifecycle.
 */
public enum PilotState{
    /**
     * Pilot at transfer gate.
     */
	AT_TRANSFER_GATE ("ATG"),

    /**
     * Plane is ready for boarding.
     */
	READY_FOR_BOARDING ("RFB"),
	
    /**
     * Waiting for all the passengers to board.
     */
	WAIT_FOR_BOARDING ("WFB"),

    /**
     * Plane is flying to destination.
     */
	FLYING_FORWARD ("FF"),
	
	/**
     * Waiting for the last passenger to leave the plane.
     */
	DEBOARDING ("DB"),

    /**
     * Pilot is flying back the plane to the departure airport.
     */
	FLYING_BACK ("FB");

    private final String description;

    private PilotState(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return this.description;
    }
}
