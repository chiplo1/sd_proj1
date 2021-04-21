package DepartureAirport;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import ActiveEntity.AEPassenger;
import Main.GeneralRepositoryInformation;

public class SRDepartureAirport implements 	IDepartureAirport_Hostess,
											IDepartureAirport_Passenger,
											IDepartureAirport_Pilot{
	
	private final ReentrantLock DepAirportLock = new ReentrantLock(true);
    private final Condition planeReady = DepAirportLock.newCondition();
    private final Condition documents = DepAirportLock.newCondition();
    private final GeneralRepositoryInformation airport;
	
	public SRDepartureAirport(GeneralRepositoryInformation airport) {
        this.airport = airport;
    }
	
	//Pilot
	public void informPlaneReadyForBoarding() {
		DepAirportLock.lock();
		System.out.println("informPlaneReadyForBoarding");
		planeReady.signal();
		DepAirportLock.unlock();
	}
	//Hostess
	public void prepareForPassBoarding() {
		DepAirportLock.lock();
		System.out.println("prepareForPassBoarding");
		try {
			planeReady.await();
		} catch (InterruptedException e) {
		}
		DepAirportLock.unlock();
	}
	public void checkDocuments() {
		System.out.println("checkDocuments");
		try {
			documents.await();
		} catch (InterruptedException e) {
		}
	}
	public boolean waitForNextPassenger() {
		System.out.println("waitForNextPassenger");
		return false;
	}
	public void waitForNextFlight() {
		System.out.println("waitForNextFlight");
	}
	//Passenger
	public void travelToAirport() {
		System.out.println("travelToAirport");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		}
	}
	public void waitInQueue() {
		System.out.println("waitInQueue");
	}
	public void showDocuments() {
		System.out.println("showDocuments");
		documents.signal();
	}
}
