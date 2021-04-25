package DestinationAirport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import Main.GeneralRepositoryInformation;
import utils.BlockingQueue;

public class SRDestinationAirport implements 	IDestinationAirport_Hostess,
												IDestinationAirport_Passenger,
												IDestinationAirport_Pilot {
	
	private final GeneralRepositoryInformation airport;
	private final int totalPassengers;
	private final BlockingQueue<Integer> arrivedPassengers;
	private final BlockingQueue<Integer> plane;
	
	private final ReentrantLock DepAirportLock = new ReentrantLock(true);
	private final Condition planeEmpty = DepAirportLock.newCondition();
		
	public SRDestinationAirport(GeneralRepositoryInformation airport, BlockingQueue<Integer> plane, int totalPassengers) {
        this.airport = airport;
        this.totalPassengers = totalPassengers;
        this.arrivedPassengers= new BlockingQueue<>(totalPassengers);
        this.plane = plane;
    }
	
	//Pilot
	@Override
	public void flyToDeparturePoint() {
		DepAirportLock.lock();
		try {
			while(plane.size()>0)
				planeEmpty.await();
		} catch (InterruptedException e1) {
		}
		try {
			TimeUnit.SECONDS.sleep((long) Math.random() * 2 + 1);
		} catch (InterruptedException e) {
		}finally {
			DepAirportLock.unlock();
		}
	}
	
	//Passenger
	@Override
	public void leaveThePlane(int id) {
		DepAirportLock.lock();
		try {
			arrivedPassengers.put(plane.take());
		} catch (InterruptedException e) {
		};
		if(plane.size()==0)
			planeEmpty.signal();
		System.out.println("Passengers at destination with ["+arrivedPassengers.size()+"] passengers : "+arrivedPassengers.toString());
		DepAirportLock.unlock();
	}
	
	//Other
	public boolean morePassengers() {
		DepAirportLock.lock();
		if(arrivedPassengers.size()==totalPassengers) {
			DepAirportLock.unlock();
			return false;
		}	
		else {
			DepAirportLock.unlock();
			return true;
		}	
	}
}
