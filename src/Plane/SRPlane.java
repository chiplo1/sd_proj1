package Plane;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import Main.GeneralRepositoryInformation;
import utils.BlockingQueue;

public class SRPlane implements IPlane_Hostess,
								IPlane_Passenger,
								IPlane_Pilot {
	
	private final GeneralRepositoryInformation airport;
	
	private final ReentrantLock PlaneLock = new ReentrantLock(true);
	private final Condition arrived = PlaneLock.newCondition();
	
	private final BlockingQueue<Integer> plane;
	private boolean arr;
	
	public SRPlane(GeneralRepositoryInformation airport, BlockingQueue<Integer> plane) {
        this.airport = airport;
        this.plane = plane;
    }
	
	//Pilot
	@Override
	public void flyToDestinationPoint() {
		PlaneLock.lock();
		try {
			TimeUnit.SECONDS.sleep((long) Math.random() * 2 + 1);
		} catch (InterruptedException e) {
		}finally {
			PlaneLock.unlock();
		}
	}
	
	@Override
	public void announceArrival() {
		PlaneLock.lock();
		arr=false;
		arrived.signalAll();
		arr=true;
		PlaneLock.unlock();
	}
	
	@Override
	public void parkAtTransferGate() {
		PlaneLock.lock();
		PlaneLock.unlock();
	}

	//Passenger
	@Override
	public void boardThePlane(int passengerID) {
		PlaneLock.lock();
		try {
			plane.put(passengerID);
			System.out.println("Passengers in the plane: "+plane.toString());
		} catch (InterruptedException e) {
		}
		PlaneLock.unlock();
	}
	
	@Override
	public void waitForEndOfFlight() {
		PlaneLock.lock();
		try {
			while(!arr)
				arrived.await();
		} catch (InterruptedException e) {
		}
		PlaneLock.unlock();
	}
}
