package DepartureAirport;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import Main.GeneralRepositoryInformation;

public class SRDepartureAirport implements 	IDepartureAirport_Hostess,
											IDepartureAirport_Passenger,
											IDepartureAirport_Pilot{
	
	private final ReentrantLock DepAirportLock = new ReentrantLock(true);
    private final Condition planeReady = DepAirportLock.newCondition();
    private final Condition documents = DepAirportLock.newCondition();
    
    private final GeneralRepositoryInformation airport;
    private final LinkedList<Integer> queue = new LinkedList<>();
    private final int nInPlane;
	
	public SRDepartureAirport(GeneralRepositoryInformation airport) {
        this.airport = airport;
        this.queue.clear();
        this.nInPlane = 0;
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
		DepAirportLock.lock();
		System.out.println("checkDocuments");
		try {
			documents.await();
		} catch (InterruptedException e) {
		}
		DepAirportLock.unlock();
	}
	
	public boolean waitForNextPassenger() {
		DepAirportLock.lock();
		System.out.println("waitForNextPassenger");
		if(queue.size() == 0) {
			DepAirportLock.unlock();
			return false;
		}
		DepAirportLock.unlock();
		return true;
	}
	
	public void informPlaneReadyToTakeOff() {
		DepAirportLock.lock();
		System.out.println("informPlaneReadyToTakeOff");
		DepAirportLock.unlock();
	}
	
	public void waitForNextFlight() {
		DepAirportLock.lock();
		System.out.println("waitForNextFlight");
		DepAirportLock.unlock();
	}
	//Passenger
	public void travelToAirport(int id) {
		DepAirportLock.lock();
		System.out.println("travelToAirport");
		try {
			TimeUnit.SECONDS.sleep((long) Math.random());
		} catch (InterruptedException e) {
			queue.add(id);
		}
		DepAirportLock.unlock();
	}
	public void waitInQueue() {
		System.out.println("waitInQueue");
	}
	public void showDocuments() {
		System.out.println("showDocuments");
		documents.signal();
	}
	public void boardThePlane() {
		System.out.println("boardThePlane");
	}
}
