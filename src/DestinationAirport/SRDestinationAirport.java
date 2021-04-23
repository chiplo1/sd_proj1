package DestinationAirport;

import Main.GeneralRepositoryInformation;

public class SRDestinationAirport implements 	IDestinationAirport_Hostess,
												IDestinationAirport_Passenger,
												IDestinationAirport_Pilot {
	
	private final GeneralRepositoryInformation airport;
	private final int totalPassengers;
	private int arrivedPassengers;
	
	public SRDestinationAirport(GeneralRepositoryInformation airport, int totalPassengers) {
        this.airport = airport;
        this.totalPassengers = totalPassengers;
        this.arrivedPassengers = 0;
    }
	
	public boolean morePassengers() {
		if(arrivedPassengers==totalPassengers)
			return false;
		else
			return true;
	}

}
