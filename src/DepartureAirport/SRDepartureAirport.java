package DepartureAirport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import Main.GeneralRepositoryInformation;
import utils.BlockingQueue;

public class SRDepartureAirport implements 	IDepartureAirport_Hostess,
											IDepartureAirport_Passenger,
											IDepartureAirport_Pilot{
	
	private final ReentrantLock DepAirportLock = new ReentrantLock(true);
    private final Condition readyForBoarding = DepAirportLock.newCondition();
    private final Condition readyToFly = DepAirportLock.newCondition();
    private final Condition documents = DepAirportLock.newCondition();
    private final Condition showDocuments = DepAirportLock.newCondition();
    private final Condition inqueue = DepAirportLock.newCondition();
    
    private final GeneralRepositoryInformation airport;
	private final BlockingQueue<Integer> queue;
    private int nInPlane;
	private final int MIN;
	private final int MAX;
	private int totalLeft;
	
	public SRDepartureAirport(GeneralRepositoryInformation airport, int totalPassengers, int minPassengers, int maxPassengers) {
        this.airport = airport;
        this.queue= new BlockingQueue<>(totalPassengers);
        this.nInPlane = 0;
        this.MAX = maxPassengers;
        this.MIN = minPassengers;
        this.totalLeft = totalPassengers;
    }
	
	//Pilot
	public void informPlaneReadyForBoarding() {
		DepAirportLock.lock();
		System.out.println("Pilot: informPlaneReadyForBoarding");
		totalLeft = totalLeft - nInPlane;
		nInPlane = 0;
		readyForBoarding.signal();
		DepAirportLock.unlock();
	}
	public void waitForAllInBoard() {
		DepAirportLock.lock();
		System.out.println("Pilot: waitForAllInBoard");
		try {
			readyToFly.await();
		} catch (InterruptedException e) {
		}
		finally{
			DepAirportLock.unlock();
		}
	}
	
	//Hostess
	public void waitForNextFlight() {
		DepAirportLock.lock();
		System.out.println("Hostess: waitForNextFlight");
		try {
			readyForBoarding.await();
		} catch (InterruptedException e) {
		} 
		finally{
			DepAirportLock.unlock();
		}
	}
	
	public void prepareForPassBoarding() {
		DepAirportLock.lock();
		System.out.println("Hostess: prepareForPassBoarding");
		try {
			inqueue.await();
		} catch (InterruptedException e) {
		} 
		finally {
			DepAirportLock.unlock();
		}
	}
	
	public void checkDocuments() {
		DepAirportLock.lock();
		System.out.println("Hostess: checkDocuments");
		try {
			showDocuments.signal();
			documents.await();
		}
		catch (InterruptedException e) {
		} 
		finally {
			nInPlane++;
			DepAirportLock.unlock();
		}
	}
	
	public boolean waitForNextPassenger() {
		DepAirportLock.lock();
		System.out.println("Hostess: waitForNextPassenger");
		if((queue.size() == 0 && nInPlane>=MIN && nInPlane<=MAX) || totalLeft == 0 ) {
			DepAirportLock.unlock();
			return false;
		}
		DepAirportLock.unlock();
		return true;
	}
	
	public void informPlaneReadyToTakeOff() {
		DepAirportLock.lock();
		System.out.println("Hostess: informPlaneReadyToTakeOff");
		readyToFly.signal();
		DepAirportLock.unlock();
	}
	
	//Passenger
	public void travelToAirport() {
		DepAirportLock.lock();
		System.out.println("Passenger: travelToAirport");
		try {
			TimeUnit.SECONDS.sleep((long) Math.random());
		} catch (InterruptedException e) {
		} 
		finally {
			DepAirportLock.unlock();
		}
	}
	public void waitInQueue(int id) {
		DepAirportLock.lock();
		System.out.println("Passenger: waitInQueue");
		try {
			queue.put(id);
			inqueue.signal();
		} catch (InterruptedException e) {
		} 
		finally {
			DepAirportLock.unlock();
		}
	}
	public void showDocuments() {
		DepAirportLock.lock();
		System.out.println("Passenger: showDocuments");
		try {
			showDocuments.await();
			documents.signal();
		} catch (InterruptedException e) {
		} 
		finally {
			DepAirportLock.unlock();
		}
	}
}
