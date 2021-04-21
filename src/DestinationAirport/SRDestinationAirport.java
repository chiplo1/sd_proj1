package DestinationAirport;

import Main.GeneralRepositoryInformation;

public class SRDestinationAirport implements 	IDestinationAirport_Hostess,
												IDestinationAirport_Passenger,
												IDestinationAirport_Pilot {
	
	private final GeneralRepositoryInformation airport;
	
	public SRDestinationAirport(GeneralRepositoryInformation airport) {
        this.airport = airport;
    }

}
