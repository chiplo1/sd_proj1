package DestinationAirport;

import Main.GeneralRepositoryInformation;

public class SRDestinationAirport implements 	IDestinationAirport_Hostess,
												IDestinationAirport_Passenger,
												IDestinationAirport_Pilot {
	
	private final GeneralRepositoryInformation airport;
	private final int totalPassengers;
	private int arrivedPassengers[];
	private int countArrived;
	
	public SRDestinationAirport(GeneralRepositoryInformation airport, int totalPassengers) {
        this.airport = airport;
        this.totalPassengers = totalPassengers;
        this.arrivedPassengers = new int[totalPassengers];
        countArrived = 0;
    }
	
	public boolean morePassengers() {
		if(countArrived==totalPassengers)
			return false;
		else
			return true;
	}
	
	public void arrivedAtDestinationAirport(int id) {
		arrivedPassengers[countArrived++] = id;
	}

}
