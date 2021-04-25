package DepartureAirport;

import java.util.Random;
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
    private final Condition boarding = DepAirportLock.newCondition();
    
    private final GeneralRepositoryInformation airport;
	private final BlockingQueue<Integer> queue;
	private final BlockingQueue<Integer> plane;
    private int nInPlane;
	private final int MIN;
	private final int MAX;
	private int totalLeft;
	private boolean rfb , rtf, doc, show, inq, board = false;
	
	public SRDepartureAirport(GeneralRepositoryInformation airport, BlockingQueue<Integer> plane, int totalPassengers, int minPassengers, int maxPassengers) {
        this.airport = airport;
        this.queue= new BlockingQueue<>(totalPassengers);
        this.nInPlane = 0;
        this.MAX = maxPassengers;
        this.MIN = minPassengers;
        this.totalLeft = totalPassengers;
        this.plane = plane;
    }
	
	//Pilot
	@Override
	public void informPlaneReadyForBoarding() {
		DepAirportLock.lock();
		totalLeft = totalLeft - nInPlane;
		nInPlane = 0;
		rfb=false;
		readyForBoarding.signal();
		rfb=true;
		DepAirportLock.unlock();
	}
	
	@Override
	public void waitForAllInBoard() {
		DepAirportLock.lock();
		try {
			while(!rtf)
				readyToFly.await();
		} catch (InterruptedException e) {
		}
		finally{
			DepAirportLock.unlock();
		}
	}
	
	//Hostess
	@Override
	public void waitForNextFlight() {
		DepAirportLock.lock();
		try {
			while(!rfb)
				readyForBoarding.await();
		} catch (InterruptedException e) {
		} 
		finally{
			DepAirportLock.unlock();
		}
	}
	
	@Override
	public void prepareForPassBoarding() {
		DepAirportLock.lock();
		try {
			while(!inq)
				inqueue.await();
		} catch (InterruptedException e) {
		} 
		finally {
			DepAirportLock.unlock();
		}
	}
	
	@Override
	public void checkDocuments() {
		DepAirportLock.lock();
		try {
			show=false;
			showDocuments.signal();
			show=true;
			while(!doc)
				documents.await();
		}
		catch (InterruptedException e) {
		} 
		finally {
			try {
				queue.take();
			} catch (InterruptedException e) {
			}
			DepAirportLock.unlock();
		}
	}
	
	@Override
	public boolean waitForNextPassenger() {
		DepAirportLock.lock();
		//System.out.println(queue.size()+" - "+nInPlane);
		if(nInPlane==MAX || (queue.size() == 0 && nInPlane>=MIN && nInPlane<=MAX) || totalLeft == 0 ) {
			DepAirportLock.unlock();
			return false;
		}
		board=false;
		boarding.signal();
		board=true;
		DepAirportLock.unlock();
		return true;
	}
	
	@Override
	public void informPlaneReadyToTakeOff() {
		DepAirportLock.lock();
		rtf=false;
		readyToFly.signal();
		rtf=true;
		rfb=false;
		DepAirportLock.unlock();
	}
	
	//Passenger
	@Override
	public void travelToAirport() {
		try {
			TimeUnit.SECONDS.sleep((long) (Math.random() * 3)); // Sleep 0 to 3 seconds
		} catch (InterruptedException e) {
		} 
	}
	
	@Override
	public void waitInQueue(int id) {
		DepAirportLock.lock();
		try {
			queue.put(id);
			inq=false;
			inqueue.signal();
			inq=true;
			while(!show)
				showDocuments.await();
		} catch (InterruptedException e) {
		} 
		finally {
			DepAirportLock.unlock();
		}
	}
	
	@Override
	public void showDocuments() {
		DepAirportLock.lock();
		try {
			doc=false;
			documents.signal();
			doc=true;
			while(!board)
				boarding.await();
		} catch (InterruptedException e) {
		} 
		finally {
			nInPlane++;
			DepAirportLock.unlock();
		}
	}
}
