package ActiveEntity;

/**
 * Enum with the possible states of the Hostess on his lifecycle.
 */
public enum HostessState{
    /**
     * Hostess is waiting for plane ready for boarding.
     */
	WAIT_FOR_NEXT_FLIGHT ("WFF"),

    /**
     * Hostess waiting for passengers waiting in queue.
     */
	WAIT_FOR_PASSENGER ("WFP"),
	
    /**
     * Hostess checking passenger's documents.
     */
	CHECK_PASSENGER ("CP"),

    /**
     * All passengers in the plane, all ready to fly.
     */
	READY_TO_FLY ("RTF");

    private final String description;

    private HostessState(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return this.description;
    }
}
