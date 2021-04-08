package Main;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CFifo {
	
	private Integer size;
	private Integer[] fifo;
	final Lock rl = new ReentrantLock();
	private final Condition isEmpty;
	private final Condition notFull;
	
	private Integer count = 0;
	private Integer idxWrite = 0;
	private Integer idxRead = 0;
	
	public CFifo(Integer size) {
		this.size = size;
		this.fifo = new Integer[size];
		this.isEmpty = rl.newCondition();
		this.notFull = rl.newCondition();
	}
	public Integer read() {
		Integer value = null;
		try {
			rl.lock();
			while(count==0)
				isEmpty.await();
			value = fifo[idxRead];
			idxRead = (idxRead++) % size;
			count--;
			
			notFull.signal();
			notFull.signalAll();
		} catch(Exception ex) {}
		finally {
			rl.unlock();
		}
		return value;
	}
	public void write(Integer value) {
		// TO DO
		try {
			rl.lock();
			while(count==0)
				isEmpty.await();
			value = fifo[idxRead];
			idxRead = (idxRead++) % size;
			count--;
			
			notFull.signal();
			notFull.signalAll();
		} catch(Exception ex) {}
		finally {
			rl.unlock();
		}
	}
}
