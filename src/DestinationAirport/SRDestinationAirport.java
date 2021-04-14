package DestinationAirport;

import ActiveEntity.AEHostess;
import ActiveEntity.AEPassenger;
import ActiveEntity.AEPilot;

public class SRDestinationAirport implements 	IDestinationAirport_Hostess,
												IDestinationAirport_Passenger,
												IDestinationAirport_Pilot {
	
	private AEPilot pilot;
	private AEHostess hostess;
	private AEPassenger[] passengers;
	
	public SRDestinationAirport(AEPilot pilot, AEHostess hostess, AEPassenger[] passengers) {
        this.pilot = pilot;
        this.hostess = hostess;
        this.passengers = passengers;
    }

}
