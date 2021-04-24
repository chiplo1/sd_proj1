package Plane;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import Main.GeneralRepositoryInformation;

public class SRPlane implements IPlane_Hostess,
								IPlane_Passenger,
								IPlane_Pilot {
	
	private final GeneralRepositoryInformation airport;
	private int inPlane;
	
	private final ReentrantLock PlaneLock = new ReentrantLock(true);
	private final Condition arrived = PlaneLock.newCondition();
	
	public SRPlane(GeneralRepositoryInformation airport) {
        this.airport = airport;
        this.inPlane = 0;
    }
	
	//Pilot
	public void flyToDestinationPoint() {
		PlaneLock.lock();
		System.out.println("Pilot: flyToDestinationPoint");
		try {
			TimeUnit.SECONDS.sleep((long) Math.random());
		} catch (InterruptedException e) {
		}finally {
			PlaneLock.unlock();
		}
	}
	public void announceArrival() {
		PlaneLock.lock();
		System.out.println("Pilot: announceArrival");
		arrived.signalAll();
		PlaneLock.unlock();
	}
	public void flyToDeparturePoint() {
		PlaneLock.lock();
		System.out.println("Pilot: flyToDeparturePoint");
		try {
			TimeUnit.SECONDS.sleep((long) Math.random());
		} catch (InterruptedException e) {
		}finally {
			PlaneLock.unlock();
		}
	}
	public void parkAtTransferGate() {
		PlaneLock.lock();
		System.out.println("Pilot: parkAtTransferGate");
		inPlane = 0;
		PlaneLock.unlock();
	}

	//Passenger
	public void boardThePlane(int passengerID) {
		PlaneLock.lock();
		System.out.println("Passenger: boardThePlane");
		inPlane++;
		PlaneLock.unlock();
	}
	public void waitForEndOfFlight() {
		PlaneLock.lock();
		System.out.println("Passenger: waitForEndOfFlight");
		try {
			arrived.await();
		} catch (InterruptedException e) {
		}
		PlaneLock.unlock();
	}
}
