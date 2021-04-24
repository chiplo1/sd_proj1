package DestinationAirport;

import java.util.concurrent.locks.ReentrantLock;

import Main.GeneralRepositoryInformation;

public class SRDestinationAirport implements 	IDestinationAirport_Hostess,
												IDestinationAirport_Passenger,
												IDestinationAirport_Pilot {
	
	private final GeneralRepositoryInformation airport;
	private final int totalPassengers;
	private int arrivedPassengers[];
	private int countArrived;
	
	private final ReentrantLock DepAirportLock = new ReentrantLock(true);
	
	public SRDestinationAirport(GeneralRepositoryInformation airport, int totalPassengers) {
        this.airport = airport;
        this.totalPassengers = totalPassengers;
        this.arrivedPassengers = new int[totalPassengers];
        countArrived = 0;
    }
	
	//Passenger
	public void leaveThePlane(int id) {
		DepAirportLock.lock();
		System.out.println("Passenger: leaveThePlane");
		arrivedPassengers[countArrived++] = id;
		System.out.printf("Passengers at destination: %d\n", countArrived);
		DepAirportLock.unlock();
	}
	
	//Other
	public boolean morePassengers() {
		DepAirportLock.lock();
		if(countArrived==totalPassengers) {
			DepAirportLock.unlock();
			return false;
		}	
		else {
			DepAirportLock.unlock();
			return true;
		}	
	}
}
